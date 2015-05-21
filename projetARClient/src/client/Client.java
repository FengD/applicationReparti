package client;

import java.rmi.RemoteException;

public interface Client {
	public boolean signUp(String name, String pwd) throws RemoteException;
	
	public boolean signIn(String name, String pwd) throws RemoteException;
}
