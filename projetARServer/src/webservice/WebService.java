package webservice;

import java.rmi.Remote;
import java.rmi.RemoteException;

import client.controller.ClientAction;

public interface WebService extends Remote{
	public boolean register(String name, String pwd) throws RemoteException;
	public Service login(ClientAction clientAction) throws RemoteException;
	public boolean disconnect(String name) throws RemoteException;
}
