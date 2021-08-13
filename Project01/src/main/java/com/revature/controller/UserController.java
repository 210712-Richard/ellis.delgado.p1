package com.revature.controller;

import io.javalin.http.Context;

public interface UserController {

		void login (Context ctx);
		
		void logout (Context ctx);
		
		void register(Context ctx);
		
		void getInbox(Context ctx);
		
		void getEvent(Context ctx);
		
		void addEvent(Context ctx);
		
		void updateReim(Context ctx);
		
}
