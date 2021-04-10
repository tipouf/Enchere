package fr.eni.TrocEnchere.bll;

import fr.eni.TrocEnchere.BusinessException;
import fr.eni.TrocEnchere.bo.Retrait;
import fr.eni.TrocEnchere.dal.DAOFactory;
import fr.eni.TrocEnchere.dal.retrait.RetraitDAO;

public class RetraitManager {

	private RetraitDAO retraitDAO;

	public RetraitManager() {
		retraitDAO = DAOFactory.getRetraitDAO();
	}

	public void ajouter(Retrait retrait) throws BusinessException {

		retraitDAO.insert(retrait);
	}

	public void modifier(Retrait retrait) throws BusinessException {

		retraitDAO.update(retrait);
	}

	public void effacer(Retrait retrait) throws BusinessException {

		retraitDAO.delete(retrait);;
	}

	public Retrait SelectParId(int noArticle) throws BusinessException {

		return retraitDAO.getById(noArticle);
	}
}
