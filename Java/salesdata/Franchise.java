package salesdata;

public class Franchise {
	private Store stores[];

	public Franchise(int num) {
		stores = new Store[num];
	}

	public Store getStores(int i) {
		return stores[i];
	}

	public void setStores(Store stores, int i) {
		this.stores[i] = stores;
	}
	public int numberOfStores() {
		return stores.length;
	}

}