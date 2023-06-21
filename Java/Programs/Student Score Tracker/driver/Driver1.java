package driver;

import exception.StudentGradingException;
import studentscores.Constants;
import studentscores.Student;
import studentscores.Util;

public class Driver1 {

	// Demonstration: Exception Handler
	public static void main(String[] args) {

		// Intentional Error: too small array size < 40
	    Student[] classroom = new Student[10];
	    
	    try {
			classroom = Util.readFile(Constants.getWrongFilePath(), classroom);
		} catch (StudentGradingException e1) {
			e1.printStackTrace();
			System.out.println();
			
			// Fix Exception 1
			try {
				classroom = Util.readFile(Constants.getDefaultFilePath(), classroom);
			} catch (StudentGradingException e2) {
				e2.printStackTrace();
				System.out.println();
				
				// Fix Exception 2
				try {
				    Student[] classroom2 = new Student[40];
					classroom = Util.readFile(Constants.getDefaultFilePath(), classroom2);
				} catch (StudentGradingException e3) {
					e3.printStackTrace();
					System.out.println("\n Error: Unhandled exceptions.");
				}
			}
		}
	    System.out.println("If student's scores are printed, then exceptions are fixed.\n");
	    try {
	    	Student.printScores(classroom, 1);
	    	System.out.println("[Fixed]\n");
	    } catch (StudentGradingException e2) {
	    	e2.printStackTrace();
	    }
	    System.out.println("End of program.");
	}
	
}