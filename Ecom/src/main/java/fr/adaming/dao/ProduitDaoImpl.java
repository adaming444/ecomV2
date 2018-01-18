package fr.adaming.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
		String req = "from Produit as e where e.formateur.idFormateur=:pIdForm";

		// création d'un query
		Query query = s.createQuery(req);

		// assignation des paramètres
		query.setParameter("pIdForm", f.getIdFormateur());

		List<Etudiant> listEtudiants = query.list();

		return listEtudiants;
	}

	@Override
	public Produit addProduit(Produit p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Produit updateProduit(Produit p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteProduit(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteProduitByName(String name) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Produit getProduitbyId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Produit getProduitbyName(String designation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Produit> getAllProduitByCategorie(long idCat) {
		// TODO Auto-generated method stub
		return null;
	}

}
