package client.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

import client.controller.ClientController;

public class NewsPanel extends JPanel{
	
	private static NewsPanel single = null;
	
	private JButton followButton, tweetButton;
	private JTextField tweetMessage;
	private JPanel newsP, tweetP, followP;
	private JComboBox usersBox;
	private DefaultComboBoxModel usersBoxModel;
	
	private ClientController controller;
	
	
	private NewsPanel(){
		buildPanel();
	}
	
	private void buildPanel(){
		followButton = new JButton("FOLLOW");
		tweetButton = new JButton("TWEET");
		tweetMessage = new JTextField();
		tweetP = new JPanel();
		newsP = new JPanel();
		followP = new JPanel();
		String[] test = {"1","2","3"};
		usersBoxModel = new DefaultComboBoxModel(test);
		usersBox = new JComboBox(usersBoxModel);
		
		this.setLayout(new BorderLayout());
		this.add(tweetP, BorderLayout.NORTH);
		this.add(followP, BorderLayout.SOUTH);
		this.add(newsP);
		
		tweetP.setLayout(new BorderLayout());
		tweetP.add(tweetMessage);
		tweetP.add(tweetButton, BorderLayout.EAST);
		
		followP.setLayout(new BorderLayout());
		followP.add(usersBox);
		followP.add(followButton, BorderLayout.EAST);
		
		tweetButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					controller.getService().writeTweet(controller.getUserName(), controller.getUserName(), tweetMessage.getText());
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
		});
	}
	
	public void setController(ClientController controller){
		this.controller = controller;
	}
	
	public static NewsPanel getNewsPanel(){
		if(single == null){
			single = new NewsPanel();
		}
		return single;
	}

}
