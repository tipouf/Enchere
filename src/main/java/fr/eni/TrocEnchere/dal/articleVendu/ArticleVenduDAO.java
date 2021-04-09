package fr.eni.TrocEnchere.dal.articleVendu;

import fr.eni.TrocEnchere.BusinessException;
import fr.eni.TrocEnchere.bo.ArticleVendu;

import java.util.List;

public interface ArticleVenduDAO {
    public ArticleVendu getById(int id) throws BusinessException;
    public List<ArticleVendu> getAll() throws BusinessException;
    public void insert(ArticleVendu articleVendu) throws BusinessException;
    public void update(ArticleVendu articleVendu) throws BusinessException;
    public void delete(ArticleVendu articleVendu) throws BusinessException;
}