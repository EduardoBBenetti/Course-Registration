package Server.CourseInfo;

import Server.DatabaseModel.*;
import java.io.PrintWriter;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;

/**
 * This class receives user input from server and sends the output to server.
 * It implements Runnable and its run method is called by the server.
 * @author Gurmukh Singh, Dillon Sahadevan, Eduardo Benetti
 *
 */
public class FrontEnd implements Runnable
{
	/**
	 * object of RegistrationApp
	 */
	private RegistrationApp regApp;
	/**
	 * Socket
	 */
	private Socket socket;
	/**
	 * Printwriter object to send output to server
	 */
	private PrintWriter socketOut;
	/**
	 * BufferedReader to reads input from client
	 */
	private BufferedReader socketIn;
	/**
	 * object of DatabaseDriver
	 */
	private DatabaseDriver databaseDriver;

	/**
	 * Constructor for class FrontEnd.
	 * @param s the socket to use for client server communication.
	 */
	public FrontEnd(Socket s){
		socket = s;
		try {
			socketIn = new BufferedReader(new InputStreamReader(
				socket.getInputStream()));
			socketOut = new PrintWriter((socket.getOutputStream()), true);
		} catch (IOException e) {
			System.err.println("Error getting communcation methods");
			System.err.println(e.getStackTrace());
		}
		regApp = new RegistrationApp();
		databaseDriver = new DatabaseDriver(regApp);
	}
	/**
	 * returns registrationApp object
	 * @return registrationApp
	 */
	public RegistrationApp getRegApp() {
		return regApp;
	}


	/**
	 * searches the course by name and number and returns course information, if found.
	 */
	private void searchCatalogue()
	{
		try {
			//socketOut.println("Enter the course name:\0");
			String name = socketIn.readLine();
			//socketOut.println("Enter the course number:\0");
			int num = Integer.parseInt(socketIn.readLine());
			socketOut.println("multipleLines");
			socketOut.println(regApp.searchCatalogue(name, num));
			socketOut.println("\0");
		} catch (IOException e) {
			System.err.println("Communication error");
			System.err.println(e.getStackTrace());
		}
	}
	
	/**
	 * adds course to a given student's registration
	 */
	private void addCourse()
	{
		try {
			String student = socketIn.readLine();
			String name = socketIn.readLine();
			int num = Integer.parseInt(socketIn.readLine());
			
			Student st = regApp.searchStudent(student);
			
			socketOut.println("multipleLines");
			
			if(st == null){
				socketOut.println("Error student not found.");
				socketOut.println("\0");
				return;
			}
		
			Course course = regApp.checkCatalogue(name, num);
			if(course == null){
				socketOut.println("Error course not found.");
				socketOut.println("\0");
				return;
			}
			socketOut.println(regApp.addRegistration(st, course));
			socketOut.println("\0");
		} catch (IOException e) {
			System.err.println("Communication error");
			System.err.println(e.getStackTrace());
		}
	}
	/**
	 * adds  course to CourseCatalogue
	 */
	private void addCourseToCat() {
		try {
			String courseName = socketIn.readLine();
			String cNum = socketIn.readLine();
			int courseNum = Integer.parseInt(cNum);
			if(regApp.checkCatalogue(courseName, courseNum) == null) {
				regApp.getCourseCatalogue().addCourse(courseName, courseNum);
			}
		} catch (IOException e) {
			System.err.println("Communication error");
			System.err.println(e.getStackTrace());
		}
	}

	/**
	 * remove a course from student registration.
	 * 
	 */
	private void removeCourse()
	{
		try {
			String student = socketIn.readLine();
			String name = socketIn.readLine();
			int num = Integer.parseInt(socketIn.readLine());
			Course course = regApp.checkCatalogue(name, num);
			
			Student st = regApp.searchStudent(student);
			
			socketOut.println("multipleLines");
			
			if(st == null){
				socketOut.println("Student not found!");
				socketOut.println("\0");
				return;
			}
			
			if(course == null){
				socketOut.println("Course not found!");
				socketOut.println("\0");
				return;
			}
			regApp.removeRegistration(st, course);
			socketOut.println("Course removed Successfully!");
			socketOut.println("\0");
		} catch (IOException e) {
			System.err.println("Communication error");
			System.err.println(e.getStackTrace());
		}
	}

	/**
	 * prints all courses registered by student
	 */
	private void viewStudentCourses()
	{
		try {
			//socketOut.println("Enter student name:\0");
			String student = socketIn.readLine();
			Student st = regApp.searchStudent(student);
			
			socketOut.println("multipleLines");
			if(st == null){
				socketOut.println("Error student not found.");
				socketOut.println("\0");
				return;
			}
			
			socketOut.println(st.printRegistrations());
			socketOut.println("\0");
		} catch (IOException e) {
			System.err.println("Communication error");
			System.err.println(e.getStackTrace());
		}
	}
	/**
	 * reads input from client and calls appropriate methods
	 */
	public void menu()
	{
		try {
			while(true)
			{	
				String input = socketIn.readLine();
				int choice = Integer.parseInt(input);
				switch(choice)
				{
					case 1:
						searchCatalogue();
						break;
					case 2:
						addCourse();
						break;
					case 3:
						removeCourse();
						break;
					case 4:
						socketOut.println("multipleLines");
						socketOut.println(regApp.displayCourseCatalogue());
						socketOut.println("\0");
						break;
					case 5:
						viewStudentCourses();
						break;
					case 7:
						socketOut.println("multipleLines");
						socketOut.println(regApp);
						socketOut.println("\0");
						break;
					case 8:
						System.out.println("Connection closed");
						break;
					case 9:
						addCourseToCat();
						break;
					case 10:
						socketOut.println("multipleLines");
						socketOut.println(databaseDriver.getUsers());
						socketOut.println("\0");
						break;
					case 11:
						addUser();
						break;
					case 12:
						removeUser();
						break;
					case 13:
						socketOut.println("multipleLines");
						socketOut.println(databaseDriver.getStudents());
						socketOut.println("\0");
						break;
					case 14:
						addStudent();
						break;
					case 15:
						removeStudent();
						break;
					case 16:
						System.out.println("User logged out");
						break;
					case 17:
						checkLogin();
						break;
					default:
						socketOut.println("Invalid input!!");
						break;
				}
				
			}
		} catch (IOException e) {
			System.err.println("Communication error");
			System.err.println(e.getStackTrace());
		}
	}
	
	/**
	 * returns DatabaseDriver
	 * @return DatabaseDriver
	 */
	public DatabaseDriver getDatabaseDriver() {
		return databaseDriver;
	}

	private void addUser() {
		try {
			String username = socketIn.readLine();
			String password = socketIn.readLine();
			int userStatus = Integer.parseInt(socketIn.readLine());
			databaseDriver.addUser(username, password, userStatus);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * adds student to database by calling addStudent() of DatabaseDriver
	 */
	private void addStudent() {
		try {
			String name = socketIn.readLine();
			
			int id = Integer.parseInt(socketIn.readLine());
			
			databaseDriver.addStudent(name, id);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * remove user by calling removeUser() of DatabaseDriver
	 */
	private void removeUser() {
		try {
			String name = socketIn.readLine();
			databaseDriver.removeUser(name);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * remove student by calling removeStudent() of DatabaseDriver
	 */
	
	private void removeStudent() {
		try {
			int id = Integer.parseInt(socketIn.readLine());
			databaseDriver.removeStudent(id);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Checks the sign-in information and communicate to the client on whether
	 * the user is student or admin.
	 */
	private void checkLogin() {
		int loginStatus = -1;
		try {
			while(loginStatus == -1) {
				String[] userData = socketIn.readLine().split(" ");
				loginStatus = databaseDriver.checkLogin(userData[0], userData[1]);
				if(loginStatus == -1) {
					socketOut.println("Login Fail");
				}
			}
			if(loginStatus == 0) {
				socketOut.println("Login User");
			} else {
				socketOut.println("Login Admin");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * run method of FrontEnd
	 */
	public void run() {
		menu();
	}
}