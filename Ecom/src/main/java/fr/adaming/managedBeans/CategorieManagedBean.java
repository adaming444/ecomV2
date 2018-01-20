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
import fr.adaming.model.Categorie;
import fr.adaming.service.ICategorieService;

@ManagedBean(name = "catMB")
@RequestScoped
public class CategorieManagedBean implements Serializable {

	@ManagedProperty(value = "#{catService}")
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

	// Getters et setters
	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public List<Categorie> getListeCategorie() {
		return listeCategorie;
	}

	public void setListeCategorie(List<Categorie> listeCategorie) {
		this.listeCategorie = listeCategorie;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public void setCategorieService(ICategorieService categorieService) {
		this.categorieService = categorieService;
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Categorie getSelectedCat() {
		return selectedCat;
	}

	public void setSelectedCat(Categorie selectedCat) {
		this.selectedCat = selectedCat;
	}

	// methodes metier
	public String addCategorie() {

		this.categorie = categorieService.addCategorie(this.categorie);
		// this.image=null;
		// this.file=null;

		if (this.categorie.getIdCategorie() != 0) {
			this.listeCategorie = categorieService.getAllCategorie();

			maSession.setAttribute("categorieListe", this.listeCategorie);

			return "accueilAdmin";
		} else {
			return "ajout_categorie";
		}
	}

	public void upload(FileUploadEvent event) {
		UploadedFile ufile = event.getFile();
		byte[] contents = ufile.getContents();
		this.categorie.setPhoto(contents);
		this.image = "data:image/png;base64," + Base64.encodeBase64String(contents);
	}

	public String deleteCategorie() {
		int verif = categorieService.deleteCategorie(this.categorie.getIdCategorie());

		if (verif == 1) {
			this.listeCategorie = categorieService.getAllCategorie();

			maSession.setAttribute("categorieListe", this.listeCategorie);

			return "accueilAdmin";
		} else {
			return "suppr_categorie";
		}

	}

	public String updateCategorie() {

		// InputStream input = file.getInputStream();
		// this.categorie.setPhoto(IOUtils.toByteArray(input)); // Apache
		// commons IO.

		this.categorie = categorieService.updateCategorie(this.categorie);

		if (this.categorie.getIdCategorie() != 0) {
			this.listeCategorie = categorieService.getAllCategorie();

			maSession.setAttribute("categorieListe", this.listeCategorie);

			return "accueilAdmin";
		} else {
			return "modif_categorie";
		}
	}

	@SuppressWarnings("unused")
	public void recupCategorieById() {
		Categorie cOut = categorieService.getCategorieById(this.categorie.getIdCategorie());
		cOut.setImage("data:image/png;base64," + Base64.encodeBase64String(cOut.getPhoto()));
		if (cOut != null) {
			this.categorie = cOut;
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Une erreur est survenue lors de la recherche."));
		}

	}

	public String recupAllCategorie() {
		this.listeCategorie = categorieService.getAllCategorie();

		if (listeCategorie.size() > 0) {
			List<Categorie> listeTemp = new ArrayList<>();
			for (Categorie categ : listeCategorie) {
				categ.setImage("data:image/png;base64," + Base64.encodeBase64String(categ.getPhoto()));
				listeTemp.add(categ);
			}
			this.setListeCategorie(listeTemp);
			maSession.setAttribute("categorieListe", this.listeCategorie);
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Une erreur est survenue lors du chargement de la liste."));
		}
		return "affiche_categories";
	}

}
