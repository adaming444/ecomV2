package fr.adaming.service;

import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

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

	@Override
	public void confirmAddClient(Client c) throws Exception {
		String smtpHost = "smtp.gmail.com";
		String from = "adaming444@gmail.com";
		String to = c.getEmail();
		String username = "adaming444@gmail.com";
		String password = "adaming44";

		Properties props = new Properties();
		props.put("mail.smtp.host", smtpHost);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.port", "587");

		Session session = Session.getDefaultInstance(props);
		session.setDebug(true);

		MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress(from));
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
		message.setSubject("Confirmation identification Super carnaval");
		// message.setText("Vous avez bien ajouter le produit
		// :"+p.getDesignation());
		// message.setText("La quantité du produit ajouté est de :
		// :"+p.getQuantite());
		// message.setText(" Descriptif du produit :"+p.getDescription());
		message.setText("Vous avez bien creé votre compte :" + c.getNomClient() + '\n' + " Adresse email :"
				+ c.getEmail() + '\n' + "Le num de tel est : :" + c.getTel()) ;

		Transport tr = session.getTransport("smtp");
		tr.connect(smtpHost, username, password);
		message.saveChanges();

		// tr.send(message);
		/**
		 * Genere l'erreur. Avec l authentification, oblige d utiliser
		 * sendMessage meme pour une seule adresse...
		 */

		tr.sendMessage(message, message.getAllRecipients());
		tr.close();
		
	}

}
