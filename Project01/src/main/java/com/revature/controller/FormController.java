package com.revature.controller;

import io.javalin.http.Context;

public interface FormController {

	void addForm(Context ctx);
	
	void getForm(Context ctx);
	
	void getFile(Context ctx);
	
	void addFile(Context ctx);
	
	void deleteForm(Context ctx);
	
	void udpateGrade(Context ctx);
	
	void updateStatus(Context ctx);
	
}
