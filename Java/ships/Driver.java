package ships;

public class Driver {

	public static void main(String[] args) {

		// User Interface Object
		UserInterface ui = new UserInterface();

		// Methods
		ui.howManyShips();

		ui.createShips();

		ui.listFleet();

		// Testing Class Methods
		System.out.printf("\nThe 1st fleet's weight is: %.0f tonnes\n"
				,ui.fleet[0].getWeight());

		System.out.printf("The 3rd fleet's color is: %s\n"
				, ui.fleet[2].getColor());

		// In these tests, minimum fleet size is 3
		// Testing if Cargo & Cruise Ships
		if (ui.fleet[1] instanceof CruiseShip) {
			System.out.printf("\nThe 2nd fleet's number of stores is: %s\n\n"
					, ((CruiseShip) ui.fleet[1]).getStores());
		} else if (ui.fleet[1] instanceof CargoShip) {
			System.out.printf("\nThe 2nd fleet's number of stores is: %b\n"
					, ((CargoShip) ui.fleet[1]).isCrane());
		}

		// Polymorphism
		System.out.println("Ship 1:");
		ui.fleet[0].sails();
		System.out.println("Ship 2:");
		ui.fleet[1].sails();
		System.out.println("Ship 3:");
		ui.fleet[2].sails();

		System.out.println("Ship 2:");
		ui.fleet[1].docks();
		System.out.printf("Is the 2nd ship out at sea?: %b\n\n", ui.fleet[1].isSailing());

		System.out.printf("The max capacity of the 1st ship is: %d\n", ui.fleet[0].getMaxPassangers());
		ui.fleet[0].setMaxPassangers(100);
		System.out.printf("The max capacity of the 1st ship is now: %d\n", ui.fleet[0].getMaxPassangers());

		ui.closeScanner();

	}

}
