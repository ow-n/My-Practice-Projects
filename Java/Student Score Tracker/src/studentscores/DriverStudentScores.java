package studentscores;

import exception.StudentGradingException;

public class DriverStudentScores {
	
	public static void main(String[] args) {

		// New Student Array
		Student classroom36A[] = new Student[40];
		
		// Populate Classroom Array + Tests custom exception handler
		try {
			classroom36A = Util.readFile(Constants.getWrongFilePath(), classroom36A);
		} catch (StudentGradingException | NumberFormatException e1) {
			e1.printStackTrace();
			System.out.println();
			
			// Fixes exception
			try {
				classroom36A = Util.readFile(Constants.getDefaultFilePath(), classroom36A);
			} catch (StudentGradingException e) {
				System.out.println("Unexpected Exception");
				e.printStackTrace();
			}

		}
		
		
		// Tests if objects were initialized in array properly
//		for (int i = 0; i < classroom36A.length; i++)
//		    System.out.println(classroom36A[i].getScoreIndex(1));
		
		
		// Print Student Data
		try {
		Student.printScores(classroom36A, 1); // print student (x)
		} catch (StudentGradingException e2) {
			e2.printStackTrace();
		}
		Util.printScores(classroom36A);
		
		// Access stats methods
		Statistics stats = new Statistics();
		stats.findLow(classroom36A);
		stats.findHigh(classroom36A);
		stats.findAvg(classroom36A);

		//Print the data and statistics
		stats.printLow();
		stats.printHigh();
		stats.printAvg();
		
		
	}

}