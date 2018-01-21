package fr.adaming.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fr.adaming.model.Admin;

@Repository
public class AdminDaoImpl implements IAdminDao {

	@Autowired // injection du collaborateur sessionFactory
	private SessionFactory sf;

	// setter pour l'injection de dépendance
	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}

	@Override
	public Admin isExist(Admin a) {
		
		System.out.println("Entrée Dao ==============" + a);
		
		// ouverture de la session (récupère session existente ou en ouvre une
		// si n'existe pas)
		Session s = sf.getCurrentSession();

		// écriture de la requete HQL
		String req = "from Admin as a where a.mail=:pMail and a.mdp=:pMdp";

		// création d'un query
		Query query = s.createQuery(req);

		// assignation des paramètres
		query.setParameter("pMail", a.getMail());
		query.setParameter("pMdp", a.getMdp());

		Admin aOut = (Admin) query.uniqueResult();
		
		System.out.println("Sortie Dao ==============" + aOut);

		return aOut;
	}

}
