package fr.eni.TrocEnchere.dal;

import fr.eni.TrocEnchere.BusinessException;
import fr.eni.TrocEnchere.bo.ArticleVendu;

public interface ArticleVenduDAO {
    public void selectById(ArticleVendu articleVendu) throws BusinessException;
    public void selectAll(ArticleVendu articleVendu) throws BusinessException;
    public void insert(ArticleVendu articleVendu) throws BusinessException;
    public void update(ArticleVendu articleVendu) throws BusinessException;
    public void delete(ArticleVendu articleVendu) throws BusinessException;
}
