package fr.eni.TrocEnchere.dal;

import fr.eni.TrocEnchere.dal.articleVendu.ArticleVenduDAO;
import fr.eni.TrocEnchere.dal.articleVendu.ArticleVenduDAOJdbcImpl;
import fr.eni.TrocEnchere.dal.categorie.CategorieDAO;
import fr.eni.TrocEnchere.dal.categorie.CategorieDAOJdbcImpl;
import fr.eni.TrocEnchere.dal.util.UtilisateurDAO;
import fr.eni.TrocEnchere.dal.util.UtilisateurDAOJdbcImpl;

public class DAOFactory {
	
	public static UtilisateurDAO getUtilisateurDAO() {
		return new UtilisateurDAOJdbcImpl();
	}
	public static ArticleVenduDAO getArticleVenduDAO() {
		return new ArticleVenduDAOJdbcImpl();
	}
	public static CategorieDAO getCategorieDAO() {
		return new CategorieDAOJdbcImpl();
	}
	public static RetraitDAO getRetraitDAO() {
		return new RetraitDAOJdbcImpl();
	}
}
