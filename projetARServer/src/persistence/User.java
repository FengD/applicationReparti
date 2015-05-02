package persistence;

import java.util.Stack;

import rmi.Tweeter;

public class User {

	private Stack<Tweeter> interesse;
	private Stack<User> abonnement;

	public Stack<User> getAbonnement() {
		return abonnement;
	}

	public void setAbonnement(Stack<User> abonnement) {
		this.abonnement = abonnement;
	}

	public Stack<Tweeter> getInteresse() {
		return interesse;
	}

	public void setInteresse(Stack<Tweeter> interesse) {
		this.interesse = interesse;
	}

	public Stack<User> getAbonners() {
		return abonners;
	}

	public void setAbonners(Stack<User> abonners) {
		this.abonners = abonners;
	}

	private Stack<User> abonners; // interesé par l'autre

	private String userName;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	private String mdp;

	public User(String userName, String password) {

		this.userName = userName;
		this.mdp = password;
		this.interesse = new Stack<Tweeter>(); // tout les tweets d'ami par qui
												// je suis intéressé plus le
												// mien
		this.abonners = new Stack<User>(); // les gens par qui je suis intéressé
		this.abonnement = new Stack<User>();
	}

}
