package webservice;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server {

	private static String host="127.0.0.1";
	
	public static void main(String[] agrs) throws RemoteException {

//		if (System.getSecurityManager() == null) 
//		{
//		    System.setSecurityManager(new java.rmi.RMISecurityManager());
//		}
		WebService webservice = new WebServiceImpl();
//		WebService stub = (WebService) UnicastRemoteObject.exportObject(webservice, 9000);
		Registry registry = LocateRegistry.createRegistry(8000);
		registry.rebind("rmi://"+host+":8000/webService", webservice);
//		registry.rebind("rmi://192.168.1.87:8000/activemq", obj);
		System.out.println("Server ready");
	}
}
