package client.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.text.View;

import client.controller.ClientController;

public class NewsPanel extends JPanel{
	
	private static NewsPanel single = null;
	
	private JButton followButton, tweetButton;
	private JTextField tweetMessage;
	private JPanel viewNewsP,tweetP, followP;
	private JScrollPane newsP;
	private JComboBox usersBox;
	private DefaultComboBoxModel usersBoxModel;
	private JLabel newsLabel;
	
	private ClientController controller;
	
	
	private NewsPanel(){
		buildPanel();
	}
	
	public void setNewsLabel(String news){
		viewNewsP.add(new JLabel(news));
		newsP.getViewport().add(viewNewsP);
//		newsLabel.setText(newsLabel.getText() + news);
	}
	
	private void buildPanel(){
		followButton = new JButton("FOLLOW");
		tweetButton = new JButton("TWEET");
		tweetMessage = new JTextField();
		newsLabel = new JLabel();
		tweetP = new JPanel();
		
		viewNewsP = new JPanel();
		viewNewsP.setLayout(new BoxLayout(viewNewsP, BoxLayout.Y_AXIS));
		newsP = new JScrollPane(viewNewsP);
		
		followP = new JPanel();
		String[] s = {};
		usersBoxModel = new DefaultComboBoxModel(s);
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
		
		followButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					controller.getService().addFollowing((String)usersBox.getSelectedItem(), controller);
					
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
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
	
	public void setUserBox(Object[] usersName){
		usersBoxModel.removeAllElements();
		for(int i = 0;i < usersName.length;i++){
			usersBoxModel.addElement(usersName[i]);
		}
	}
	
	public void addNewUser(String newUser){
		usersBoxModel.addElement(newUser);
	}

}
