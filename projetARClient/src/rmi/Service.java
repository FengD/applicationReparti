package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Stack;

public interface Service extends Remote {

	public Stack<Tweeter> listerAbonnement() throws RemoteException;

	public boolean abonner(String aime) throws RemoteException;

	public void desabonner(String deteste) throws RemoteException;

	public void publier(String contenu) throws RemoteException;

	public void supprimer(int id) throws RemoteException;

	// public void desinscrire()throws RemoteException;
	public void publier(String contenu, String topic) throws RemoteException;

	public Stack<Tweeter> listerFollows(String topic) throws RemoteException;

	public List<String> listerTopic() throws RemoteException;
}
