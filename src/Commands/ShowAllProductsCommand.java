package Commands;

import View.ViewOption4ShowAllProduct;
import javafx.scene.paint.Color;

public class ShowAllProductsCommand implements Command {
	private ViewOption4ShowAllProduct op;

	public ShowAllProductsCommand(ViewOption4ShowAllProduct op) {
		this.op = op;
	}

	@Override
	public void execute() {
		this.op.viewAskModelProductList();
		this.op.getMsgUser().setTextFill(Color.BLACK);
	}
}
