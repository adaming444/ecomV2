package fr.adaming.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fr.adaming.model.LigneCommande;

@Repository
public class LigneCommandeDaoImpl implements ILigneCommandeDao {

	@Autowired // injection du collaborateur sf
	private SessionFactory sf;

	// Setter pour l'injection de dependance
	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}

	@Override
	public LigneCommande addLigneCommande(LigneCommande lc) {
		Session s = sf.getCurrentSession();
		s.save(lc);
		return lc;
	}

	@Override
	public LigneCommande updateLigneCommande(LigneCommande lc) {
		Session s = sf.getCurrentSession();
		LigneCommande ligneOut = (LigneCommande) s.get(LigneCommande.class, lc.getIdLigne());

		ligneOut.setQuantite(lc.getQuantite());
		ligneOut.setPrix(lc.getPrix());

		s.saveOrUpdate(ligneOut);
		return ligneOut;
	}

	@Override
	public int deleteLigneCommande(Long id) {
		Session s = sf.getCurrentSession();
		
		
		
		return 0;
	}

	@Override
	public LigneCommande getLigneCommandeById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<LigneCommande> getAllLigneCommande() {
		Session s = sf.getCurrentSession();
		// construire la requete QL
		String req = "FROM LigneCommande";

		// créer le query
		Query query = s.createQuery(req);

		// envoyer la requete et recup du resultat
		return query.list();
	}

}
