package fr.adaming.managedBeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import fr.adaming.model.Admin;
import fr.adaming.model.Produit;
import fr.adaming.service.IProduitService;

@ManagedBean(name = "pMb")
@RequestScoped
public class ProduitManagedBean implements Serializable {

	// transformation de l'association UML en java
	@ManagedProperty(value = "#{pService}")
	private IProduitService pService;

	// @ManagedProperty(value = "#{catService}")
	// private ICategorieService categorieService;

	// Les attributs du modele MVC
	private Admin admin;
	private List<Produit> listProduits;
	private HttpSession maSession;
	private Produit produit;
	// private List<Categorie> listCategories;
	private UploadedFile file;
	private Produit selectedPdt;
	private String image;

	public ProduitManagedBean() {
		// Instancier un produit
		this.produit = new Produit();
	}

	@PostConstruct
	public void init() {
		this.listProduits = new ArrayList<Produit>();
		this.maSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		this.admin = (Admin) maSession.getAttribute("adminSession");
	}

	public IProduitService getpService() {
		return pService;
	}

	public void setpService(IProduitService pService) {
		this.pService = pService;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public List<Produit> getListProduits() {
		return listProduits;
	}

	public void setListProduits(List<Produit> listProduits) {
		this.listProduits = listProduits;
	}

	public HttpSession getMaSession() {
		return maSession;
	}

	public void setMaSession(HttpSession maSession) {
		this.maSession = maSession;
	}

	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public Produit getSelectedPdt() {
		return selectedPdt;
	}

	public void setSelectedPdt(Produit selectedPdt) {
		this.selectedPdt = selectedPdt;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	// Les methodes metiers

	public String addProduit() throws Exception {
		// //Appel de la methode service
		System.out.println(this.produit);
		Produit p1 = pService.addProduit(this.produit);

		// System.out.println("------------------- " +
		// this.produit.getIdProduit());
		if (this.produit.getIdProduit() != 0) {
			// Recuperer la nouvelle liste de la bd
			this.listProduits = pService.getAllProduit();
			// Mettre a jour la liste des produits dans la session
			maSession.setAttribute("produitList", this.listProduits);

			// pService.confirmAddProd(p1);
			return "accueilAdmin";
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Une erreur est survenue lors de l'ajout."));
			return "ajout_produit";
		}
	}

	public void upload(FileUploadEvent event) {
		UploadedFile ufile = event.getFile();
		byte[] contents = ufile.getContents();
		this.produit.setPhoto(contents);
		this.image = "data:image/png;base64," + Base64.encodeBase64String(contents);
	}

	public String updateProduit() {
		Produit pModif = pService.updateProduit(this.produit);
		if (pModif != null) {
			// Recuperer la nouvelle liste de la bd
			List<Produit> listeP = pService.getAllProduit();
			// Mettre a jour la liste des voitures dans la session
			maSession.setAttribute("produitList", listeP);
			return "accueilAdmin";
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Une erreur est survenue lors de la modification."));
			return "modif_produit";
		}
	}

	public String getProduitbyId() {
		Produit pFind = pService.getProduitbyId(this.produit.getIdProduit());
		pFind.setImage("data:image/png;base64," + Base64.encodeBase64String(pFind.getPhoto()));
		if (pFind != null) {
			this.produit = pFind;
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Une erreur est survenue lors de la recherche."));
		}
		return "rechercher_produit";
	}

	public String deleteProduit() {
		pService.deleteProduit(this.produit.getIdProduit());
		// Recuperer la nouvelle liste de la bd
		List<Produit> listeP = pService.getAllProduit();
		// Mettre a jour la liste des voitures dans la session
		maSession.setAttribute("produitList", listeP);
		return "accueilAdmin";
	}

	public String getAllProduits() {
		this.listProduits = pService.getAllProduit();
		if (listProduits.size() > 0) {

			List<Produit> listeTemp = new ArrayList<Produit>();
			for (Produit prod : listProduits) {
				prod.setImage("data:image/png;base64," + Base64.encodeBase64String(prod.getPhoto()));
				listeTemp.add(prod);
			}
			this.setListProduits(listeTemp);

			maSession.setAttribute("produitList", this.listProduits);
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Une erreur est survenue du chargement de la liste."));
		}
		return "affiche_produits";
	}

	public String getAllProduitsClient() {
		this.listProduits = pService.getAllProduit();
		if (listProduits.size() > 0) {

			List<Produit> listeTemp = new ArrayList<Produit>();
			for (Produit prod : listProduits) {
				prod.setImage("data:image/png;base64," + Base64.encodeBase64String(prod.getPhoto()));
				listeTemp.add(prod);
			}
			this.setListProduits(listeTemp);

			maSession.setAttribute("produitList", this.listProduits);
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Une erreur est survenue du chargement de la liste."));
		}
		return "affiche_produitsparcategorietheoriquement";
	}

}
