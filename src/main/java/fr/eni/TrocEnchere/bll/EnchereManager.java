package fr.eni.TrocEnchere.bll;

import fr.eni.TrocEnchere.BusinessException;
import fr.eni.TrocEnchere.dal.DAOFactory;
import fr.eni.TrocEnchere.dal.enchere.EnchereDAO;
import fr.eni.TrocEnchere.bo.Enchere;

import java.util.ArrayList;

public class EnchereManager {

    private EnchereDAO enchereDAO;

    public EnchereManager() {
        enchereDAO = DAOFactory.getEnchereDAO();
    }

    public ArrayList<Enchere> getAll() throws BusinessException {
        return enchereDAO.getAll();
    }

    public void upsert(Enchere enchere) throws BusinessException {
        enchereDAO.upsert(enchere);
    }

    public Enchere getLatestForArticle(int noArticle) throws BusinessException {
        return enchereDAO.getLatestForArticle(noArticle);
    }
}
