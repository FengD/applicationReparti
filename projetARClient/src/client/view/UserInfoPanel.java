package client.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import client.controller.ClientController;

public class UserInfoPanel extends JPanel {

	private static UserInfoPanel single = null;

	private JLabel userName, following, followers, photoLabel, stateLabel;
	private JLabel followingN, followersN;
	private JPanel userInfoP, userNameP, infoP, followingP, userPanelPhoto,
			followersP, topicP, buttonsP;
	private JLabel[] topics;
	private JButton deconnectButton;
	
	private ClientController controller;

	public UserInfoPanel(ClientController controller) {
		this.controller = controller;
		buildPanel();
	}

	private void buildPanel() {
		userName = new JLabel();
		userName.setForeground(Color.RED);
		userName.setFont(new Font("Georgia", Font.PLAIN, 20));
		Image photo = new ImageIcon("resourse/photo.jpg").getImage();
		photoLabel = new JLabel();
		photoLabel.setIcon(new ImageIcon(photo));
		following = new JLabel("FOLLOWING");
		followers = new JLabel("FOLLOWERS");
		stateLabel = new JLabel("State: Connected");
		followingN = new JLabel();
		followersN = new JLabel();
		userPanelPhoto = new JPanel();
		buttonsP = new JPanel();
		userInfoP = new JPanel();
		userNameP = new JPanel();
		infoP = new JPanel();
		followingP = new JPanel();
		followersP = new JPanel();
		topicP = new JPanel();
		deconnectButton = new JButton("DECONNECT");
		
		this.setLayout(new BorderLayout());

		this.add(userInfoP, BorderLayout.NORTH);
		this.add(topicP);
		this.add(buttonsP, BorderLayout.SOUTH);

		userInfoP.add(userPanelPhoto);
		userInfoP.add(infoP);
		userInfoP.setLayout(new GridLayout(0, 1));

		userPanelPhoto.setLayout(new BorderLayout());
		userPanelPhoto.add(photoLabel, BorderLayout.WEST);
		userPanelPhoto.add(userNameP);
		
		userNameP.setLayout(new GridLayout(0, 1));
		userNameP.add(userName);
		userNameP.add(stateLabel);

		infoP.add(followingP);
		infoP.add(followersP);
		infoP.setLayout(new GridLayout(1, 0));

		followingP.add(following);
		followingP.add(followingN);
		followingP.setLayout(new GridLayout(0, 1));
		followingP.setBorder(BorderFactory.createLineBorder(Color.black));

		followersP.add(followers);
		followersP.add(followersN);
		followersP.setLayout(new GridLayout(0, 1));
		followersP.setBorder(BorderFactory.createLineBorder(Color.black));
		
		buttonsP.add(deconnectButton);
		
		deconnectButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if(controller.deconnect(controller.getUserName())){
						controller.closePersonalPage();
						controller.displayLogin();
					}
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});

		topics = new JLabel[10];
		for (int i = 0; i < 10; i++) {
			topics[i] = new JLabel("topic" + i);
		}

		for (int i = 0; i < 10; i++) {
			topicP.add(topics[i]);
		}

		topicP.setLayout(new GridLayout(0, 1));
	}
	
	public void setUserInfo(String n){
		userName.setText("User Name: " + n);
	}
	
	public void setFollowings(String nbFollowings){
		followingN.setText(nbFollowings);
	}
	
	public void setFollowers(String nbFollowers){
		followersN.setText(nbFollowers);
	}
	
	

}
