package ships;

class CargoShip extends Ship{
	
	// Class Variable
	protected boolean crane;
	
	// Constructor
	protected CargoShip() {
		super();
		crane = true;
		setWeight(100000);
		setMaxPassangers(20);
		setColor("red");
	}

	protected boolean isCrane() {
		return crane;
	}

	protected void setCrane(boolean crane) {
		this.crane = crane;
	}

	// Methods
	protected void sails() {
		System.out.println("This cargo ship went sailing.");
	}
	
	protected void docks() {
		System.out.println("This cargo ship has docked.");
		setSailing(false);;
	}
	
}
