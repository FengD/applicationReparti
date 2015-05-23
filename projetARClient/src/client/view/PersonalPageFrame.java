package client.view;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowListener;
import java.rmi.RemoteException;

import javax.swing.JFrame;

import com.sun.glass.events.WindowEvent;

import client.controller.ClientController;

public class PersonalPageFrame extends PersonalPageView {

	private JFrame frame;
	private NewsPanel newsPanel;
	private UserInfoPanel userInfoPanel;

	public PersonalPageFrame(ClientController tc) {
		frame = new JFrame();
		frame.setBounds(0, 0, 1000, 500);
		setController(tc);
		frame.setTitle("Welcome");
		// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(java.awt.event.WindowEvent e) {
				try {
					getController().deconnect(getController().getUserName());
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.exit(0);
			}
		});

		buildFrame();
		frame.setLocationRelativeTo(null);
	}

	public void buildFrame() {
		newsPanel = NewsPanel.getNewsPanel();
		newsPanel.setController(getController());
		userInfoPanel = UserInfoPanel.getUserInfoPanel();
		userInfoPanel.setController(getController());
		frame.add(userInfoPanel, BorderLayout.WEST);
		frame.add(newsPanel);
	}

	@Override
	public void display() {
		frame.setVisible(true);
	}

	@Override
	public void close() {
		frame.dispose();
	}

	@Override
	public void setNews(String news) {
		newsPanel.setNewsLabel(news);
	}

	@Override
	public void setUserInfo(String name) {
		userInfoPanel.setUserInfo(name);
		frame.setTitle(frame.getTitle() + " " + name);
	}

}
