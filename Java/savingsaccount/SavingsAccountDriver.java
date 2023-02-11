package savingsaccount;

class SavingsAccountDriver {

	public static void main(String[] args) {

		// Two Saving Accounts created
		SavingsAccount saver1 = new SavingsAccount(-500);
		SavingsAccount saver2 = new SavingsAccount(500);

		// Calculate Month 1 for APR: 4%
		SavingsAccount.setAnnualInterestRate(-0.02);
		System.out.printf("The Annual Interest Rate has been set to %.0f%%.\n", saver2.getAnnualInterestRate() * 100);
		saver1.calculateMonthlyInterest();
		System.out.printf("%s's monthly interest added for month %d.\n",saver1.getName(), saver1.getMonth());
		saver2.calculateMonthlyInterest();
		System.out.printf("%s's monthly interest added for month %d.\n\n",saver2.getName(), saver2.getMonth());

		saver1.printSavingsBal();
		saver2.printSavingsBal();

		// Calculate Month 2 for APR: 5%
		SavingsAccount.setAnnualInterestRate(0.05);
		System.out.printf("\nThe Annual Interest Rate has been set to %.0f%%.\n", saver2.getAnnualInterestRate() * 100);
		saver1.calculateMonthlyInterest();
		System.out.printf("%s's monthly interest added for month %d.\n",saver1.getName(), saver1.getMonth());
		saver2.calculateMonthlyInterest();
		System.out.printf("%s's monthly interest added for month %d.\n\n",saver2.getName(), saver2.getMonth());

		saver1.printSavingsBal();
		saver2.printSavingsBal();

	}

}