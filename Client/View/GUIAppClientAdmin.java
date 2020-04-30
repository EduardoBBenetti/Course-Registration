package Client.View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import javax.swing.*;
import Client.Controller.RegistrationClient;

/**
 * This class creates the admin GUI of the program and handle its functionality.
 * @author Gurmukh Singh, Dillon Sahadevan, Eduardo Benetti
 *
 */
public class GUIAppClientAdmin extends JFrame {
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
	 *addCourseToCatButton 
	 */
	private JButton addCourseToCatButton;		// Add course to catalogue				9
	/**
	 * viewUsersButton
	 */
	private JButton viewUsersButton;			// View all users						10
	/**
	 * addUserButton
	 */
	private JButton addUserButton;				// Add user								11
	/**
	 * removeUserButton
	 */
	private JButton removeUserButton;			// Remove user							12
	/**
	 * viewStudentsButton
	 */
	private JButton viewStudentsButton;			// View all students					13
	/**
	 * addStudentButton
	 */
	private JButton addStudentButton;			// Add student							14
	/**
	 *removeStudentButton 
	 */
	private JButton removeStudentButton;		// Remove student						15
	/**
	 * logoutButton
	 */
	private JButton logoutButton;				// Logout button
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
    public GUIAppClientAdmin(String frameName, RegistrationClient rg) {
        
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
       	addCourseToCatButton = new JButton("Add course to catalogue");
       	viewUsersButton = new JButton("View all users");
       	addUserButton = new JButton("Add a user");
       	removeUserButton = new JButton("Remove a user");
       	viewStudentsButton = new JButton("View all students");
       	addStudentButton = new JButton("Add a student");
       	removeStudentButton = new JButton("Remove a student");
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
    	buttonsPanel.add(addCourseToCatButton);
    	buttonsPanel.add(Box.createRigidArea(new Dimension (X_AXIS,Y_AXIS)));
    	buttonsPanel.add(viewUsersButton);
    	buttonsPanel.add(Box.createRigidArea(new Dimension (X_AXIS,Y_AXIS)));
    	buttonsPanel.add(addUserButton);
    	buttonsPanel.add(Box.createRigidArea(new Dimension (X_AXIS,Y_AXIS)));
    	buttonsPanel.add(removeUserButton);
    	buttonsPanel.add(Box.createRigidArea(new Dimension (X_AXIS,Y_AXIS)));
    	buttonsPanel.add(viewStudentsButton);
    	buttonsPanel.add(Box.createRigidArea(new Dimension (X_AXIS,Y_AXIS)));
    	buttonsPanel.add(addStudentButton);
    	buttonsPanel.add(Box.createRigidArea(new Dimension (X_AXIS,Y_AXIS)));
    	buttonsPanel.add(removeStudentButton);
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
        
        // sets viewUsersButton
        viewUsersButton.addActionListener((ActionEvent e)->{
        	registrationClient.getSocketOut().println(10);
        });
        
        //sets addUserButton
        addUserButton.addActionListener((ActionEvent e) -> {
        	JTextField username = new JTextField();
        	JPasswordField password = new JPasswordField();
        	String[] options = {"Standard", "Elevated"};
        	JComboBox userSt = new JComboBox(options);
        	userSt.setSelectedIndex(0);
        	Object [] fields = {
        			"Username:", username,
        			"Password:", password,
        			"User Status:", userSt
        	};
        	JOptionPane.showConfirmDialog(null, fields, "User information", JOptionPane.OK_CANCEL_OPTION);
        	
        	String name = username.getText();
        	String pass = new String(password.getPassword());
        	int userStatus = userSt.getSelectedIndex();
        	if(name.length() > 0 && pass.length() > 0) {
        		registrationClient.getSocketOut().println(11);
	        	
	        	registrationClient.getSocketOut().println(name);
	        	registrationClient.getSocketOut().println(pass);	
	        	registrationClient.getSocketOut().println(userStatus);	
        	}
        });
        
        // sets viewStudentsButton
        viewStudentsButton.addActionListener((ActionEvent e)->{
        	registrationClient.getSocketOut().println(13);
        });
        
        //sets addStudentButton
        addStudentButton.addActionListener((ActionEvent e) -> {
        	JTextField name = new JTextField();
        	JTextField id = new JTextField();
        	Object [] fields = {
        			"Name:", name,
        			"ID:", id
        	};
        	JOptionPane.showConfirmDialog(null, fields, "Student information", JOptionPane.OK_CANCEL_OPTION);
        	
        	String studentName = name.getText();
        	int studentID = Integer.parseInt(id.getText());
        	if(studentName.length() != 0) {
        		registrationClient.getSocketOut().println(14);
	        	
	        	registrationClient.getSocketOut().println(studentName);
	        	registrationClient.getSocketOut().println(studentID);	
        	}
        });
        
        //sets removeUserButton
        removeUserButton.addActionListener((ActionEvent e) -> {
        	String name = JOptionPane.showInputDialog("Enter the username of the user: ");
			if(name.length()>0) {
				registrationClient.getSocketOut().println(12);
	        	registrationClient.getSocketOut().println(name);
			}	
        });
        
        //sets removeStudentButton
        removeStudentButton.addActionListener((ActionEvent e) -> {
        	String id = JOptionPane.showInputDialog("Enter the id of the student: ");
			if(id.length()>0) {
				registrationClient.getSocketOut().println(15);
	        	registrationClient.getSocketOut().println(id);
			}	
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
	        	
	        	registrationClient.getSocketOut().println(name);
	        	registrationClient.getSocketOut().println(num);						
        	}
		});
   

        // sets addCourseButton to get course information and which student for the information to be added
		addCourseButton.addActionListener((ActionEvent e) ->{
        	
        	JTextField courseName = new JTextField();
        	JTextField courseNum = new JTextField();
        	JTextField studentName = new JTextField();
        	
        	Object [] fields = {
        			
        			"Student Name:", studentName,
        			"Course Name (eg ENSF):" , courseName,
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
		
		addCourseToCatButton.addActionListener((ActionEvent e) -> {
			JTextField courseName = new JTextField();
			JTextField courseNum = new JTextField();
			Object [] fields = {
					"Course name:", courseName,
					"Course number:", courseNum
			};
			
			JOptionPane.showConfirmDialog(null, fields, "Add course to catalogue", JOptionPane.OK_CANCEL_OPTION);
			String cName = courseName.getText();
			String cNum = courseNum.getText();
			if(cName.length() > 0 && cNum.length() > 0) {
				registrationClient.getSocketOut().println(9);
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