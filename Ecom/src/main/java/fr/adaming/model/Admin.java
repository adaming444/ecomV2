package fr.adaming.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "admins")
public class Admin implements Serializable{
	
	// Déclarations des attributs
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idAdmin;
	private String mail;
	private String mdp;

	// Déclarations des trois constructeurs

	public Admin() {
		super();

	}

	public Admin(String mail, String mdp) {
		super();
		this.mail = mail;
		this.mdp = mdp;
	}

	public Admin(int idAdmin, String mail, String mdp) {
		super();
		this.idAdmin = idAdmin;
		this.mail = mail;
		this.mdp = mdp;
	}

	// Déclarations des get et set

	public int getIdAdmin() {
		return idAdmin;
	}

	public void setIdAdmin(int idAdmin) {
		this.idAdmin = idAdmin;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	@Override
	public String toString() {
		return "Admin [idAdmin=" + idAdmin + ", mail=" + mail + ", mdp=" + mdp + "]";
	}

}
