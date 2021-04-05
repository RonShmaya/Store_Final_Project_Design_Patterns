package Commands;

import View.ViewOption6FileUpdateMap;
import javafx.scene.paint.Color;

public class ReadFromFileToMapCommand implements Command {

	private ViewOption6FileUpdateMap op;

	public ReadFromFileToMapCommand(ViewOption6FileUpdateMap op) {
		this.op = op;
	}

	@Override
	public void execute() {
		this.op.getMapDataMsg().setVisible(true);
		this.op.viewUpdateMapFromFile();
		this.op.viewAskModelProductList();
		this.op.getMsgUser().setTextFill(Color.BLACK);
	}

}
