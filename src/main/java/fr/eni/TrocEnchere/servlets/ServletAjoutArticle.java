package fr.eni.TrocEnchere.servlets;

import fr.eni.TrocEnchere.BusinessException;
import fr.eni.TrocEnchere.bll.ArticleVenduManager;
import fr.eni.TrocEnchere.bo.ArticleVendu;
import fr.eni.TrocEnchere.bo.Categorie;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;


/**
 * Servlet implementation class ServletAjoutArticle
 */
@WebServlet("/ajoutArticle")
public class ServletAjoutArticle extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/UtilisateurConnexion.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String nomArticle = req.getParameter("nomArticle");
        String description = req.getParameter("description");
        Date dateDebutEncheres = Date.valueOf(req.getParameter("dateDebutEncheres"));
        Date dateFinEncheres = Date.valueOf(req.getParameter("dateFinEncheres"));
        int prixInitial = Integer.parseInt(req.getParameter("prixInitial"));
        int prixVente = Integer.parseInt(req.getParameter("prixVente"));
        Utilisateur proprietaire = DAOFactory.getUtilisateurDAO().getById(Integer.parseInt(req.getParameter("proprietaireId")));
        Categorie categorie = DAOFactory.getCategorieDAO().getById(Integer.parseInt(req.getParameter("categorieId")));

        ArticleVenduManager articleVenduManager = new ArticleVenduManager();
        ArticleVendu articleVendu = new ArticleVendu(nomArticle,description,dateDebutEncheres,dateFinEncheres,prixInitial,prixVente,proprietaire,categorie);

        try {
            articleVenduManager.ajouter(articleVendu);
        } catch (BusinessException e) {
            e.printStackTrace();
        }

        RequestDispatcher rd = null;
        rd = req.getRequestDispatcher("./index");
    }
}
