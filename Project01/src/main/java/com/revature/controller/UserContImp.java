package com.revature.controller;

import java.time.LocalDate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.beans.User;
import com.revature.data.EventDAO;
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

//	@Override
//	public void getForms(Context ctx) {
//		String username = ctx.pathParam("username");
//		User loggedUser = (User) ctx.sessionAttribute("loggedUser");
//		if(loggedUser == null || !loggedUser.getUsername().equals(username)) {
//			log.error(loggedUser+ "not authorized");
//			ctx.status(403);
//			return; 
//		}
//		
//	}

	@Override
	public void logout(Context ctx) {
		log.trace("User logout method");
		ctx.req.getSession().invalidate();
		ctx.status(204);
		
	}

	@Override
	public void register(Context ctx) {
		log.trace("User register method");
		User user = ctx.bodyAsClass(User.class);
		
		User newUser = userSer.register(user.getUsername(), user.getEmail(),
				user.getUserType().toString(), user.getSupervisor(), user.getDepartmentHead(), user.getBenCo());
		ctx.status(201);
		ctx.json(newUser);
	
	}

	@Override
	public void getInbox(Context ctx) {
		log.trace("User get Inbox method");
		String username = ctx.pathParam("username");
		User loggedUser = (User) ctx.sessionAttribute("loggedUser");
		if(loggedUser == null || !loggedUser.getUsername().equals(username)) {
		log.error(loggedUser+ "not authorized");
			ctx.status(403);
			return; 
			}
		ctx.json(loggedUser.getInbox());
	}

	@Override
	public void getEvent(Context ctx) {
		String username = ctx.pathParam("username");
		String title = ctx.pathParam("title");
		String type = ctx.pathParam("type");
		User loggedUser = (User) ctx.sessionAttribute("loggedUser");
		if(loggedUser == null || !loggedUser.getUsername().equals(username)) {
			log.error(loggedUser+ "not authorized");
			ctx.status(403);
		return; 
		}
		ctx.json(userSer.getEventbyTitleAndType(title, type));
		
	}

	@Override
	public void addEvent(Context ctx) {
		String username = ctx.pathParam("username");
		String title = ctx.pathParam("title");
		String type = ctx.pathParam("type");
		String description = ctx.pathParam("description"); 
		String year = ctx.pathParam("year");
		String month = ctx.pathParam("month");
		String day = ctx.pathParam("day");
		User loggedUser = (User) ctx.sessionAttribute("loggedUser");
		if(loggedUser == null || !loggedUser.getUsername().equals(username)) {
			log.error(loggedUser+ "not authorized");
			ctx.status(403);
		return; 
		}
		ctx.json(userSer.addEvent(title, type, LocalDate.of(Integer.valueOf(year), Integer.valueOf(month), Integer.valueOf(day)), description));
	}

	@Override
	public void updateReim(Context ctx) {
		String username = ctx.pathParam("username");
		
		User loggedUser = (User) ctx.sessionAttribute("loggedUser");
		if(loggedUser == null || !loggedUser.getUsername().equals(username)) {
			log.error(loggedUser+ "not authorized");
			ctx.status(403);
		return; 
		}
		
		String employee = ctx.pathParam("employee");
		String event = ctx.pathParam("event");
		String eventType = ctx.pathParam("type");
		userSer.updateUserReimbursement(loggedUser, employee, event, eventType);
		
		
	}

}
