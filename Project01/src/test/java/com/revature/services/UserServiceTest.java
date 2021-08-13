package com.revature.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import com.revature.beans.Form;
import com.revature.beans.User;
import com.revature.beans.UserType;
import com.revature.data.EventDAO;
import com.revature.data.EventDAOImp;
import com.revature.data.InboxDAO;
import com.revature.data.InboxDAOImp;
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
		
		assertEquals(email, user.getEmail(), "Asserting emails");
		assertEquals(type, user.getUserType(), "Asserting types");
		assertEquals(supervisor, user.getSupervisor(), "Asserting supervisor");
		assertEquals(depHead, user.getDepartmentHead(), "Asserting departmentHead");
		assertEquals(benCo, user.getBenCo(), "Asserting BenCo");
	}
	
	@Test
	public void testUpdateSuper() {
		String employee = "Henry";
		userSer.updateSupervisor(user, employee);
		
		assertEquals(employee, user.getSupervisor(), "Asserting supervisor");
	}
	
	@Test
	public void testUpdateDepHead() {
		String employee = "Wendy";
		userSer.updateDepHead(user, employee);
		
		assertEquals(employee, user.getDepartmentHead(), "Asserting supervisor");
	}
	@Test
	public void testUpdateBenCo() {
		String employee = "Boba Fett";
		userSer.updateBenCo(user, employee);
		
		assertEquals(employee, user.getBenCo(), "Asserting supervisor");
	}
	@Test
	public void testUpdateInbox() {
		InboxDAO inboxDao = new InboxDAOImp();
		String title = "Hello";
		String message = "testing 1, 2, 3";
		
		userSer.updateInbox(user, title, message);
		
		assertEquals(user.getInbox(), inboxDao.getInbox(user.getUsername()), "Assertin inboxes equal");
	}
	@Test
	public void testGetInbox() {
		InboxDAO inboxDao = new InboxDAOImp();
		userSer.getUserInbox(user);
		
		assertEquals(user.getInbox(), inboxDao.getInbox(user.getUsername()), "Assertin inboxes equal");
	}
	@Test
	public void updateReimburse() {
		user.setUserType(UserType.Benefits_Coordinator);
		
		
		
		String employee = "Wendy";
		String event = "Test_event";
		String eventType = "certification";
		
		userSer.updateUserReimbursement(user, employee, event, eventType);
		
		assertEquals(userSer.userDao.getUser(employee).getReimbursement(), userSer.userDao.getUser(employee).getReimbursement(), "Asserting reimbursement");
		
	}
	@Test 
	public void addEvent() {
		Form form = new Form();
		EventDAO eventDao = new EventDAOImp();
		
		String title = "Test_event";
		String eventType = "certification";
		LocalDate startDate = LocalDate.of(2021, 8, 8);
		String description = "Testing again";
		
		userSer.addEvent(title, eventType, startDate, description);
		
		assertEquals(form.getEvent(), eventDao.getAllEvents(), "Asserting get events" );
	}
	@Test 
	public void testGetEvents() {
		Form form = new Form();
		EventDAO eventDao = new EventDAOImp();
		userSer.getEvents();
		
		assertEquals(form.getEvent(), eventDao.getAllEvents(), "Asserting get events" );
		
	}
	@Test
	public void testGetEventByTitleAndType() {
		Form form = new Form();
		EventDAO eventDao = new EventDAOImp();
		
		String title = "Test_event";
		String eventType = "certification";
		
		userSer.getEventbyTitleAndType(title, eventType);
		
		assertEquals(form.getEvent(), eventDao.getAllEvents(), "Asserting get events" );	
			
	}
	

}
