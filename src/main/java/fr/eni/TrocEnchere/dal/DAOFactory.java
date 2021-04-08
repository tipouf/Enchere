package fr.eni.TrocEnchere.dal;

import fr.eni.TrocEnchere.dal.util.UtilisateurDAO;
import fr.eni.TrocEnchere.dal.util.UtilisateurDAOJdbcImpl;

public class DAOFactory {
	
	public static UtilisateurDAO getUtilisateurDAO() {
		return new UtilisateurDAOJdbcImpl();
	}
}
