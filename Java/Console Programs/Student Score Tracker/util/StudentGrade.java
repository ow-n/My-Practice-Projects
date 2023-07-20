package util;

import java.io.Serializable;
import studentscores.Student;
import studentscores.Statistics;

public class StudentGrade implements Serializable {

	private static final long serialVersionUID = 1L;
	private Student student;
	private Statistics stats;

	public StudentGrade(Student student, Statistics stats) {
		this.student = student;
		this.stats = stats;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Statistics getStats() {
		return stats;
	}

	public void setStats(Statistics stats) {
		this.stats = stats;
	}

}