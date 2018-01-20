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

import fr.adaming.model.Admin;
import fr.adaming.model.Commande;
import fr.adaming.service.ICommandeService;

@ManagedBean(name = "comMB")
@RequestScoped
public class CommandeManagedBean implements Serializable {

	// transformation de l'association UML en java
	@ManagedProperty(value = "#{commandeService}")
	private ICommandeService commandeService;

	// les attributs du modele mvc
	private Commande commande;
	private Admin admin;
	private List<Commande> listeCommandes;
	private HttpSession maSession;

	// constructuer vide
	public CommandeManagedBean() {
		this.commande = new Commande();
	}

	// methode qui s'execute après l'instanciation du managed bean
	@PostConstruct
	public void init() {
		this.maSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		this.admin = (Admin) maSession.getAttribute("AdminSession");
		this.listeCommandes = commandeService.getAllCommandes();
	}
	
	// setters pour l'injection de dépendance
	public void setCommandeService(ICommandeService commandeService) {
		this.commandeService = commandeService;
		this.listeCommandes = new ArrayList<Commande>();
	}

	//les getters et setters
	public Commande getCommande() {
		return commande;
	}

	public void setCommande(Commande commande) {
		this.commande = commande;
	}

	public List<Commande> getListeCommandes() {
		return listeCommandes;
	}

	public void setListeCommandes(List<Commande> listeCommandes) {
		this.listeCommandes = listeCommandes;
	}

	public HttpSession getMaSession() {
		return maSession;
	}

	public void setMaSession(HttpSession maSession) {
		this.maSession = maSession;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public String addCommande() {
		this.commande = commandeService.addCommande(this.commande);
		//verif que l'ajout a été effectué
		if (this.commande.getIdCommande() != 0) {
			// recup de la nouvelle liste de la bd
			this.listeCommandes = commandeService.getAllCommandes();
			// mettre a jour la liste dans la session
			maSession.setAttribute("commandesList", this.listeCommandes);
			//retourner à la page
			return "success";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Une erreur est survenue lors de l'ajout."));
			return "#";
		}
	}

	public String updateCommande() {
		this.commande = commandeService.updateCommande(this.commande);
		//verif que la modif a été effectué
		if (this.commande.getIdCommande() != 0) {
			// recup de la nouvelle liste de la bd
			this.listeCommandes = commandeService.getAllCommandes();
			// mettre a jour la liste dans la session
			maSession.setAttribute("commandesList", this.listeCommandes);
			return "success";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Une erreur est survenue lors de la modification."));
			return "#";
		}

	}

	public String deleteCommande() {
		if (commandeService.deleteCommande(this.commande.getIdCommande()) == 1) {
			this.listeCommandes = commandeService.getAllCommandes();
			maSession.setAttribute("commandesList", this.listeCommandes);
			return "success";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Une erreur est survenue lors de la suppression."));
			return "#";
		}
	}

	public String getCommandeById() {
		Commande cOut = commandeService.getCommandeById(this.commande.getIdCommande());
		if (cOut != null) {
			this.commande = cOut;
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Une erreur est survenue lors de la recherche."));
		}
		return "rechercher_commande";
	}
	
	public String getAllCommandes() {
		this.listeCommandes = commandeService.getAllCommandes();
		if (listeCommandes.size() > 0) {
			this.listeCommandes = commandeService.getAllCommandes();
			maSession.setAttribute("commandesList", this.listeCommandes);
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Une erreur est survenue du chargement de la liste."));
		}
		return "#";
	}
	
	
	

}
