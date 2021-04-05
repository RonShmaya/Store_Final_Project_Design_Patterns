package Model;

public class Product {

	private String nameProduct;
	private int costPrice;
	private int sellingPrice;
	private Client client;

	public Product(String nameProduct, int costPrice, int sellingPrice, Client coustumer) throws Exception {
		setNameProduct(nameProduct);
		setCostPrice(costPrice);
		setSellingPrice(sellingPrice);
		setCoustumer(coustumer);
	}

	private void setNameProduct(String nameProduct) throws Exception { // can be empty
		this.nameProduct = nameProduct;
	}

	private void setCostPrice(int costPrice) throws Exception { // can be empty
		if (costPrice < 0)
			throw new Exception("Cost Price Must Be Positive");
		this.costPrice = costPrice;
	}

	private void setSellingPrice(int sellingPrice) throws Exception { // can be empty
		if (costPrice < 0)
			throw new Exception("Selling Price Must Be Positive");
		this.sellingPrice = sellingPrice;
	}

	private void setCoustumer(Client coustumer) {
		this.client = coustumer;
	}


	public String getNameProduct() {
		return nameProduct;
	}

	public int getCostPrice() {
		return costPrice;
	}

	public int getSellingPrice() {
		return sellingPrice;
	}

	public Client getClient() {
		return client;
	}

	public int getProfit() {
		return sellingPrice - costPrice;
	}

	public String toString() {
		return "Product --->\tName: " + this.nameProduct + "\tCost To Store: " + this.costPrice + "\tSelling Price: "
				+ this.sellingPrice + "\n" + this.client;
	}
}
