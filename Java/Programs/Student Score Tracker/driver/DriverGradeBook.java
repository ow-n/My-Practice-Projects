package driver;

import exception.StudentGradingException;
import studentscores.ChemistryStudent;
import studentscores.ComputerScienceStudent;
import studentscores.Student;
import studentscores.Util;
import util.FileIO;
import studentscores.ClassRoom;
import adapter.StudentAPI;
import adapter.StudentAPIImpl;
import studentscores.Constants;

public class DriverGradeBook {

	public static void main(String[] args) {

		// Instantiating classes
		Student chemClass[] = new ChemistryStudent[40];
		Student csClass[] = new ComputerScienceStudent[40];
		Student defaultClass[] = new Student[40];

		try {
			// Initialize the arrays with objects
			chemClass = Util.readFile(Constants.getChemistryFilePath(), chemClass);
			csClass = Util.readFile(Constants.getComputerScienceFilePath(), csClass);
			defaultClass = Util.readFile(Constants.getDefaultFilePath(), defaultClass);
			
			// Create Grade Book Object
			ClassRoom gradeBook = new ClassRoom(chemClass, csClass, defaultClass);
			
			ClassRoom deserializedGradeBook = new ClassRoom();
			
			// Serialize each Class in Grade Book
			FileIO.serializeGradeBook(gradeBook);
			
			// Deserialize Grade Book
			deserializedGradeBook = FileIO.deserializeGradeBook(Constants.getSerFilePath());
			
			System.out.println("* * * * * * * * * * * * * * * * * * *\n");
			
			// Print Grade Book
			StudentAPI studentAPI = new StudentAPIImpl();

			//System.out.println("gradebook before");
			//studentAPI.printStudentStatistics(gradeBook);
			System.out.println("Gradebook read from serialized file:");
			System.out.println("====================================");
			studentAPI.printStudentStatistics(deserializedGradeBook);

		} catch (StudentGradingException e) {
			e.printStackTrace();
		}
		
	}


}