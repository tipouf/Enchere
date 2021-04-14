package fr.eni.TrocEnchere.servlets;

import fr.eni.TrocEnchere.BusinessException;
import fr.eni.TrocEnchere.bll.ArticleVenduManager;
import fr.eni.TrocEnchere.bll.RetraitManager;
import fr.eni.TrocEnchere.bo.ArticleVendu;
import fr.eni.TrocEnchere.bo.Categorie;
import fr.eni.TrocEnchere.bo.Retrait;
import fr.eni.TrocEnchere.bo.Utilisateur;
import fr.eni.TrocEnchere.dal.DAOFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;


/**
 * Servlet implementation class ServletAjoutArticle
 */
@WebServlet("/ajoutArticle")
public class ServletAjoutArticle extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("user_id") != null) {

            Utilisateur utilisateur = DAOFactory.getUtilisateurDAO().getById((int) req.getSession().getAttribute("user_id"));
            ArrayList<Categorie> listeCategories = (ArrayList<Categorie>) DAOFactory.getCategorieDAO().getAll();

            RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/base.jsp");
            req.setAttribute("pageAAfficher", "/WEB-INF/ArticleVenduCreation.jsp");
            req.setAttribute("utilisateur", utilisateur);
            req.setAttribute("listeCategories", listeCategories);
            rd.forward(req, resp);

        } else {
            resp.sendRedirect(req.getContextPath() + "/connexion");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Récupération des paramètres de formulaire pour l'article
        String nomArticle = req.getParameter("nomArticle");
        String description = req.getParameter("description");

        Date dateDebutEncheres;
        if (!req.getParameter("dateDebutEncheres").equals("")){
            dateDebutEncheres = Date.valueOf(req.getParameter("dateDebutEncheres"));
        }else{
            dateDebutEncheres = null;
        }

        Date dateFinEncheres;
        if (!req.getParameter("dateFinEncheres").equals("")){
            dateFinEncheres = Date.valueOf(req.getParameter("dateFinEncheres"));
        }else{
            dateFinEncheres = null;
        }

        int prixInitial;
        if (!req.getParameter("prixInitial").equals("")){
            prixInitial = Integer.parseInt(req.getParameter("prixInitial"));
        }else {
            prixInitial = 0;
        }

        Utilisateur proprietaire = DAOFactory.getUtilisateurDAO().getById((int) req.getSession().getAttribute("user_id"));
        Categorie categorie = DAOFactory.getCategorieDAO().getById(Integer.parseInt(req.getParameter("categorieId")));

        // Récupération des paramètres de formulaire pour le retrait
        String rue = req.getParameter("rue");
        String codePostal = req.getParameter("codePostal");
        String ville = req.getParameter("ville");

        // Instanciation du nouvel article
        ArticleVenduManager articleVenduManager = new ArticleVenduManager();
        ArticleVendu articleVendu = new ArticleVendu(nomArticle, description, dateDebutEncheres, dateFinEncheres, prixInitial, proprietaire, categorie);

        // Instanciation du nouveau retrait
        RetraitManager retraitManager = new RetraitManager();
        Retrait retrait = new Retrait(rue,codePostal,ville);

        RequestDispatcher rd = null;

        // Persistance des entités
        try {
            ArticleVendu articleVenduMAJ = articleVenduManager.ajouter(articleVendu);
            retrait.setNoArticle(articleVenduMAJ.getNoArticle());
        } catch (BusinessException e) {
            rd = req.getRequestDispatcher("/WEB-INF/base.jsp");
            req.setAttribute("error", e.getMessage());
            req.setAttribute("pageAAfficher", "/WEB-INF/ArticleVenduCreation.jsp");
            rd.forward(req, resp);
        }
    //        try {
    //            retraitManager.ajouter(retrait);
    //        } catch (BusinessException e) {
    //            e.printStackTrace();
    //        }

        resp.sendRedirect(req.getContextPath() + "/index");
    }
}
