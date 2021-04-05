package View;

import Commands.GetProfitsCommand;

import javafx.scene.layout.VBox;

public class ViewOption9Profits extends ViewOption {

	public ViewOption9Profits(VBox placeView, String title, MainView myMainView) {
		super(placeView, title, myMainView);
		btnAdd.setText("Show");
		this.property.add(btnAdd, 0, 0);
		this.property.add(msgUser, 0, 1);

		this.btnAdd.setOnAction(e -> {
			GetProfitsCommand cmd = new GetProfitsCommand(this);
			cmd.execute();
		});
	}

	public void getProfits() {
		this.myMainView.viewGetProfitsFromModel();

	}

}
