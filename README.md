# ENSF 409 Term Project W20 - University of Calgary

* MEMBERS OF THE GROUP:
Dillon Sahadevan (30075927) - dillon.sahadevan@ucalgary.ca
Gurmukh Singh (30079144) -  gurmukh.singh@ucalgary.ca
Eduardo Benetti (30080086) - eduardo.benetti@ucalgary.ca


* ABOUT THE PROGRAM:
The program is designed for students to register for courses. With a Server-Client model (through a Socket design), the program runs with a Server accepting multiple users at the same time to register for courses, which can be found on the MySQL database whose tables can be found in the data folder.


* EXECUTION OF THE PROGRAM:
The program reads information from the MySQL database, which are kept as tables. With the information loaded into the program, the user can login as a regular user (as a student), to register for courses or as an admin user (as the university), to add courses for the students to register, along with other features.

- Features for the Regular User:
	- Search course catalogue                                 - used to retrieve information about a specific course
	- Add course to student's courses                         - register a student for a sepcific course
	- Remove courses from student's courses                   - remove a certain course from student's records
	- View all courses in the catalogue		                  - retrieve information about all courses in the catalogue
	- View all course taken by the student                    - view course information about a specific student
	- Print student's record                                  - view information about the student
	- Quit                                                    - quit the program

- Features for the Admin:
	- Search for specific course 			                  - used to retrieve information about a specific course
	- Add course to Student          		                  - register a student for a certain course
	- Remove course from Student   			                  - remove a certain course from student's records
	- View all courses				      	                  - retrieve information about all courses in the catalogue
	- View courses taken by student   	                      - view course information about a specific student
	- View registrations           			                  - view all registrations created by students
	- Add course to catalogue                                 - add a course to the course catalogue
    - Remove course from catalougue                           - remove a course from the course catalogue
    - Add user                                                - adds a user to the program with either standard or elevated privileges
    - Remove user                                             - Removes a user from the program
    - View all users                                          - View all users and there user privileges
    - Add student                                             - adds a student to the database
    - Remove student                                          - removes a student from the database
    - View all students                                       - view all students in the database
	- Quit the program                                        - quit the program


* HOW TO RUN THE PROGRAM:
Import the tables in data into a local MySQL Server and change the url and password in the DatabaseDriver class constructor.
Execute RegistrationServer.java and RegistrationClient.java one after the other.
Each student may login to the program using their name as their username and "password" as password.
For elevated privileges, login with "admin" as username and "adminpassword" as password.

* DATABASE INFORMATION ABOUT THE PROGRAM FOR TESTING: 
All the data the program uses is kept as tables in the MySQL (version 8.0) database. 

- The program has a total of 5 MySQL tables, as follows:
    - Courses
	- Prerequisites
	- Completed Courses 
	- Students
	- Users

- Information in the Courses table:
	- ENGG 233
	- ENGG 202
	- ENGG 225
	- ENSF 409 (only course that requires a pre-req. Its pre-req is ENGG 233)
	- PHYS 259

- Information in the Students table:
	- John
	- Violet
	- Karl
	- Lara
	- Kane
	- Jude
	- Adam
	- Sarah (the only student who have completed ENGG 233 and is eligible for taking ENSF 409)


* BONUS FEATURES IMPLEMENTED: 
1. Login feature to differentiate between the regular user and the admin
2. Admin feature to add and remove courses to the database for further uses for the students, along with all the features provided to regular users
3. Admin feature to add and remove users to the database.
4. Admin feature to add and remove student to the database.
