package fr.adaming.model;

import java.util.List;

public class Panier {

	// attribut du modele
	private double total;
	
	// Association avec ligne de commande
	private List<LigneCommande> listeLigneCommande;
	
	
	// constructeur
	public Panier() {
		this.total = 0.0;
	}
	
	

	public Panier(double total, List<LigneCommande> listeLigneCommande) {
		super();
		this.total = total;
		this.listeLigneCommande = listeLigneCommande;
	}



	// Getters et setters
	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public List<LigneCommande> getListeLigneCommande() {
		return listeLigneCommande;
	}

	public void setListeLigneCommande(List<LigneCommande> listeLigneCommande) {
		this.listeLigneCommande = listeLigneCommande;
	}

	@Override
	public String toString() {
		return "Panier [total=" + total + ", listeLigneCommande=" + listeLigneCommande + "]";
	}
	
}
