package fr.eni.TrocEnchere.dal.categorie;

import fr.eni.TrocEnchere.BusinessException;
import fr.eni.TrocEnchere.bo.Categorie;

import java.util.List;

public interface CategorieDAO {
    public Categorie getById(int id);
    public List<Categorie> getAll();
}
