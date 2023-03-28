package ships;

import java.util.Scanner;
import java.util.InputMismatchException;

class UserInterface {
	
	// Class Variables
	private Scanner input = new Scanner(System.in);
	private int numShips;
	private boolean validInput;
	private int shipType;
	private int i;
	protected Ship[] fleet;
	
	public Ship[] getFleet() {
		return fleet;
	}


	public void setFleet(Ship[] fleet) {
		this.fleet = fleet;
	}
	
	// Methods
	protected void howManyShips() {
		validInput = false;
		System.out.print("How many ships are in your fleet? ");

		while (!validInput) {
			try {
				numShips = input.nextInt();
				if (numShips < 0) {
					System.out.print("Invalid input. Enter a positive integer: ");
				} else {
					validInput = true;
				}
			} catch (InputMismatchException e) {
				System.out.print("Invalid input. Enter a valid integer: ");
				input.next(); // clear scanner buffer
			}
		}

		setFleet(new Ship[numShips]); 
		
		System.out.printf("You have declared %d ships.\n", numShips);
		
	}

	
	protected void createShips() {
		System.out.println("\nChoose what kinds of ships you want in your fleet.");
		System.out.println("Enter '1' for a default ship, "
				+ "'2' for a cruise ship, or '3' for a cargo ship.");
		for (int i = 0; i < numShips; i++) {
			System.out.printf("Ship %d: ", i + 1);
			
			validInput = false;
			while (!validInput) {
				try {
					shipType = input.nextInt();
					if (shipType >= 1 && shipType <= 3) {
						validInput = true;
					} else {
						System.out.print("Select an integer between 1 and 3: ");
					}
				} catch (InputMismatchException e) {
					System.out.print("Invalid input. Please enter 1, 2, or 3: ");
					input.next(); // clears scanner buffer
				}
			}
			
			chooseShip(shipType);
		}
		
		System.out.printf("You have successfully created your %d ships.\n\n", numShips);
	}
	
	
	private void chooseShip(int shipType) {
		if (shipType == 1) {
			getFleet()[i] = new Ship();
			i++;
		} else if (shipType == 2) {
			getFleet()[i] = new CruiseShip();
			i++;
		} else if (shipType == 3) {
			getFleet()[i] = new CargoShip();
			i++;
		} else {
			System.out.println("Error Occured: Ship Type invalid.");
		}
	}
	

	protected void listFleet() {
		int defaultShip = 0;
		int cruiseShip = 0;
		int cargoShip = 0;
		
		for (i = 0; i < getFleet().length; i++) {
			if (getFleet()[i] instanceof CruiseShip) {
				cruiseShip++;
			} else if (getFleet()[i] instanceof CargoShip) {
				cargoShip++;
			} else {
				defaultShip++;
			}
		}
		
		System.out.printf("You have %d default ship(s), %d cruise ship(s), "
				+ "and %d cargo ship(s).\n", defaultShip, cruiseShip, cargoShip);
	}
	
	
	
	
	protected void closeScanner() {
		input.close();
	}



}
