package fr.eni.TrocEnchere.bll;

import fr.eni.TrocEnchere.BusinessException;
import fr.eni.TrocEnchere.bo.ArticleVendu;
import fr.eni.TrocEnchere.dal.DAOFactory;
import fr.eni.TrocEnchere.dal.articleVendu.ArticleVenduDAO;

import java.util.List;

public class ArticleVenduManager {
    ArticleVenduDAO articleVenduDAO = DAOFactory.getArticleVenduDAO();

    public ArticleVendu ajouter(ArticleVendu articleVendu) throws BusinessException {
        if (!articleVendu.getNomArticle().equals("")
                && !articleVendu.getDescription().equals("")
                &&  articleVendu.getDateDebutEncheres() != null
                &&  articleVendu.getDateFinEncheres() != null
                &&  articleVendu.getCategorie() != null
                &&  articleVendu.getProprietaire() != null){

            throw new BusinessException();
        }else{
            articleVenduDAO.insert(articleVendu);
        }

        return articleVendu;
    }

    public ArticleVendu modifierPrixVente(ArticleVendu articleVendu, int prixVente) throws BusinessException {
        if (prixVente > articleVendu.getPrixVente()){
            articleVendu.setPrixVente(prixVente);
            articleVenduDAO.update(articleVendu);
        }else{
            throw new BusinessException();
        }
        return articleVendu;
    }

    public List<ArticleVendu> afficherArticles() throws BusinessException {

        return articleVenduDAO.getAll();
    }
}
