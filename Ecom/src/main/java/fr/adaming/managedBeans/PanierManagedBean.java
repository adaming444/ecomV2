package fr.adaming.managedBeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import fr.adaming.model.Commande;
import fr.adaming.model.LigneCommande;
import fr.adaming.model.Panier;
import fr.adaming.model.Produit;
import fr.adaming.service.ICommandeService;
import fr.adaming.service.ILigneCommandeService;
import fr.adaming.service.IProduitService;

@ManagedBean(name = "panMB")
@SessionScoped
public class PanierManagedBean implements Serializable {

	// attribut
	private Panier panier;
	private LigneCommande ligneCommande;
	private Produit produit;
	private Commande commande;

	@ManagedProperty(value = "ligneService")
	private ILigneCommandeService ligneCommandeService;

	@ManagedProperty(value = "pService")
	private IProduitService produitService;

	@ManagedProperty(value = "commandeService")
	private ICommandeService commandeService;

	private HttpSession maSession;

	// Constructeur par defaut
	public PanierManagedBean() {
		this.panier = new Panier();
		this.ligneCommande = new LigneCommande();
		this.produit = new Produit();
		this.commande = new Commande();
	}

	// methode qui s'execute apres l'instanciation du ManagedBean
	@PostConstruct
	public void init() {
		this.maSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		// ajouter nettoyage des ligne sans commande delete si sans id commande
	}

	// Getters et setter
	public Panier getPanier() {
		return panier;
	}

	public void setPanier(Panier panier) {
		this.panier = panier;
	}

	public LigneCommande getLigneCommande() {
		return ligneCommande;
	}

	public void setLigneCommande(LigneCommande ligneCommande) {
		this.ligneCommande = ligneCommande;
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

	public void setLigneCommandeService(ILigneCommandeService ligneCommandeService) {
		this.ligneCommandeService = ligneCommandeService;
	}

	public void setProduitService(IProduitService produitService) {
		this.produitService = produitService;
	}

	public void setCommandeService(ICommandeService commandeService) {
		this.commandeService = commandeService;
	}

	// Methodes metier
	public String addLigneCommande() {
		// calcul du prix de la ligne puis on l'ajoute à la ligne
		this.ligneCommande.setPrix(this.ligneCommande.getQuantite() * this.produit.getPrix());
		// ajout de la ligne dans la bdd + ajout de l'id de la ligne
		this.ligneCommande = this.ligneCommandeService.addLigneCommande(this.ligneCommande, this.produit);
		// ajout de la ligne a la liste de ligne
		this.panier.getListeLigneCommande().add(this.ligneCommande);
		// actualise le total du panier
		this.panier.setTotal(this.panier.getTotal() + this.ligneCommande.getPrix());
		// Reinitialise la ligne du Bean
		this.ligneCommande = new LigneCommande();
		return "panier";
	}

	public void deleteLigneCommande() {
		// Recuperation de la ligne via son id
		this.ligneCommande = this.ligneCommandeService.getLigneCommandeById(this.ligneCommande.getIdLigne());
		// Soustrait le prix de la ligne au total
		this.panier.setTotal(this.panier.getTotal() - this.ligneCommande.getPrix());
		// appel de la methode delete de lignecommandeservice et recuperation du
		// resultat pour test
		int verif = this.ligneCommandeService.deleteLigneCommande(this.ligneCommande.getIdLigne());
		// test sur reussite du delete
		if (verif == 1) {
			// instanciation d'une liste
			List<LigneCommande> listeOut = new ArrayList<LigneCommande>();
			// parcout de la liste
			for (LigneCommande lc : this.panier.getListeLigneCommande()) {

				if (lc.getIdLigne() != this.ligneCommande.getIdLigne()) {
					// ajout a nouvelle liste si id different de ligne a delete
					listeOut.add(lc);
				}
			}
			// recuperation nouvelle liste
			this.panier.setListeLigneCommande(listeOut);
		} else {
			// message echec + recuperation du total
			this.panier.setTotal(this.panier.getTotal() + this.ligneCommande.getPrix());
		}
		this.ligneCommande = new LigneCommande();
	}

	public void updateLigneCommande() {
		// retrait prix ancienne ligne
		this.panier.setTotal(this.panier.getTotal() - this.ligneCommande.getPrix());
		// calcul nouveau prix de ligne plus ajout a la ligne
		this.ligneCommande.setPrix(this.ligneCommande.getQuantite() * this.produit.getPrix());
		// mise a jour de la ligne de commande
		this.ligneCommande = this.ligneCommandeService.updateLigneCommande(this.ligneCommande, this.produit);
		// nouvelle liste pour stockage plus récup de toutes les lignes bdd
		List<LigneCommande> listeOut = new ArrayList<LigneCommande>();
		List<LigneCommande> listeAll = this.ligneCommandeService.getAllLigneCommande();

		// itération pour recup id de liste panier
		for (LigneCommande lc : this.panier.getListeLigneCommande()) {
			// itération sur ligne de bdd pour tester id
			for (LigneCommande lCom : listeAll) {
				if (lc.getIdLigne() == lCom.getIdLigne()) {
					// ajout des lignes apres test id
					listeOut.add(lCom);
				}
			}
		}
		// remplacement de la liste de ligne
		this.panier.setListeLigneCommande(listeOut);
		// ajout au total du prix de la nouvelle ligne
		this.panier.setTotal(this.panier.getTotal() + this.ligneCommande.getPrix());
		// reinitialise ligne commande
		this.ligneCommande = new LigneCommande();
	}

	public void viderPanier() {
		// itération sur chaque ligne du panier
		for (LigneCommande lc : this.panier.getListeLigneCommande()) {
			// suppression de la bdd de la ligne
			this.ligneCommandeService.deleteLigneCommande(lc.getIdLigne());
		}
		// reinitialise le total a 0
		this.panier.setTotal(0.0);
		// Reinitialise la liste de ligne de commande
		this.panier.setListeLigneCommande(new ArrayList<LigneCommande>());
	}

	public String validerPanier() throws Exception {
		// Creation de la commande a la date actuel + ajout dans bdd
		this.commande = commandeService.addCommande(new Commande(new Date()));
		// attribution des lignes a une commande
		this.panier.setListeLigneCommande(
				ligneCommandeService.associerCommande(this.panier.getListeLigneCommande(), this.commande));
		// actualisation des stocks
		for (LigneCommande lc : this.panier.getListeLigneCommande()) {
			// recuperation produit associé a la ligne
			Produit pOut = lc.getProduit();
			// calcul nouveau stock
			pOut.setQuantite(pOut.getQuantite() - lc.getQuantite());
			// actualisation dans la bdd du stock
			this.produitService.updateProduit(pOut);
			// Envoie du mail de validation commande
			// commandeService.confirmCommandeMail(this.commande);
		}
		this.panier = new Panier();
		return "valider_commande";
	}

}
