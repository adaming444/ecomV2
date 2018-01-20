package fr.adaming.service;

import java.util.List;

import fr.adaming.model.Client;

public interface IClientService {

	public List<Client> getAllClients();

	public Client addClient(Client c);

	public Client updateClient(Client c);

	public int deleteClient(Long id);

	public Client getClientById(Long id);

}
