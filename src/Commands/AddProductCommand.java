package Commands;

import Model.Client;
import Model.Product;
import View.ViewOption1AddProduct;
import javafx.scene.paint.Color;

public class AddProductCommand implements Command {
	private ViewOption1AddProduct op;	

	public AddProductCommand(ViewOption1AddProduct op) {
		this.op = op;
	}

	@Override
	public void execute() {
		try {
			String mkt = op.getMktField().getText();
			String nameProd = op.getNameField().getText();
			String costStr = op.getCostField().getText(), sellingStr = op.getSellingField().getText();
			int cost, selling;
			if (costStr.equals(""))
				cost = 0;
			else
				cost = Integer.parseInt(costStr);
			if (sellingStr.equals(""))
				selling = 0;
			else
				selling = Integer.parseInt(sellingStr);
			String clientName = op.getClientNameField().getText();
			String clientPhone = op.getClientPhoneField().getText();
			if (op.getrGroup().getSelectedToggle() == null)
				throw new Exception("Must Pick If User Get Updates");
			boolean isWantUpdate;
			if (op.getClientWantGetUpdate().isSelected())
				isWantUpdate = true;
			else
				isWantUpdate = false;
			Client c = new Client(clientName, clientPhone, isWantUpdate);
			Product prod = new Product(nameProd, cost, selling, c);
			op.viewUpdateModelAddProduct(mkt, prod);

		} catch (Exception e) {
			op.getMsgUser().setText("Exception ---> " + e.getMessage());
			op.getMsgUser().setTextFill(Color.DARKRED);
		}

	}

}
