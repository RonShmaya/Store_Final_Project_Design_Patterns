package View;

import Commands.AddProductCommand;
import Model.Product;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;

public class ViewOption1AddProduct extends ViewOption {
	private TextField mktField;
	private TextField nameField;
	private TextField costField;
	private TextField sellingField;
	private TextField clientNameField;
	private TextField clientPhoneField;
	private RadioButton clientWantGetUpdate;
	private RadioButton clientDontWantGetUpdate;
	private ToggleGroup rGroup;

	public ViewOption1AddProduct(VBox placeView, String title, MainView myMainView) {
		super(placeView, title, myMainView);
		mktField = new TextField();
		nameField = new TextField();
		costField = new TextField();
		sellingField = new TextField();
		clientNameField = new TextField();
		clientPhoneField = new TextField();
		clientWantGetUpdate = new RadioButton("Yes");
		clientDontWantGetUpdate = new RadioButton("No");
		rGroup = new ToggleGroup();
		clientWantGetUpdate.setToggleGroup(rGroup);
		clientDontWantGetUpdate.setToggleGroup(rGroup);
		addProperty();
		this.btnAdd.setOnAction(e -> {
			AddProductCommand cmd = new AddProductCommand(this);
			cmd.execute();
		});
	}

	private void addProperty() {
		property.add(new Label("MKT:"), 0, 0);
		property.add(mktField, 1, 0);
		property.add(new Label("Cannot Be Empty"), 2, 0);

		property.add(new Label("Product name:"), 0, 1);
		property.add(nameField, 1, 1);
		property.add(new Label("Can Be Empty"), 2, 1);

		property.add(new Label("Cost to Store:"), 0, 2);
		property.add(costField, 1, 2);
		property.add(new Label("Can Be Empty,(Only Digits,Must Be Positive)"), 2, 2);

		property.add(new Label("Selling Price:"), 0, 3);
		property.add(sellingField, 1, 3);
		property.add(new Label("Can Be Empty,(Only Digits,Must Be Positive)"), 2, 3);

		property.add(new Label("Client name:"), 0, 4);
		property.add(clientNameField, 1, 4);
		property.add(new Label("Cannot Be Empty,(Without Digits)"), 2, 4);

		property.add(new Label("Client phone:"), 0, 5);
		property.add(clientPhoneField, 1, 5);
		property.add(new Label("Cannot Be Empty,(0123456789)"), 2, 5);

		property.add(new Label("Client Want To Get Updates:"), 0, 7);
		property.add(clientWantGetUpdate, 0, 8);
		property.add(clientDontWantGetUpdate, 1, 8);
		property.add(this.btnAdd, 0, 9);
		this.mainView.getChildren().add(this.msgUser);
	}

	public void viewUpdateModelAddProduct(String mkt, Product proud) {
		this.myMainView.viewUpdateModelAddProduct(mkt, proud);
	}

	public TextField getMktField() {
		return mktField;
	}

	public TextField getNameField() {
		return nameField;
	}

	public TextField getCostField() {
		return costField;
	}

	public TextField getSellingField() {
		return sellingField;
	}

	public TextField getClientNameField() {
		return clientNameField;
	}

	public TextField getClientPhoneField() {
		return clientPhoneField;
	}

	public RadioButton getClientWantGetUpdate() {
		return clientWantGetUpdate;
	}

	public RadioButton getClientDontWantGetUpdate() {
		return clientDontWantGetUpdate;
	}

	public ToggleGroup getrGroup() {
		return rGroup;
	}

}
