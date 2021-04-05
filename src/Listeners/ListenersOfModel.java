package Listeners;

public interface ListenersOfModel {
	void modelUpdateViewMsg(String msg);

	void modelUpdateViewStatusCommand(boolean isSucceded);

	void modelUpdateViewIfOpenFile(String msg);
}
