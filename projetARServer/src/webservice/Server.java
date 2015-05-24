package webservice;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server {

	private static String host="localhost";//"192.168.43.219";
	
	public static void main(String[] agrs) throws RemoteException {

//		if (System.getSecurityManager() == null) 
//		{
//		    System.setSecurityManager(new java.rmi.RMISecurityManager());
//		}
		WebService webservice = new WebServiceImpl();
		Registry registry = LocateRegistry.createRegistry(8000);
		registry.rebind("rmi://"+host+":8000/webService", webservice);
		System.out.println("Server ready");
	}
}
