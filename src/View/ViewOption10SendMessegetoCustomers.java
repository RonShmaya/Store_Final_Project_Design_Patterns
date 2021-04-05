package View;

import java.util.List;

import Commands.SendMessegetoCustomersCommand;
import Model.Sender;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class ViewOption10SendMessegetoCustomers extends ViewOption {
	private Label msgLabel;
	private TextField msgField;
	private Button showReciversNames;
	private Label names;

	public ViewOption10SendMessegetoCustomers(VBox placeView, String title, MainView myMainView) {
		super(placeView, title, myMainView);
		msgLabel = new Label("MSG:");
		msgField = new TextField();
		showReciversNames = new Button("Show names Recivers");
		names = new Label();
		names.setFont(new Font(19));
		this.property.add(msgLabel, 0, 0);
		this.property.add(msgField, 1, 0);
		this.property.add(this.btnAdd, 0, 1);
		this.property.add(this.msgUser, 0, 2);
		this.property.add(this.showReciversNames, 0, 3);
		mainView.getChildren().add(this.names);
		this.msgField.setPrefSize(700, 75);
		this.showReciversNames.setDisable(true);
		this.btnAdd.setOnAction(e -> {
			SendMessegetoCustomersCommand cmd = new SendMessegetoCustomersCommand(this);
			cmd.execute();
		});
		threadOption();
	}

	private void threadOption() {
		this.showReciversNames.setOnAction(e -> {
			this.btnAdd.setDisable(true);
			Sender sender = Sender.getInstance();
			List<String> cleintsNames = sender.reciversList();
			Thread showNamesThread = new Thread(() -> {
				synchronized (this) {
					try {
						for (String name : cleintsNames) {
							Thread.sleep(2000);
							Platform.runLater(() -> {
								this.names.setText(this.names.getText() + "Client Name:\t" + name + "\n");
							});
						}
						Platform.runLater(() -> {
							this.names.setText(this.names.getText() + "Finish...\n");
							this.btnAdd.setDisable(false);
						});
					} catch (InterruptedException e1) {
					}
				}
			});
			showNamesThread.start();
		});
	}

	public TextField getMsgField() {
		return msgField;
	}

	public Button getShowReciversNames() {
		return showReciversNames;
	}

	public void updateClientsWantGetMsg() {
		this.myMainView.viewAskFromModelUpdateClientOberevrs();
	}

	@Override
	public void clearPane() {
		super.clearPane();
		mainView.getChildren().removeAll(names);
	}
}
