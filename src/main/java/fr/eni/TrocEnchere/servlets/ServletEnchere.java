package fr.eni.TrocEnchere.servlets;

import fr.eni.TrocEnchere.BusinessException;
import fr.eni.TrocEnchere.bll.ArticleVenduManager;
import fr.eni.TrocEnchere.bll.RetraitManager;
import fr.eni.TrocEnchere.bo.ArticleVendu;
import fr.eni.TrocEnchere.bo.Retrait;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/enchere/*")
public class ServletEnchere extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // L'utilisateur est-il connecté ?
        if (request.getSession().getAttribute("user_id") == null) {
            request.setAttribute("error", "Vous devez être connecté pour accéder à cette partie du site.");
            RequestDispatcher rd = request.getRequestDispatcher("./connexion");
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

        try {
            ArticleVendu article = articleManager.getById(articleId);
            Retrait retrait = retraitManager.getById(articleId);

            request.setAttribute("article", article);
            request.setAttribute("retrait", retrait);

        } catch (BusinessException e) {
            System.err.println(e.getMessage());
        }
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/enchere.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
