package gradebook;

import java.io.Serializable;
import studentscores.Student;

public class Chemistry implements Serializable {

	private static final long serialVersionUID = 1L;
	private Student[] students = new Student[40];

	public Chemistry() {
	}

	public void addStudent(Student student, int index) {
		students[index] = student;
	}

	public Student getStudent(int index) {
		return students[index];
	}

	public Student[] getStudents() {
		return students;
	}

	public void setStudents(Student[] students) {
		this.students = students;
	}
}