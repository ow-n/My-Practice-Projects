package driver;

import studentscores.Constants;
import studentscores.Student;
import studentscores.Util;
import util.FileIO;
import exception.StudentGradingException;

public class Driver2 {

	public static void main(String[] args) {
		
		// New Student Array
		Student classroom36A[] = new Student[40];

		// Populate Classroom Array + Tests custom exception handler
		try {
			// Initialized the array with objects
			classroom36A = Util.readFile(Constants.getDefaultFilePath(), classroom36A);
			
			// Serializes the students in the classroom array
			FileIO.serializeStudentGrade(classroom36A, Constants.getSerFilePath());
		
			// Deserializes the File and Prints it
			Util.printScores(FileIO.deserializeStudentGrade(Constants.getSerFilePath()));
			
		} catch (StudentGradingException e) {
			e.printStackTrace();
		}

	}

}