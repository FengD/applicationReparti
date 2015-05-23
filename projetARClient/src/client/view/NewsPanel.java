package client.view;

import java.awt.BorderLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class NewsPanel extends JPanel{
	
	private static NewsPanel single = null;
	
	private JButton followButton, tweetButton;
	private JTextField tweetMessage;
	private JPanel newsP, tweetP, followP;
	private JComboBox usersBox;
	private DefaultComboBoxModel usersBoxModel;
	
	
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
	}
	
	public static NewsPanel getNewsPanel(){
		if(single == null){
			single = new NewsPanel();
		}
		return single;
	}

}
