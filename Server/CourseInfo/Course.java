package Server.CourseInfo;
import java.util.ArrayList;

/**
 * This class stores the course realted information.
 * @author Gurmukh Singh, Dillon Sahadevan, Eduardo Benetti
 *
 */
public class Course {

	/**
	 * name of the course
	 */
	private String courseName;
	/**
	 * course number
	 */
	private int courseNum;
	/**
	 * Array list of pre-req courses
	 */
	private ArrayList<Course> preReq;
	/**
	 * arraylist of course offerings
	 */
	private ArrayList<CourseOffering> offeringList;
	/**
	 * number of students enrolled
	 */
	private int numEnrolled;

	/**
	 * Constructor of course
	 * @param courseName name of course
	 * @param courseNum course number
	 */
	public Course(String courseName, int courseNum) {
		this.setCourseName(courseName);
		this.setCourseNum(courseNum);
		preReq = new ArrayList<Course>();
		offeringList = new ArrayList<CourseOffering>();
	}

	/**
	 * A function to add a CourseOffering to the offeringList.
	 * @param offering the course offering to add.
	 */
	public void addOffering(CourseOffering offering) {
		if (offering != null && offering.getTheCourse() == null) {
			offering.setTheCourse(this);
			if (!offering.getTheCourse().getCourseName().equals(courseName)
					|| offering.getTheCourse().getCourseNum() != courseNum) {
				System.err.println("Error! This section belongs to another course!");
				return;
			}
			offeringList.add(offering);
		}
	}

	/**
	 * increases numEnrolled by 1
	 */
	public void addStudent(){
		numEnrolled++;
	}
	/**
	 * decreases numEnrolled by 1
	 */
	public void removeStudent(){
		numEnrolled--;
	}
	/**
	 * add pre-req to the preReq
	 * @param c Course
	 */
	public void addPreReq(Course c){
		preReq.add(c);
	}

	/**
	 * returns preReq
	 * @return ArrayList of course
	 */
	public ArrayList<Course> getPreReq(){
		return this.preReq;
	}

	/**
	 * returns course name
	 * @return course name
	 */
	public String getCourseName() {
		return courseName;
	}

	/**
	 * sets course name
	 * @param courseName course name
	 */
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	/**
	 * returns course number
	 * @return course number
	 */
	public int getCourseNum() {
		return courseNum;
	}

	/**
	 * sets course number
	 * @param courseNum course number
	 */
	public void setCourseNum(int courseNum) {
		this.courseNum = courseNum;
	}
	
	/**
	 * overrides toString method of course.
	 * Prints course name, number, offering list
	 */
	@Override
	public String toString () {
		String st = "";
		st += getCourseName() + " " + getCourseNum ();
		st += "  Students enrolled: " + numEnrolled;
		st += "\1All course sections:\1";
		for (CourseOffering c : offeringList)
			st += c;
		return st;
	}

	/**
	 * A function that shows the running status of the course.
	 * @return A string that shows the status of the course.
	 */
	public String isRunning(){
		String result;
		if(numEnrolled >= 8){
			result = numEnrolled + "students enrolled. Course is running.\1";
		} else {
			result = "Only " + numEnrolled + " student(s) are currently enrolled.\1";
			result += "Course will start running after " + (8 - numEnrolled) +
					" more student(s) enroll.\1";
		}
		return result;
	}

	/**
	 * returns course offering at specified index
	 * @param i index
	 * @return CourseOffering object
	 */
	public CourseOffering getCourseOfferingAt(int i) {
		// TODO Auto-generated method stub
		if (i < 0 || i >= offeringList.size() )
			return null;
		else
			return offeringList.get(i);
	}

}