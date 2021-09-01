# TRMS System

## Project Description

  A TRMS (Tution Reinbursement Management System) designed to help users go through the process of requesting a reimbursement for a course, approving a reimbursement form, and distributing the award to the requested user. 
  
## Technologies Used
  * Java
  * Log4j
  * JUnit
  * Javalin
  * Postman
  * AWS Keyspaces
  * S3

## Features
  * Functioning Login with different User types
  * An employee can submit a reimbursement request through a URL
  * An employee's direct supervisor can approve or deny the request of the loan
  * If the loan is approved, the Benefits Coordinator can award the reimbursement based on the type of course taken
  * If the user trying to access the employee's form is not a direct user, they will be denied access
 
 ## Getting Started
 
  * Clone the repository in your terminal with the `git clone <repository url>` command
  * You need to establish a Keyspace connection on the AWS website
      - Once you get your *AWS Username* and *AWS Password* from the IAM, you need to set those as            environmental variables
  
## Usage
  * To start the application, go to the driver.java file and run the application
  * Once the connection is established, use *Postman* to test any routes from the User Controller!

## Contributors
  * Ellis Delgado
      - Github: Ellisd16

## Liscense

  
 
