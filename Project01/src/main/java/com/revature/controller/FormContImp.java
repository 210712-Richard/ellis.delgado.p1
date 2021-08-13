package com.revature.controller;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.beans.Form;
import com.revature.beans.Status;
import com.revature.beans.User;
import com.revature.factory.BeanFactory;
import com.revature.service.FormService;
import com.revature.service.FormServiceImp;
import com.revature.util.S3Util;

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
	User loggedUser = ctx.sessionAttribute("loggedUser");
		
		if(loggedUser == null) {
			log.error("User not logged in");
			ctx.status(401);
			return;
		}
		Form form = formSer.getEmployeeForm(loggedUser);
		ctx.json(form);
	}

	@Override
	public void getFile(Context ctx) {
	User loggedUser = ctx.sessionAttribute("loggedUser");
		
		if(loggedUser == null) {
			log.error("User not logged in");
			ctx.status(401);
			return;
		}
		Form form = formSer.getEmployeeForm(loggedUser);
		String file = form.getFile();
		
		ctx.json(file);
		
	}

	@Override
	public void addFile(Context ctx) {
	User loggedUser = ctx.sessionAttribute("loggedUser");
	String fileName = ctx.pathParam("fileName");
	
	String userFile = formSer.getUserFile(loggedUser);
		if(userFile == null) {
					log.error("File Name null");
					ctx.status(400);
					return;
			}
		
		if(loggedUser == null) {
			log.error("User not logged in");
			ctx.status(401);
			return;
		}
		String filetype = ctx.header("extension");
		
		if(filetype == null) {
			log.error("File null");
			ctx.status(400);
			return;
		}
		String key = fileName+ "."+ filetype;
		S3Util.getInstance().uploadToBucket(key, ctx.bodyAsBytes());
		
		formSer.addFile(loggedUser, key);
		ctx.json(userFile);
	}

	@Override
	public void deleteForm(Context ctx) {
	User loggedUser = ctx.sessionAttribute("loggedUser");
		
		if(loggedUser == null) {
			log.error("User not logged in");
			ctx.status(401);
			return;
		}
		formSer.deleteForm(loggedUser);
	}

	@Override
	public void udpateGrade(Context ctx) {
		User loggedUser = ctx.sessionAttribute("loggedUser");
		
		String grade = ctx.pathParam("grade");
		
		if(loggedUser == null) {
			log.error("User not logged in");
			ctx.status(401);
			return;
		}
		Form form = formSer.getEmployeeForm(loggedUser);
		form.setGrade(grade);
		
	}

	@Override
	public void updateStatus(Context ctx) {
User loggedUser = ctx.sessionAttribute("loggedUser");
		
		Status status = Status.valueOf(ctx.pathParam("status"));
		
		if(loggedUser == null) {
			log.error("User not logged in");
			ctx.status(401);
			return;
		}
		Form form = formSer.getEmployeeForm(loggedUser);
		form.setStatus(status);
	}

}
