package Server.CourseInfo;

/**
 * This class stores the registration information and handles registration of students.
 * @author Gurmukh Singh, Dillon Sahadevan, Eduardo Benetti
 *
 */
public class Registration {
	/**
	 * Student object
	 */
	private Student theStudent;
	/**
	 * CourseOffering object
	 */
	private CourseOffering theOffering;
	/**
	 * grade of student
	 */
	private char grade;
	
	/**
	 * Function to complete a registration of a student into a course.
	 * @param st the Student to add to the course.
	 * @param of the course offering to which the student should be added to.
	 */
	public void completeRegistration (Student st, CourseOffering of) {
		theStudent = st;
		theOffering = of;
		addRegistration ();
	}

	/**
	 * Removes the student from the registration from student registration and
	 * course offering registration.
	 * 
	 */
	public void removeRegistration()
	{
		theStudent.removeRegistration(this);
		theOffering.removeRegistration(this);
		System.out.println("Registration removed");
	}
	
	/**adds registration to the student and the courseOffering.
	 * 
	 */
	private void addRegistration () {
		theStudent.addRegistration(this);
		theOffering.addRegistration(this);
	}
	
	/**
	 * returns Student object
	 * @return Student object
	 */
	public Student getTheStudent() {
		return theStudent;
	}
	/**
	 * sets the Student to a given Student object
	 * @param theStudent Student
	 */
	public void setTheStudent(Student theStudent) {
		this.theStudent = theStudent;
	}
	/**
	 * returns CourseOffering
	 * @return CourseOffering
	 */
	public CourseOffering getTheOffering() {
		return theOffering;
	}
	
	/**
	 * sets CourseOffering
	 * @param theOffering CourseOffering
	 */
	public void setTheOffering(CourseOffering theOffering) {
		this.theOffering = theOffering;
	}
	/**
	 * returns grade
	 * @return grade
	 */
	public char getGrade() {
		return grade;
	}
	/**
	 * sets grade
	 * @param grade grade
	 */
	public void setGrade(char grade) {
		this.grade = grade;
	}
	
	/**
	 * Overrides toString method of Regsitration
	 * prints Student, offering ad grade
	 */
	@Override
	public String toString () {
		String st = "\n";
		st += getTheStudent() + "\1";
		st += "The Offering: " + getTheOffering ();
		st += "Grade: " + getGrade() + "\1";
		return st;
		
	}
	

}