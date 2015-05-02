package rmi;

import java.io.Serializable;



public class Tweeter implements Serializable{
/**
	 * 
	 */
private static final long serialVersionUID = 1L;
private String editer;
private String topic;
public String getTopic() {
	return topic;
}
public void setTopic(String topic) {
	this.topic = topic;
}
public String getEditer() {
	return editer;
}
public void setEditer(String editer) {
	this.editer = editer;
}
private String contenu;
public String getContenu() {
	return contenu;
}
public void setContenu(String contenu) {
	this.contenu = contenu;
}
private int id;
public int getId() {
	return id;
}
private static int Timer=0;
public Tweeter(String contenu,String editer)
{
	this.topic = null;
	this.editer = editer;
	this.contenu = contenu;
	this.id = Timer;
	Timer++;
}
}
