package fr.eni.TrocEnchere.dal.util;

import java.util.ArrayList;

import fr.eni.TrocEnchere.BusinessException;
import fr.eni.TrocEnchere.bo.Utilisateur;

public interface UtilisateurDAO {
	
	public void insert(Utilisateur utilisateur) throws BusinessException;
	
	public ArrayList<Utilisateur> getAll();

	public void update(Utilisateur utilisateur);

	public Utilisateur getByEmail(String email);
	
	public Utilisateur getById(int id);
	
	public Utilisateur getByEmailOrPseudo(String emailOrPseudo);
	
	public Utilisateur getByPseudo(String email);

}
