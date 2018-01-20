package fr.adaming.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.dao.ICommandeDao;
import fr.adaming.model.Commande;

@Service("commandeService")
@Transactional
public class CommandeServiceImpl implements ICommandeService {

	@Autowired
	private ICommandeDao commandeDao;

	public void setCommandeDao(ICommandeDao commandeDao) {
		this.commandeDao = commandeDao;
	}

	@Override
	public List<Commande> getAllCommandes() {
		return commandeDao.getAllCommandes();
	}

	@Override
	public Commande addCommande(Commande c) {
		return commandeDao.addCommande(c);
	}

	@Override
	public Commande updateCommande(Commande cl) {

		Commande cFind = commandeDao.getCommandeById(cl.getIdCommande());
		return commandeDao.updateCommande(cFind);
	}

	@Override
	public int deleteCommande(Long id) {
		return commandeDao.deleteCommande(id);
	}

	@Override
	public Commande getCommandeById(Long id) {
		return commandeDao.getCommandeById(id);
	}

}