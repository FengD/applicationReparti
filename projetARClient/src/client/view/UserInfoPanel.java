package client.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class UserInfoPanel extends JPanel {

	private static UserInfoPanel single = null;

	private JLabel userName, tweets, following, followers;
	private JLabel tweetsN, followingN, followersN;
	private JPanel userInfoP, userNameP, infoP, tweetsP, followingP,
			followersP, topicP;
	private JLabel[] topics;

	private UserInfoPanel() {
		buildPanel();
	}

	private void buildPanel() {
		userName = new JLabel("SHI Tianhao");
		tweets = new JLabel("TWEETS");
		following = new JLabel("FOLLOWING");
		followers = new JLabel("FOLLOWERS");
		tweetsN = new JLabel("1");
		followingN = new JLabel("15");
		followersN = new JLabel("2");
		userInfoP = new JPanel();
		userNameP = new JPanel();
		infoP = new JPanel();
		tweetsP = new JPanel();
		followingP = new JPanel();
		followersP = new JPanel();
		topicP = new JPanel();
		this.setLayout(new BorderLayout());

		this.add(userInfoP, BorderLayout.NORTH);
		this.add(topicP);

		userInfoP.add(userNameP);
		userInfoP.add(infoP);
		userInfoP.setLayout(new GridLayout(0, 1));

		userNameP.add(userName);

		infoP.add(tweetsP);
		infoP.add(followingP);
		infoP.add(followersP);
		infoP.setLayout(new GridLayout(1, 0));

		tweetsP.add(tweets);
		tweetsP.add(tweetsN);
		tweetsP.setLayout(new GridLayout(0, 1));

		followingP.add(following);
		followingP.add(followingN);
		followingP.setLayout(new GridLayout(0, 1));

		followersP.add(followers);
		followersP.add(followersN);
		followersP.setLayout(new GridLayout(0, 1));

		topics = new JLabel[10];
		for (int i = 0; i < 10; i++) {
			topics[i] = new JLabel("topic" + i);
		}

		for (int i = 0; i < 10; i++) {
			topicP.add(topics[i]);
		}

		topicP.setLayout(new GridLayout(0, 1));
	}

	public static UserInfoPanel getUserInfoPanel() {
		if (single == null) {
			single = new UserInfoPanel();
		}
		return single;
	}

}
