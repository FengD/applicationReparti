package rmi;
import java.util.Iterator;
import java.util.Map.Entry;
import persistence.DataBase;
import persistence.User;

public class LoginModule {
   
     private String userName;
     private String password;

	public LoginModule(String userName,String password) throws Exception
     {
    	 this.userName = userName;
    	 this.password = password;
    	 login();
     }
     private void login() throws Exception
     {
        Iterator<Entry<String, User>> iter = DataBase.getDataBase().entrySet().iterator();
        while(iter.hasNext())
        {
        	Entry<String, User> entry=iter.next();
        	User client = entry.getValue();
        	String bd_userName = client.getUserName();
        	String bd_password = client.getMdp();
            if(this.userName.equals(bd_userName)&&this.password.equals(bd_password))
            {
            	return;
            }
        }
        throw new Exception();
     }
     
}
