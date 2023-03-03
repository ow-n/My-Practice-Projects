package studentscores;

import java.io.Serializable;

public class Statistics implements Serializable {

	private static final long serialVersionUID = 1L;
	private int[] lowscores = new int[5];
	private int[] highscores = new int[5];
	private float[] avgscores = new float[5];

	// Finds Lowest Score and stores it in an array named lowScores.
	public void findLow(Student[] classroom) {
	    // Loop for each test
	    
		for (int t = 0; classroom[0] != null && t < classroom[0].getScores().length; t++) {
	        int lowestScore = classroom[t].getScoreIndex(0); // sets lowest score to first score
	        // Loop each student and checks their score for the current test
	        for (int i = 0; i < classroom.length; i++) {
	            if (classroom[i] != null && classroom[i].getScoreIndex(t) < lowestScore) {
	            	lowestScore = classroom[i].getScoreIndex(t);
	            }
	        }
	        lowscores[t] = lowestScore;
	    }
	}

	public void printLow() {
		System.out.println("Lowest Scores");
		for (int i = 0; i < lowscores.length; i++) {
			System.out.printf("Test %d: %d\n", i+1, lowscores[i]);
		}
		System.out.println();
	}
	
	// Finds Highest Score and stores it in an array named lowScores.
	public void findHigh(Student[] classroom) {
	    // Loop for each test
	    for (int t = 0; classroom[t] != null && t < classroom[0].getScores().length; t++) {
	        int highestScore = classroom[t].getScoreIndex(0); // sets highest score to first score
	        // Loop each student and checks their score for the current test
	        for (int i = 0; i < classroom.length; i++) {
	            if (classroom[i] != null && classroom[i].getScoreIndex(t) > highestScore) {
	                highestScore = classroom[i].getScoreIndex(t);
	            }
	        }
	        highscores[t] = highestScore;
	    }
	}

	public void printHigh() {
		System.out.println("Highest Scores");
		for (int i = 0; i < highscores.length; i++) {
			System.out.printf("Test %d: %d\n", i+1, highscores[i]);
		}
		System.out.println();
	}
	
	
	// Finds Average Score for each quiz and stores it in an array named avgScores.
	public void findAvg(Student[] classroom) {
	    // Loop for each test
	    for (int t = 0; classroom[t] != null && t < classroom[0].getScores().length; t++) {
			int totalScore = 0, testTakers = 0;
	        // Loop each student and checks their score for the current test
	        for (int i = 0; i < classroom.length; i++) {
	        	if (classroom[i] != null) {
	        		totalScore += classroom[i].getScoreIndex(t);
	        		testTakers++;
	        	}
	        }
	        avgscores[t] = (float) totalScore / testTakers;
	    }
	}

	public void printAvg() {
		System.out.println("Average Scores");
		for (int i = 0; i < highscores.length; i++) {
			System.out.printf("Test %d: %.2f\n", i+1, avgscores[i]);
		}
		System.out.println();
	}
	
	
}