package com.revature.controller;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.beans.Form;
import com.revature.beans.User;
import com.revature.factory.BeanFactory;
import com.revature.service.FormService;
import com.revature.service.FormServiceImp;

import io.javalin.http.Context;

public class FormContImp implements FormController{
	
	private static FormService formSer = (FormService) BeanFactory.getFactory().get(FormService.class, FormServiceImp.class);
	private static Logger log = LogManager.getLogger(FormContImp.class);
	
	@Override
	public void addForm(Context ctx) {
		User loggedUser = ctx.sessionAttribute("loggedUser");
		
		if(loggedUser == null) {
			log.error("User not logged in");
			ctx.status(401);
			return;
		}
		Form userForm = ctx.bodyAsClass(Form.class);
		log.debug(userForm);
		
		formSer.createAForm(userForm);
		
		ctx.json(userForm);
		
		
	}

	@Override
	public void getForm(Context ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getFile(Context ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addFile(Context ctx) {
	User loggedUser = ctx.sessionAttribute("loggedUser");
		
		if(loggedUser == null) {
			log.error("User not logged in");
			ctx.status(401);
			return;
		}
		String file = ctx.header("extension");
		
		if(file == null) {
			log.error("File null");
			ctx.status(400);
			return;
		}
		
	}

	@Override
	public void deleteForm(Context ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void udpateGrade(Context ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateStatus(Context ctx) {
		// TODO Auto-generated method stub
		
	}

}
