package Client.View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import javax.swing.*;
import Client.Controller.RegistrationClient;
/**
 * This class creates the student GUI of the program and handle its functionality.
 * @author Gurmukh Singh, Dillon Sahadevan, Eduardo Benetti
 *
 */
public class GUIAppClient extends JFrame {
	/**
	 * deals with the size of buttons and labels
	 */
	final int X_AXIS = 20;
	/**
	 * deals with the size of buttons and labels
	 */
	final int Y_AXIS = 10;
	/**
	 * searchCourseButton
	 */
	private JButton searchCourseButton; 		// Search course						1
	/**
	 * addCourseButton
	 */
	private JButton addCourseButton;			// Add course to students courses		2	
	/**
	 * removeCourseButton
	 */
	private JButton removeCourseButton;			// Remove course from student courses	3
	/**
	 * viewFromCatalogue
	 */
	private JButton viewFromCatalogue;			// View all course in the catalogue		4
	/**
	 * viewFromStudent
	 */
	private JButton viewFromStudent;			// View courses taken by the student	5	
	/**
	 * viewRegistrations
	 */
	private JButton viewRegistrations;			// Print student registrations			7
	/**
	 * log out button
	 */
	private JButton logoutButton;
	/**
	 * quit button
	 */
	private JButton quit;						// Quit
	/**
	 * label 
	 */
	private JLabel label;
	/**
	 * object of RegistrationClient
	 */
	private RegistrationClient registrationClient;
    
    /**
     * Constructor for class GUIAppClient
     * @param frameName Name of the window
     * @param rg The registration client object that will be used
     */
    public GUIAppClient(String frameName, RegistrationClient rg) {
        
        super(frameName);
        registrationClient = rg;
        
        //organizes panels in border layout
        this.setLayout(new BorderLayout());
        this.setGUI();
        this.setButtons();
        this.setLabel();
        this.pack();
    }
    
    /**
     * Initializes and sets the JButton texts to form the menu for the application.
     */
    private void setButtons() {
    	
    	JPanel buttonsPanel = new JPanel();
    	buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));
        buttonsPanel.setVisible(true);

        //create button objects
        searchCourseButton = new JButton("Search for specific Course");
        addCourseButton = new JButton("Add course to Student");
        removeCourseButton = new JButton("Remove course from Student");
        viewFromCatalogue = new JButton("View all courses");
        viewFromStudent = new JButton("View courses taken by student");;
        viewRegistrations = new JButton("View registrations");
        logoutButton = new JButton("Logout");
        quit = new JButton("Quit the program");
        
        //adds button objects to the buttonsPanel
        buttonsPanel.add(searchCourseButton);
        buttonsPanel.add(Box.createRigidArea(new Dimension (X_AXIS,Y_AXIS)));
        buttonsPanel.add(addCourseButton);
        buttonsPanel.add(Box.createRigidArea(new Dimension (X_AXIS,Y_AXIS)));
        buttonsPanel.add(removeCourseButton);
        buttonsPanel.add(Box.createRigidArea(new Dimension (X_AXIS,Y_AXIS)));
        buttonsPanel.add(viewFromCatalogue);
        buttonsPanel.add(Box.createRigidArea(new Dimension (X_AXIS,Y_AXIS)));
        buttonsPanel.add(viewFromStudent);
        buttonsPanel.add(Box.createRigidArea(new Dimension (X_AXIS,Y_AXIS)));
        buttonsPanel.add(viewRegistrations);
        buttonsPanel.add(Box.createRigidArea(new Dimension (X_AXIS,Y_AXIS)));
        buttonsPanel.add(logoutButton);
        buttonsPanel.add(Box.createRigidArea(new Dimension (X_AXIS,Y_AXIS)));
        buttonsPanel.add(quit);

        //adds buttonPanel to the SOUTH of frame
        this.add(buttonsPanel,BorderLayout.WEST);
 
        // sets logoutButton
        logoutButton.addActionListener((ActionEvent e)->{
        	setVisible(false);
        	registrationClient.logout();
        });
        
        // sets searchCourseButton 
        searchCourseButton.addActionListener((ActionEvent e) ->{
        	
        	JTextField courseName = new JTextField();
        	JTextField courseNum = new JTextField();
        	
        	Object [] fields = {
        			"Course tag (eg ENSF):" , courseName,
        			"Course Number (eg 409):" , courseNum	
        	};
        	
        	// Window to input course info
        	JOptionPane.showConfirmDialog(null, fields, "Course infomation",JOptionPane.OK_CANCEL_OPTION);        	
        	
        	String name = courseName.getText();
        	String num = courseNum.getText();
        	
        	if(name.length()>0 && num.length()>0 ) {
        		registrationClient.getSocketOut().println(1);
	        	
	        	registrationClient.getSocketOut().println(courseName.getText());
	        	registrationClient.getSocketOut().println(courseNum.getText());						
        	}
		});
   

        // sets addCourseButton to get course information and which student for the information to be added
		addCourseButton.addActionListener((ActionEvent e) ->{
        	
        	JTextField courseName = new JTextField();
        	JTextField courseNum = new JTextField();
        	JTextField studentName = new JTextField();
        	
        	Object [] fields = {
        			
        			"Student Name:", studentName,
        			"Course tag (eg ENSF):" , courseName,
        			"Course Number (eg 409):" , courseNum
        	};
			
        	// Window to input course info
        	JOptionPane.showConfirmDialog(null, fields, "Add course to student",JOptionPane.OK_CANCEL_OPTION);        	
        	
        	String Name = studentName.getText();
        	String cName = courseName.getText();
        	String cNum = courseNum.getText();
        	
        	
        	if(Name.length()>0 && cNum.length()>0 && cName.length()>0)
        	{
        	registrationClient.getSocketOut().println(2);
        	
        	registrationClient.getSocketOut().println(Name);
        	registrationClient.getSocketOut().println(cName);
        	registrationClient.getSocketOut().println(cNum);
        	
        	}
		});

		
        // sets removeCourseButton to get course information and which student for the information to be removed
		removeCourseButton.addActionListener((ActionEvent e) ->{
        	
        	JTextField courseName = new JTextField();
        	JTextField courseNum = new JTextField();
        	JTextField studentName = new JTextField();
        	
        	Object [] fields = {
        			"Student Name:", studentName,
        			"Course tag (eg ENSF):" , courseName,
        			"Course Number (eg 409):" , courseNum
        	};
			
        	// Window to input course info
        	JOptionPane.showConfirmDialog(null, fields, "Remove course from student",JOptionPane.OK_CANCEL_OPTION);        	
        	
          	String cName = courseName.getText();
        	String cNum = courseNum.getText();
        	String Name = studentName.getText();
        	
        	if(Name.length()>0 && cNum.length()>0 && cName.length()>0) {
	        	registrationClient.getSocketOut().println(3);
	        	registrationClient.getSocketOut().println(Name);
	        	registrationClient.getSocketOut().println(cName);
	        	registrationClient.getSocketOut().println(cNum);
        	}
		});
		
        // sets viewFromCatalogue to get course information and which student for the information to be removed
		viewFromCatalogue.addActionListener((ActionEvent e) ->{
			registrationClient.getSocketOut().println(4);
		});
		
		viewFromStudent.addActionListener((ActionEvent e) ->{
			String name = JOptionPane.showInputDialog("Enter the name of the student: ");
			if(name.length()>0) {
				registrationClient.getSocketOut().println(5);
	        	registrationClient.getSocketOut().println(name);
			}		
		});

		viewRegistrations.addActionListener((ActionEvent e) ->{
			registrationClient.getSocketOut().println(7);
		});
		
		quit.addActionListener((ActionEvent e) ->{
			this.setVisible(false);
			registrationClient.getSocketOut().println(8);
			System.exit(0);
		});		
    }

    /**
     * creates an object of JLabel and adds them to the frame by using a panel.
     */
    private void setLabel(){
        JPanel labelPanel = new JPanel();
        
        //sets the panel in border layout
        labelPanel.setLayout(new BorderLayout());
       

        label = new JLabel("Select one of the following options");
        label.setHorizontalAlignment(JLabel.CENTER);
        labelPanel.add(label, BorderLayout.CENTER);
        labelPanel.add(Box.createRigidArea(new Dimension (30,30)));
        
        //adds panel to the NORTH of frame
        this.add(label,BorderLayout.NORTH);  
    }
	
    /**
     * setup for the GUI 
     */
    private void setGUI(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000,1000);
        this.setVisible(false);
    }
    /**
     * returns object of RegistrationClient
     * @return RegistrationClient
     */
    public RegistrationClient getRegistrationClient() {
    	return registrationClient;
    }
}