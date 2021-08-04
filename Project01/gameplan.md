# Game Plan

## What are we making??

* Building a *REST API* for a *TRMS*

	- TRMS: Tuition Reimbursement Management System
	
### Rules for TRMS

	- Use these technologies:
	
* AWS S3 Integration for File Upload
* RESTful API built with Javalin
* Data persistence through AWS Keystore for Apache Cassandra
* Logging with Log4J2
* Unit testing with JUnit


### Other

* Complete approval process
* File upload to skip supervisor approval
* Discrete Data Access layer
* Full test coverage for at least the service layer




## User Story
** As a User, I want to log into my account
** As a User, I want to logout of my account
** As an employee, I want to submit a reimbursement requests
** As an employee, I want to be able to submit any files needed 
** 


## What do we need to do?

1. Make users with different access levels
2. Create database to hold user info / reimbursement requests
3. Date and time management


** Basically,** we have to pass a file up from employee all the way to BenCo. If anyone tries to access it before their turn, they should get a 403 error. Once the file is all the way at the top, it should get approved and the state of approved for the reimbursement should be set to true 



## General notes

- we have different types of users who have different abilities
	* Employee (Jubilee)
		- create reimbursement 
	* Direct Supervisor (Wolverine)
		- approve or deny reimbursement
			- if *denied* must say why
		- can request addition info from employee
		- has to send to department head unless IS department head as well
		- if not completed in time frame, request auto approved
		-must confirm that the presentation was satisfactory and presented 			to the appropriate parties
	* Department Head (Jean Grey)
		- approve or deny reimbursement
		- can request addition info from employee or DS
		- has to send to department head unless IS department head as well
		- if not completed in time frame, request auto approved
	* Benefits Coordinator (Xavier)
		- MUST approve or deny reimbursement
		- Can change amount
			- must state why amount changed
				- if exceeding, reimbursement must be marked as exceeding 					available funds for reporting purposes
		- Must confirm if grade from employee is passing

## Extra info

* The Tuition Reimbursement System, TRMS, allows users to submit reimbursements for courses and training. The submitted reimbursement must be approved by that employee's supervisor, department head, and benefits coordinator. The benefits coordinator then reviews the grade received before finalizing the reimbursement.
		
## Okay, so what do we need to do today?
* well, 
	-  We finished creating the user object and the form object
	- should we build the UserDAO
		*the only thing about that is that im a little confused 			
		about the functionality of the app right now
	- Or we could try and create a user databse?
		* we probably need the DAO first 
		
		
*08/03*
	Agenda
		- Lets work on that UserDAO,
		- Lets figure out if we need to create an object for
			the reimbursements
		- Lets see if we can get the User services started
		
*08/04*
	- lets get the reimbursements going
	- lets upload a file to aws
	- lets view said file
	
		
	
	





