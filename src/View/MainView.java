package View;

import Commands.Command;
import Commands.CommandOptionView;
import Listeners.ListenersOfView;
import Model.MyMap.eSortPick;
import Model.Product;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class MainView {
	private HBox mainPain;
	private VBox menu;
	private VBox placeOption;
	private ViewOption viewOp;
	private RadioButton option1;
	private RadioButton option2;
	private RadioButton option3;
	private RadioButton option4;
	private RadioButton option5;
	private RadioButton option6;
	private RadioButton option7;
	private RadioButton option8;
	private RadioButton option9;
	private RadioButton option10;
	private ListenersOfView listener;
	private Stage stage;

	public MainView(Stage stage) {
		this.stage = stage;
		this.stage.setTitle("Manage Selling In Store");
		menu();
		this.stage.setScene(new Scene(mainPain, 1200, 800));
		this.stage.show();

	}

	private void menu() {
		mainPain = new HBox(10);
		menu = new VBox(10);
		menu.setAlignment(Pos.TOP_LEFT);
		menu.setPadding(new Insets(10));
		ToggleGroup groupMenu = new ToggleGroup();
		option1 = new RadioButton("Add Product");
		option2 = new RadioButton("Sort Options");
		option3 = new RadioButton("Search Product");
		option4 = new RadioButton("Show All Products");
		option5 = new RadioButton("Undo");
		option6 = new RadioButton("Read Data From File To Map");
		option7 = new RadioButton("Remove Product From File");
		option8 = new RadioButton("Remove All Products From File");
		option9 = new RadioButton("Show Profit");
		option10 = new RadioButton("Send Messege to Customers");
		option1.setToggleGroup(groupMenu);
		option2.setToggleGroup(groupMenu);
		option3.setToggleGroup(groupMenu);
		option4.setToggleGroup(groupMenu);
		option5.setToggleGroup(groupMenu);
		option6.setToggleGroup(groupMenu);
		option7.setToggleGroup(groupMenu);
		option8.setToggleGroup(groupMenu);
		option9.setToggleGroup(groupMenu);
		option10.setToggleGroup(groupMenu);
		Label menuTitle = new Label("Menu");
		menuTitle.setFont(new Font(25));
		menu.getChildren().addAll(menuTitle, option1, option2, option3, option4, option5, option6, option7, option8,
				option9, option10);
		placeOption = new VBox();
		mainPain.getChildren().addAll(menu, placeOption);
		option2.setSelected(true);
		viewOp = new ViewOption2SortOptions(placeOption, "Sort Options", this);
		events();
	}

	private void events() {//view option commands using anonymous command class, each option is a different class!

		option1.setOnAction(e -> {
			Command cmd = new CommandOptionView(viewOp, this) {
				@Override
				public void execute() {
					viewOp.clearPane();
					viewOp = new ViewOption1AddProduct(this.mView.placeOption, "Add Prodect", this.mView);
				}
			};
			cmd.execute();
		});
		option2.setOnAction(e -> {
			Command cmd = new CommandOptionView(viewOp, this) {
				@Override
				public void execute() {
					viewOp.clearPane();
					viewOp = new ViewOption2SortOptions(this.mView.placeOption, "Sort Options", this.mView);
				}
			};
			cmd.execute();
		});
		option3.setOnAction(e -> {
			Command cmd = new CommandOptionView(viewOp, this) {
				@Override
				public void execute() {
					viewOp.clearPane();
					viewOp = new ViewOption3SearchProduct(this.mView.placeOption, "Search Product", this.mView);
				}
			};
			cmd.execute();
		});
		option4.setOnAction(e -> {
			Command cmd = new CommandOptionView(viewOp, this) {
				@Override
				public void execute() {
					viewOp.clearPane();
					viewOp = new ViewOption4ShowAllProduct(this.mView.placeOption, "Products", this.mView);
				}
			};
			cmd.execute();
		});
		option5.setOnAction(e -> {
			Command cmd = new CommandOptionView(viewOp, this) {
				@Override
				public void execute() {
					viewOp.clearPane();
					viewOp = new ViewOption5UndoProduct(this.mView.placeOption, "Undo", this.mView);
				}
			};
			cmd.execute();
		});
		option6.setOnAction(e -> {
			Command cmd = new CommandOptionView(viewOp, this) {
				@Override
				public void execute() {
					viewOp.clearPane();
					viewOp = new ViewOption6FileUpdateMap(this.mView.placeOption, "Read Data From File To Map",
							this.mView);
				}
			};
			cmd.execute();
		});
		option7.setOnAction(e -> {
			Command cmd = new CommandOptionView(viewOp, this) {
				@Override
				public void execute() {
					viewOp.clearPane();
					viewOp = new ViewOption7RemoveProductFromFile(this.mView.placeOption, "Remove Product From File",
							this.mView);
				}
			};
			cmd.execute();
		});
		option8.setOnAction(e -> {
			Command cmd = new CommandOptionView(viewOp, this) {
				@Override
				public void execute() {
					viewOp.clearPane();
					viewOp = new ViewOption8RemoveAllProductsFromFile(this.mView.placeOption,
							"Remove All Products From File", this.mView);
				}
			};
			cmd.execute();
		});
		option9.setOnAction(e -> {
			Command cmd = new CommandOptionView(viewOp, this) {
				@Override
				public void execute() {
					viewOp.clearPane();
					viewOp = new ViewOption9Profits(this.mView.placeOption, "Profits", this.mView);
				}
			};
			cmd.execute();
		});
		option10.setOnAction(e -> {
			Command cmd = new CommandOptionView(viewOp, this) {
				@Override
				public void execute() {
					viewOp.clearPane();
					viewOp = new ViewOption10SendMessegetoCustomers(this.mView.placeOption, "Send Messege to Customers",
							this.mView);
				}
			};
			cmd.execute();
		});
	}

	public void addListener(ListenersOfView listenersOfView) {
		listener = listenersOfView;
	}

	// view contact with model methods

	public void updateMsgFromModel(String msg) {
		this.viewOp.mainViewUpdateOptionMsg(msg);
	}

	public void mainViewUpdateOptionStatusCommands(boolean isSucceded) {
		this.viewOp.mainViewUpdateOptionStatusCommands(isSucceded);
	}

	public void viewUpdateModelSortedPick(eSortPick pick) {
		listener.viewUpdateModelSortedPick(pick);
	}

	public void viewUpdateModelAddProduct(String mkt, Product proud) {
		listener.viewUpdateModelAddProduct(mkt, proud);
	}

	public void viewSendModelMktToSearch(String mkt) {
		listener.viewSendModelMktToSearch(mkt);
	}

	public void viewAskModelProductList() {
		listener.viewAskModelProductList();

	}

	public void viewUpdateModelUndoProductUsingMemento() {
		listener.viewUpdateModelUndoProductUsingMemento();
	}

	public void updateIfOpenFile(String msg) {
		this.stage.setTitle("Manage Selling In Store ---> " + msg);
	}

	public void viewUpdateModelMapFromFile() {
		listener.viewUpdateModelMapFromFile();

	}

	public void viewSendModelMktToRemoveFromFile(String mkt) {
		listener.viewSendModelMktToRemoveFromFile(mkt);

	}

	public void viewAskFormModelDeleteAllProducts() {
		listener.viewAskFormModelDeleteAllProducts();

	}

	public void viewGetProfitsFromModel() {
		listener.viewGetProfitsFromModel();

	}

	public void viewAskFromModelUpdateClientOberevrs() {
		listener.viewAskFromModelUpdateClientOberevrs();

	}

}
