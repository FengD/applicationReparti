package webservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import db.Topic;

public interface WebService extends Remote{
	public boolean register(String name, String pwd) throws RemoteException;
	public Service login(String name, String pwd) throws RemoteException;
	public boolean disconnect() throws RemoteException;
}
