package com.revature.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.beans.User;

import io.javalin.http.Context;

public class UserContImp implements UserController{

	private static Logger log = LogManager.getLogger(UserContImp.class);
	
	@Override
	public void login(Context ctx) {
		log.debug(ctx.body());
		
		User user = ctx.bodyAsClass(User.class);
		log.debug(user);
		
		if (user != null) {
			ctx.sessionAttribute("loggedUser", user);
			ctx.json(user);
			return;
		}
		log.error("User failed to log in");
		ctx.status(401);
		
	}

	@Override
	public void getForms(Context ctx) {
		String username = ctx.pathParam("username");
		User loggedUser = (User) ctx.sessionAttribute("loggedUser");
		if(loggedUser == null || !loggedUser.getUsername().equals(username)) {
			log.error(loggedUser+ "not authorized");
			ctx.status(403);
			return; 
		}
		
	}

	@Override
	public void createForm(Context ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void logout(Context ctx) {
		// TODO Auto-generated method stub
		
	}

}
