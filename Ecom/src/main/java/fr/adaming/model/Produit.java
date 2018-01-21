package fr.adaming.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "produits")
public class Produit implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idProduit;
	private String designation;
	private String description;
	private double prix;
	private int quantite;
	@Lob
	private byte[] photo;
	@Transient
	private String image;
	

	// Transformation de l'UML en java avec catégorie
	@ManyToOne
	@JoinColumn(name = "idCategorie", referencedColumnName = "idCategorie")
	private Categorie categorie;

	// Transformation de l'UML en java avec la ligne de commande
	@OneToMany(mappedBy = "produit", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	private List<LigneCommande> ligneCommande;

	// Déclaration des constructeurs

	public Produit() {
		super();

	}

	public Produit(String designation, String description, double prix, int quantite, byte[] photo) {
		super();
		this.designation = designation;
		this.description = description;
		this.prix = prix;
		this.quantite = quantite;
		this.photo = photo;
	}

	public Produit(int idProduit, String designation, String description, double prix, int quantite, byte[] photo) {
		super();
		this.idProduit = idProduit;
		this.designation = designation;
		this.description = description;
		this.prix = prix;
		this.quantite = quantite;
		this.photo = photo;
	}

	// Déclaration des get et set

	public int getIdProduit() {
		return idProduit;
	}

	public void setIdProduit(int idProduit) {
		this.idProduit = idProduit;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public List<LigneCommande> getLigneCommande() {
		return ligneCommande;
	}

	public void setLigneCommande(List<LigneCommande> ligneCommande) {
		this.ligneCommande = ligneCommande;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	

	// Déclaration du ToString

	
	public String toString() {
		return "Produit [idProduit=" + idProduit + ", designation=" + designation + ", description=" + description
				+ ", prix=" + prix + ", quantite=" + quantite + ", photo=" + Arrays.toString(photo) + "]";
	}

}
