package fr.adaming.dao;

import java.util.List;

import org.hibernate.Query;
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
		if (c.getProduit() != null) {
			catOut.setProduit(c.getProduit());
		}

		// actualisation de la bdd
		s.saveOrUpdate(catOut);
		return catOut;
	}

	@Override
	public int deleteCategorie(Long id) {
		Session s = sf.getCurrentSession();
		// ecriture de la requete
		String req = "DELETE FROM Categorie c WHERE c.idCategorie=:pId";

		// creation de la query
		Query query = s.createQuery(req);

		// Assignation des parametres
		query.setParameter("pId", id);

		// execute la requete et retourne l'entier résultat
		return query.executeUpdate();

	}

	@Override
	public Categorie getCategorieById(Long id) {
		Session s = sf.getCurrentSession();
		return (Categorie) s.get(Categorie.class, id);
	}

	@Override
	public Categorie getCategorieByName(String name) {
		Session s = sf.getCurrentSession();
		// ecriture de la requete
		String req = "FROM Categorie c WHERE c.nomCategorie=: pNomC";

		// creation de la query
		Query query = s.createQuery(req);

		// Assignation des parametres
		query.setParameter("pNomC", name);

		// envoie et recuperation du resultat et retour
		return (Categorie) query.uniqueResult();
	}

	@Override
	public List<Categorie> getAllCategorie() {
		Session s = sf.getCurrentSession();
		// ecriture de la requete
		String req = "FROM Categorie c";

		// creation de la query
		Query query = s.createQuery(req);

		// envoie et recuperation du resultat et retour
		return query.list();
	}

}
