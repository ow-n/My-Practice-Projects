package savingsaccount;

class SavingsAccount {

	// Class Variables
	protected static final int MONTHS_IN_YEAR = 12;
	protected static final int DECIMAL_TO_PERCENT = 1;
	protected static double annualInterestRate = 0;
	private double savingsBal;
	private double lastSavingsBal;
	private double cashBal;
	private double lastCashBal;
	protected int month;
	private String name;
	private static int accountCounter;


	// Constructors
	protected SavingsAccount(double savingsBal) {
		accountCounter++;
		this.savingsBal = savingsBal;
		this.name = "Saver " + accountCounter;
	}

	protected SavingsAccount(double savingsBal, String name, double cashBal) {
		accountCounter++;
		this.savingsBal = savingsBal;
		this.name = name;
		this.cashBal = cashBal;
	}


	// Getters & Setters
	protected double getAnnualInterestRate() {
		return annualInterestRate;
	}

	protected static void setAnnualInterestRate(double annualInterestRate) {
		SavingsAccount.annualInterestRate = annualInterestRate;
	}

	protected double getSavingsBal() {
		return savingsBal;
	}

	protected void setSavingsBal(double savingsBal) {
		this.savingsBal = savingsBal;
	}

	protected double getLastSavingsBal() {
		return lastSavingsBal;
	}

	protected void setLastSavingsBal(double lastSavingsBal) {
		this.lastSavingsBal = lastSavingsBal;
	}

	protected double getCashBal() {
		return cashBal;
	}

	protected void setCashBal(double cashBal) {
		this.cashBal = cashBal;
	}

	protected double getLastCashBal() {
		return lastCashBal;
	}

	protected void setLastCashBal(double lastCashBal) {
		this.lastCashBal = lastCashBal;
	}

	protected int getMonth() {
		return month;
	}

	protected void setMonth(int month) {
		this.month = month;
	}

	protected String getName() {
		return name;
	}

	protected void setName(String name) {
		this.name = name;
	}

	// Methods
	protected void calculateMonthlyInterest() {
		double monthlyInterest = (getSavingsBal() * getAnnualInterestRate()) / MONTHS_IN_YEAR;
		if (getSavingsBal() < 0) { // Fixes negative negative balance calculations
			monthlyInterest *= -1;
		}
		setLastSavingsBal(getSavingsBal());
		setSavingsBal(getSavingsBal() + monthlyInterest);
		month++;
	}

	protected void modifyInterestRate(double newInterestRate) {
		annualInterestRate = newInterestRate;
	}

	protected void printSavingsBal() {
		System.out.printf("%s's savings balance for month %d is: $%.2f\n", getName(), getMonth(), getSavingsBal());
	}

	protected void depositCash(double depositAmt) {
		if (depositAmt <= getCashBal()) {
			setLastCashBal(getCashBal());
			setCashBal(getCashBal() - depositAmt);
			setLastSavingsBal(getSavingsBal());
			setSavingsBal(getSavingsBal() + depositAmt);
		} else {
			System.out.printf("%s does not have enough funds to deposit: $%.2f\n", getName(), depositAmt);
		}
	}

	protected void withdrawCash (double withdrawAmt){
		if (withdrawAmt <= getSavingsBal()) {
			setLastSavingsBal(getSavingsBal());
			setSavingsBal(getSavingsBal() - withdrawAmt);
			setLastCashBal(getCashBal());
			setCashBal(getCashBal() + withdrawAmt);
		} else {
			System.out.printf("You do not have enough funds to withdraw: $%.2f\n", withdrawAmt);
		}
	}

	protected void addCashBal(double addcashBalAmt) {
		setLastCashBal(getCashBal());
		setCashBal(getCashBal() + addcashBalAmt);
	}

	protected void printCashBal() {
		System.out.printf("%s's cash balance for month %d is: $%.2f\n", getName(), getMonth(), getCashBal());
	}

	protected void transferSavingsBal(SavingsAccount saverNum, double transferAmt) {
		if (transferAmt <= getSavingsBal()) {
			setLastSavingsBal(getSavingsBal());
			setSavingsBal(getSavingsBal() - transferAmt);
			saverNum.setLastSavingsBal(saverNum.getSavingsBal());
			saverNum.setSavingsBal(saverNum.getSavingsBal() + transferAmt);
		} else {
			System.out.printf("%s has insufficient funds. Available funds: $%.2f.\n", getName(), getSavingsBal());
		}

	}
}
