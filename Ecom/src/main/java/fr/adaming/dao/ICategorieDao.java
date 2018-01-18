package fr.adaming.dao;

import java.util.List;

import fr.adaming.model.Categorie;

public interface ICategorieDao {
	
	public Categorie addCategorie( Categorie c);
	
	public Categorie updateCategorie(Categorie c);
	
	public int deleteCategorie(Long id);
	
	public Categorie getCategorieById(Long id);
	
	public Categorie getCategorieByName(String name);
	
	public List<Categorie> getAllCategorie();
	
}
