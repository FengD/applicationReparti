package rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import userInterface.Portail;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// TODO Auto-generated method stub

		Client c = null;

		ServeurPublic cs = null; // cs est un stub vers l objet remote, obtenu
									// par le lookup
		try {
			c = new Client();
			cs = (ServeurPublic) Naming.lookup("rmi://localhost"
					+ ":2004/Serveur");
			c.setServeur(cs);

			Portail p = new Portail(c);
			c.setPortail(p);
			// System.out.println("dfdfdsfsdf");

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}

	}
}
