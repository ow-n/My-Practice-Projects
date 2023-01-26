package personalinfo;

// Public Personal Info Class
public class PersonalInfo{

	// 4 Parameters
	private String name;
	private int age;
	private String gender;
	private String nationality;
	

	// Constructor
	public PersonalInfo(String name, int age, String gender, String nationality) {
		this.name = name;
		this.age = age;
		this.nationality = nationality; // ERROR: swapped correctly
		this.gender = gender; // ERROR: swapped correctly
	}
	
	
	// Getter
	public String getName() {
		return name;
	}
	
	public int getAge() {
		return age;
	}
	
	public String getGender() {
		return gender; // ERROR: swapped correctly
	}
	
	
	public String getNationality() {
		return nationality; // ERROR: swapped correctly
	}
	
	
	// Setter
	public void setName(String name) {
		this.name = name;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public void setGender(String gender) {
		this.gender = gender; // ERROR: swapped correctly
	}
	
	public void setNationality(String nationality) {
		this.nationality = nationality; // ERROR: swapped correctly
	}
	
	
}