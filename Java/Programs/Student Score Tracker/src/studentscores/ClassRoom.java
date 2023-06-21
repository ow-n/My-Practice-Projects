package studentscores;

import java.io.Serializable;

public class ClassRoom implements Serializable{

	private static final long serialVersionUID = 1L;
    private Student[] chemClass;
    private Student[] csClass;
    private Student[] defaultClass;

    public ClassRoom() { 
	}
    
    public ClassRoom(Student[] chemClass, Student[] csClass, Student[] defaultClass) {
		this.chemClass = chemClass;
		this.csClass = csClass;
		this.defaultClass = defaultClass;
	}

	public Student[] getChemClass() {
		return chemClass;
	}

	public void setChemClass(Student[] chemClass) {
		this.chemClass = chemClass;
	}

	public Student[] getCsClass() {
		return csClass;
	}

	public void setCsClass(Student[] csClass) {
		this.csClass = csClass;
	}

	public Student[] getDefaultClass() {
		return defaultClass;
	}

	public void setDefaultClass(Student[] defaultClass) {
		this.defaultClass = defaultClass;
	}

}