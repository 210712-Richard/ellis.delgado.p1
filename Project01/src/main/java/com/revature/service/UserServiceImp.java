package com.revature.service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.beans.AlertType;
import com.revature.beans.EventOp;
import com.revature.beans.Form;
import com.revature.beans.Inbox;
import com.revature.beans.Status;
import com.revature.beans.User;
import com.revature.beans.UserType;
import com.revature.data.EventDAO;
import com.revature.data.EventDAOImp;
import com.revature.data.FormDAO;
import com.revature.data.FormDAOImp;
import com.revature.data.InboxDAO;
import com.revature.data.InboxDAOImp;
import com.revature.data.UserDAO;
import com.revature.data.UserDAOImp;

public class UserServiceImp implements UserService{
	public UserDAO userDao = new UserDAOImp();
	public static FormDAO formDao = new FormDAOImp();
	public static EventDAO eventDao = new EventDAOImp();
	public static InboxDAO inboxDao = new InboxDAOImp();
	
	private static Logger log = LogManager.getLogger(UserServiceImp.class);
	
	
	@Override
	public User login(String username) {
		User user = userDao.getUser(username);
		
		List<UUID> formsId = userDao.getUserForms(username);
		if (formsId == null) {
			return user;
		}
		List<Form> forms = formsId.stream().map(id -> formDao.getFormbyId(id))
				.collect(Collectors.toList());
		user.setForms(forms);
		return user;
	}

	@Override
	public User register(String username, String email, String userType,
			String supervisor, String departmentHead, String benCo) {
		User user = new User();
		user.setUsername(username);
		user.setUserType(UserType.valueOf(userType));
		user.setEmail(email);
		user.setSupervisor(supervisor);
		user.setDepartmentHead(departmentHead);
		user.setBenCo(benCo);
		
		log.trace("User returned: "+ user);
		return user;
	}
	
	
	
	@Override
	public void updateFormStatus(User user, String employee) {
		User loggedUser = user;
		
		if (userDao.getUser(employee).getUserType().equals(UserType.Employee) 
				&& !loggedUser.getUserType().equals(UserType.Direct_Supervisor)) {
			log.error("Logged user unauthorized");
			return;
		}
		if (userDao.getUser(employee).getUserType().equals(UserType.Direct_Supervisor) 
				&& !loggedUser.getUserType().equals(UserType.Department_Head)) {
			log.error("Logged user unauthorized");
			return;
		}
		if (userDao.getUser(employee).getUserType().equals(UserType.Department_Head) 
				&& !loggedUser.getUserType().equals(UserType.Benefits_Coordinator)) {
			log.error("Logged user unauthorized");
			return;
		}
		
	
		//checking if grades are to standard
	try {	
		Form empForm = formDao.getFormbyEmployee(employee);
		String empGrade = empForm.getGrade();
		gradeCheck(empGrade, empForm);
		log.trace("Form status: "+ empForm.getStatus());
		}catch(Exception e) {
			log.error("Failed to change status: " + e);
		}
	}

	
	
	@Override
	public void updateUserReimbursement(User user, String employee, String event, String eventType) {
		User loggedUser = user; 
		
		//if not a benco, then the user can't edit the reimbursement status
		if (!loggedUser.getUserType().equals(UserType.Benefits_Coordinator)) {
			return; 
		}
		
		User emp = userDao.getUser(employee);
		Long currReimburse = emp.getReimbursement();
		EventOp empEvent = eventDao.getEventbyTitleAndType(event, eventType);
		Form empForm = formDao.getFormbyEmployee(employee);
		Integer empCost = empForm.getCost();
		String empGrade = empForm.getGrade();
		
		Integer maxTuition = 1000;
		
		log.trace("Employee form status: "+ empForm.getStatus());
		
		if (empForm.getStatus().equals(Status.Approved)) {
			log.trace("Calculating Tuition");
			Long newAmount = reimburseCalc(eventType, maxTuition, currReimburse, empCost);
			emp.setReimbursement(newAmount);
			empForm.setDescription("Congratulations! Amount awarded: " + newAmount.toString());
			log.trace("New amount: "+ newAmount);
			log.trace("Employee reimbursement"+emp.getReimbursement());
		}else {
			log.trace("Deny statement");
			empForm.setDescription("Unfortunately you did not met the requirements for a reimbursement. "
					+ "Please try again at a later date");
			
		}
	
		
	
		
	}
	
	
	

	@Override
	public List<Inbox> getUserInbox(User user) {
		String username = user.getUsername();
		
		
		List<Inbox> inbox = inboxDao.getInbox(username);
		user.setInbox(inbox);
		
		return inbox;
	}


	@Override
	public void updateSupervisor(User user, String employee) {
		user.setSupervisor(employee);
	}

	@Override
	public void updateDepHead(User user, String employee) {
		user.setDepartmentHead(employee);
		
	}

	@Override
	public void updateBenCo(User user, String employee) {
		user.setBenCo(employee);
		
	}

	@Override
	public void updateInbox(User user, String title, String message) {
	
	
	Inbox newMessage = new Inbox();
	UUID inboxId = UUID.randomUUID();
	newMessage.setMessageId(inboxId);
	newMessage.setAlert(AlertType.Urgent);
	newMessage.setTitle(title);
	newMessage.setMessage(message);
	
	inboxDao.addInbox(newMessage);
//	user.setInbox((List<Inbox>) newMessage);
	List<Inbox> userInbox = inboxDao.getInbox(user.getUsername());
	userInbox.add(newMessage);
		
	}
	@Override
	public	List<EventOp> getEvents() {
		List<EventOp>events = eventDao.getAllEvents();
		return events;
	}
	public EventOp getEventbyTitleAndType(String title, String type) {
		EventOp event = eventDao.getEventbyTitleAndType(title, type);
		return event;
	}

	public EventOp addEvent(String title, String type, LocalDate startDate, String description) {
		EventOp newEvent = new EventOp();
		UUID eventId = UUID.randomUUID();
		newEvent.setEventId(eventId);
		newEvent.setTitle(title);
		newEvent.setType(type);
		newEvent.setStartDate(startDate);
		newEvent.setDescription(description);
		
		eventDao.addEvent(newEvent);
		return newEvent;
	}
	
	public void gradeCheck(String grade, Form form) {
		if (grade.equals("A") || grade.equals("B") ) {
			log.trace("Calculating Tuition");
			form.setStatus(Status.Approved);	
		
		}else {
			log.trace("Deny statement");
			form.setStatus(Status.Denied);
	
		}
		
	}
	
	
	public Long reimburseCalc(String eventType, Integer maxTuition, 
			Long currReimburse, Integer empCost) {
		
		Long newAmount = 0l;
	
		//certification
		if(eventType == "certification") {
			Integer coveredTuit = empCost * 1;
			if (coveredTuit > maxTuition) {
				coveredTuit = 1000;
			}
			newAmount += coveredTuit.longValue();
			
			//university_courses
		}else if (eventType == "university_courses") {
			Double coveredTuit = empCost * .8;
			if (coveredTuit > maxTuition) {
				coveredTuit = 1000.0;
				
			}
			newAmount += coveredTuit.longValue();
			
			
			//seminar
		}else if (eventType == "seminar") {
			Double coveredTuit = empCost * .6;
					if (coveredTuit > maxTuition) {
							coveredTuit = 1000.0;		
					}
					
					newAmount += coveredTuit.longValue();
				
				
				//certification prep
		}else if (eventType == "certification_prep") {
			Double coveredTuit = empCost * .75;
					if (coveredTuit > maxTuition) {
						coveredTuit = 1000.0;
					}
					newAmount += coveredTuit.longValue();
			
			
			//technical_training
		}else if (eventType == "technical_training") {
			Double coveredTuit = empCost * .9;
			if (coveredTuit > maxTuition) {
				coveredTuit = 1000.0;
				
			}
			newAmount += coveredTuit.longValue();
			
			
			//other
		}else if (eventType == "other") {
				Double coveredTuit = empCost * .3;
				if (coveredTuit > maxTuition) {
					coveredTuit = 1000.0;
					
				}
				newAmount += coveredTuit.longValue();
		}
		return newAmount;
		
	}


}
