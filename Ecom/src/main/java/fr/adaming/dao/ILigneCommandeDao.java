package fr.adaming.dao;

import java.util.List;

import fr.adaming.model.LigneCommande;

public interface ILigneCommandeDao {

	public LigneCommande addLigneCommande( LigneCommande lc);
	
	public LigneCommande updateLigneCommande(LigneCommande lc);
	
	public int deleteLigneCommande(Long id);
	
	public LigneCommande getLigneCommandeById(Long id);
	
	public List<LigneCommande> getAllLigneCommande();
	
}
