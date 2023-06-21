package studentscores;

public class Constants {

    private static final String DEFAULT_FILE_PATH = "C:\\Users\\yuuyu\\Documents\\Eclipse Workspace\\Java_Intro\\src\\studentscores\\Student Scores";
    private static final String COMPUTER_SCIENCE_FILE_PATH = "C:\\Users\\yuuyu\\Documents\\Eclipse Workspace\\Java_Intro\\src\\studentscores\\Computer Science Scores";
    private static final String CHEMISTRY_FILE_PATH = "C:\\Users\\yuuyu\\Documents\\Eclipse Workspace\\Java_Intro\\src\\studentscores\\Chemistry Scores";
    private static final String SER_FILE_PATH = "C:\\Users\\yuuyu\\Documents\\Eclipse Workspace\\Java_Intro\\student_grades.ser";
	private final static String WRONG_FILE_PATH = "C:\\Users\\yuuyu";
    
    public static String getDefaultFilePath() {
        return DEFAULT_FILE_PATH;
    }

    public static String getComputerScienceFilePath() {
        return COMPUTER_SCIENCE_FILE_PATH;
    }

    public static String getChemistryFilePath() {
        return CHEMISTRY_FILE_PATH;
    }

    public static String getSerFilePath() {
        return SER_FILE_PATH;
    }

	public static String getWrongFilePath() {
		return WRONG_FILE_PATH;
	}
}
