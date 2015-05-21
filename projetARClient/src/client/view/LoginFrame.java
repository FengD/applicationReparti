package client.view;

import java.awt.Container;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class LoginFrame extends JFrame{
	
	private JTextField username;
	private JPasswordField password;
	
	private JLabel label1;
	private JLabel label2;
	private JLabel label3;
	private JLabel label4;
	
	private JButton login;
	private JButton register;
	
	public LoginFrame(){
		this.setTitle("Twitter Login");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		init();
		this.setLayout(null);
		this.setBounds(0, 0, 355, 250);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
	}
	
	private void init(){
		Container con = this.getContentPane();
		label1 = new JLabel();
		label1.setBounds(0, 0, 355, 250);
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
        
        label1.add(label2);
        label1.add(label3);
        label1.add(label4);
        label1.add(login);
        label1.add(register);
        con.add(label1);
        con.add(username);
        con.add(password);
	}
	
	public void dispaly(){
		this.setVisible(true);
	}
	
	public void close(){
		this.dispose();
	}
	
	public static void main(String[] agrs){
		LoginFrame loginFrame = new LoginFrame();
		loginFrame.dispaly();
	}

}
