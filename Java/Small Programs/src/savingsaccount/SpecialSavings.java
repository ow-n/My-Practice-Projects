package savingsaccount;

import java.util.Scanner;

class SpecialSavings extends SavingsAccount{

	// Class Variables
	private static final double SPECIAL_SAVINGS_RATE = 0.10; // 10% APR
	protected static final double SPECIAL_SAVINGS_REQUIREMENT = 10000;
	private final String USER_YES = "y";
	private static Scanner input = new Scanner(System.in);
	protected String proceed;

	// Constructor
	protected SpecialSavings(double savingsBal) {
		super(savingsBal);
	}

	protected SpecialSavings(double savingsBal, String name, double cash) {
		super(savingsBal, name, cash);
	}

	protected String getProceed() {
		return proceed;
	}

	protected void setProceed(String proceed) {
		this.proceed = proceed;
	}


	// Methods
	protected void calculateMonthlyInterest() {
		if (getSavingsBal() >= SPECIAL_SAVINGS_REQUIREMENT) {
			double monthlyInterest = (getSavingsBal() * SPECIAL_SAVINGS_RATE) / MONTHS_IN_YEAR;
			setLastSavingsBal(getSavingsBal());
			setSavingsBal(getSavingsBal() + monthlyInterest);
			System.out.println("Special interest rate successfully applied.");
			month++;
		} else {
			System.out.printf("%s's savings balance does not meet the minimum $10k requirement ($%.2f / $%.2f)\n",
					getName(), getSavingsBal(), SPECIAL_SAVINGS_REQUIREMENT);

			System.out.print("Do you wish to apply the regular interest rate instead ('y' - yes, anything else for no): ");
			setProceed(input.next());
			setProceed(getProceed().toLowerCase());
			if (getProceed() == "yes") {
				setProceed("y");
			}
			if (proceed.equals(USER_YES)) {
				super.calculateMonthlyInterest();
				System.out.printf("Regular interest rates has been applied for month %1d.\n", getMonth());
			} else {
				System.out.printf("Interest has not been applied for month %d.\n", getMonth() + 1);
			} 
		}
	}
	
	protected static void closeScanner() {
		input.close();
	}

}