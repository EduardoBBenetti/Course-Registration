package Server.CourseInfo;


import java.util.ArrayList;

/**
 * This class stores the registration information and handles total operation of registration of students.
 * It seraches for thr courses, checks pre-req and updates the registrations.
 * @author Gurmukh Singh, Dillon Sahadevan, Eduardo Benetti
 *
 */
public class RegistrationApp {
	
	/** 
	 * courseCatalogue object
	 */
	private CourseCatalogue cat;
	/**
	 * Arraylist of the students
	 */
	private ArrayList<Student> students;
	/**
	 * Array list of registrations
	 */
	private ArrayList<Registration> registrations;

	/**
	 * Constructor for class RegistrationApp
	 */
	public RegistrationApp(){
		cat = new CourseCatalogue();
		students = new ArrayList<Student>();
		registrations = new ArrayList<Registration>();
	}

	/**
	 * displays CourseCatalogue
	 * @return CourseCatalogue
	 */
	public CourseCatalogue displayCourseCatalogue(){
		return cat;
	}

	/**
	 * Searches the catalogue for the requested course.
	 * @param name the name of the course
	 * @param num the course number
	 * @return information oc course
	 */
	public String searchCatalogue(String name, int num){
		Course c = cat.searchCat(name, num);
		String result = c == null ? "" : c.toString();
		return result;
	}
	
	/**
	 * Searches the catalogue for the requested course.
	 * @param name the name of the course
	 * @param num the course number
	 * @return Course
	 */
	public Course checkCatalogue(String name, int num){
		return cat.searchCat(name, num);
	}
	
	/**
	 * Searches the student list for a requested student
	 * @param name the name of the student to search for.
	 * @return Student
	 */
	public Student searchStudent(String name)
	{
		for(Student st : students){
			if(st.getStudentName().equals(name)){
				return st;
			}
		}
		displayStudentNotFound();
		return null;
	}

	/**
	 * diaplays student not found error
	 */
	private void displayStudentNotFound(){
		System.err.println("Requested student not found.");
	}

	/**
	 * checks if student has completed pre-reqs for a given course
	 * @param st Student
	 * @param c Course
	 * @return true if pre-reqs are completed, false otherwise
	 */
	private boolean checkPrerequisites(Student st, Course c)
	{
		ArrayList<Course> preReq = c.getPreReq();
		for(int i = 0; i < preReq.size(); i++)
		{
			if(!st.checkIfCompleted(preReq.get(i))){
				System.err.println("This student has not completed all prerequisites");
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Removes a student from a course registration.
	 * @param st the student to remove.
	 * @param c the course to remove the student from.
	 */
	public void removeRegistration(Student st, Course c)
	{
		for(Registration reg : registrations)
		{
			if(reg.getTheStudent().getStudentId() == st.getStudentId() &&
				reg.getTheOffering().getTheCourse().getCourseName().equals(c.getCourseName()) &&
				reg.getTheOffering().getTheCourse().getCourseNum() == c.getCourseNum()){
				reg.removeRegistration();
				c.removeStudent();
			}
		}
	}

	/**
	 * Adds a student to a course
	 * @param st the student to add
	 * @param c the course to add the student to
	 * @return string of info
	 */
	public String addRegistration(Student st, Course c)
	{
		if(!checkPrerequisites(st, c)){
			return "Student does not have the prerequisites";
		}
		Registration reg = new Registration();
		CourseOffering co = c.getCourseOfferingAt(0);
		reg.completeRegistration(st, co);
		registrations.add(reg);
		c.addStudent();
		String result = c.isRunning() + reg.toString();
		return result;
	}
	
	/**
	 * Adds a student to the student list
	 * @param name the name of the student.
	 * @param id the id of the student.
	 */
	public void addStudent(String name, int id){
		Student st = new Student(name, id);
		students.add(st);
	}

	/**
	 * Adds a student to the student list.
	 * @param st the student to add to the list.
	 */
	public void addStudent(Student st){
		students.add(st);
	}
	
	/**
	 * Creates a course offering.
	 * @param c the course for which to add an offering.
	 * @param num the number of sections.
	 * @param cap the maximum capacity of the course offering.
	 */
	public void createCourseOffering(Course c, int num, int cap){
		cat.createCourseOffering(c, num, cap);
	}
	
	/**
	 * Returns the course catalogue
	 * @return the course catalogue
	 */
	public CourseCatalogue getCourseCatalogue() {
		return cat;
	}
	
	public String toString() {
		String st ="";
		for(Registration r: registrations) {
			st += r + "\1";
		}
		return st;
		
	}
}
