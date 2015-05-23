package rmi;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import persistence.DataBase;
import persistence.Topic;
import persistence.User;

public class Serveur {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//
//		User duan = new User("duan", "123");
//		User zhou = new User("zhou", "123");
//		User fangfang = new User("fangfang", "123");
//
//		duan.getAbonners().push(zhou);
//		zhou.getAbonnement().push(duan);
//
//		zhou.getAbonners().push(duan);
//		duan.getAbonnement().push(zhou);
//
//		Topic topic = new Topic("guerre");
//		DataBase.getTopics().put("guerre", topic);
//		// User visiteur = new User();
//		DataBase.getDataBase().put(duan.getUserName(), duan);
//		DataBase.getDataBase().put(zhou.getUserName(), zhou);
//		DataBase.getDataBase().put(fangfang.getUserName(), fangfang);
//
//		// DataBase.getDataBase().put(visiteur.getUserName(),visiteur);
//		Tweeter t1 = new Tweeter("je viens de manger", duan.getUserName());
//		Tweeter t4 = new Tweeter("je vais dormir", fangfang.getUserName());
//		Tweeter t2 = new Tweeter("je vais manger", zhou.getUserName());
//		Tweeter t3 = new Tweeter("je viens de me lever", duan.getUserName());
//		Tweeter t5 = new Tweeter("je viens de me lever", fangfang.getUserName());
//		Tweeter t6 = new Tweeter("je viens de me lever", fangfang.getUserName());
//
//		DataBase.getTweets().push(t1);
//		DataBase.getTweets().push(t4);
//		DataBase.getTweets().push(t2);
//		DataBase.getTweets().push(t3);
//		DataBase.getTweets().push(t5);
//		DataBase.getTweets().push(t6);
//
//		duan.getInteresse().push(t1);
//		zhou.getInteresse().push(t1);
//
//		zhou.getInteresse().push(t2);
//		duan.getInteresse().push(t2);
//
//		zhou.getInteresse().push(t3);
//		duan.getInteresse().push(t3);
//
//		fangfang.getInteresse().push(t4);
//		fangfang.getInteresse().push(t5);
//		fangfang.getInteresse().push(t6);
//
//		ServeurPublic serveur_public = null;
//
//		try {
//			serveur_public = new ServeurPublicImpl();
//		} catch (RemoteException e) {
//			e.printStackTrace();
//		}
//		try {
//			Registry registry = LocateRegistry.createRegistry(2004);
//			System.out.println("on a lances au registry ecoutant sur 2004");
//			registry.rebind("Serveur", serveur_public);
//
//		} catch (RemoteException e) {
//			e.printStackTrace();
//		}
//		System.out.println("Attente d invocation, Ctrl-C pour stopper!");
	}
}
