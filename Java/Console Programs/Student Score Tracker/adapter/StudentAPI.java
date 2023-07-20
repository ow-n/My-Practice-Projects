package adapter;

import studentscores.ClassRoom;
import studentscores.Student;

public abstract interface StudentAPI {
	// For Driver 3
	void printStudentStatistics(Student[] classroom);
    void printScoreFromId(Student[] classroom, int id);
    
    // For Grade Book Driver
	void printStudentStatistics(ClassRoom gradeBook);
}
