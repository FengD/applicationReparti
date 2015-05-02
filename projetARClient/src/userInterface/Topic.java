package userInterface;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Stack;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import rmi.Client;
import rmi.Tweeter;

public class Topic extends JFrame implements ItemListener{
    
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JComboBox<Object> comboBox;
	private String topicSelected;
	private JPanel panel;
	private Client client;
	
	public Topic(final Client c) throws RemoteException {
		this.client = c;
		setTitle("bienvenu");
		setSize(400, 400);
		// TODO Auto-generated constructor stub
		panel = new JPanel(new GridLayout(0,1));
		this.getContentPane().add(panel,BorderLayout.CENTER);
		
		topicSelected="";
		comboBox = new JComboBox<Object>();
		comboBox.addItemListener(this);
		List<String> topics =c.getService().listerTopic();
		refreshTopics(topics);
		
		this.getContentPane().add(comboBox,BorderLayout.NORTH);
		
		addWindowListener(new WindowAdapter() {

			public void windowClosing(WindowEvent e) {
				super.windowClosing(e);
				c.setTopic(null);
				dispose();
			}

		});
		setVisible(true);
	}

	public String topicSelected() {
		// TODO Auto-generated method stub
		return topicSelected;
	}

	public void refreshFollows(Stack<Tweeter> listerFollows) {
		// TODO Auto-generated method stub
		    panel.removeAll();
		    for(int i =listerFollows.size()-1;i>=0;i--)
		    {
		    JLabel tw_label = new JLabel(listerFollows.get(i).getEditer()+": "+listerFollows.get(i).getContenu()+"  id: "+ listerFollows.get(i).getId());
		   // tw_label.setSize(80, 300);
		    panel.add(tw_label);
		    }
		    
		    panel.repaint();
		    this.validate();
	}

	public void refreshTopics(List<String> listerTopic) {
		// TODO Auto-generated method stub
		comboBox.removeAllItems();
	 for(int i =0;i< listerTopic.size();i++)
	 {
		 comboBox.addItem(listerTopic.get(i));
	 }
	 
	 comboBox.repaint();
	 this.validate();
	 comboBox.setSelectedItem(topicSelected);
	}

	@Override
	public void itemStateChanged(ItemEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getStateChange() == ItemEvent.SELECTED)
        {
			topicSelected=(String)comboBox.getSelectedItem();
                  if(!topicSelected.equals(""))
                  {
                     
                      try {
						refreshFollows(client.getService().listerFollows(topicSelected));
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                	
                  }
        }
	}

}
