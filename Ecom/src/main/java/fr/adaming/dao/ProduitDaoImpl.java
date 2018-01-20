package fr.adaming.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fr.adaming.model.Categorie;
import fr.adaming.model.Produit;

@Repository
public class ProduitDaoImpl implements IProduitDao {

	@Autowired // injection du collaborateur sessionFactory
	private SessionFactory sf;

	// setter pour l'injection de dépendance
	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}

	@Override
	public List<Produit> getAllProduit() {
		// ouverture de la session
		Session s = sf.getCurrentSession();

		// écriture de la requete HQL
		String req = "from Produit";

		// création d'un query
		Query query = s.createQuery(req);

		// assignation des paramètres

		List<Produit> listProduits = query.list();

		return listProduits;
	}

	@Override
	public Produit addProduit(Produit p) {
		// ouverture de la session
		Session s = sf.getCurrentSession();

		// ajout de l'étudiant dans la BD
		s.save(p);

		return p;
	}

	@Override
	public Produit updateProduit(Produit p) {
		// ouverture de la session
		Session s = sf.getCurrentSession();

		Produit pOut = (Produit) s.get(Produit.class, p.getIdProduit());
		pOut.setDesignation(p.getDesignation());
		pOut.setDescription(p.getDescription());
		pOut.setPrix(p.getPrix());
		pOut.setQuantite(p.getQuantite());

		s.saveOrUpdate(pOut);
		return pOut;
	}

	@Override
	public int deleteProduit(int id) {
		// ouverture de la session
		Session s = sf.getCurrentSession();

		// création de la requete HQL
		String req = "delete from Produit as p where p.idProduit =:pIdProd";

		// création d'un query
		Query query = s.createQuery(req);

		// assignation des paramètres
		query.setParameter("pIdProd", id);

		return query.executeUpdate();
	}

	@Override
	public int deleteProduitByName(String name) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Produit getProduitbyId(int id) {
		// TODO Auto-generated method stub// ouverture de la session
		Session s = sf.getCurrentSession();

		// écriture de la requete HQL
		String req = "from Produit as p where p.idProduit=:pIdProd";

		// création d'un query
		Query query = s.createQuery(req);

		// assignation des paramètres
		query.setParameter("pIdProd", id);

		Produit pOut = (Produit) query.uniqueResult();
		return pOut;
	}

	@Override
	public Produit getProduitbyName(String designation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Produit> getAllProduitsByCategorie(Categorie c) {
		// ouverture de la session
		Session s = sf.getCurrentSession();

		// écriture de la requete HQL
		String req = "from Produit p where p.categorie.idCategorie=:pIdCat";

		// création d'un query
		Query query = s.createQuery(req);
		query.setParameter("pIdCat", c.getIdCategorie());
		// assignation des paramètres

		List<Produit> listProduitsCat = query.list();

		return listProduitsCat;
	}

}
