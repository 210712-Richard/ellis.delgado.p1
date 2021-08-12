package com.revature.service;

import java.util.List;

import com.revature.beans.Form;
import com.revature.beans.User;
import com.revature.data.*;

public class FormServiceImp implements FormService {
	
	public static FormDAO formDao = new FormDAOImp();
	
	@Override
	public List<Form> getForms(User user) {
		String username = user.getUsername();
		
		List<Form> userForms = formDao.getUserForms(username);		
		return userForms;
	}

	@Override
	public String getUserFile(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Form getEmployeeForm(User user) {
		// TODO Auto-generated method stub
		return null;
	}
}
