package fr.eni.TrocEnchere.servlets;

import fr.eni.TrocEnchere.BusinessException;
import fr.eni.TrocEnchere.bll.ArticleVenduManager;
import fr.eni.TrocEnchere.bll.CategorieManager;
import fr.eni.TrocEnchere.bll.UtilisateurManager;
import fr.eni.TrocEnchere.bo.ArticleVendu;
import fr.eni.TrocEnchere.bo.Categorie;
import fr.eni.TrocEnchere.dal.DAOFactory;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/index")
public class ServletIndex extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			ArrayList<Categorie> listeCategories = new CategorieManager().getAll();
			ArrayList<ArticleVendu> listeArticles = new ArticleVenduManager().getAll();

			request.setAttribute("listeCategories", listeCategories);
			request.setAttribute("listeArticles", listeArticles);

		} catch (BusinessException e) {
			System.err.println(e.getMessage());
		}

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/index.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
