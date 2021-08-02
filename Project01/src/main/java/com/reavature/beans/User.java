package com.reavature.beans;

import java.io.Serializable;

public class User implements Serializable{
/*
 * What does do our users need?
 * 
 *	1. Name
 *	2. User Type
 *	3. Email
 *	4. Employee number
 *		file - this  will be its own object me thinks
 *	5. currency?
 *		- maybe to hold reimbursement
 */
	private String username;
	private String email;
	private int employeeId;
	private UserType userType;
	private FileObject fileObject;
	
	public User() {
		super();
		this.userType = userType;
		this.fileObject = fileObject;
	}
	
	public User(String username, String email, int employeeId ) {
		this.username= username;
		this.email = email;
		this.employeeId= employeeId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public FileObject getFileObject() {
		return fileObject;
	}

	public void setFileObject(FileObject fileObject) {
		this.fileObject = fileObject;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + employeeId;
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (employeeId != other.employeeId)
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", email=" + email + ", employeeId=" + employeeId + ", getUsername()="
				+ getUsername() + ", getEmail()=" + getEmail() + ", getEmployeeId()=" + getEmployeeId()
				+ ", hashCode()=" + hashCode() + ", getClass()=" + getClass() + ", toString()=" + super.toString()
				+ "]";
	}


	
}
