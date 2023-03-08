package util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.BufferedInputStream;

import java.util.List;
import java.util.ArrayList;

import exception.StudentGradingException;
import studentscores.ClassRoom;
import studentscores.Student;

public class FileIO {

	public static void serializeStudentGrade(Student[] classroom, String filePath) throws StudentGradingException {
		FileOutputStream fileOut = null;
		try {
			fileOut = new FileOutputStream(filePath);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);

			for (int i = 0; i < classroom.length; i++) {
				out.writeObject(classroom[i]);
			}

			out.close();
			fileOut.close();
			System.out.println("Serialized data is saved in " + filePath);
			System.out.println();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fileOut != null) {
					fileOut.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}


	public static Student[] deserializeStudentGrade(String filePath) throws StudentGradingException {
	    FileInputStream fileIn;
	    List<Student> studentList = new ArrayList<>();
	    try {
	        fileIn = new FileInputStream(filePath);
	        BufferedInputStream bufIn = new BufferedInputStream(fileIn);
	        ObjectInputStream in = new ObjectInputStream(bufIn);

	        // Read objects from file and cast to Student
	        System.out.println("Deserialized data from " + filePath);
	        System.out.println();
	        Student student = null;
	        while ((student = (Student) in.readObject()) != null) {
	            studentList.add(student);
	        }

	        in.close();
	        bufIn.close();
	        fileIn.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    }
	    // Convert List<Student> to Student[]
	    Student[] classroom = new Student[studentList.size()];
	    return studentList.toArray(classroom);
	}


	public static void serializeGradeBook(ClassRoom gradeBook) {
		final String serFilePath = "C:\\Users\\yuuyu\\Documents\\"
				+ "Eclipse Workspace\\Java_Intro\\student_grades.ser";

		// Code to read the data into the arrays goes here
		try {
			// Serialize the arrays to a file
			FileOutputStream fos = new FileOutputStream(serFilePath);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(gradeBook);
			oos.close();
			fos.close();

			System.out.println("Serialized gradebook data is saved in " + serFilePath);
			System.out.println();
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	
	public static ClassRoom deserializeGradeBook(String serFilePath) {
	    ClassRoom gradeBook = null;

	    try {
	        FileInputStream fis = new FileInputStream(serFilePath);
	        ObjectInputStream ois = new ObjectInputStream(fis);
	        gradeBook = (ClassRoom) ois.readObject();
	        ois.close();
	        fis.close();
	        
	        // Read objects from file and cast to Student
	        System.out.println("Deserialized data from " + serFilePath);
	        System.out.println();
	    } catch (Exception ex) {
	        ex.printStackTrace();
	    }

	    return gradeBook;
	}
	
}