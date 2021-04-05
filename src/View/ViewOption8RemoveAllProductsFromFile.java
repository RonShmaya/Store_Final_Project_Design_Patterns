package View;

import Commands.RemoveAllProductsFromFileCommand;
import javafx.scene.layout.VBox;

public class ViewOption8RemoveAllProductsFromFile extends ViewOption {

	public ViewOption8RemoveAllProductsFromFile(VBox placeView, String title, MainView myMainView) {
		super(placeView, title, myMainView);
		this.property.add(btnAdd, 0, 0);
		this.mainView.getChildren().add(this.msgUser);
		btnAdd.setOnAction(e -> {
			RemoveAllProductsFromFileCommand cmd = new RemoveAllProductsFromFileCommand(this);
			cmd.execute();
		});

	}

	public void viewAskForDeleteAllProducts() {
		this.myMainView.viewAskFormModelDeleteAllProducts();

	}

}
