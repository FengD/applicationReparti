package userInterface;

import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.rmi.RemoteException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import rmi.Client;
import rmi.Service;

public class LoginOUInscrire extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Client c;
	private Portail port;
	private JPanel bt_panel;
	private JTextField txt_user;
	private JTextField txt_mdp;

	public LoginOUInscrire(final Portail p, String title) {
		this.port = p;
		this.c = p.getC();
		setTitle(title);
		setSize(300, 300);
		GridLayout gridLayout = new GridLayout(2, 2);
		JPanel panel = new JPanel();
		panel.setLayout(gridLayout);
		JLabel userName = new JLabel("userName");
		JLabel passWord = new JLabel("password");
		panel.add(userName);
		txt_user = new JTextField();
		panel.add(txt_user);
		panel.add(passWord);
		txt_mdp = new JTextField();
		panel.add(txt_mdp);
		this.getContentPane().setLayout(new GridLayout(2, 1));
		this.getContentPane().add(panel);

		bt_panel = new JPanel();
		GridLayout bt_gridLayout = new GridLayout(1, 2);
		bt_panel.setLayout(bt_gridLayout);

		makeButton(title);

		makeButton("cancel");

		this.getContentPane().add(bt_panel);
		setVisible(true);
		addWindowListener(new WindowAdapter() {

			public void windowClosing(WindowEvent e) {
				super.windowClosing(e);
				
				p.setEnabled(true);
			}

		});
	}

	private void makeButton(final String name) {
		JButton bt = new JButton(name);
		bt_panel.add(bt);
		bt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(name.equals("cancel"))
				{
					txt_user.setText("");
					txt_mdp.setText("");
				}
			
				if (name.equals("inscrire")) {
					c.setUserName(txt_user.getText());
					c.setPassword(txt_mdp.getText());
					try {
						if (c.getServeur().inscrire(c)) {
							dispose();
							port.setEnabled(true);
						} else {
							JOptionPane.showMessageDialog(null,
									"le peudo exist déjà");
						}
					} catch (HeadlessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if (name.equals("connecter")) {
					c.setUserName(txt_user.getText());
					c.setPassword(txt_mdp.getText());
					try {
						Service s = c.getServeur().logon(c);
						if(s!=null)
						c.setService(s);
						else
						{
						JOptionPane.showMessageDialog(null,
								"ce client ne exist pas");
					    return;
						}
						c.setConnecter(true);
						dispose();
						MainWindow mw = new MainWindow(port.getC());
						port.dispose();

					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

		});
	}

}
