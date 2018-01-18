package fr.adaming.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="lignes_commande")
public class LigneCommande implements Serializable {

	// Attributs du modele
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long idLigne;
	
	private int quantite;
	
	private double prix;

	
	// Transformation de l'association UML en Java
	@ManyToOne
	@JoinColumn(referencedColumnName="idProduit")
	private Produit produit;
	
	@ManyToOne
	@JoinColumn(referencedColumnName="idCommande")
	private Commande commande;
	
	// Constructeurs
	public LigneCommande() {
		super();
	}

	public LigneCommande(int quantite, int prix) {
		super();
		this.quantite = quantite;
		this.prix = prix;
	}

	public LigneCommande(long idLigne, int quantite, int prix) {
		super();
		this.idLigne = idLigne;
		this.quantite = quantite;
		this.prix = prix;
	}

	
	// getters et setters
	public long getIdLigne() {
		return idLigne;
	}

	public void setIdLigne(long idLigne) {
		this.idLigne = idLigne;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}
	
	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	public Commande getCommande() {
		return commande;
	}

	public void setCommande(Commande commande) {
		this.commande = commande;
	}

	// methode tooString
	@Override
	public String toString() {
		return "LigneCommande [idLigne=" + idLigne + ", quantite=" + quantite + ", prix=" + prix + "]";
	}
	
	
	
}
