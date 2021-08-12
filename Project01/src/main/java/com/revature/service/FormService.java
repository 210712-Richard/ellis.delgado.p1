package com.revature.service;

import java.util.List;

import com.revature.beans.Form;
import com.revature.beans.User;

public interface FormService {
List<Form> getForms(User user);
	
	String getUserFile (User user);
	
	Form getEmployeeForm(User user);
	
	
}
