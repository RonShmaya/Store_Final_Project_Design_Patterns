package Commands;

import View.ViewOption7RemoveProductFromFile;
import javafx.scene.paint.Color;

public class RemoveProductFromFileCommand implements Command {
	private ViewOption7RemoveProductFromFile op;

	public RemoveProductFromFileCommand(ViewOption7RemoveProductFromFile op) {
		this.op = op;
	}

	@Override
	public void execute() {
		try {
			op.getMsgUser().setText("");
			op.getMapDataMsg().setText("");
			op.getIsFound().setText("");
			String mkt = op.getMktField().getText();
			if (mkt.equals(""))
				throw new Exception("There is'nt Empty MKT"); // stop here and show a message if field empty
			op.viewSendModelMktToSearch(mkt); // search mkt in modal
			if (op.getIsSucceeded()) { // if we found
				op.getIsFound().setText("Details Product Deleted --->\n" + op.getMsgUser().getText()); //modal update in op.getMsgUser()  deleted Product,
																										//we move it to isfound
				
				this.op.viewSendModalMktToRemove(op.getMktField().getText()); // we again call to modal with mkt to
																				// remove
				op.getMapDataMsg().setText("Map Data--->");
				this.op.viewAskModelProductList(); // get from modal all product after we delete all
				this.op.getMsgUser().setTextFill(Color.BLACK);
			} else {
				op.getIsFound().setText(op.getMsgUser().getText()); // print-> not found
				op.getMsgUser().setText("");
			}

		} catch (Exception e) {
			op.getIsFound().setText("Exception ---> " + e.getMessage());
		}
	}

}
