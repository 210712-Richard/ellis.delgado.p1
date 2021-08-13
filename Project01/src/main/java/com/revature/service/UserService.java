package com.revature.service;

import java.util.List;

//import java.util.List;

//import com.revature.beans.Form;
import com.revature.beans.Inbox;
import com.revature.beans.User;

public interface UserService {

	User login (String username);
	
	User register (String username, String email, String userType,
			String supervisor, String departmentHead, String benCo);
	
	void updateUserReimbursement(User user, String employee, String event, String eventType);
	
	List<Inbox> getUserInbox(User user);
	
	void updateSupervisor(User user, String employee);
	
	void updateDepHead(User user, String employee);
	
	void updateBenCo(User user, String employee);
	
	void updateInbox(User user, String title, String message);
	
	
	
	
}
