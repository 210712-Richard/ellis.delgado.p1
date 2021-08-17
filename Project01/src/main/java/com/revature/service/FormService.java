package com.revature.service;

import java.util.List;

import com.revature.beans.Form;
import com.revature.beans.Status;
import com.revature.beans.User;

public interface FormService {
List<Form> getForms(User user);
	
	String getUserFile (User user);
	
	Form getEmployeeForm(User user);
	
	void addFile(User user, String fileUrl);
	
	
	void updateGrade(User user, String userGrade);
	
	void updateStatus(User user, Status status);
	
//	void deleteForm(User user);
	void deleteForm(Form form);
	
	Form createAForm(Form userForm);
	
	
	
}
