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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


import rmi.Client;
import rmi.Tweeter;

public class MainWindow extends JFrame {

	/**
	 * 
	 */
	private Client c;
	private JPanel panel;
	private JPanel bt_panel;
	private JTextField contenu;
	private Topic top;
	private static final long serialVersionUID = 1L;

	public Client getC() {
		return c;
	}

	public void setC(Client c) {
		this.c = c;
	}

	public MainWindow(Client client) throws RemoteException {
		// TODO Auto-generated constructor stub
		this.c = client;
		this.c.setMw(this);
		
		setTitle("bienvenu "+this.c.getUserName());
		setSize(400, 400);
        JPanel aj_panel = new JPanel(new GridLayout(0, 1));
        contenu = new JTextField();
        aj_panel.add(contenu);
        makeButton("publier",aj_panel);
        
		panel = new JPanel();
		panel.setLayout(new GridLayout(0, 1));

		Stack<Tweeter> list = c.getService().listerAbonnement();
		refresh(list);
		this.getContentPane().setLayout(new BorderLayout());
		this.getContentPane().add(aj_panel, BorderLayout.NORTH);
		this.getContentPane().add(panel, BorderLayout.CENTER);

		bt_panel = new JPanel();
		GridLayout bt_gridLayout = new GridLayout(1, 3);
		bt_panel.setLayout(bt_gridLayout);
		makeButton("deconnecter");
		makeButton("abonner");
		//makeButton("desinscrire");
		makeButton("sujet");
		this.getContentPane().add(bt_panel, BorderLayout.SOUTH);
		setVisible(true);
		
		addWindowListener(new WindowAdapter() {

			public void windowClosing(WindowEvent e) {
				super.windowClosing(e);
				if(top!=null)
				top.dispose();
				try {
					c.getServeur().remotedeconnecter(c);
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
//				c.setServeur(null);
//				c.setService(null);
//				c.setMw(null);
//				c.setTopic(null);
//				c.setConnecter(false);
//				c = null;
				System.exit(0);
			}
		});
	}

	private void makeButton(final String name) {
		JButton bt = new JButton(name);
		bt_panel.add(bt);
		bt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				/*if(name.equals("desinscrire"))
				{
					c.setConnecter(false);
					try {
						c.getService().desinscrire();
						Portail nouPort = new Portail(c);
						c.setPortail(nouPort);
						c.setService(null);
						c.setMw(null);
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					dispose();
				}*/
				// TODO Auto-generated method stub
				if (name.equals("deconnecter")) {
               
             
                 c.setConnecter(false);
                 try {
					Portail nouPort = new Portail(c);
					if(top!=null)
					top.dispose();			
					c.setPortail(nouPort);
					c.setService(null);
					c.setMw(null);
					c.setTopic(null);
					
			
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                 dispose();
				}
				if (name.equals("abonner")) {
                setEnabled(false);
                Abonner a = new Abonner(MainWindow.this);
				}
				if (name.equals("sujet")) {
	                //setEnabled(false);
	                //Topic t = null;
					try {
						top = new Topic(c);
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	                c.setTopic(top);
					}
			}

		});
	}

	private void makeButton(final String name, JPanel panel) {
		JButton bt = new JButton(name);
		panel.add(bt);
		bt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if (name.equals("publier")) {
					try {
						String text = contenu.getText();
						String[] matchers = text.split("#");
					    
						if(text.equals(""))
						{
							JOptionPane.showMessageDialog(null,"votre tweet ne peut pas être vide");
							return;
						}
						if(matchers.length!=3)
						c.getService().publier(text);
						else
						c.getService().publier(text, matchers[1]);
						contenu.setText("");
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

		});
	}
	private void makeButton(final String name, JPanel panel, final int id) {
		JButton bt = new JButton(name);
		panel.add(bt);
		bt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if (name.equals("supprimer")) {
					try {
						c.getService().supprimer(id);
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

		});
	}
	
	private void makeButton(final String name, JPanel panel, final String deteste) {
		JButton bt = new JButton(name);
		panel.add(bt);
		bt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if (name.equals("desabonner")) {
					try {
						c.getService().desabonner(deteste);
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

		});
	}

	public void refresh(Stack<Tweeter> listerAbonnement) throws RemoteException // changer
																				// la
																				// method
																				// getUserName
	{

		// TODO Auto-generated method stub
		panel.removeAll();
		for (int i = listerAbonnement.size()-1; i >=0; i--) {
			JLabel tw_label = new JLabel(listerAbonnement.get(i).getEditer()
					+ ": " + listerAbonnement.get(i).getContenu() + "  id: "
					+ listerAbonnement.get(i).getId());
			JPanel tw_panel = new JPanel(new GridLayout(2, 1));
			JPanel tw_bt_panel;

			tw_panel.add(tw_label);
			tw_bt_panel = new JPanel(new GridLayout(1, 1));
			if (listerAbonnement.get(i).getEditer().equals(c.getUserName())) {

				makeButton("supprimer", tw_bt_panel, listerAbonnement.get(i)
						.getId());
			} else {

				makeButton("desabonner", tw_bt_panel, listerAbonnement.get(i)
						.getEditer());

			}

			tw_panel.add(tw_bt_panel);

			panel.add(tw_panel);
		}
		panel.repaint();
		this.validate();
	}

}
