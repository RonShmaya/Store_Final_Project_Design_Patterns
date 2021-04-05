package View;

import Commands.UndoProductCommand;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class ViewOption5UndoProduct extends ViewOption {

	public ViewOption5UndoProduct(VBox placeView, String title, MainView myMainView) {
		super(placeView, title, myMainView);
		btnAdd.setText("Undo");
		this.property.add(btnAdd, 0, 0);
		this.property.add(msgUser, 0, 1);
		this.msgUser.setTextFill(Color.BLACK);
		msgUser.setText("this Undo ->\n1. Defult: Remove Last product.\n"
				+ "2.Undo file and product list after delete all product.\n3.Undo file and product list after delete one product.\n(depend on the last command)");
		this.btnAdd.setOnAction(e -> {
			UndoProductCommand cmd = new UndoProductCommand(this);
			cmd.execute();
		});
	}

	public void viewUpdateModelUndoProductUsingMemnto() {
		this.myMainView.viewUpdateModelUndoProductUsingMemento();
	}
}
