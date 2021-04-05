package Listeners;

import Model.MyMap.eSortPick;
import Model.Product;

public interface ListenersOfView {
	void viewUpdateModelSortedPick(eSortPick pick);

	void viewUpdateModelAddProduct(String mkt, Product proud);

	void viewSendModelMktToSearch(String mkt);

	void viewAskModelProductList();

	void viewUpdateModelUndoProductUsingMemento();

	void viewUpdateModelMapFromFile();

	void viewSendModelMktToRemoveFromFile(String mkt);

	void viewAskFormModelDeleteAllProducts();

	void viewGetProfitsFromModel();

	void viewAskFromModelUpdateClientOberevrs();

}
