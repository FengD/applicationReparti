package rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import userInterface.MainWindow;
import userInterface.Portail;
import userInterface.Topic;

public class Client extends UnicastRemoteObject implements ClientActive {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String userName;
	private String passWord;
	private boolean connecter;
	private Topic topic;

	public Portail getPortail() {
		return portail;
	}

	public void setPortail(Portail portail) {
		this.portail = portail;
	}

	public MainWindow getMw() {
		return mw;
	}

	public void setMw(MainWindow mw) {
		this.mw = mw;
	}

	private Portail portail;
	private MainWindow mw;

	public void setConnecter(boolean c) {
		connecter = c;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setPassword(String password) {
		this.passWord = password;
	}

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public ServeurPublic getServeur() {
		return serveur;
	}

	public void setServeur(ServeurPublic serveur) {
		this.serveur = serveur;
	}

	private Service service;
	private ServeurPublic serveur;

	protected Client() throws RemoteException {
		super();
		this.userName = null;
		this.passWord = null;
		this.serveur = null;
		this.service = null;
		this.connecter = false;
		this.mw = null;
		this.portail = null;
		this.topic = null;
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getUserName() throws RemoteException {
		// TODO Auto-generated method stub
		return userName;
	}

	@Override
	public String getPassword() throws RemoteException {
		// TODO Auto-generated method stub
		return passWord;
	}

	@Override
	public void refreshList() throws RemoteException {

		this.mw.refresh(getService().listerAbonnement());

	}

	@Override
	public boolean isConnecte() throws RemoteException {
		// TODO Auto-generated method stub
		return this.connecter;
	}

	@Override
	public void refreshListTout() throws RemoteException {
		// TODO Auto-generated method stub
		this.portail.refresh(getServeur().listerTout(this));
	}

	public Topic getTopic() {
		return topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}

	@Override
	public void refreshTopics() throws RemoteException {
		// TODO Auto-generated method stub
		if (topic != null) {
			String sub = topic.topicSelected();
			if (!sub.equals(""))
				topic.refreshFollows(getService().listerFollows(sub));
			topic.refreshTopics(getService().listerTopic());
		}
	}

}
