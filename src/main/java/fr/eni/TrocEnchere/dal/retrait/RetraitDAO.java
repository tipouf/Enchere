package fr.eni.TrocEnchere.dal.retrait;

import fr.eni.TrocEnchere.BusinessException;
import fr.eni.TrocEnchere.bo.Retrait;

public interface RetraitDAO {
    public void insert(Retrait retrait) throws BusinessException;
    public Retrait getById(int id) throws BusinessException;
    public void update(Retrait retrait) throws BusinessException;
    public void delete(Retrait retrait) throws BusinessException;
}
