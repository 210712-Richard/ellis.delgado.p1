package com.revature.service;

import java.util.List;

import com.revature.beans.Form;
import com.revature.beans.User;

public interface UserService {

	User login (String username);
	
	User register (String username, String email, String userType,
			String supervisor, String departmentHead, String benCo);
	
	List<Form> getForms(User user);
	
}
