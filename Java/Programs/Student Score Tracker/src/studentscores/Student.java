package studentscores;

import java.io.Serializable;

import exception.StudentGradingException;

public class Student implements Serializable {

	// Variables
	private static final long serialVersionUID = 1L;
	private int SID; // Student ID
	private int scores[] = new int[5];

	// Constructors
	public Student(int SID, int scores[]) {
		this.SID = SID;
		this.scores = scores;
	}
	
	public Student(int SID) {
		this.SID = SID;
	}

	// Getters & Setters
	public int getSID() {
		return SID;
	}

	public void setSID(int sID) {
		SID = sID;
	}

	public int[] getScores() {
		return scores;
	}
	
	public int getScoreIndex(int index) {
		return scores[index];
	}

	public void setScores(int[] scores) {
		this.scores = scores;
	}

	// Print Methods
	public void printSID() {
		System.out.println("Student ID: " + SID);
	}

	public static void printScores(Student[] classroom, int pupil) throws StudentGradingException {
		System.out.printf("Student %d's Scores\n", pupil);
		for (int i = 0; i < classroom[pupil].scores.length; i++) {
			System.out.printf("Exam %d: %d\n", i+1, classroom[pupil].getScoreIndex(i));
		}
		System.out.println();
	}
}