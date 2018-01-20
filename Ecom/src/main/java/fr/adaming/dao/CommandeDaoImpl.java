package fr.adaming.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fr.adaming.model.Commande;

@Repository
public class CommandeDaoImpl implements ICommandeDao {

	@Autowired // injection du collaborateur sessionFactory
	private SessionFactory sf;

	// setter pour l'injection de dépendance
	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}

	@Override
	public List<Commande> getAllCommandes() {
		// ouverture de la session
		Session s = sf.getCurrentSession();

		// écriture de la requete HQL
		String req = "from Commande as c";

		// création d'un query
		Query query = s.createQuery(req);

		@SuppressWarnings("unchecked")
		List<Commande> listeCommandes = query.list();

		return listeCommandes;
	}

	@Override
	public Commande addCommande(Commande c) {
		// ouverture de la session
		Session s = sf.getCurrentSession();

		// ajout de l'étudiant dans la BD
		s.save(c);

		return c;
	}

	@Override
	public Commande updateCommande(Commande c) {
		// ouverture de la session
		Session s = sf.getCurrentSession();

		Commande cOut = (Commande) s.get(Commande.class, c.getIdCommande());
		cOut.setDateCommande(c.getDateCommande());
		cOut.setClient(c.getClient());

		s.saveOrUpdate(cOut);
		return cOut;
	}

	@Override
	public int deleteCommande(Long id) {
		// ouverture de la session
		Session s = sf.getCurrentSession();

		// création de la requete HQL
		String req = "delete from Commande as c where c.idCommande =:pIdCommande";

		// création d'un query
		Query query = s.createQuery(req);

		// assignation des paramètres
		query.setParameter("pIdCommande", id);

		return query.executeUpdate();
	}

	@Override
	public Commande getCommandeById(Long id) {
		// ouverture de la session
		Session s = sf.getCurrentSession();

		// écriture de la requete HQL
		String req = "from Commande as c where c.idCommande=:pIdCommande";

		// création d'un query
		Query query = s.createQuery(req);

		// assignation des paramètres
		query.setParameter("pIdCommande", id);

		Commande cOut = (Commande) query.uniqueResult();
		return cOut;
	}

}
