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
import fr.adaming.model.Client;
import fr.adaming.service.IClientService;

@ManagedBean(name = "clMB")
@RequestScoped
public class ClientManagedBean implements Serializable {

	// transformation de l'association UML en java
	@ManagedProperty(value = "#{clientService}")
	private IClientService clientService;

	// les attributs du modele mvc
	private Client client;
	private List<Client> listeClients;
	private HttpSession maSession;
	private Admin admin;

	
	// methode qui s'execute après l'instanciation du managed bean
	@PostConstruct
	public void init() {
		this.maSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		this.admin = (Admin) maSession.getAttribute("AdminSession");
		this.listeClients = clientService.getAllClients();
	}
	
	//constructuer vide
	public ClientManagedBean() {
		this.client = new Client();
		this.listeClients = new ArrayList<Client>();
	}

	//setters pour l'injection de dépendance
	public void setClientService(IClientService clientService) {
		this.clientService = clientService;
	}

	//les getters et setters
	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public List<Client> getListeClients() {
		return listeClients;
	}

	public void setListeClients(List<Client> listeClients) {
		this.listeClients = listeClients;
	}

	public HttpSession getMaSession() {
		return maSession;
	}

	public void setMaSession(HttpSession maSession) {
		this.maSession = maSession;
	}

	public String addClient() throws Exception {
		this.client = clientService.addClient(this.client);
		if (this.client.getIdClient() != 0) {
			// reucp de la nouvelle liste de la bd
			this.listeClients = clientService.getAllClients();
			// mettre ajour la liste dans la session
			maSession.setAttribute("clientsList", this.listeClients);
			clientService.confirmAddClient(this.client);
			return "fin_commande";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Une erreur est survenue lors de l'ajout."));
			return "valider_commande";
		}
	}

	public String updateClient() {
		this.client = clientService.updateClient(this.client);
		if (this.client.getIdClient() != 0) {
			// reucp de la nouvelle liste de la bd
			this.listeClients = clientService.getAllClients();
			// mettre ajour la liste dans la session
			maSession.setAttribute("clientsList", this.listeClients);
			return "success";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Une erreur est survenue lors de la modification."));
			return "modif_client";
		}

	}

	public String deleteClient() {
		if (clientService.deleteClient(this.client.getIdClient()) == 1) {
			this.listeClients = clientService.getAllClients();
			maSession.setAttribute("clientsList", this.listeClients);
			return "accueilAdmin";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Une erreur est survenue lors de la suppression."));
			return "suppr_client";
		}
	}

	public String getClientById() {
		Client cOut = clientService.getClientById(this.client.getIdClient());
		if (cOut != null) {
			this.client = cOut;
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Une erreur est survenue lors de la recherche."));
		}
		return "rechercher_client";
	}
	
	public String getAllClients() {
		this.listeClients = clientService.getAllClients();
		if (listeClients.size() > 0) {
			this.listeClients = clientService.getAllClients();
			maSession.setAttribute("clientsList", this.listeClients);
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Une erreur est survenue du chargement de la liste."));
		}
		return "affiche_clients";
	}
	
	public String redirectAccueil() {
		return "accueil_client";
	}
	
}
