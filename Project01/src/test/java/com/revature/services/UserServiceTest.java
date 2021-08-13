package com.revature.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.UUID;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import com.revature.beans.User;
import com.revature.beans.UserType;
import com.revature.data.UserDAO;
import com.revature.service.UserServiceImp;

public class UserServiceTest {
	private static UserServiceImp userSer;
	private static User user;
	
	@BeforeAll
	public static void setUpClass() {
		user = new User();
		user.setUsername("Bob");
	}
	
	@BeforeEach 
	public void setUpTest() {
		userSer = new UserServiceImp();
		userSer.userDao = Mockito.mock(UserDAO.class);
	}
	
	@Test
	public void testRegister() {
		String username = "Bob";
		String email = "bob@gmail.com";
//		UUID employeeId = UUID.randomUUID();
		String type = UserType.Employee.toString();
		String supervisor = "Joe";
		String depHead = "May";
		String benCo = "Danny";
		
		userSer.register(username, email, type, supervisor, depHead, benCo);
		
		ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
		
		Mockito.verify(userSer.userDao).addUser(captor.capture());
		
		User user = captor.getValue();
		
		assertEquals(email, user.getEmail(), "Asserting emails");
		assertEquals(type, user.getUserType(), "Asserting types");
		assertEquals(supervisor, user.getSupervisor(), "Asserting supervisor");
		assertEquals(depHead, user.getDepartmentHead(), "Asserting departmentHead");
		assertEquals(benCo, user.getBenCo(), "Asserting BenCo");
	}
	

}
