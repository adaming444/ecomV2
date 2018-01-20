package fr.adaming.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.dao.ILigneCommandeDao;
import fr.adaming.model.Commande;
import fr.adaming.model.LigneCommande;
import fr.adaming.model.Produit;

@Service("ligneService")
@Transactional
public class LigneCommandeServiceImpl implements ILigneCommandeService {

	@Autowired
	ILigneCommandeDao ligneCommandeDao;

	// setter ligne commande dao
		public void setLigneCommandeDao(ILigneCommandeDao ligneCommandeDao) {
			this.ligneCommandeDao = ligneCommandeDao;
		}

		@Override
		public LigneCommande addLigneCommande(LigneCommande lc, Produit p) {
			lc.setProduit(p);
			lc.setPrix(lc.getQuantite()*p.getPrix());
			return ligneCommandeDao.addLigneCommande(lc);
		}

		@Override
		public LigneCommande updateLigneCommande(LigneCommande lc, Produit p) {
			lc.setProduit(p);
			lc.setPrix(lc.getQuantite()*p.getPrix());
			return ligneCommandeDao.updateLigneCommande(lc);
		}

		@Override
		public int deleteLigneCommande(Long id) {
			return ligneCommandeDao.deleteLigneCommande(id);
		}

		@Override
		public LigneCommande getLigneCommandeById(Long id) {
			return ligneCommandeDao.getLigneCommandeById(id);
		}

		@Override
		public List<LigneCommande> getAllLigneCommande() {
			return ligneCommandeDao.getAllLigneCommande();
		}

		public List<LigneCommande> associerCommande(List<LigneCommande> listLC, Commande c){
			// Instanciation nouvelle liste
			List<LigneCommande> listOut = new ArrayList<LigneCommande>();
			for (LigneCommande lc : listLC) {
				// Association des lignes a leur commande
				lc.setCommande(c);
				// mise a jour de la bdd
				this.updateLigneCommande(lc, lc.getProduit());
				// Remplissage de la nouvelle liste
				listOut.add(lc);
			}
			// retourne la liste des lignes avec l'association a la commande
			return listOut;
		}
}
