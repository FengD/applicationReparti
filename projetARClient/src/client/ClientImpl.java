package client;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import stub.WebService;

public class ClientImpl extends UnicastRemoteObject implements Client, Runnable{

	private static final long serialVersionUID = 1L;
	
	WebService webService;

	protected ClientImpl() throws RemoteException {
		super();
	}

	@Override
	public void run() {
		try {
			System.out.println("Start get registry");
			Registry registry = LocateRegistry.getRegistry("localhost", 8000);
			System.out.println("end get registry");
			webService = (WebService) registry
					.lookup("rmi://localhost:8000/webService");
			System.out.println("end look up");
			System.out.println(signUp("toto", "tot"));
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	@Override
	public boolean signUp(String name, String pwd) throws RemoteException {
		return webService.register(name, pwd);
	}

	@Override
	public boolean signIn(String name, String pwd) throws RemoteException {
		return webService.connect(name, pwd);
	}

}
