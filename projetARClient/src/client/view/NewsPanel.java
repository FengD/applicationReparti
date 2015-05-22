package client.view;

import javax.swing.JPanel;

public class NewsPanel extends JPanel{
	
	private static NewsPanel single = null;
	
	private NewsPanel(){
		buildPanel();
	}
	
	private void buildPanel(){
		
	}
	
	public static NewsPanel getNewsPanel(){
		if(single == null){
			single = new NewsPanel();
		}
		return single;
	}

}
