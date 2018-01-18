package fr.adaming.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fr.adaming.model.Client;

@Repository
public class ClientDaoImpl implements IClientDao{

	@Autowired // injection du collaborateur sessionFactory
	private SessionFactory sf;

	// setter pour l'injection de dépendance
	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}
	
	@Override
	public List<Client> getAllClients() {
		// ouverture de la session
		Session s = sf.getCurrentSession();

		// écriture de la requete HQL
		String req = "from Client as c";

		// création d'un query
		Query query = s.createQuery(req);

		@SuppressWarnings("unchecked")
		List<Client> listeClients = query.list();

		return listeClients;
	}

	@Override
	public Client addClient(Client c) {
		// ouverture de la session
		Session s = sf.getCurrentSession();

		// ajout de l'étudiant dans la BD
		s.save(c);

		return c;
	}

	@Override
	public Client updateClient(Client c) {
		// ouverture de la session
		Session s = sf.getCurrentSession();
		
		Client cOut = (Client) s.get(Client.class, c.getIdClient());
		cOut.setEmail(c.getEmail());
		cOut.setNomClient(c.getNomClient());
		cOut.setTel(c.getTel());
		cOut.setAdresse(c.getAdresse());

		s.saveOrUpdate(cOut);
		return cOut;
	}

	@Override
	public int deleteClient(Long id) {
		// ouverture de la session
				Session s = sf.getCurrentSession();
				
				//création de la requete HQL
				String req = "delete from Client as c where c.idClient =:pIdClient";
				
				//création d'un query
				Query query = s.createQuery(req);
				
				//assignation des paramètres
				query.setParameter("pIdClient", id);
				
				return query.executeUpdate();
	}

	@Override
	public Client getClientById(Long id) {
		// ouverture de la session
		Session s = sf.getCurrentSession();

		// écriture de la requete HQL
		String req = "from Client as c where c.idClient=:pIdClient";

		// création d'un query
		Query query = s.createQuery(req);

		// assignation des paramètres
		query.setParameter("pIdClient", id);

		Client cOut = (Client) query.uniqueResult();
		return cOut;
	}
	
}
