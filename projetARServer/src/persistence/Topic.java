package persistence;

import java.util.Stack;

import rmi.Tweeter;

public class Topic {
	private String name;
	private Stack<Tweeter> follows;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Stack<Tweeter> getFollows() {
		return follows;
	}

	public void setFollows(Stack<Tweeter> follows) {
		this.follows = follows;
	}

	public Topic(String name) {
		this.name = name;
		follows = new Stack<Tweeter>();
	}
}
