package Client.View;

import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.event.*;
import javax.swing.*;
import Client.Controller.*;
/**
 * This class creates GUI for user sign-in and handles its functionality.
 * @author Gurmukh Singh, Dillon Sahadevan, Eduardo Benetti
 *
 */
public class LoginForm extends JFrame{
	/**
	 * JPanel
	 */
	private JPanel panel;
	/**
	 * JLabels usernameLabel, passwordLabel, message
	 */
	private JLabel usernameLabel, passwordLabel, message;
	/**
	 * Text field to get user name
	 */
	private JTextField usernameText;
	/**
	 * Text field to get password
	 */
	private JPasswordField passwordText;
	/**
	 * JButton submit
	 */
	private JButton submit;
	/**
	 * object of GUIAppClient
	 */
	private GUIAppClient guiApp;
	private GUIAppClientAdmin guiAppAdmin;
	
	/**
	 * Constructor of LoginForm
	 * @param rc RegsitrationClient
	 */
    public LoginForm(RegistrationClient rc) {
    	guiApp = new GUIAppClient("", rc);
    	guiAppAdmin = new GUIAppClientAdmin("", rc);
    	usernameLabel = new JLabel();
    	usernameLabel.setText("User Name:");
        usernameText = new JTextField();
        passwordLabel = new JLabel();
        passwordLabel.setText("Password:");
        passwordText = new JPasswordField();
        submit = new JButton("SUBMIT");
        panel = new JPanel(new GridLayout(3, 1));
        panel.add(usernameLabel);
        panel.add(usernameText);
        panel.add(passwordLabel);
        panel.add(passwordText);
        message = new JLabel();
        panel.add(message);
        panel.add(submit);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(panel, BorderLayout.CENTER);
        setTitle("Please Login Here!");
        setSize(300,150);
        
        submit.addActionListener((ActionEvent e) ->{
    	   String username = usernameText.getText();
    	   char[] password = passwordText.getPassword();
           usernameText.setText("");
           passwordText.setText("");
           String passwordString = new String(password);
           System.out.println(passwordString);
           guiApp.getRegistrationClient().getSocketOut().println(username + " " + passwordString);
        });
        setVisible(true);
    }
   
    /**
     * returns GUIAppClient object
     * @return GUIAppClient
     */
    public GUIAppClient getGUIApp() {
    	return guiApp;
    }
    /**
     * returns GUIAppClientAdmin object
     * @return GUIAppClientAdmin
     */
    public GUIAppClientAdmin getGUIAppAdmin() {
    	return guiAppAdmin;
    }
}