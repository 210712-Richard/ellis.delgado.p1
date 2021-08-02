package com.reavature.data;

import java.util.List;

import com.reavature.beans.Form;
import com.reavature.beans.User;

public interface UserDAO {
	void addUser(User user);
	
	List<User> getUsers();
	
	User getUser(String username);
	
	void updateUser(User user);
	
	List<Form> getUserForms();
	
}
