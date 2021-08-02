package com.reavature.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User implements Serializable{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
	private Long reimbursement;
	private List<Form> forms;
	
	public User() {
		super();
		this.userType = UserType.Employee;
		this.reimbursement= 0L;
//		this.fileObject = fileObject;
		this.forms = new ArrayList<Form>();
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

	public List<Form> getForms() {
		return forms;
	}

	public void setForm(List<Form> forms) {
		this.forms = forms;
	}

	public Long getReimbursement() {
		return reimbursement;
	}

	public void setReimbursement(Long reimbursement) {
		this.reimbursement = reimbursement;
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, employeeId, forms, reimbursement, userType, username);
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
		return Objects.equals(email, other.email) && employeeId == other.employeeId
				&& Objects.equals(forms, other.forms) && Objects.equals(reimbursement, other.reimbursement)
				&& userType == other.userType && Objects.equals(username, other.username);
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", email=" + email + ", employeeId=" + employeeId + ", userType="
				+ userType + ", reimbursement=" + reimbursement + ", forms=" + forms + "]";
	}

	
	

	
}
