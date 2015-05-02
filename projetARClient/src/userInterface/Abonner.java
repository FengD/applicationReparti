package userInterface;

import java.awt.GridLayout;
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

public class Abonner extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MainWindow mw;
	private Client c;
	private JTextField txt_nom;
	private JPanel bt_panel;

	public Abonner(final MainWindow parent) {
		this.mw = parent;
		c = parent.getC();
		setTitle("Abonner");
		setSize(300, 300);
		GridLayout gridLayout = new GridLayout(1, 1);
		JPanel panel = new JPanel();
		panel.setLayout(gridLayout);
		JLabel sonNom = new JLabel("nom:");
		panel.add(sonNom);
		txt_nom = new JTextField();
		panel.add(txt_nom);

		this.getContentPane().setLayout(new GridLayout(2, 1));
		this.getContentPane().add(panel);

		bt_panel = new JPanel();
		GridLayout bt_gridLayout = new GridLayout(1, 2);
		bt_panel.setLayout(bt_gridLayout);

		makeButton("abonner");
		makeButton("cancel");

		this.getContentPane().add(bt_panel);

		setVisible(true);
		addWindowListener(new WindowAdapter() {

			public void windowClosing(WindowEvent e) {
				super.windowClosing(e);

				parent.setEnabled(true);
			}

		});
	}

	private void makeButton(final String name) {
		// TODO Auto-generated method stub
		JButton bt = new JButton(name);
		bt_panel.add(bt);
		bt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(name.equals("cancel"))
				{
					txt_nom.setText("");
				}
					
				
				if (name.equals("abonner")) {
					try {
                       
						String nom = txt_nom.getText();
						if(mw.getC().getUserName().equals(nom))
						{
							JOptionPane.showMessageDialog(null,
									"vous ne pouvez pas ajouter vous même");
							return;
						}
						if (c.getService().abonner(txt_nom.getText())) {
							dispose();
							mw.setEnabled(true);
						} 
						else {
							JOptionPane.showMessageDialog(null,
				     			"ce client ne exist pas");
						}

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
