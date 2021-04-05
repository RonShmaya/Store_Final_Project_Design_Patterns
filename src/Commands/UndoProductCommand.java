package Commands;

import View.ViewOption5UndoProduct;

public class UndoProductCommand implements Command {
	private ViewOption5UndoProduct op;

	public UndoProductCommand(ViewOption5UndoProduct op) {
		this.op = op;
	}

	@Override
	public void execute() {
		this.op.viewUpdateModelUndoProductUsingMemnto();
	}

}
