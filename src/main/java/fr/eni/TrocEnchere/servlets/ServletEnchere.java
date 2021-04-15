package fr.eni.TrocEnchere.servlets;

import fr.eni.TrocEnchere.BusinessException;
import fr.eni.TrocEnchere.bll.ArticleVenduManager;
import fr.eni.TrocEnchere.bll.EnchereManager;
import fr.eni.TrocEnchere.bll.RetraitManager;
import fr.eni.TrocEnchere.bll.UtilisateurManager;
import fr.eni.TrocEnchere.bo.ArticleVendu;
import fr.eni.TrocEnchere.bo.Enchere;
import fr.eni.TrocEnchere.bo.Retrait;
import fr.eni.TrocEnchere.bo.Utilisateur;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Date;

@WebServlet("/enchere/*")
public class ServletEnchere extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // L'utilisateur est-il connecté ?
        if (request.getSession().getAttribute("user_id") == null) {
            request.setAttribute("error", "Vous devez être connecté pour accéder à cette partie du site.");
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/UtilisateurConnexion.jsp");
            rd.forward(request, response);
        }

        // L'URL est-elle au format /enchere/articleId
        if (request.getPathInfo() == null) {
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/index.jsp");
            rd.forward(request, response);
        }

        int articleId = Integer.parseInt(request.getPathInfo().replace("/", ""));

        ArticleVenduManager articleManager = new ArticleVenduManager();
        RetraitManager retraitManager = new RetraitManager();
        EnchereManager enchereManager = new EnchereManager();

        try {
            ArticleVendu article = articleManager.getById(articleId);
            Retrait retrait = retraitManager.getById(articleId);
            Enchere enchere = enchereManager.getLatestForArticle(articleId);

            request.setAttribute("article", article);
            request.setAttribute("retrait", retrait);
            request.setAttribute("enchere", enchere);

        } catch (BusinessException e) {
            System.err.println(e.getMessage());
        }
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/base.jsp");
        request.setAttribute("pageAAfficher", "/WEB-INF/EncheresDetail.jsp");

        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String error = null;

        // L'utilisateur est-il connecté ?
        if (request.getSession().getAttribute("user_id") == null) {
            request.setAttribute("error", "Vous devez être connecté pour pouvoir enchérir.");
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/base.jsp");
            request.setAttribute("pageAAfficher", "/WEB-INF/UtilisateurConnexion.jsp");

            rd.forward(request, response);
        }

        // L'URL est-elle au format /enchere/articleId
        if (request.getPathInfo() == null) {
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/base.jsp");
            request.setAttribute("pageAAfficher", "/WEB-INF/index.jsp");

            rd.forward(request, response);
        }

        int articleId = Integer.parseInt(request.getPathInfo().replace("/", ""));
        int userId = (int) request.getSession().getAttribute("user_id");
        int montantEnchere = Integer.parseInt(request.getParameter("montant"));

        UtilisateurManager utilisateurManager = new UtilisateurManager();
        ArticleVenduManager articleManager = new ArticleVenduManager();
        EnchereManager enchereManager = new EnchereManager();

        try {
            Utilisateur utilisateur = utilisateurManager.getById(userId);
            ArticleVendu article = articleManager.getById(articleId);
            Enchere latestEnchere = enchereManager.getLatestForArticle(articleId);

            // L'utilisateur a-t-il suffisement de crédits pour poser cette enchère ?
            if (utilisateur.getCredit() < montantEnchere) {
                error = "Vous ne disposez pas de suffisement de crédits pour poser cette enchère.";

            } else if (latestEnchere != null && latestEnchere.getMontantEnchere() > montantEnchere) {
                error = "Vous devez proposer un prix supérieur à l'enchère précédente.";

            // Empêche l'utilisateur d'énchérir sur sa propre enchère, pour éviter le problème du select asynchrone avec l'update
            // Expl : Si l'utilisateur est identique à l'ancien enchérisseur, recréditer les crédits (plus bas) va récupérer
            //        l'utilisateur avant que l'update de ses crédits ne soit terminé, et donc annuler cette opération
            } else if (latestEnchere != null && utilisateur.getNoUtilisateur() == latestEnchere.getUtilisateur().getNoUtilisateur()) {
                error = "Vous ne pouvez pas enchérir sur votre propre enchère";

            } else {

                // Création ou update de l'enchère
                Enchere enchere = new Enchere(
                        new java.sql.Date(new Date().getTime()),
                        montantEnchere,
                        utilisateur,
                        article
                );
                new EnchereManager().upsert(enchere);

                // Modification du prix de vente de l'article
                articleManager.modifierPrixVente(article, montantEnchere);

                // Déduction des crédits de l'utilisateur
                utilisateur.setCredit(utilisateur.getCredit() - montantEnchere);
                utilisateurManager.update(utilisateur);

                // Recréditer les crédits au précédent enchérisseur, s'il existe
                if (latestEnchere != null) {
                    Utilisateur latestEncherisseur = latestEnchere.getUtilisateur();
                    latestEncherisseur.setCredit(latestEncherisseur.getCredit() + latestEnchere.getMontantEnchere());
                    utilisateurManager.update(latestEncherisseur);
                }
            }
        } catch (BusinessException e) {
            System.err.println(e.getMessage());
        }

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/base.jsp");

        if (error != null) {
            request.setAttribute("error", error);
            request.setAttribute("pageAAfficher", "/WEB-INF/EncheresDetail.jsp");
            doGet(request, response);

        } else {
            request.setAttribute("pageAAfficher", "/WEB-INF/index.jsp");
            response.sendRedirect(request.getContextPath() + "/index");
        }
    }
}