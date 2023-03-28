package ships;

class Ship {
	
	// Default Variables for Small Fishing Boat
	private double weight; // tonnes
	private int maxPassangers;
	private String color;
	private boolean isSailing;
	
	

	// Constructors
	protected Ship() {
		weight = 10;
		maxPassangers = 15;
		color = "brown";
	}
	
	protected Ship(double weight, int maxPassangers, String color) {
		this.weight = weight;
		this.maxPassangers = maxPassangers;
		this.color = color;
	}

	// Getters & Setters
	protected double getWeight() {
		return weight;
	}

	protected void setWeight(double weight) {
		this.weight = weight;
	}

	protected int getMaxPassangers() {
		return maxPassangers;
	}

	protected void setMaxPassangers(int maxPassangers) {
		this.maxPassangers = maxPassangers;
	}

	protected String getColor() {
		return color;
	}

	protected void setColor(String color) {
		this.color = color;
	}

	protected boolean isSailing() {
		return isSailing;
	}

	protected void setSailing(boolean isSailing) {
		this.isSailing = isSailing;
	}
	
	
	// Methods
	protected void sails() {
		System.out.println("This ship went sailing.");
		setSailing(true);
	}
	
	protected void docks() {
		System.out.println("This ship has docked.");
		setSailing(false);;
	}
	
}