package client.view;

import java.awt.Container;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import webservice.Service;
import client.controller.ClientController;

public class LoginFrame extends TweetsView {

	private JFrame frame;

	private JTextField username;
	private JPasswordField password;

	private JLabel label1;
	private JLabel label2;
	private JLabel label3;
	private JLabel label4;

	private JButton login;
	private JButton register;

	public LoginFrame(ClientController tc) {
		setController(tc);
		frame = new JFrame();
		frame.setTitle("Twitter Login");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		buildFrame();
		frame.setLayout(null);
		frame.setBounds(0, 0, 355, 300);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
	}

	private void buildFrame() {
		Container con = frame.getContentPane();
		label1 = new JLabel();
		label1.setBounds(0, 0, 355, 300);
		label4 = new JLabel();
		Image photo = new ImageIcon("resourse/photo.jpg").getImage();
		label4.setIcon(new ImageIcon(photo));
		label4.setBounds(40, 95, 60, 60);
		username = new JTextField();
		username.setBounds(100, 100, 150, 20);
		label3 = new JLabel("Username");
		label3.setBounds(260, 100, 100, 20);
		password = new JPasswordField();
		password.setBounds(100, 130, 150, 20);
		label2 = new JLabel("Password");
		label2.setBounds(260, 130, 70, 20);
		login = new JButton("Login");
		login.setBounds(250, 220, 95, 20);
		register = new JButton("Register");
		register.setBounds(5, 220, 95, 20);
		
		register.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				getController().closeLogin();
				getController().displayRegister();
			}
			
		});
		
		login.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					getController().setUserName(username.getText());
					getController().setPassword(password.getText());
					Service service;
					service = getController().login(getController());
					if( service != null){
						getController().setService(service);
						getController().closeLogin();
						getController().displayPersonalPage();
					} else {
						
					}
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		});

		label1.add(label2);
		label1.add(label3);
		label1.add(label4);
		label1.add(login);
		label1.add(register);
		con.add(label1);
		con.add(username);
		con.add(password);
	}

	@Override
	public void display() {
		frame.setVisible(true);

	}

	@Override
	public void close() {
		frame.dispose();

	}

}
