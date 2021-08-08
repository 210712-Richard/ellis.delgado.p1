package com.revature.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.beans.User;
import com.revature.factory.BeanFactory;
import com.revature.service.UserService;
import com.revature.service.UserServiceImp;

import io.javalin.http.Context;

public class UserContImp implements UserController{

	private static Logger log = LogManager.getLogger(UserContImp.class);
	private UserService userSer = (UserService) BeanFactory.getFactory().get(UserService.class, UserServiceImp.class);
	
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
		ctx.req.getSession().invalidate();
		ctx.status(204);
		
	}

	@Override
	public void register(Context ctx) {
		User user = ctx.bodyAsClass(User.class);
		
		User newUser = userSer.register(user.getUsername(), user.getEmail(),
				user.getUserType().toString(), user.getSupervisor(), user.getDepartmentHead(), user.getBenCo());
		ctx.status(201);
		ctx.json(newUser);
	
	}

}
