package salesdata;

public class Store {

	private float salesByWeek[][];
	protected float totalSalesForWeek[] = new float[5];
	protected float avgSalesForWeek[] = new float[5];
	protected float totalSalesForAllWeeks = 0.0f;
	protected float averageWeeklySales = 0.0f;
	protected int weekWithHighestSaleAmt = 0;
	protected int weekWithLowestSaleAmt = 0;

	Store() {
		salesByWeek = new float[5][7];
	}

	// Getter and Setters
	public void setSaleForWeekdayIntersection(int week, int day, float sale) {
		salesByWeek[week][day] = sale;
	}

	public void printData() { // For FileIO class
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 7; j++) {
				System.out.print(salesByWeek[i][j] + " ");
			}
			System.out.println("");
		}
	}

	float [] getSalesForEntireWeek(int week) {
		return salesByWeek[week];
	}

	float getSaleForWeekdayIntersection(int week, int day) {
		return salesByWeek[week][day];
	}

	// Business Methods
	// Option A - Calculate Sale for Week
	public float[] getSalesForWeek() {
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 7; j++) {
				totalSalesForWeek[i] += salesByWeek[i][j];
			}
		}
		return totalSalesForWeek;
	}


	// Option B - Calculate Average Sale for Each Week
	public float[] getAvgSalesEachWeek() {
		for (int i = 0; i < 5; i++) {
			avgSalesForWeek[i] = totalSalesForWeek[i] / 7.0f;
			averageWeeklySales += totalSalesForWeek[i];
		}
		return avgSalesForWeek;
	}


	// Option C - Calculate Total Sales for All Weeks
	public float getTotalSalesForAllWeeks() {
		for (int i = 0; i < 5; i++) {
			float totalSales = 0.0f;
			for (int j = 0; j < 7; j++) {
				totalSales += salesByWeek[i][j];
				totalSalesForAllWeeks += salesByWeek[i][j];
			}
			totalSalesForWeek[i] = totalSales;
		}
		return totalSalesForAllWeeks;
	}

	// Option D - Calculate Average Sale for All Weeks
	public float getAverageWeeklySales() {
		for (int i = 0; i < 5; i++) {
			avgSalesForWeek[i] = totalSalesForWeek[i] / 7.0f;
			averageWeeklySales += totalSalesForWeek[i];
		}
		averageWeeklySales /= 5.0f;
		return averageWeeklySales;
	}


	// Option E - Calculate Week with Highest Sale Amount
	public int getWeekWithHighestSaleAmt() {
		for (int i = 1; i < 5; i++) {
			if (totalSalesForWeek[i] > totalSalesForWeek[weekWithHighestSaleAmt]) {
				weekWithHighestSaleAmt = i;
			}
		}
		return weekWithHighestSaleAmt;
	}


	// Option F - Calculate Week with Lowest Sale Amount
	public int getWeekWithLowestSaleAmt() {
		for (int i = 1; i < 5; i++) {
			if (totalSalesForWeek[i] < totalSalesForWeek[weekWithLowestSaleAmt]) {
				weekWithLowestSaleAmt = i;
			}
		}
		return weekWithLowestSaleAmt;
	}

}