package com.revature.service;

//import java.util.List;

import com.revature.beans.Form;
import com.revature.beans.Inbox;
import com.revature.beans.User;

public interface UserService {

	User login (String username);
	
	User register (String username, String email, String userType,
			String supervisor, String departmentHead, String benCo);
	
	void updateUserReimbursement(User user);
	
	Inbox getUserInbox(User user);
	
	void deleteUser(User user);
	
	void updateSupervisor(User user);
	
	void updateDepHead(User user);
	
	void updateBenCo(User user);
	
	void updateInbox(User user);
	
	
	
	
}
