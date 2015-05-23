package client.view;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import client.controller.ClientController;


public class PersonalPageFrame extends TweetsView{
	
	private JFrame frame;
	private NewsPanel newsPanel;
	private UserInfoPanel userInfoPanel;

	public PersonalPageFrame(ClientController tc){
		frame = new JFrame();
		setController(tc);
		frame.setTitle("Hello Page");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		buildFrame();
	}
	
	public void buildFrame(){
		newsPanel = NewsPanel.getNewsPanel();
		userInfoPanel = UserInfoPanel.getUserInfoPanel();
		frame.add(userInfoPanel,BorderLayout.WEST);
		frame.add(newsPanel);
		frame.pack();
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
