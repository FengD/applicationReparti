package client.controller;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientAction extends Remote{
	public String getPassword() throws RemoteException;

	public String getUserName() throws RemoteException;

	public void refreshList() throws RemoteException;

	public void refreshListTout() throws RemoteException;

	public boolean isConnecte() throws RemoteException;

	public void refreshTopics() throws RemoteException;

}
