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
		if (!retrait.getRue().equals("")
				&& !retrait.getCodePostal().equals("")
				&& !retrait.getVille().equals("")) {

			retraitDAO.insert(retrait);
		} else {
			String error = "";
			if (retrait.getRue().equals("")) {
				error += "La rue ne peut être nulle. \n";
			}
			if (retrait.getCodePostal().equals("")) {
				error += "Le code postal ne peut être nul. \n";
			}
			if (retrait.getVille().equals("")) {
				error += "La ville ne peut être nulle. \n";
			}

			throw new BusinessException(error);
		}
	}

	public void modifier(Retrait retrait) throws BusinessException {

		retraitDAO.update(retrait);
	}

	public void effacer(Retrait retrait) throws BusinessException {

		retraitDAO.delete(retrait);;
	}

	public Retrait getById(int noArticle) throws BusinessException {

		return retraitDAO.getById(noArticle);
	}
}
