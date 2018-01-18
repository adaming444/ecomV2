package fr.adaming.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="categories")
public class Categorie implements Serializable {
	
	// Attributs du modele
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long idCategorie;
	
	private String nomCategorie;
	
	@Lob
	private byte[] photo;
	
	private String description;
	
	@Transient
	private String image;
	
	
	// Transformation des associations UML en Java
	@OneToMany(mappedBy="categorie")
	private List<Produit> produit;

	
	// Constructeurs
	public Categorie() {
		super();
	}


	public Categorie(String nomCategorie, byte[] photo, String description) {
		super();
		this.nomCategorie = nomCategorie;
		this.photo = photo;
		this.description = description;
	}


	public Categorie(long idCategorie, String nomCategorie, byte[] photo, String description) {
		super();
		this.idCategorie = idCategorie;
		this.nomCategorie = nomCategorie;
		this.photo = photo;
		this.description = description;
	}

	// Getters et setters
	public long getIdCategorie() {
		return idCategorie;
	}


	public void setIdCategorie(long idCategorie) {
		this.idCategorie = idCategorie;
	}


	public String getNomCategorie() {
		return nomCategorie;
	}


	public void setNomCategorie(String nomCategorie) {
		this.nomCategorie = nomCategorie;
	}


	public byte[] getPhoto() {
		return photo;
	}


	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public List<Produit> getProduit() {
		return produit;
	}


	public void setProduit(List<Produit> produit) {
		this.produit = produit;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	

	// methode tooString
	@Override
	public String toString() {
		return "Categorie [idCategorie=" + idCategorie + ", nomCategorie=" + nomCategorie + ", photo="
				+ Arrays.toString(photo) + ", description=" + description + "]";
	}
	
	
	

}
