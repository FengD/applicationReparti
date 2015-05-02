package rmi;



import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Stack;

import persistence.DataBase;
import persistence.User;

public class ServeurPublicImpl extends UnicastRemoteObject implements ServeurPublic{

 	
	protected ServeurPublicImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}
    public static ArrayList<ClientActive> cas = new ArrayList<ClientActive>();
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Service logon(ClientActive ca)
			throws RemoteException{
	    // chaque fois initialise un nouveau Service
		try
		{
		
			/*    for(int i=0;i<cas.size();i++)
			    {
			    	if(cas.get(i).isConnecte()&&cas.get(i).getUserName().equals(ca.getUserName())&&cas.get(i).getPassword().equals(ca.getPassword()))
			    	{
			    		return cas.get(i).getRemoteService();
			    	}
			    }*/
				LoginModule lm = new LoginModule(ca.getUserName(),ca.getPassword());
				Service service = new ServiceImpl(ca);  
				return service;
			
		
		}catch(RemoteException e)
		{
			//e.printStackTrace();
			return null;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			return null;
		}
		
		
	  
	}
	@Override
	public Stack<Tweeter> listerTout(ClientActive ca) throws RemoteException {
		// TODO Auto-generated method stub
		if(!cas.contains(ca))
		cas.add(ca);
		return DataBase.getTweets();
	}
	@Override
	public boolean inscrire(ClientActive ca) throws RemoteException {
		// TODO Auto-generated method stub
		String userName = ca.getUserName();
		String password = ca.getPassword();
		if(DataBase.getDataBase().containsKey(userName))
				return false;
		else
			DataBase.getDataBase().put(ca.getUserName(), new User(userName,password));
		return true;
	}
	@Override
	public void remotedeconnecter(ClientActive ca) throws RemoteException {
		// TODO Auto-generated method stub
		if(cas.contains(ca))
			cas.remove(ca);
	}

}
