package View;

import Commands.RemoveProductFromFileCommand;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class ViewOption7RemoveProductFromFile extends ViewOption {
	private TextField mktField;
	private Label isFound;
	private Label mapDataMsg;

	public ViewOption7RemoveProductFromFile(VBox placeView, String title, MainView myMainView) {
		super(placeView, title, myMainView);
		mktField = new TextField();
		isFound = new Label();
		mapDataMsg = new Label();
		mapDataMsg.setFont(new Font(25));
		this.property.add(new Label("MKT:"), 0, 0);
		this.property.add(mktField, 1, 0);
		this.property.add(this.btnAdd, 0, 1);
		this.mainView.getChildren().addAll(isFound, mapDataMsg, this.msgUser);
		this.btnAdd.setOnAction(e -> {
			RemoveProductFromFileCommand cmd = new RemoveProductFromFileCommand(this);
			cmd.execute();
		});
	}

	public void viewAskModelProductList() {
		this.myMainView.viewAskModelProductList();
	}

	public void viewSendModelMktToSearch(String mkt) {
		this.myMainView.viewSendModelMktToSearch(mkt);
	}

	public void viewSendModalMktToRemove(String mkt) {
		this.myMainView.viewSendModelMktToRemoveFromFile(mkt);

	}

	public TextField getMktField() {
		return mktField;
	}

	public Label getIsFound() {
		return isFound;
	}

	public Label getMapDataMsg() {
		return mapDataMsg;
	}

	public void clearPane() {
		super.clearPane();
		mainView.getChildren().removeAll(isFound, mapDataMsg);
	}

}
