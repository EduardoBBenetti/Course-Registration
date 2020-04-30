package Server.DatabaseModel;


import java.sql.*;
import Server.CourseInfo.*;

/**
 * This class is responsible for reading info from the database.
 * @author Gurmukh Singh, Dillon Sahadevan, Eduardo Benetti
 *
 */
public class DatabaseDriver {
	/**
	 * Connection object
	 */
	private Connection connection;
	/**
	 * object ofRegistrationApp
	 */
	private RegistrationApp regApp;
	
	/**
	 * constructs object of DatabaseDriver
	 * @param r RegsitartionApp
	 */
	public DatabaseDriver(RegistrationApp r) {
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/CourseRegistration?useLegacyDatetimeCode=false&serverTimezone=UTC",
					"root", "QwErTy?123");	
		} catch (Exception e) {
			System.err.println("Error connecting to database!");
			e.printStackTrace();
		}
		regApp = r;
		loadCourseData();
		loadStudentData();
		loadPrerequisites();
		loadCompletedCourses();
	}
	
	/**
	 * Reads course data from database and courses to course catalogue and 
	 * creates course offering for course.
	 */
	private void loadCourseData() {
		try {
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM Courses");
			while(result.next()) {
				String name = result.getString("CourseName");
				int num = result.getInt("CourseNumber");
				int section = result.getInt("SectionNumber");
				int cap = result.getInt("Capacity");
				regApp.getCourseCatalogue().addCourse(name, num);
				regApp.createCourseOffering(regApp.checkCatalogue(name, num), section, cap);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * reads student from database and add student to the RegistrationApp.
	 */
	private void loadStudentData() {
		try {
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM Students");
			while(result.next()) {
				String name = result.getString("name");
				int id = result.getInt("id");
				regApp.addStudent(name, id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * reads pre-req data from database and adds pre-req to course.
	 */
	private void loadPrerequisites() {
		try {
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM Prerequisites");
			while(result.next()) {
				int id = result.getInt("CourseID");
				Integer c1 = result.getInt("Prerequisite1");
				Integer c2 = result.getInt("Prerequisite2");
				Integer c3 = result.getInt("Prerequisite3");
				
				//gets the course from database
				if(c1 != null) {
					Statement st = connection.createStatement();
					ResultSet res = st.executeQuery("SELECT * FROM Courses WHERE SectionNumber = " + id);
					Course c = null;
					if(res.next()) {
						String name = res.getString("CourseName");
						int num = res.getInt("CourseNumber");
						c = regApp.checkCatalogue(name, num);
					}
					
					//adds pre-req to different offerings
					ResultSet r1 = st.executeQuery("SELECT * FROM Courses WHERE SectionNumber = " + c1);
					if(r1.next()) {
						String s1 = r1.getString("CourseName");
						int n1 = r1.getInt("CourseNumber");
						c.addPreReq(regApp.checkCatalogue(s1, n1));
					}
					if(c2 != null) {
						ResultSet r2 = st.executeQuery("SELECT * FROM Courses WHERE SectionNumber = " + c2);
						if(r2.next()) {
							String s2 = r2.getString("CourseName");
							int n2 = r2.getInt("CourseNumber");
							c.addPreReq(regApp.checkCatalogue(s2, n2));
						}
						if(c3 != null) {
							ResultSet r3 = st.executeQuery("SELECT * FROM Courses WHERE SectionNumber = " + c3);
							if(r3.next()) {
								String s3 = r3.getString("CourseName");
								int n3 = r3.getInt("CourseNumber");
								c.addPreReq(regApp.checkCatalogue(s3, n3));
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Gets all registered users
	 * @return a string with all the users.
	 */
	public String getUsers() {
		StringBuilder st = new StringBuilder();
		try {
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM Users");
			while(result.next()) {
				String username = result.getString("Username");
				int userSt = result.getInt("UserStatus");
				String userStatus = (userSt == 0 ? "Standard user" : "Elevated user");
				st.append("Username: " + username + "\1User Status: " + userStatus + "\1\1");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return st.toString();
	}
	
	/**
	 * Gets all registered students
	 * @return a string with all the students.
	 */
	public String getStudents() {
		StringBuilder st = new StringBuilder();
		try {
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM Students");
			while(result.next()) {
				String name = result.getString("name");
				int id = result.getInt("id");
				st.append("Name: " + name + "\1ID: " + id + "\1\1");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return st.toString();
	}
	
	/**
	 * Adds a user to the database
	 * @param name username
	 * @param pass password
	 * @param status user status, 0 for standard, 1 for admin
	 */
	public void addUser(String name, String pass, int status) {
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate("INSERT INTO CourseRegistration.Users VALUES('" + name + "', '" +
									pass + "', " + status + ")");
			statement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Adds a student to the database
	 * @param name name of the student
	 * @param id students id
	 */
	public void addStudent(String name, int id) {
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate("INSERT INTO CourseRegistration.Students VALUES(" + id + ", '" + name + "')");
			statement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Removes a user from the database
	 * @param name
	 */
	public void removeUser(String name) {
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate("DELETE FROM Users WHERE Username = '" + name + "'");
			statement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Removes a student from the database
	 * @param id the students id to remove
	 */
	public void removeStudent(int id) {
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate("DELETE FROM Students WHERE id = " + id);
			statement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * reads completed course info from database and adds them to the student.
	 */
	private void loadCompletedCourses() {
		try {
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM CompletedCourses");
			while(result.next()) {
				int id = result.getInt("StudentID");
				Integer c1 = result.getInt("Course1");
				Integer c2 = result.getInt("Course2");
				Integer c3 = result.getInt("Course3");
				
				//reads student info from table Student
				if(c1 != null) {
					Statement st = connection.createStatement();
					ResultSet res = st.executeQuery("SELECT * FROM Students WHERE id = " + id);
					Student s= null;
					if(res.next()) {
						String name = res.getString("name");
						s = regApp.searchStudent(name);
					}
					
					//reads different courses and adds them to student
					ResultSet r1 = st.executeQuery("SELECT * FROM Courses WHERE SectionNumber = " + c1);
					if(r1.next()) {
						String s1 = r1.getString("CourseName");
						int n1 = r1.getInt("CourseNumber");
						s.addCompletedCourse(regApp.checkCatalogue(s1, n1));
					}
					if(c2 != null) {
						ResultSet r2 = st.executeQuery("SELECT * FROM Courses WHERE SectionNumber = " + c2);
						if(r2.next()) {
							String s2 = r2.getString("CourseName");
							int n2 = r2.getInt("CourseNumber");
							s.addCompletedCourse(regApp.checkCatalogue(s2, n2));
						}
						if(c3 != null) {
							ResultSet r3 = st.executeQuery("SELECT * FROM Courses WHERE SectionNumber = " + c3);
							if(r3.next()) {
								String s3 = r3.getString("CourseName");
								int n3 = r3.getInt("CourseNumber");
								s.addCompletedCourse(regApp.checkCatalogue(s3, n3));
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * reads the sign-in info from client. matches the student info with database
	 * and returns user status.
	 * @param name user name
	 * @param password password
	 * @return user status if info matches, -1 otherwise
	 */
	public int checkLogin(String name, String password) {
		try {
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM Users");
			String databaseName;
			String databasePassword;
			int userStatus;
			while(result.next()) {
				databaseName = result.getString("Username");
				databasePassword= result.getString("Password");
				userStatus = result.getInt("UserStatus");
				if(databaseName.contentEquals(name) && databasePassword.contentEquals(password)){
					System.out.println("found");
					return userStatus;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	/**
	 * Checks if the section number is available
	 * @param n section number to check
	 */
	public boolean checkSectionNumber(int n) {
		try {
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery("SELECT SectionNumber FROM Courses ORDER");
			while(result.next()) {
				if(result.getInt("SectionNumber") == n) {
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
