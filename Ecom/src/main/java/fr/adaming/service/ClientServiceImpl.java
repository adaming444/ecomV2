package fr.adaming.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.dao.IClientDao;
import fr.adaming.model.Client;

@Service("clientService")
@Transactional
public class ClientServiceImpl implements IClientService {

	@Autowired
	private IClientDao clientDao;
	
	public void setClientDao(IClientDao clientDao) {
		this.clientDao = clientDao;
	}

	@Override
	public List<Client> getAllClients() {
		return clientDao.getAllClients();
	}

	@Override
	public Client addClient(Client c) {
		return clientDao.addClient(c);
	}

	@Override
	public Client updateClient(Client cl) {
		
		Client cFind = clientDao.getClientById(cl.getIdClient());
		return clientDao.updateClient(cFind);
	}

	@Override
	public int deleteClient(Long id) {
		return clientDao.deleteClient(id);
	}

	@Override
	public Client getClientById(Long id) {
		return clientDao.getClientById(id);
	}

}
