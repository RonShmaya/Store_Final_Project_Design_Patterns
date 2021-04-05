package View;

import Commands.ShowAllProductsCommand;
import javafx.scene.layout.VBox;

public class ViewOption4ShowAllProduct extends ViewOption {

	public ViewOption4ShowAllProduct(VBox placeView, String title, MainView myMainView) {
		super(placeView, title, myMainView);
		this.property.add(btnAdd, 0, 0);
		btnAdd.setOnAction(e -> {
			ShowAllProductsCommand cmd = new ShowAllProductsCommand(this);
			cmd.execute();
		});
		this.mainView.getChildren().add(this.msgUser);

	}

	public void viewAskModelProductList() {
		this.myMainView.viewAskModelProductList();
	}

}
