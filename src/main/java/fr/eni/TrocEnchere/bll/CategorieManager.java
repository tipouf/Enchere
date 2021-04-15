package fr.eni.TrocEnchere.bll;

import fr.eni.TrocEnchere.bo.Categorie;
import fr.eni.TrocEnchere.dal.DAOFactory;
import fr.eni.TrocEnchere.dal.categorie.CategorieDAO;

import java.util.ArrayList;

public class CategorieManager {
    private CategorieDAO categorieDAO;

    public CategorieManager() {
        categorieDAO = DAOFactory.getCategorieDAO();
    }

    public ArrayList<Categorie> getAll() {
        return (ArrayList<Categorie>) categorieDAO.getAll();
    }

    public Categorie getById(int id){
        return categorieDAO.getById(id);
    }
}
