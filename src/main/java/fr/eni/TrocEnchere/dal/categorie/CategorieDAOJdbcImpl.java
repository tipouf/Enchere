package fr.eni.TrocEnchere.dal.categorie;

import fr.eni.TrocEnchere.BusinessException;
import fr.eni.TrocEnchere.bo.ArticleVendu;
import fr.eni.TrocEnchere.bo.Categorie;
import fr.eni.TrocEnchere.dal.ConnectionProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategorieDAOJdbcImpl implements CategorieDAO {

    private static final String GET_ALL = "SELECT * FROM CATEGORIES";

    private static final String GET_BY_ID = "SELECT * FROM CATEGORIES WHERE no_categorie =?";

    @Override
    public Categorie getById(int id){
        List<Categorie> listes = new ArrayList<>();

        try(Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement pStmt = cnx.prepareStatement(GET_BY_ID);
            pStmt.setInt(1, id);

            ResultSet rs = pStmt.executeQuery();

            while(rs.next()) {
                Categorie nouvelleCategorie = new Categorie(rs.getInt("no_categorie"),
                        rs.getString("libelle"));
                listes.add(nouvelleCategorie);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listes.get(0);
    }

    @Override
    public List<Categorie> getAll(){
        List<Categorie> listes = new ArrayList<>();

        try(Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement pStmt = cnx.prepareStatement(GET_ALL);

            ResultSet rs = pStmt.executeQuery();

            while(rs.next()) {
                Categorie nouvelleCategorie = new Categorie(rs.getInt("no_categorie"),
                        rs.getString("libelle"));
                listes.add(nouvelleCategorie);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listes;
    }

}
