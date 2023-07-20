package driver;

import adapter.StudentAPI;
import adapter.StudentAPIImpl;
import exception.StudentGradingException;
import studentscores.Student;
import studentscores.Util;
import util.FileIO;

public class Driver3 {
	
	public static void main(String[] args) {
		
		final String filePath = "C:\\Users\\yuuyu\\"
				+ "Documents\\Eclipse Workspace\\Java_Intro\\src\\"
				+ "studentscores\\Student Scores";
		
		final String serFilePath = "C:\\Users\\yuuyu\\Documents\\"
				+ "Eclipse Workspace\\Java_Intro\\student_grades.ser";
		
		// New Student Array
		Student classroom36A[] = new Student[40];
		Student classroomDeserialized[] = new Student[40];
		
		try {
			// Initialized the array with objects
			classroom36A = Util.readFile(filePath, classroom36A);
			
			// Serializes each student in the array
			FileIO.serializeStudentGrade(classroom36A, serFilePath);
			
			// Deserializes the File and Prints it
			classroomDeserialized = FileIO.deserializeStudentGrade(serFilePath);
		} catch (StudentGradingException e) {
			e.printStackTrace();
		}

		StudentAPI studentAPI = new StudentAPIImpl();

		// Print student statistics
		System.out.println("Deserialized Student statistics:");
		studentAPI.printStudentStatistics(classroomDeserialized);

		// Print scores for a given student ID
		int studentID = 1234;
		System.out.printf("Scores for deserialized student with ID %04d:\n", studentID);
		studentAPI.printScoreFromId(classroomDeserialized, studentID);
		
		int fakeStudentId = 0000;
		System.out.printf("Scores for deserialized student with ID %04d:\n", fakeStudentId);
		studentAPI.printScoreFromId(classroomDeserialized, fakeStudentId);
		
	}
	
}