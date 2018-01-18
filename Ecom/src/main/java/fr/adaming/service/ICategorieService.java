package fr.adaming.service;

import java.util.List;

import fr.adaming.model.Categorie;

public interface ICategorieService {

	public Categorie addCategorie(Categorie c);

	public Categorie updateCategorie(Categorie c);

	public int deleteCategorie(Long id);

	public Categorie getCategorieById(Long id);
	
	public Categorie getCategorieByName(String name);

	public List<Categorie> getAllCategorie();
	
}
