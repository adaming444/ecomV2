package fr.adaming.managedBeans;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.servlet.http.HttpSession;

import fr.adaming.model.Client;
import fr.adaming.service.IClientService;

@ManagedBean
@RequestScoped
public class ClientManagedBean implements Serializable {

	// transformation de l'association UML en java
	@ManagedProperty(value = "#{clientService}")
	private IClientService clientService;

	// les attributs du modele mvc
	private Client client;
	private List<Client> listeClients;
	private HttpSession maSession;
	
	//constructuer vide
	public ClientManagedBean() {
		this.client = new Client();
	}

	//setters pour l'injection de dépendance
	public void setClientService(IClientService clientService) {
		this.clientService = clientService;
	}


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

	
	
}
