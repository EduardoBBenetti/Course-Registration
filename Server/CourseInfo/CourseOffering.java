package Server.CourseInfo;

import java.util.ArrayList;

/**
 * This class deals with the course offering of course.
 * It stores the section number, capacity and registrations of course.
 * @author  Gurmukh Singh, Dillon Sahadevan, Eduardo Benetti
 *
 */
public class CourseOffering {
	
	/**
	 * section number of offering
	 */
	private int secNum;
	/**
	 * capacity of offering
	 */
	private int secCap;
	/**
	 * course for offering
	 */
	private Course theCourse;
	/**
	 * arraylist of registrations
	 */
	private ArrayList <Registration> offeringRegList;
	
	/**
	 * Constructs the courseOffering object
	 * @param secNum section number
	 * @param secCap capacity
	 */
	public CourseOffering (int secNum, int secCap) {
		this.setSecNum(secNum);
		this.setSecCap(secCap);
		offeringRegList = new ArrayList <Registration>();
	}
	/**
	 * returns section number
	 * @return section number
	 */
	public int getSecNum() {
		return secNum;
	}
	/**
	 * sets section number to a given value
	 * @param secNum  section number
	 */
	public void setSecNum(int secNum) {
		this.secNum = secNum;
	}
	/**
	 * returns section capacity
	 * @return capacity
	 */
	public int getSecCap() {
		return secCap;
	}
	
	/**
	 * sets capacity to given value
	 * @param secCap capacity
	 */
	public void setSecCap(int secCap) {
		this.secCap = secCap;
	}
	
	/**
	 * returns the course object
	 * @return course object
	 */
	public Course getTheCourse() {
		return theCourse;
	}
	
	/**
	 * sets course to a given course object
	 * @param theCourse course object
	 */
	public void setTheCourse(Course theCourse) {
		this.theCourse = theCourse;
	}
	
	/**
	 * Returns the data of the course offering as a string.
	 * prints course information, section number, section capacity
	 */
	@Override
	public String toString () {
		String st = "\n";
		st += getTheCourse().getCourseName() + " " + getTheCourse().getCourseNum() + "\1";
		st += "Section Num: " + getSecNum() + ", section cap: "+ getSecCap() +"\1";
		//We also want to print the names of all students in the section
		return st;
	}
	
	/**
	 * Adds a registration to the offering.
	 * @param registration the registration object to add.
	 */
	public void addRegistration(Registration registration) {
		// TODO Auto-generated method stub
		offeringRegList.add(registration);
	}
	
	/**
	 * Removes a registration from the offering.
	 * @param reg the registration to remove.
	 */
	public void removeRegistration(Registration reg) {
		offeringRegList.remove(reg);
	}

}