package Server.CourseInfo;

import java.util.ArrayList;

/**
 * Stores teh student information and handle its functionality
 * @author Gurmukh Singh, Dillon Sahadevan, Eduardo Benetti
 *
 */
public class Student {
	
	/**
	 * name of the student
	 */
	private String studentName;
	/**
	 * ID of student
	 */
	private int studentId;
	/**
	 * Array list of registrations
	 */
	private ArrayList<Registration> studentRegList;
	/**
	 * Array list of completed courses
	 */
	private ArrayList<Course> completedCourses;
	
	/**
	 * Constructor for class student
	 * @param studentName the name of the student
	 * @param studentId the id of the student
	 */
	public Student (String studentName, int studentId) {
		this.setStudentName(studentName);
		this.setStudentId(studentId);
		studentRegList = new ArrayList<Registration>();
		completedCourses = new ArrayList<Course>();
	}

	/**
	 * returns Student name
	 * @return Student name
	 */
	public String getStudentName() {
		return studentName;
	}

	/**
	 * sets  Student name
	 * @param studentName  Student name
	 */
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	/**
	 * returns student ID
	 * @return student ID
	 */
	public int getStudentId() {
		return studentId;
	}

	/**
	 * sets Student ID
	 * @param studentId Student ID
	 */
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	/**
	 * Overrides toString method of Student
	 * Prints student name, ID
	 */
	@Override
	public String toString () {
		String st = "Student Name: " + getStudentName() + "\1" +
				"Student Id: " + getStudentId() + "\1";
		return st;
	}

	/**
	 * adds registration to studentRegList
	 * @param registration registration
	 */
	public void addRegistration(Registration registration) {
		// TODO Auto-generated method stub
		studentRegList.add(registration);
	}

	/**
	 * removes registration to studentRegList
	 * @param reg registration
	 */
	public void removeRegistration(Registration reg) {
		studentRegList.remove(reg);
	}

	/**
	 * add course to completedCourses
	 * @param c Course
	 */
	public void addCompletedCourse(Course c){
		completedCourses.add(c);
	}
	
	/**
	 * @return a string containing all the registrations of the student.
	 */
	public String printRegistrations()
	{
		StringBuilder st = new StringBuilder();
		if(studentRegList.isEmpty())
			st.append("No courses found!");
		else
			for(Registration reg : studentRegList){
				st.append(reg.toString() + "\1");
			}
		return st.toString();
	}

	/**
	 * Checks if a student has completed the necessary prerequisites for a course
	 * @param c the course to check.
	 * @return true if course completed, false otherwise
	 */
	public boolean checkIfCompleted(Course c)
	{
		for(Course course : completedCourses)
		{
			if(course.getCourseName().equals(c.getCourseName()) &&
				course.getCourseNum() == c.getCourseNum()) {
				return true;
			}
		}
		return false;
	}
}