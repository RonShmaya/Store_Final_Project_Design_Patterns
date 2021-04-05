package Commands;

import View.MainView;
import View.ViewOption;

public class CommandOptionView implements Command { // this concrete command using in mainView (for all options view)
	public ViewOption op;
	public MainView mView;

	public CommandOptionView(ViewOption op, MainView mView) {
		this.op = op;
		this.mView = mView;
	}

	@Override
	public void execute() { // we override in mainView

	}

}
