package fr.adaming.managedBeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.model.UploadedFile;

import fr.adaming.model.Admin;
import fr.adaming.model.Categorie;
import fr.adaming.service.ICategorieService;

@ManagedBean(name = "catMB")
@RequestScoped
public class CategorieManagedBean implements Serializable {

	@ManagedProperty(value="#{catService}")
	private ICategorieService categorieService;

	private Categorie categorie;

	private List<Categorie> listeCategorie;

	private Admin admin;

	private HttpSession maSession;

	private UploadedFile file;

	private String image;

	private Categorie selectedCat;

	// Constructeur par defaut
	public CategorieManagedBean() {
		this.categorie = new Categorie();
		this.listeCategorie = new ArrayList<Categorie>();
	}

	// methode qui s'execute apres l'instanciation du ManagedBean
	@PostConstruct
	public void init() {
		this.maSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		this.admin = (Admin) maSession.getAttribute("adminSession");
	}

	
}
