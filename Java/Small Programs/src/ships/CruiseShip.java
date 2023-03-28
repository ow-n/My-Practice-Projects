package ships;

class CruiseShip extends Ship{
	
	// Class Variables
	private int stores;
	
	// Constructor
	protected CruiseShip() {
		super();
		stores = 20;
		setWeight(20000);
		setMaxPassangers(1500);
		setColor("white");
	}

	protected int getStores() {
		return stores;
	}

	protected void setStores(int stores) {
		this.stores = stores;
	}

	// Methods
	protected void sails() {
		System.out.println("This cruise ship went sailing.");
	}

	protected void docks() {
		System.out.println("This cruise ship has docked.");
		super.setSailing(false);;
	}
	
}
