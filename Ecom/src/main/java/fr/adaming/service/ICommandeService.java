package fr.adaming.service;

import java.util.List;

import fr.adaming.model.Commande;

public interface ICommandeService {

	public List<Commande> getAllCommandes();

	public Commande addCommande(Commande c);

	public Commande updateCommande(Commande c);

	public int deleteCommande(Long id);

	public Commande getCommandeById(Long id);

}
