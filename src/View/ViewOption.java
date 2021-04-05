package View;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public abstract class ViewOption { 		
	protected VBox mainView;
	protected MainView myMainView;
	protected Label titleLabel;
	protected GridPane property;
	protected Label msgUser;
	protected Button btnAdd;
	protected boolean isSucceeded;

	public ViewOption(VBox placeView, String title, MainView myMainView) {
		this.mainView = placeView;
		this.myMainView = myMainView;
		titleLabel = new Label(title);
		titleLabel.setFont(new Font(35));

		property = new GridPane();
		property.setAlignment(Pos.TOP_LEFT);
		property.setHgap(10);
		property.setVgap(10);
		mainView.getChildren().addAll(titleLabel, property);
		mainView.setSpacing(10);
		msgUser = new Label();
		msgUser.setTextFill(Color.DARKRED);
		btnAdd = new Button("Submit");
	}

	public void mainViewUpdateOptionStatusCommands(boolean isSucceded) {
		if (isSucceded) {
			this.msgUser.setText("Succeeded");
			this.msgUser.setTextFill(Color.GREEN);
		} else
			this.msgUser.setTextFill(Color.DARKRED);
		this.isSucceeded = isSucceded;
	}

	public void mainViewUpdateOptionMsg(String msg) {
		this.msgUser.setText(msg);
	}

	public GridPane getProperty() {
		return property;
	}

	public Label getMsgUser() {
		return msgUser;
	}

	public void clearPane() {
		mainView.getChildren().removeAll(titleLabel, property, mainView, msgUser);
	}

	public boolean getIsSucceeded() {
		return isSucceeded;
	}
}