package client.view;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
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

import client.controller.ClientController;

public class RegisterFrame extends TweetsView {
	private JFrame frame;

	private JTextField username;
	private JPasswordField password;

	private JLabel label1;
	private JLabel label2;
	private JLabel label3;
	private JLabel label4;
	private JLabel errorMessageLabel;

	private JButton register, back;

	public RegisterFrame(ClientController tc) {
		setController(tc);
		frame = new JFrame();
		frame.setTitle("Twitter Register");
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
		Image photo = new ImageIcon("resourse/logo.png").getImage();
		label4.setIcon(new ImageIcon(photo));
		label4.setBounds(60, 30, 230, 150);
		username = new JTextField();
		username.setBounds(150, 140, 150, 20);
		label3 = new JLabel("New Username");
		label3.setBounds(10, 140, 130, 20);
		password = new JPasswordField();
		password.setBounds(150, 170, 150, 20);
		label2 = new JLabel("New Password");
		label2.setBounds(10, 170, 130, 20);
		register = new JButton("Register");
		register.setBounds(250, 220, 95, 20);
		back = new JButton("Back");
		back.setBounds(5, 220, 95, 20);
		errorMessageLabel = new JLabel();
		errorMessageLabel.setBounds(150, 200, 150, 20);
		errorMessageLabel.setForeground(Color.red);

		makeButtonAction();

		label1.add(label2);
		label1.add(label3);
		label1.add(label4);
		label1.add(register);
		label1.add(back);
		label1.add(errorMessageLabel);
		con.add(username);
		con.add(password);
		con.add(label1);
	}
	
	private void makeButtonAction(){
		register.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (getController().register(username.getText(),
							password.getText())) {
						getController().closeRegister();
						getController().displayLogin();
					} else {
						errorMessageLabel.setText("User Already Exists");
					}
				} catch (RemoteException e1) {
					errorMessageLabel.setText("Error");
					e1.printStackTrace();
				}
			}

		});
		
		back.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				getController().closeRegister();
				getController().displayLogin();
			}
			
		});
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
