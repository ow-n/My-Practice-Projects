package salesdata;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Driver {

	public static void main(String[] args) {
		// Creates FileIO object to read data from the file
		FileIO a1 = new FileIO("C:\\Users\\yuuyu\\Documents\\Eclipse Workspace\\Java_Intro_Labs\\src\\salesdata\\Salesdat.txt");

		// Read data from the file and store it in a Franchise object
		Franchise f = a1.readData();

		Scanner scanner = new Scanner(System.in);
		String again; // String instead of Char to reject 'y....' answers

		do { // User Interface
			boolean isValidInput = false;
			int storeNumber = 0;
			char infoOption = 'a';
			
			// Input for Store Number
			System.out.print("\nPlease select a store (1-6): ");
			while (!isValidInput) { 
				try {;
					storeNumber = scanner.nextInt();
					
					if (storeNumber >= 1 && storeNumber <= 6) {
						isValidInput = true;
					} else {
						System.out.println("Invalid input. Please enter a value between 1 and 6: ");
					}
				} catch (InputMismatchException e) {
					System.out.println("Invalid input. Please enter a valid integer value: ");
					scanner.next(); // clear scanner buffer
				}
			}

			// Input for Letter Option
			isValidInput = false;
			System.out.print("\nPlease select an option (a-g): ");
			while (!isValidInput) { 
				try {
					String inputTest = scanner.next();

					if (inputTest.length() == 1) {
						infoOption = inputTest.charAt(0);
						if ((infoOption >= 'a' && infoOption <= 'g') || (infoOption >= 'A' && infoOption <= 'G')) {
							isValidInput = true;
							infoOption = Character.toLowerCase(infoOption);
						} else {
							System.out.println("Invalid input. Please enter a character between 'a' and 'g': ");
						}
					} else {
						System.out.println("Invalid input. Please enter a single character: ");
					}
				} catch (InputMismatchException e) {
					System.out.println("Invalid input. Please enter a valid character value: ");
					scanner.next();
				}
			}

			System.out.println("\nStore " + (storeNumber));
			System.out.println("========================");

			// Print Options
			switch(infoOption) {
			case 'a':
				float salesForWeek[] = f.getStores(storeNumber - 1).getSalesForWeek();
				System.out.println("a) Total Sales For Week: ");
				for (int i = 0; i < 5; i++) {
					System.out.printf("Week %d: $%.2f\n", i + 1, salesForWeek[i]);
				}
				break;

			case 'b':
				f.getStores(storeNumber - 1).getSalesForWeek();
				float avgSalesEachWeek[] = f.getStores(storeNumber - 1).getAvgSalesEachWeek();
				System.out.println("b) Avgerage Sales For Week: ");
				for (int i = 0; i < 5; i++) {
					System.out.printf("Week %d: $%.2f\n", i + 1, avgSalesEachWeek[i]);
				}
				break;

			case 'c':
				float totalSalesForAllWeeks = f.getStores(storeNumber - 1).getTotalSalesForAllWeeks();
				System.out.printf("c) Total Sales For All Weeks: $%.2f\n", totalSalesForAllWeeks);
				break;

			case 'd':
				f.getStores(storeNumber - 1).getSalesForWeek();
				float averageWeeklySales = f.getStores(storeNumber - 1).getAverageWeeklySales();
				System.out.printf("d) Average Weekly Sales: $%.2f\n", averageWeeklySales);
				break;

			case 'e':
				f.getStores(storeNumber - 1).getSalesForWeek();
				int weekWithHighestSaleAmt = f.getStores(storeNumber - 1).getWeekWithHighestSaleAmt();
				System.out.printf("e) Week With Highest Sale Amout: Week %d\n", weekWithHighestSaleAmt + 1);
				break;

			case 'f':
				f.getStores(storeNumber - 1).getSalesForWeek();
				int weekWithLowestSaleAmt = f.getStores(storeNumber - 1).getWeekWithLowestSaleAmt();
				System.out.printf("f) Week With Highest Sale Amout: Week %d\n", weekWithLowestSaleAmt + 1);
				break;

			case 'g':
				float salesForWeekG[] = f.getStores(storeNumber - 1).getSalesForWeek();
				System.out.println("a) Total Sales For Week: ");
				for (int i = 0; i < 5; i++) {
					System.out.printf("Week %d: $%.2f\n", i + 1, salesForWeekG[i]);
				}

				float avgSalesEachWeekG[] = f.getStores(storeNumber - 1).getAvgSalesEachWeek();
				System.out.println("b) Avgerage Sales For Week: ");
				for (int i = 0; i < 5; i++) {
					System.out.printf("Week %d: $%.2f\n", i + 1, avgSalesEachWeekG[i]);
				}

				float totalSalesForAllWeeksG = f.getStores(storeNumber - 1).getTotalSalesForAllWeeks();
				System.out.printf("c) Total Sales For All Weeks: $%.2f\n", totalSalesForAllWeeksG);

				float averageWeeklySalesG = f.getStores(storeNumber - 1).getAverageWeeklySales();
				System.out.printf("d) Average Weekly Sales: $%.2f\n", averageWeeklySalesG);

				int weekWithHighestSaleAmtG = f.getStores(storeNumber - 1).getWeekWithHighestSaleAmt();
				System.out.printf("e) Week With Highest Sale Amount: Week %d\n", weekWithHighestSaleAmtG + 1);

				int weekWithLowestSaleAmtG = f.getStores(storeNumber - 1).getWeekWithLowestSaleAmt();
				System.out.printf("f) Week With Highest Sale Amount: Week %d\n", weekWithLowestSaleAmtG + 1);
			}

			// Runs program again
			System.out.println("\nDo you want to find something else out?");
			System.out.print("(Type 'y' for yes or any other character to terminate the program.) ");
			
			again = scanner.next();
			if (again.equals("Y") || again.equals("yes") || again.equals("Yes")) {
				again = "y";
			} else if (!again.equals("y")){
				System.out.print("\nTerminating program.");
			}

		} while(again.equals("y"));

		scanner.close();

	}
}