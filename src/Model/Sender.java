package Model;

import java.util.ArrayList;
import java.util.List;

public class Sender {
	private static Sender _instance = new Sender();
	private List<ClientObserver> observers;
	private List<String> reciversNames;

	private Sender() {
		observers = new ArrayList<>();
		reciversNames = new ArrayList<>();
	}

	public void addObserver(ClientObserver ob) {
		observers.add(ob);
	}

	public void sendMsgToObserver(String msg) { 
		for (ClientObserver client : observers)
			client.reciveMsg(this, msg);	// (update)
	}

	public void getRecive(String name) {
		reciversNames.add(name);	// reciver get name of client that get the message
	}

	public List<String> reciversList() {
		return reciversNames;	//all names of clients who get the message
	}

	public void reset() {
		observers.clear();
		reciversNames.clear();
	}

	public static Sender getInstance() {
		return _instance;
	}

}
