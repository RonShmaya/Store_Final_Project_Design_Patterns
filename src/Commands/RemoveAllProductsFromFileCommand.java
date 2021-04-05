package Commands;

import View.ViewOption8RemoveAllProductsFromFile;

public class RemoveAllProductsFromFileCommand implements Command {
	private ViewOption8RemoveAllProductsFromFile op;

	public RemoveAllProductsFromFileCommand(ViewOption8RemoveAllProductsFromFile op) {
		this.op = op;
	}

	@Override
	public void execute() {
		this.op.viewAskForDeleteAllProducts();

	}

}
