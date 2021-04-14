package fr.eni.TrocEnchere.bll;

import fr.eni.TrocEnchere.BusinessException;
import fr.eni.TrocEnchere.bo.Categorie;
import fr.eni.TrocEnchere.dal.DAOFactory;
import fr.eni.TrocEnchere.dal.categorie.CategorieDAO;

import java.util.ArrayList;

public class CategorieManager {
    private CategorieDAO categorieDAO;

    public CategorieManager() {
        categorieDAO = DAOFactory.getCategorieDAO();
    }

    public ArrayList<Categorie> getAll() throws BusinessException {
        return (ArrayList<Categorie>) categorieDAO.getAll();
    }

    public Categorie getById(int id) throws BusinessException {
        return categorieDAO.getById(id);
    }

    public int getByLibelle(String libelle) throws BusinessException {
        return categorieDAO.getByLibelle(libelle);
    }
}
