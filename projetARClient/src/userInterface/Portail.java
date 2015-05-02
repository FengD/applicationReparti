package userInterface;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.rmi.RemoteException;
import java.util.Stack;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import rmi.Client;
import rmi.Tweeter;

public class Portail extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Client c;
	public Client getC() {
		return c;
	}



	public void setC(Client c) {
		this.c = c;
	}

    private JPanel panel;
    private JPanel bt_panel;
	
	
	
	public Portail(Client client) throws RemoteException
	{
		// System.out.println("dfdfdsfsdf");
		this.c = client;
		setTitle("bienvenu");
		setSize(400,400);
		panel = new JPanel(new GridLayout(0,1)); 
		// System.out.println("dfdfdsfsdf");
	    Stack<Tweeter> list = c.getServeur().listerTout(c);	
	   // System.out.println("dfdfdsfsdf");
	    refresh(list);
	    this.getContentPane().setLayout(new BorderLayout());
	    this.getContentPane().add(panel,BorderLayout.CENTER);
	    
	    bt_panel = new JPanel();
	    GridLayout bt_gridLayout = new GridLayout(1,2);
	    bt_panel.setLayout(bt_gridLayout);
	    
	    makeButton("connecter");
	    makeButton("inscrire");
	    
	    this.getContentPane().add(bt_panel,BorderLayout.SOUTH);
	    setVisible(true);
		addWindowListener(new WindowAdapter() {

			public void windowClosing(WindowEvent e) {
				super.windowClosing(e);
				try {
					c.getServeur().remotedeconnecter(c);
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
//				c.setService(null);
//				c.setMw(null);
//				c.setTopic(null);
//				c.setConnecter(false);
//				c = null;
				System.exit(0);
			}
		});

	}



	public void refresh(Stack<Tweeter> listerTout) {
		// TODO Auto-generated method stub
	   
	    panel.removeAll();
	    for(int i =listerTout.size()-1;i>=0;i--)
	    {
	    JLabel tw_label = new JLabel(listerTout.get(i).getEditer()+": "+listerTout.get(i).getContenu()+"  id: "+ listerTout.get(i).getId());
	   // tw_label.setSize(80, 300);
	    panel.add(tw_label);
	    }
	    panel.repaint();
	    this.validate();
	}
  
	private void makeButton(final String name)
	{
		JButton bt = new JButton(name);
		bt_panel.add(bt);
		bt.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
			
					setEnabled(false);
					LoginOUInscrire l = new LoginOUInscrire(Portail.this,name);
		
				
			}
			
		});
	}
	
	
	
}
