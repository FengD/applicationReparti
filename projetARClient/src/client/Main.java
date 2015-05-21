package client;

import java.rmi.RemoteException;

public class Main {
	public static void main(String[] args) {
		if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
		System.out.println("fini security");
		Thread c1;
		try {
			c1 = new Thread(new ClientImpl());
			c1.start();
			c1.join();
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
