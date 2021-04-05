package Controller;

import Listeners.ListenersOfModel;
import Listeners.ListenersOfView;
import Model.MyMap.eSortPick;
import Model.Product;
import Model.StoreModel;

import View.MainView;

public class StoreController implements ListenersOfModel, ListenersOfView {
	private MainView storeView;
	private StoreModel storeModel;

	public StoreController(StoreModel model, MainView view) {
		storeModel = model;
		storeView = view;
		storeView.addListener(this);
		storeModel.addListener(this);
		storeModel.readDataFromFile();
		storeModel.setUndoWas(true);
	}

//model update in view
	@Override
	public void modelUpdateViewMsg(String msg) {
		storeView.updateMsgFromModel(msg);

	}

	@Override
	public void modelUpdateViewIfOpenFile(String msg) {
		storeView.updateIfOpenFile(msg);

	}

	@Override
	public void modelUpdateViewStatusCommand(boolean isSucceded) {
		storeView.mainViewUpdateOptionStatusCommands(isSucceded);
	}

	// view update in model
	@Override
	public void viewUpdateModelSortedPick(eSortPick pick) {
		storeModel.sortPick(pick);

	}

	@Override
	public void viewUpdateModelAddProduct(String mkt, Product proud) {
		storeModel.addProduct(mkt, proud);
	}

	@Override
	public void viewSendModelMktToSearch(String mkt) {
		storeModel.searchProduct(mkt);
	}

	@Override
	public void viewAskModelProductList() {
		storeModel.showProductsMap();
	}

	@Override
	public void viewUpdateModelUndoProductUsingMemento() {
		storeModel.undo();
	}

	@Override
	public void viewUpdateModelMapFromFile() {
		storeModel.readDataFromFile();

	}

	@Override
	public void viewSendModelMktToRemoveFromFile(String mkt) {
		storeModel.removeProductByMKT(mkt);

	}

	@Override
	public void viewAskFormModelDeleteAllProducts() {
		storeModel.removeAllProducts();

	}

	@Override
	public void viewGetProfitsFromModel() {
		storeModel.getProfits();

	}

	@Override
	public void viewAskFromModelUpdateClientOberevrs() {
		storeModel.updateObservers();

	}

}
