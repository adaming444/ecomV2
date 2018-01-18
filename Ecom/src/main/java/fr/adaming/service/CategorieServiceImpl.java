package fr.adaming.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.dao.ICategorieDao;
import fr.adaming.model.Categorie;

@Service("catService")
@Transactional
public class CategorieServiceImpl implements ICategorieService {

	@Autowired
	ICategorieDao categorieDao;

	// Setter de categorie Dao
	public void setCategorieDao(ICategorieDao categorieDao) {
		this.categorieDao = categorieDao;
	}

	@Override
	public Categorie addCategorie(Categorie c) {
		return categorieDao.addCategorie(c);
	}

	@Override
	public Categorie updateCategorie(Categorie c) {
		return categorieDao.updateCategorie(c);
	}

	@Override
	public int deleteCategorie(Long id) {
		return categorieDao.deleteCategorie(id);
	}

	@Override
	public Categorie getCategorieById(Long id) {
		return categorieDao.getCategorieById(id);
	}

	@Override
	public Categorie getCategorieByName(String name) {
		return categorieDao.getCategorieByName(name);
	}

	@Override
	public List<Categorie> getAllCategorie() {
		return categorieDao.getAllCategorie();
	}

}
