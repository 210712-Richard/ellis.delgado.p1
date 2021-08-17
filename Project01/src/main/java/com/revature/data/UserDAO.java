package com.revature.data;

import java.util.List;
import java.util.UUID;

import com.revature.beans.Form;
import com.revature.beans.Inbox;

import com.revature.beans.User;

public interface UserDAO {
	void addUser(User user);
	
	List<User> getUsers();
	
	User getUser(String username);
	
	void updateUser(User user);
	
	void updateUserReim(Long amount, User user);

	List<UUID> getUserForms(String username);

	List<UUID> getUserInbox(String username);
	
}
