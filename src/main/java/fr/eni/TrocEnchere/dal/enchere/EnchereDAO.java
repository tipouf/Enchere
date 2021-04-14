package fr.eni.TrocEnchere.dal.enchere;

import fr.eni.TrocEnchere.BusinessException;
import fr.eni.TrocEnchere.bo.ArticleVendu;
import fr.eni.TrocEnchere.bo.Enchere;

import java.util.ArrayList;

public interface EnchereDAO {
    public ArrayList<Enchere> getAll();
    public Enchere getById(int noUtilisateur, int noArticle);

    public void upsert(Enchere enchere) throws BusinessException;

    public Enchere getLatestForArticle(int noArticle) throws BusinessException;
}
