package webservice;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server {

	public static void main(String[] agrs) throws RemoteException {

		WebService webservice = new WebServiceImpl();
//		WebService stub = (WebService) UnicastRemoteObject.exportObject(webservice, 9000);
		Registry registry = LocateRegistry.createRegistry(8000);
		registry.rebind("rmi://localhost:8000/webService", webservice);
		System.out.println("Server ready");

	}

}
