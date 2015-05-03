package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Stack;

public interface ServeurPublic extends Remote {
	public Service logon(ClientActive ca) throws RemoteException;

	public Stack<Tweeter> listerTout(ClientActive ca) throws RemoteException;

	public boolean inscrire(ClientActive ca) throws RemoteException;

	public void remotedeconnecter(ClientActive ca) throws RemoteException;
}
