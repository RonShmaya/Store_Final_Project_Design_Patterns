package Commands;

import Model.Sender;
import View.ViewOption10SendMessegetoCustomers;

public class SendMessegetoCustomersCommand implements Command {
	private ViewOption10SendMessegetoCustomers op;

	public SendMessegetoCustomersCommand(ViewOption10SendMessegetoCustomers op) {
		this.op = op;
	}

	@Override
	public void execute() {
		this.op.updateClientsWantGetMsg(); // model will update Sender who want to listen
		Sender sender = Sender.getInstance();
		sender.sendMsgToObserver(this.op.getMsgField().getText()); // sender send msg to all clients
		this.op.getShowReciversNames().setDisable(false);
	}

}
