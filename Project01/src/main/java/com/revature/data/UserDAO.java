package com.revature.data;

import java.util.List;

import com.revature.beans.Form;
import com.revature.beans.Inbox;

import com.revature.beans.User;

public interface UserDAO {
	void addUser(User user);
	
	List<User> getUsers();
	
	User getUser(String username);
	
	void updateUser(User user);

	List<Form> getUserForms(String username);

	List<Inbox> getUserInbox(String username);
	
}
