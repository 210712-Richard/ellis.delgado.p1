package com.revature.service;

import java.util.List;

import com.revature.beans.Form;
import com.revature.beans.Status;
import com.revature.beans.User;
import com.revature.data.*;
import com.revature.factory.BeanFactory;

public class FormServiceImp implements FormService {
	
	private FormDAO formDao = (FormDAO) BeanFactory.getFactory().get(FormDAO.class, FormDAOImp.class);
	
	@Override
	public List<Form> getForms(User user) {
		String username = user.getUsername();
		
		List<Form> userForms = formDao.getUserForms(username);		
		return userForms;
	}

	@Override
	public String getUserFile(User user) {
		String username = user.getUsername();
		Form form = formDao.getFormbyEmployee(username);
		String file = form.getFile();
		return file;
	}

	@Override
	public Form getEmployeeForm(User user) {
		String username = user.getUsername();
		Form form = formDao.getFormbyEmployee(username);
		return form;
	}

	@Override
	public void addFile(User user, String fileUrl) {
		String username = user.getUsername();
		Form form = formDao.getFormbyEmployee(username);
		form.setFile(fileUrl);
		
	}

	@Override
	public void updateGrade(User user, String userGrade) {
		String username = user.getUsername();
		Form form = formDao.getFormbyEmployee(username);
		form.setGrade(userGrade);
		
	}

	@Override
	public void updateStatus(User user, Status status) {
		String username = user.getUsername();
		Form form = formDao.getFormbyEmployee(username);
		form.setStatus(status);
		
	}

	@Override
	public void deleteForm(Form form) {
//		String username = user.getUsername();
//		Form form = formDao.getFormbyEmployee(username);
		formDao.deleteForm(form);
		
	}

	@Override
	public Form createAForm(Form userForm) {
		formDao.addForm(userForm);
		return userForm;
		
	}
}
