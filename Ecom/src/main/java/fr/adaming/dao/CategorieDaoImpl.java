package fr.adaming.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fr.adaming.model.Categorie;

@Repository
public class CategorieDaoImpl implements ICategorieDao {

	@Autowired // injection du collaborateur sf
	private SessionFactory sf;

	// Setter pour l'injection de dependance
	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}

	@Override
	public Categorie addCategorie(Categorie c) {
		Session s = sf.getCurrentSession();
		s.save(c);
		return c;
	}

	@Override
	public Categorie updateCategorie(Categorie c) {
		Session s = sf.getCurrentSession();

		// Recuperation de la categorie dans la bdd
		Categorie catOut = (Categorie) s.get(Categorie.class, c.getIdCategorie());

		// on sette les nouvelles valeurs
		catOut.setNomCategorie(c.getNomCategorie());
		catOut.setDescription(c.getDescription());
		catOut.setPhoto(c.getPhoto());
		catOut.setProduit(c.getProduit());

		// actualisation de la bdd
		s.saveOrUpdate(catOut);
		return catOut;
	}

	@Override
	public int deleteCategorie(Long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Categorie getCategorieById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Categorie getCategorieByName(String Name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Categorie> getAllCategorie() {
		// TODO Auto-generated method stub
		return null;
	}

}
