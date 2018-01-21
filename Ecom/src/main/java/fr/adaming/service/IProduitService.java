package fr.adaming.service;

import java.util.List;

import fr.adaming.model.Admin;
import fr.adaming.model.Categorie;
import fr.adaming.model.Produit;

public interface IProduitService {
	
	public List<Produit> getAllProduit();

	public Produit addProduit(Produit p);

	public Produit updateProduit(Produit p);

	public int deleteProduit(int id);
	
	public int deleteProduitByName(String name);

	public Produit getProduitbyId(int id);
	
	public Produit getProduitbyName(String designation);
	
	public List<Produit> getAllProduitsByCategorie(Categorie c);

}
