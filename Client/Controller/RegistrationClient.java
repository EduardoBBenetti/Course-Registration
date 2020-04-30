package Client.Controller;

import Client.View.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import javax.swing.JOptionPane;

/**
 * This class serves as the client of the program.
 * @author Gurmukh Singh, Dillon Sahadevan, Eduardo Benetti
 *
 */

public class RegistrationClient {
	/**
	 * Printwriter object used to send user input to server
	 */
	private PrintWriter socketOut;
	/**
	 * Socket object
	 */
	private Socket registrationSocket;
	/**
	 * BufferedReader to read user input
	 */
	private BufferedReader socketIn;
	/**
	 * LoginForm object to get sign-in info from user
	 */
	private LoginForm GUI;

	/**
	 * Constructor for class RegistrationClient.
	 * @param serverName String: the name of the server the client should connect to.
	 * @param portNumber int: the port the client should use to connect.
	 */
	public RegistrationClient(String serverName, int portNumber){
		try {
			registrationSocket = new Socket(serverName, portNumber);
			new BufferedReader(new InputStreamReader(System.in));
			socketIn = new BufferedReader(new InputStreamReader(
				registrationSocket.getInputStream()));
			socketOut = new PrintWriter(registrationSocket.getOutputStream(), true);
			GUI = new LoginForm(this);
		} catch (IOException e) {
			System.err.println(e.getStackTrace());
		}
	}
	
	/**
	 * returns PrintWriter object
	 * @return socketOut
	 */
	public PrintWriter getSocketOut() {
		return socketOut;
	}
	
	/**
	 * returns the BufferedReader object
	 * @return BufferedReader socketIn
	 */
	public BufferedReader getSocketIn() {
		return socketIn;
	}
	
	/**
	 * returns the GUIApp object
	 * @return GUIApp GUIApp object
	 */
	public GUIAppClient getGUIApp() {
		return GUI.getGUIApp();
	}
	
	/**
	 * Logs the current user out.
	 */
	public void logout() {
		socketOut.println(16);
		GUI.setVisible(true);
	}
	
	/**
	 * Gets input from the user and sends it to the server.
	 * Then gets output from the server and displays it to the client.
	 */
	public void communicate() {
		
		GUI.setVisible(true);
		try {
			while(true) {
				String input = "";
					
				input = socketIn.readLine();
				
				if(input.equals("multipleLines")) {
					multipleLines();
				}
				else if(input.equals("Login User")) {
					GUI.setVisible(false);
					GUI.getGUIApp().setVisible(true);
					accessGranted();
				}
				else if (input.equals("Login Fail")) {
					displayLoginError();
				}
				else if (input.equals("Login Admin")) {
					GUI.setVisible(false);
					GUI.getGUIAppAdmin().setVisible(true);
					accessGranted();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			socketOut.close();
			socketIn.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * reads the multiple lines from server and return the string 
	 * @return string of server output
	 */
	private String readInput() {
		String input = "";
		String st = "";
		
		try {
			//prints the contents until it hits "\0"
			while(true) {
				input = socketIn.readLine();
				if(input.equals("\0")) {
					break;
				}
				st+= input;
			}
		} catch (IOException e) {
				e.printStackTrace();
		}
		return st;	
	}
	
	/**
	 * Prints the tree contents in the text area of GUIApp
	 */
	private void multipleLines() {
		String st = readInput();
		
		String [] arr = st.split("\1");
		
		System.out.print(st);
	
		TextArea ta = new TextArea("","Registration Information");
		for(String string: arr){
		
			ta.updateText(string+"\n");
		}
	}
	/**
	 * displays "Access granted" to the user
	 */
	private void accessGranted() {
		JOptionPane.showMessageDialog(GUI.getGUIApp(), "Access Granted");
	}
	
	/**
	 * displays error message if sign-in info doesn't match
	 */
	private void displayLoginError() {
		JOptionPane.showMessageDialog(GUI.getGUIApp(), "Incorrect username or password",
				"Wrong information", JOptionPane.ERROR_MESSAGE);
	}
	
	/**
	 * Runs the client at localhost:3142.
	 * @param args String[]: arguments given to the program.
	 */
	public static void main(String [] args){
		RegistrationClient registrationClient = new RegistrationClient("localhost", 3142);
		registrationClient.communicate();
	}
}