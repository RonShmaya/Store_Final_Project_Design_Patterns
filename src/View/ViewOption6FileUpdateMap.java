package View;

import Commands.ReadFromFileToMapCommand;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class ViewOption6FileUpdateMap extends ViewOption {
	private Label mapDataMsg;

	public ViewOption6FileUpdateMap(VBox placeView, String title, MainView myMainView) {
		super(placeView, title, myMainView);
		btnAdd.setText("Read From File To Map");
		mapDataMsg = new Label("Map Data --- >");
		mapDataMsg.setFont(new Font(25));
		mapDataMsg.setVisible(false);
		this.property.add(btnAdd, 0, 0);
		this.property.add(new Label("this option happend automaticlly when we start the program"), 0, 1);
		this.property.add(mapDataMsg, 0, 2);
		this.mainView.getChildren().add(this.msgUser);
		this.btnAdd.setOnAction(e -> {
			ReadFromFileToMapCommand cmd = new ReadFromFileToMapCommand(this);
			cmd.execute();
		});
	}

	public void viewAskModelProductList() {
		this.myMainView.viewAskModelProductList();
	}

	public void viewUpdateMapFromFile() {
		this.myMainView.viewUpdateModelMapFromFile();

	}

	public Label getMapDataMsg() {
		return mapDataMsg;
	}

}
