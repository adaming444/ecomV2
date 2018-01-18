package fr.adaming.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.dao.IProduitDao;
import fr.adaming.model.Produit;
@Service("pService")
@Transactional
public class ProduitServiceImpl implements IProduitService {
	
	@Autowired
	private IProduitDao produitDao;

	public void setProduitDao(IProduitDao produitDao) {
		this.produitDao = produitDao;
	}

	@Override
	public List<Produit> getAllProduit() {
		// TODO Auto-generated method stub
		return null;
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
