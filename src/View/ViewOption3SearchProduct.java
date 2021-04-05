package View;



import Commands.SearchProductCommand;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class ViewOption3SearchProduct extends ViewOption {
	private TextField mktField;

	public ViewOption3SearchProduct(VBox placeView, String title, MainView myMainView) {
		super(placeView, title, myMainView);
		mktField = new TextField();
		this.property.add(new Label("MKT:"), 0, 0);
		this.property.add(mktField, 1, 0);
		this.property.add(this.btnAdd, 0, 1);
		this.mainView.getChildren().add(this.msgUser);
		this.btnAdd.setOnAction(e -> {
			SearchProductCommand cmd = new SearchProductCommand(this);
			cmd.execute();
		});
	}

	public void viewSendModelMktToSearch(String mkt) {
		this.myMainView.viewSendModelMktToSearch(mkt);
	}

	public TextField getMktField() {
		return mktField;
	}

}
