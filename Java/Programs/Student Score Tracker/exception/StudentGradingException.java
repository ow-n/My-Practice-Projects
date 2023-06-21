package exception;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class StudentGradingException extends Exception {

    private static final long serialVersionUID = 1L;

    public StudentGradingException(String message, Throwable cause) {
        super(message, cause);
        exceptionFileOutput(cause);
    }

    private void exceptionFileOutput(Throwable cause) {
        try {
            FileWriter fileWriter = new FileWriter("exceptions.log", true);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.println("Student Grading Exception");
            cause.printStackTrace(printWriter);
            printWriter.println(); // add new line
            printWriter.flush();
            printWriter.close();
        } catch (IOException e) {
            System.err.println("Error writing exception to log file: " + e.getMessage());
        }
    }
}