package Commands;

import View.ViewOption3SearchProduct;
import javafx.scene.paint.Color;

public class SearchProductCommand implements Command {
	private ViewOption3SearchProduct op;

	public SearchProductCommand(ViewOption3SearchProduct op) {
		this.op = op;
	}

	@Override
	public void execute() {
		try {
			String mkt = op.getMktField().getText();
			if (mkt.equals(""))
				throw new Exception("There is'nt Empty MKT");
			op.viewSendModelMktToSearch(mkt); // search mkt in model, model with update automatically
			if (op.getIsSucceeded()) {
				op.getMsgUser().setText(op.getMsgUser().getText().concat("\nFound Succeeded")); // add succeeded to
																								// string
				op.getMsgUser().setTextFill(Color.BLACK);
			}

		} catch (Exception e) {
			op.getMsgUser().setText("Exception ---> " + e.getMessage());
		}

	}
}
