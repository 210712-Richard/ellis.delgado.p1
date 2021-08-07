package com.revature.controller;

import io.javalin.http.Context;

public interface UserController {

		void login (Context ctx);
		
		void getForms(Context ctx);
		
		void createForm(Context ctx);
		
		void logout (Context ctx);
		
}
