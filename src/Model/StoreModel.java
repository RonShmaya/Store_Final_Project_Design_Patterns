package Model;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.Iterator;
import java.util.Map.Entry;

import Listeners.ListenersOfModel;

import Model.MyMap.eSortPick;

public class StoreModel {
	public static final String FILE_NAME = "products.txt";
	private ListenersOfModel listener;
	private MyMap productList;
	private MyMap.MementoProud memnto;
	private boolean isUserPickSort;
	private boolean isUndoWas;

	public StoreModel() {
		productList = new MyMap();
	}

	public void readDataFromFile() {
		File f = new File(FILE_NAME);
		if (!f.exists()) {
			listener.modelUpdateViewIfOpenFile("Not Found File To Open...");
			return;
		}
		if (productList.readDataFromFile(f) == false)
			listener.modelUpdateViewIfOpenFile("Error In Open The File");
		else {
			listener.modelUpdateViewIfOpenFile("File Readed...");
			if (this.productList.isEmpty())
				isUserPickSort = false;
			else
				isUserPickSort = true;

		}

	}

	public void setUndoWas(boolean isUndoWas) {
		this.isUndoWas = isUndoWas;
	}

	public void addListener(ListenersOfModel listenersOfModel) {
		listener = listenersOfModel;
	}

	public void addProduct(String mkt, Product proud) {
		if (!isUserPickSort) {
			commandStatus("Please Pick Sort Before Adding Product (There isn't Products)", false);
			return;
		}
		if (mkt.equals("")) {
			commandStatus("MKT Cannot Be Empty", false);
			return;
		}
		memnto = productList.createMemento();
		isUndoWas = false;
		Product found = productList.searchProduct(mkt);
		if (found != null)
			removeProductByMKT(mkt);
		productList.put(mkt, proud);
		commandStatus("Succeeded", true);
		try (RandomAccessFile rna = new RandomAccessFile(new File(FILE_NAME), "rw");) {
			rna.seek(rna.length());
			writeProductToFile(rna, productList.getProduct(mkt), mkt);
		} catch (Exception e) {
			commandStatus("Error In Write To File", false);
		}
	}

	private void writeProductToFile(RandomAccessFile rna, Product proud, String mkt) throws Exception {
		rna.writeUTF(mkt);
		rna.writeUTF(proud.getNameProduct());
		rna.writeInt(proud.getCostPrice());
		rna.writeInt(proud.getSellingPrice());
		rna.writeUTF(proud.getClient().getName());
		rna.writeUTF(proud.getClient().getPhoneNumber());
		rna.writeBoolean(proud.getClient().isWantToGetUpdates());
	}

	public void sortPick(eSortPick pick) {
		if (!productList.isEmpty()) {
			commandStatus("You already pick how to sort", false);
		} else {
			productList.getSortPick(pick);
			isUserPickSort = true;
			commandStatus("Succeeded", true);
			try (RandomAccessFile rna = new RandomAccessFile(new File(FILE_NAME), "rw")) {
				rna.setLength(0);
				rna.writeUTF(pick.toString());
			} catch (Exception e) {
				commandStatus("Error In Write To File", false);
			}
		}
	}

	public void searchProduct(String mkt) {
		Product found = productList.searchProduct(mkt);
		if (found == null)
			commandStatus("MKT Not Found", false);
		else
			commandStatus(found.toString(), true);
	}

	public void showProductsMap() {
		String ans = productList.mapToString();
		if (ans == null) {
			commandStatus("There is no Products", false);
			return;
		}
		listener.modelUpdateViewMsg(ans);
	}

	public boolean removeProductByMKT(String mkt) {
		memnto = productList.createMemento();
		isUndoWas = false;
		Iterator<Entry<String, Product>> itr = productList.iterator();
		while (itr.hasNext()) {
			Entry<String, Product> entry = itr.next();
			if (entry.getKey().equals(mkt)) {
				itr.remove();
				readDataFromFile();
				return true;
			}
		}
		return false;
	}

	public void commandStatus(String msg, boolean isSucceeded) {
		listener.modelUpdateViewStatusCommand(isSucceeded);
		listener.modelUpdateViewMsg(msg);
	}

	public void undo() {
		if (isUndoWas)
			commandStatus(
					"Undo Already Done Or You Just Load From File now, After Add More Products You Can do Undo Again",
					false);
		else if (memnto == null)
			commandStatus("There is no Product", false);
		else {
			productList.setMementoProud(memnto);
			commandStatus("Succeeded", true);
			isUndoWas = true;
			isUserPickSort = true;
		}
	}

	public void removeAllProducts() {
		memnto = productList.createMemento();
		isUndoWas = false;
		isUserPickSort = false;
		Iterator<Entry<String, Product>> itr = productList.iterator();
		while (itr.hasNext()) {
			itr.next();
			itr.remove();
		}
		readDataFromFile();
		commandStatus("Succeeded", true);
	}

	public void getProfits() {
		String ProfitMsg = this.productList.getProfits();
		commandStatus(ProfitMsg, true);
	}

	public void updateObservers() {
		Sender sender = Sender.getInstance();
		sender.reset();
		productList.getAllClientsWantGetMsg();
		commandStatus("Succeeded", true);
	}
}
