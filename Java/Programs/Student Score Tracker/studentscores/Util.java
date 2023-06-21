package studentscores;

import java.io.FileReader;
import java.io.BufferedReader;
import java.util.StringTokenizer;
import java.io.IOException;

import exception.StudentGradingException;

public class Util {

	public static Student[] readFile(String filePath, Student[] classroom) throws StudentGradingException {
	    try {
	        // System.out.println("Reading file: " + filePath); // check if reading correctly
	        FileReader file = new FileReader(filePath);
	        BufferedReader buff = new BufferedReader(file);
	        buff.readLine(); // skips first line
	        String line;
	        int index = 0;

	        while ((line = buff.readLine()) != null) {
	           // System.out.println("Reading line: " + line); // check if reading correctly
	            StringTokenizer st = new StringTokenizer(line);
	            if (st.countTokens() == 6) {
	                // Get SID & Scores from File
	                int SID = Integer.parseInt(st.nextToken());
	                int scores[] = new int[5];
	                for (int i = 0; i < 5; i++) {
	                    scores[i] = Integer.parseInt(st.nextToken());
	                }
	                // Create Student object and set data
	                Student student;

	                if (filePath.equals(Constants.getChemistryFilePath())) {
	                    student = new ChemistryStudent(SID, scores);
	                } else if (filePath.equals(Constants.getComputerScienceFilePath())) {
	                    student = new ComputerScienceStudent(SID, scores);
	                } else {
	                    student = new Student(SID, scores);
	                }

	                classroom[index] = student;
	                index++;
	            }
	        }

	        buff.close();
	    } catch (IOException e) {
	        // Custom Error Handler
	        throw new StudentGradingException("Student Grading Exception: "
	                + "Error reading file", e);
	    } catch (ArrayIndexOutOfBoundsException e) {
	        // Custom Error Handler
	        throw new StudentGradingException("Student Grading Exception: "
	                + "Array Error", e);
	    }

	    return classroom;
	}

	public static void printScores(Student[] classroom) {
		System.out.println("Stud Qu1 Qu2 Qu3 Qu4 Qu5");
		for (int i = 0; i < classroom.length; i++) {
			if (classroom[i] != null) {
				System.out.printf("%4d %3d %3d %3d %3d %3d\n",
					classroom[i].getSID(), classroom[i].getScoreIndex(0),
					classroom[i].getScoreIndex(1), classroom[i].getScoreIndex(2),
					classroom[i].getScoreIndex(3), classroom[i].getScoreIndex(4));
			}
		}
		System.out.println();
	}
	
}