package Server.CourseInfo;


import java.util.ArrayList;

/**
 * This class stores the course of the available courses.
 * @author Gurmukh Singh, Dillon Sahadevan, Eduardo Benetti
 *
 */
public class CourseCatalogue {
	
	/**
	 * Array list of courses
	 */
	private ArrayList <Course> courseList;
	
	/**
	 * constructor of CourseCatalogue
	 */
	public CourseCatalogue () {
		courseList = new ArrayList<Course>();
	}
	
	/**
	 * creates course offering with given section number an dcapacity and adds it to the 
	 * offering list the course.
	 * @param c Course
	 * @param secNum section number
	 * @param secCap capacity
	 */
	public void createCourseOffering (Course c, int secNum, int secCap) {
		if (c!= null) {
			CourseOffering theOffering = new CourseOffering (secNum, secCap);
			c.addOffering(theOffering);
		}
	}
	
	/**
	 * searches courseList for the the course and returns the course if found.
	 * @param courseName name of the course
	 * @param courseNum number of the course
	 * @return Course
	 */
	public Course searchCat (String courseName, int courseNum) {
		for (Course c : courseList) {
			if (courseName.equals(c.getCourseName()) &&
					courseNum == c.getCourseNum()) {
				return c;
			}	
		}
		displayCourseNotFoundError();
		return null;
	}

	/**
	 * displays course not found
	 */
	private void displayCourseNotFoundError() {
		
		System.err.println("Course was not found!");
	}

	/**
	 * returns courseList
	 * @return  courseList
	 */
	public ArrayList <Course> getCourseList() {
		return courseList;
	}
	
	/**
	 * add course to the courseList
	 * @param name name of the course
	 * @param num number of course
	 */
	public void addCourse(String name, int num) {
		courseList.add(new Course(name, num));
	}


	/**
	 * sets the courseList to given array list
	 * @param courseList courseList
	 */
	public void setCourseList(ArrayList <Course> courseList) {
		this.courseList = courseList;
	}
	
	/**
	 * overrides toString method of courseCatalogue.
	 * prints the courses from courseList
	 */
	@Override
	public String toString () {
		String st = "All courses in the catalogue: \1";
		for (Course c : courseList) {
			st += c;  //This line invokes the toString() method of Course
			st += "\1\1";
		}
		return st;
	}

}