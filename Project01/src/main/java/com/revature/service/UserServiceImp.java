package com.revature.service;

import java.util.List;

import com.revature.beans.Form;
import com.revature.beans.User;
import com.revature.beans.UserType;
import com.revature.data.FormDAO;
import com.revature.data.FormDAOImp;
import com.revature.data.UserDAO;
import com.revature.data.UserDAOImp;

public class UserServiceImp implements UserService{
	public UserDAO userDao = new UserDAOImp();
	public static FormDAO formDao = new FormDAOImp();
	
	
	@Override
	public User login(String username) {
		User user = userDao.getUser(username);
		return user;
	}

	@Override
	public User register(String username, String email, String userType,
			String supervisor, String departmentHead, String benCo) {
		User user = new User();
		user.setUsername(username);
		user.setUserType(UserType.valueOf(userType));
		user.setEmail(email);
		user.setSupervisor(supervisor);
		user.setDepartmentHead(departmentHead);
		user.setBenCo(benCo);
		
		return user;
	}

	@Override
	public List<Form> getForms(User user) {
		String username = user.getUsername();
		
		List<Form> userForms = formDao.getUserForms(username);		
		return userForms;
	}

}
