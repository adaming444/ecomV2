package fr.adaming.managedBeans;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import fr.adaming.model.Admin;
import fr.adaming.service.IAdminService;

@ManagedBean(name = "aMb")
@RequestScoped
public class AdminManagedBean implements Serializable {

	// transformation de l'association UML en java
	@ManagedProperty(value = "#{aService}")
	private IAdminService adminService;
	// @ManagedProperty(value = "#{pService}")
	// private IProduitService produitService;

	// @ManagedProperty(value = "#{catService}")
	// private ICategorieService categorieService;

	// les attributs du modele de MVC
	private Admin admin;
	// private List<Produit> listProduits;
	// private List<Categorie> listCategories;

	public AdminManagedBean() {
		this.admin = new Admin();
	}

	// le setter pour l'injection de dépendance
	public void setAdminService(IAdminService adminService) {
		this.adminService = adminService;
	}

	// Les getters et setters

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	
	public String seConnecter() {
		this.admin = adminService.isExist(this.admin);

		if (this.admin != null) {
			
			//récupération de la liste d'étudiants de ce formateur
			//this.listProduits = produitService.getAllProduit(this.admin);
			//this.listCategories = categorieService.getAllCategorie(this.admin);
			
			//ajout de la liste dans la session
			//FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("produitsList", this.listProduits);
			//FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("categoriesList", this.listCategories);
			
			//ajout du formateur dans la session
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("admin", this.admin);
			
			return "accueil_admin";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Une erreur est survenue lors de la connexion."));
			return "login_admin";
		}
	}
}
