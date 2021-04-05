package Model;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.TreeMap;
import java.util.Map.Entry;

public class MyMap implements Iterable<Entry<String, Product>> {
	public static enum eSortPick {
		SortByAlphabeticAscending, SortByAlphabeticDescending, SortByEntryorder
	}

	private Map<String, Product> productList;
	eSortPick sortPick;

	public void getSortPick(eSortPick pick) { // get the specifies map we want (by the sort picking)
		if (pick == eSortPick.SortByAlphabeticAscending)
			productList = new TreeMap<String, Product>((o1, o2) -> {
				return o1.compareTo(o2);
			});

		else if (pick == eSortPick.SortByAlphabeticDescending)
			productList = new TreeMap<String, Product>((o1, o2) -> {
				return o2.compareTo(o1);
			});

		else
			productList = new LinkedHashMap<String, Product>();
		sortPick = pick;
	}

	public void put(String mkt, Product proud) {
		productList.put(mkt, proud);
	}

	public Product searchProduct(String mkt) {
		if (productList == null)
			return null;
		return this.productList.get(mkt);
	}

	public Product getProduct(String mkt) {
		return productList.get(mkt);
	}

	public String mapToString() {
		if (productList == null || productList.isEmpty())
			return null;
		StringBuffer back = new StringBuffer(this.sortPick.toString() + "\n");
		for (Entry<String, Product> prod : productList.entrySet()) {
			back.append("MKT: " + prod.getKey() + "\n" + prod.getValue() + "\n\n");
		}
		return back.toString();
	}

	public boolean readDataFromFile(File f) { // read from file to map, (itrerator run on file data)
		try (RandomAccessFile rna = new RandomAccessFile(f, "r")) {
			sortPick = eSortPick.valueOf(rna.readUTF());
			getSortPick(sortPick);
			Iterator<Entry<String, Product>> itr = this.iterator();
			while (itr.hasNext()) {
				Entry<String, Product> entry = itr.next();
				productList.put(entry.getKey(), entry.getValue());
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public MementoProud createMemento() {
		Map<String, Product> memntMap;
		if (productList == null)
			memntMap = null;
		else
			memntMap = new LinkedHashMap<String, Product>(productList);
		return new MementoProud(memntMap);
	}

	public void setMementoProud(MementoProud memento) {
		if (productList != null)
			this.productList.clear();
		this.productList.putAll(memento.getProudMap());
		updateFileData(memento.getDataFile());
	}

	private void updateFileData(byte[] dataFile) {
		if (dataFile != null) {
			try (RandomAccessFile rna = new RandomAccessFile(StoreModel.FILE_NAME, "rw");) {
				rna.setLength(0);
				rna.seek(0);
				rna.write(dataFile);
			} catch (Exception e) {
			}
		}
	}

	public static class MementoProud { // memento save map and file
		private Map<String, Product> productList;
		private byte[] fileData;

		private MementoProud(Map<String, Product> productList) {
			this.productList = productList;
			File f = new File(StoreModel.FILE_NAME);
			if (f.exists()) {
				try (RandomAccessFile rna = new RandomAccessFile(f, "rw");) {
					this.fileData = new byte[(int) rna.length()];
					rna.seek(0);
					rna.read(fileData);
				} catch (Exception e) {
				}

			} else
				fileData = null;
		}

		private Map<String, Product> getProudMap() {
			return productList;
		}

		private byte[] getDataFile() {
			return this.fileData;
		}
	}

	@Override
	public Iterator<Entry<String, Product>> iterator() {
		try {
			return new MyIterator();
		} catch (Exception e) {
			return null;
		}
	}

	class MyIterator implements Iterator<Entry<String, Product>> {
		private RandomAccessFile rna;
		private long pointerPlace = 0;
		private long last = -1;

		public MyIterator() throws Exception {
			rna = new RandomAccessFile(StoreModel.FILE_NAME, "rw");
			rna.readUTF();
			pointerPlace = rna.getFilePointer();
			rna.close();
		}

		@Override
		public boolean hasNext() { // check if pointer in the end ()
			try {
				rna = new RandomAccessFile(StoreModel.FILE_NAME, "rw");
				rna.seek(pointerPlace);
				boolean isHasNext = rna.getFilePointer() != rna.length();
				rna.close();
				return isHasNext;
			} catch (IOException e) {
				return false;
			}
		}

		@Override
		public Entry<String, Product> next() {
			try {
				if (!hasNext()) {
					throw new NoSuchElementException();
				}
				rna = new RandomAccessFile(StoreModel.FILE_NAME, "rw");
				Entry<String, Product> productIteratore = readProduct();
				rna.close();
				return productIteratore;
			} catch (Exception e) {
				System.out.println(e.getMessage());
				return null;
			}
		}

		@Override
		public void remove() {
			if (last == -1)
				throw new IllegalStateException();
			try {
				rna = new RandomAccessFile(StoreModel.FILE_NAME, "rw");
				rna.seek(pointerPlace);
				byte copy[] = new byte[(int) (rna.length() - pointerPlace)]; // byte in the size of all data after the
																				// product we want to delete
				rna.read(copy); // save all data after the product we want to delete
				rna.setLength(last); // remove data from product to delete until the end of the file
				rna.write(copy); // write again the data we keep
				pointerPlace = last; // pointerPlace return to the last place
				last = -1;
				rna.close();
			} catch (Exception e) {

			}
		}

		private Entry<String, Product> readProduct() throws Exception {
			last = pointerPlace;
			rna.seek(pointerPlace);
			String mkt = rna.readUTF();
			String nameProd = rna.readUTF();
			int cost = rna.readInt();
			int selling = rna.readInt();
			String nameClient = rna.readUTF();
			String phoneClient = rna.readUTF();
			boolean clientPick = rna.readBoolean();
			pointerPlace = rna.getFilePointer();
			Client client = new Client(nameClient, phoneClient, clientPick);
			Product proud = new Product(nameProd, cost, selling, client);
			Map<String, Product> temp = new LinkedHashMap<String, Product>();
			temp.put(mkt, proud);
			Entry<String, Product> productIteratore = temp.entrySet().iterator().next();
			return productIteratore;
		}

	}

	public String getProfits() {
		if (productList == null || productList.isEmpty())
			return "There is'nt Products";
		StringBuffer back = new StringBuffer();
		int totalProfit = 0;
		for (Entry<String, Product> entry : productList.entrySet()) {
			back.append("\n" + entry.getValue() + "\nProfit:\t" + entry.getValue().getProfit() + "\n");
			totalProfit += entry.getValue().getProfit();
		}
		back.append("\nTotal Profit:\t" + totalProfit);
		return back.toString();
	}

	public void getAllClientsWantGetMsg() { // (update observers to sender)
		Sender sender = Sender.getInstance();
		for (Entry<String, Product> entry : productList.entrySet()) {
			if (entry.getValue().getClient().isWantToGetUpdates())
				sender.addObserver(entry.getValue().getClient());
		}
	}

	public boolean isEmpty() {
		return  (this.productList==null ||this.productList.isEmpty());
	}

}