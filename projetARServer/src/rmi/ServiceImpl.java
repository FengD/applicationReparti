package rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Stack;

import persistence.DataBase;
import persistence.Topic;
import persistence.User;

public class ServiceImpl extends UnicastRemoteObject implements Service {
	private ClientActive ca;

	protected ServiceImpl(ClientActive ca) throws RemoteException {

		super();
		this.ca = ca;
		// TODO Auto-generated constructor stub
		// subject = null;
	}

	private static final long serialVersionUID = 1L;

	@Override
	public Stack<Tweeter> listerAbonnement() throws RemoteException {
		String userName = ca.getUserName();
		User client = DataBase.getDataBase().get(userName);
		return client.getInteresse();
	}

	@Override
	public boolean abonner(String aime) throws RemoteException {
		// TODO Auto-generated method stub
		String userName = ca.getUserName();
		User courant = null;
		User aimer = null;
		courant = DataBase.getDataBase().get(userName);
		aimer = DataBase.getDataBase().get(aime);
		if (aimer == null) {
			return false;
		}
		if (!courant.getAbonners().contains(aimer)) {
			courant.getAbonners().push(aimer);
			aimer.getAbonnement().push(courant);
		}
		return true;
		// à faire

	}

	private void post(String userName, User client) throws RemoteException {

		for (int j = 0; j < ServeurPublicImpl.cas.size(); j++) {
			if (ServeurPublicImpl.cas.get(j).isConnecte() == false) {
				ServeurPublicImpl.cas.get(j).refreshListTout();
			} else {
				User abonner = DataBase.getDataBase().get(
						ServeurPublicImpl.cas.get(j).getUserName());

				if (abonner.getAbonners().contains(client)
						|| abonner.getUserName().equals(userName)) {
					ServeurPublicImpl.cas.get(j).refreshList();
				}

			}
		}
	}

	@Override
	public void publier(String contenu, String topic) throws RemoteException {

		String userName = ca.getUserName();
		Tweeter t = new Tweeter(contenu, userName);

		User client = DataBase.getDataBase().get(userName);

		DataBase.getTweets().push(t);
		Topic suj = DataBase.getTopics().get(topic);

		if (suj == null) {
			suj = new Topic(topic);
			DataBase.getTopics().put(topic, suj);

		}
		suj.getFollows().push(t);
		t.setTopic(topic);

		client.getInteresse().push(t);
		for (int i = 0; i < client.getAbonnement().size(); i++) {
			client.getAbonnement().get(i).getInteresse().push(t);
		}

		postTopic(userName, client);
		post(userName, client);
		return;

	}

	@Override
	public void publier(String contenu) throws RemoteException {
		String userName = ca.getUserName();
		Tweeter t = new Tweeter(contenu, userName);
		User client = DataBase.getDataBase().get(userName);

		DataBase.getTweets().push(t);
		client.getInteresse().push(t);
		for (int i = 0; i < client.getAbonnement().size(); i++) {
			client.getAbonnement().get(i).getInteresse().push(t);
		}
		post(userName, client);
		return;
	}

	@Override
	public void supprimer(int id) throws RemoteException {
		// TODO Auto-generated method stub
		String userName = ca.getUserName();
		Tweeter remove = null;
		User client = DataBase.getDataBase().get(userName);

		for (int i = 0; i < DataBase.getTweets().size(); i++) {

			if (DataBase.getTweets().get(i).getId() == id) {
				remove = DataBase.getTweets().get(i);
				DataBase.getTweets().remove(i);

			}
		}
		client.getInteresse().remove(remove);
		for (int i = 0; i < client.getAbonnement().size(); i++) {
			client.getAbonnement().get(i).getInteresse().remove(remove);
		}

		if (remove.getTopic() != null) {
			Topic topic = DataBase.getTopics().get(remove.getTopic());
			topic.getFollows().remove(remove);
			postTopic(userName, client);
		}
		post(userName, client);
		return;

	}

	@Override
	public void desabonner(String deteste) throws RemoteException {
		// TODO Auto-generated method stub
		String userName = ca.getUserName();
		User client = DataBase.getDataBase().get(userName);
		User del = DataBase.getDataBase().get(deteste);

		client.getAbonners().remove(del);
		del.getAbonnement().remove(client);

		for (int i = 0; i < client.getInteresse().size();) {
			if (client.getInteresse().get(i).getEditer()
					.equals(del.getUserName())) {
				client.getInteresse().remove(i);
				continue;
			}
			i++;
		}
		for (int j = 0; j < ServeurPublicImpl.cas.size(); j++) {
			if (ServeurPublicImpl.cas.get(j).isConnecte() == true) {
				User meme = DataBase.getDataBase().get(
						ServeurPublicImpl.cas.get(j).getUserName());
				if (meme.getUserName().equals(userName)) {
					ServeurPublicImpl.cas.get(j).refreshList();
				}
			}
		}

		return;
	}

	/*
	 * @Override public void desinscrire() throws RemoteException { // TODO
	 * Auto-generated method stub String userName = ca.getUserName(); User
	 * client = DataBase.getDataBase().get(userName);
	 * 
	 * if (client != null) { for (int i = 0; i < DataBase.getTweets().size();) {
	 * Tweeter temp = DataBase.getTweets().get(i); if
	 * (temp.getEditer().equals(userName)) { if (temp.getTopic() != null) {
	 * DataBase.getTopics().get(temp.getTopic()).getFollows() .remove(temp); }
	 * DataBase.getTweets().remove(i); continue; }
	 * 
	 * i++; }
	 * 
	 * for (int i = 0; i < client.getAbonnement().size(); i++) { for (int j = 0;
	 * j < client.getAbonnement().get(i) .getInteresse().size();) { if
	 * (client.getAbonnement().get(i).getInteresse().get(j)
	 * .getEditer().equals(userName)) {
	 * client.getAbonnement().get(i).getInteresse().remove(j); continue; } j++;
	 * } } postTopic(userName, client); post(userName, client);
	 * DataBase.getDataBase().remove(userName); return; } throw new
	 * RemoteException(); }
	 */

	private void postTopic(String userName, User client) throws RemoteException {
		// TODO Auto-generated method stub
		for (int j = 0; j < ServeurPublicImpl.cas.size(); j++) {

			if (ServeurPublicImpl.cas.get(j).isConnecte() == true) {
				ServeurPublicImpl.cas.get(j).refreshTopics();
			}
		}
	}

	@Override
	public Stack<Tweeter> listerFollows(String topic) throws RemoteException {
		// TODO Auto-generated method stub
		return DataBase.getTopics().get(topic).getFollows();
	}

	@Override
	public List<String> listerTopic() throws RemoteException {
		// TODO Auto-generated method stub
		ArrayList<String> topics = new ArrayList<String>();
		Iterator<Entry<String, Topic>> iter = DataBase.getTopics().entrySet()
				.iterator();
		while (iter.hasNext()) {
			Entry<String, Topic> entry = (Entry<String, Topic>) iter.next();
			String key = (String) entry.getKey();
			topics.add(key);
		}
		return topics;

	}
}
