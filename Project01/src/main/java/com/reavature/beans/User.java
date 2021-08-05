package com.reavature.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class User implements Serializable{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/*
 * What do our users need?
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
	private UUID employeeId;
	private UserType userType;
	private Long pending;
	private Long approved;
	private List<Form> forms;
	private String supervisor;
	private String departmentHead;
	private String benCo;
	private List<Inbox> inbox;
	
	public User() {
		super();
		
		this.approved = 0L;
		this.pending = 0L;
//		this.fileObject = fileObject;
		this.forms = new ArrayList<Form>();
		this.inbox = new ArrayList<Inbox>();
	}

	//Employee
	public User(String username, String email, UUID employeeId, UserType usertype,
			String supervisor, String departmentHead, String benCo ) {
		this.username= username;
		this.email = email;
		this.employeeId= employeeId;
		this.userType = UserType.Employee;
		this.supervisor= supervisor;
		this.departmentHead = departmentHead;
		this.benCo = benCo;
	}
	//Supervisor
	public User(String username, String email, UUID employeeId, UserType usertype,
			String departmentHead, String benCo ) {
		this.username= username;
		this.email = email;
		this.employeeId= employeeId;
		this.userType = UserType.Direct_Supervisor;
		this.supervisor = null;
		this.departmentHead = departmentHead;
		this.benCo = benCo;
	}
	//DepartmentHead
	public User(String username, String email, UUID employeeId, UserType usertype,
			 String benCo ) {
		this.username= username;
		this.email = email;
		this.employeeId= employeeId;
		this.userType = UserType.Department_Head;
		this.supervisor = null;
		this.departmentHead = null;
		this.benCo = benCo;
	}
	//BenCo
	public User(String username, String email, UUID employeeId, UserType usertype) {
		this.username= username;
		this.email = email;
		this.employeeId= employeeId;
		this.userType = UserType.Benefits_Coordinator;
		this.supervisor = null;
		this.departmentHead = null;
		this.benCo = null;
		
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

	public UUID getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(UUID employeeId) {
		this.employeeId = employeeId;
	}
	public Long getPending() {
		return pending;
	}
	public void setPending(Long pending) {
		this.pending = pending;
	}
	public Long getApproved() {
		return approved;
	}
	public void setApproved(Long approved) {
		this.approved = approved;
	}

	public List<Inbox> getInbox() {
		return inbox;
	}
	public void setInbox(List<Inbox> inbox) {
		this.inbox = inbox;
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

	public String getSupervisor() {
		return supervisor;
	}
	public void setSupervisor(String supervisor) {
		this.supervisor = supervisor;
	}
	public String getDepartmentHead() {
		return departmentHead;
	}
	public void setDepartmentHead(String departmentHead) {
		this.departmentHead = departmentHead;
	}
	public String getBenCo() {
		return benCo;
	}
	public void setBenCo(String benCo) {
		this.benCo = benCo;
	}
	@Override
	public int hashCode() {
		return Objects.hash(approved, benCo, departmentHead, email, employeeId, forms, inbox, pending, supervisor,
				userType, username);
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
		return Objects.equals(approved, other.approved) && Objects.equals(benCo, other.benCo)
				&& Objects.equals(departmentHead, other.departmentHead) && Objects.equals(email, other.email)
				&& Objects.equals(employeeId, other.employeeId) && Objects.equals(forms, other.forms)
				&& Objects.equals(inbox, other.inbox) && Objects.equals(pending, other.pending)
				&& Objects.equals(supervisor, other.supervisor) && userType == other.userType
				&& Objects.equals(username, other.username);
	}
	@Override
	public String toString() {
		return "User [username=" + username + ", email=" + email + ", employeeId=" + employeeId + ", userType="
				+ userType + ", pending=" + pending + ", approved=" + approved + ", forms=" + forms + ", supervisor="
				+ supervisor + ", departmentHead=" + departmentHead + ", benCo=" + benCo + ", inbox=" + inbox + "]";
	}
	
	
	
	

	
}
