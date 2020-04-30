package Server.ServerController;

import Server.CourseInfo.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

/**
 * This class is the server of the program.
 * @author  Gurmukh Singh, Dillon Sahadevan, Eduardo Benetti
 *
 */
public class RegistrationServer {
	/**
	 * serverSocket object
	 */
	private ServerSocket serverSocket;
	/**
	 * ExecutorService object
	 */
	private ExecutorService threadPool;

	/**
	 * Construct a Server with a specified port number to run the registration server on.
	 * @param port number
	 */
	public RegistrationServer(int port) {
		try {
			serverSocket = new ServerSocket(port);
			System.out.println("Server is running...");
			threadPool = Executors.newCachedThreadPool();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * accepts the client connection and calls run method of FrontEnd.
	 */
	private void runServer() {
		try {
			while(true){
				FrontEnd frontEnd = new FrontEnd(serverSocket.accept());
				System.out.println("Server has established a connection.");
				threadPool.execute(frontEnd);
			}
		} catch (Exception e){
			e.printStackTrace();
			threadPool.shutdown();
		}
	}

	/**
	 * Starts the server on port 3142.
	 * @param args the arguments specified to the server upon runtime.
	 */
	public static void main(String [] args) {
		RegistrationServer registrationServer = new RegistrationServer(3142);
		registrationServer.runServer();
	}
}