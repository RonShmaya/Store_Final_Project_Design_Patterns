package Commands;

import View.ViewOption9Profits;
import javafx.scene.paint.Color;

public class GetProfitsCommand implements Command {
	private ViewOption9Profits op;

	public GetProfitsCommand(ViewOption9Profits op) {
		this.op = op;
	}

	@Override
	public void execute() {
		this.op.getProfits();
		this.op.getMsgUser().setTextFill(Color.BLACK);
	}

}
