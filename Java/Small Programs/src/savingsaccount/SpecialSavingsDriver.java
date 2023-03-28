package savingsaccount;

class SpecialSavingsDriver {

	public static void main(String[] args) {

		// Sets Interest Rate
		SavingsAccount.setAnnualInterestRate(0.04);

		// Two Saving Accounts created
		SpecialSavings saver1 = new SpecialSavings(2000.00);
		SpecialSavings saver2 = new SpecialSavings(3000.00, "Owen", 2000);

		System.out.println("[Checking if variables work]\n");

		// Starting Values
		saver1.printSavingsBal();
		saver1.printCashBal();
		saver2.printSavingsBal();
		saver2.printCashBal();
		saver2.depositCash(4000); // Test not enough Cash Balance


		System.out.println(); // Adds Cash to Savings
		saver2.addCashBal(5000.05);
		System.out.printf("%s added $%.2f to their cash amount.\n", saver2.getName(), 
				saver2.getCashBal() - saver2.getLastCashBal());
		saver2.printCashBal();
		saver2.depositCash(6000);
		System.out.printf("%s deposited $%.2f to their savings account.\n", saver2.getName(),
				saver2.getSavingsBal() - saver2.getLastSavingsBal());
		saver2.printSavingsBal();
		saver2.printCashBal();

		System.out.println("\n[Checking if other class methods work]");

		System.out.println(""); // Tests Interest Calculations
		saver2.depositCash(999.99);
		System.out.printf("%s deposited $%.2f to their savings account.\n", saver2.getName(),
				Math.abs(saver2.getSavingsBal() - saver2.getLastSavingsBal()));

		// Repeat calculateMonthlyInterest until eligible for Special Savings Rate
		while (saver2.getSavingsBal() < SpecialSavings.SPECIAL_SAVINGS_REQUIREMENT) {
			System.out.println("\nWe will now attempt to apply Special APR to saver2:");
			saver2.calculateMonthlyInterest();
			saver2.printSavingsBal();
			saver2.printCashBal();
		}

		if (saver2.getSavingsBal() >= SpecialSavings.SPECIAL_SAVINGS_REQUIREMENT) {
			System.out.println("\nNow we have enough for the Special Interest Rate."); 
		}

		// Tests Special Interest Calculations
		System.out.println("\nWe will now attempt to apply Special APR to saver2 again:");
		saver2.calculateMonthlyInterest();
		System.out.printf("%s's monthly interest of $%.2f was added for month %d.\n",
				saver2.getName(), (saver2.getSavingsBal() - saver2.getLastSavingsBal()), saver2.getMonth());
		saver2.printSavingsBal();

		// Tests Calculations if Withdraw Savings Balance
		System.out.println(); 
		saver2.withdrawCash(saver2.getSavingsBal() - SpecialSavings.SPECIAL_SAVINGS_REQUIREMENT + 0.01); // 1 Cent under 
		System.out.printf("%s withdrew $%.2f\n", saver2.getName(), saver2.getLastSavingsBal() - saver2.getSavingsBal());
		saver2.printSavingsBal();
		saver2.calculateMonthlyInterest();
		saver2.printSavingsBal();

		System.out.println("\n[Checking if Savings Accounts can interact with each other]");
		System.out.println("We added a new feature: Transfering Balances!");

		System.out.println("\nSaver 1's variables should be unaffected:");
		saver1.printSavingsBal();
		saver2.transferSavingsBal(saver1, 8000);
		System.out.printf("%s transfered $%.2f to %s.\n", saver2.getName(),
				saver2.getLastSavingsBal() - saver2.getSavingsBal(), saver1.getName());
		saver1.printSavingsBal();
		saver2.printSavingsBal();

		System.out.println("\nSaver 1 now has enough savings balance.\n");
		System.out.println("We will now attempt to apply Special APR to saver1:");
		saver1.calculateMonthlyInterest();
		saver1.printSavingsBal();

		System.out.println("\nThey will try to transfer their max savings balance + 0.01:");
		saver1.transferSavingsBal(saver2, saver1.getSavingsBal() + 0.01);
		System.out.println("\nThey will now transfer their max savings balance");
		saver1.transferSavingsBal(saver2, saver1.getSavingsBal());
		saver2.printSavingsBal();
		saver1.printSavingsBal();


		SpecialSavings.closeScanner();
	}

}
