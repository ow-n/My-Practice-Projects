package adapter;

import studentscores.ClassRoom;
import studentscores.Statistics;
import studentscores.Student;

public class StudentAPIImpl implements StudentAPI {

    @Override
    public void printStudentStatistics(Student[] classroom) {
        Statistics stats = new Statistics();
        stats.findLow(classroom);
        stats.findHigh(classroom);
        stats.findAvg(classroom);
        stats.printLow();
        stats.printHigh();
        stats.printAvg();
    }

    @Override
    public void printScoreFromId(Student[] classroom, int id) {
        for (Student student : classroom) {
            if (student != null && student.getSID() == id) {
                try {
                    Student.printScores(new Student[]{student}, 0);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return;
            }
        }
        System.out.println("Student with ID " + id + " not found.");
    }

    @Override
    public void printStudentStatistics(ClassRoom gradeBook) {
    	Statistics stats = new Statistics();
    	
    	// Chemistry
    	System.out.println("Chemistry Class Stats");  
    	System.out.println("---------------------"); 
        stats.findLow(gradeBook.getChemClass());
        stats.findHigh(gradeBook.getChemClass());
        stats.findAvg(gradeBook.getChemClass());
        stats.printLow();
        stats.printHigh();
        stats.printAvg();
    
        // Computer Science
    	System.out.println("Computer Science Class Stats");  
    	System.out.println("---------------------"); 
    	stats.findLow(gradeBook.getCsClass());
    	stats.findHigh(gradeBook.getCsClass());
    	stats.findAvg(gradeBook.getCsClass());
    	stats.printLow();
    	stats.printHigh();
    	stats.printAvg();

    	// Default
    	System.out.println("Default Class Stats");   
    	System.out.println("---------------------"); 
    	stats.findLow(gradeBook.getDefaultClass());
    	stats.findHigh(gradeBook.getDefaultClass());
    	stats.findAvg(gradeBook.getDefaultClass());
    	stats.printLow();
    	stats.printHigh();
    	stats.printAvg();
    }
}