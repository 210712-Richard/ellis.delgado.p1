package com.revature.service;

import java.util.List;

import com.revature.beans.EventOp;
import com.revature.beans.Form;
import com.revature.beans.Inbox;
import com.revature.beans.User;
import com.revature.beans.UserType;
import com.revature.data.EventDAO;
import com.revature.data.EventDAOImp;
import com.revature.data.FormDAO;
import com.revature.data.FormDAOImp;
import com.revature.data.UserDAO;
import com.revature.data.UserDAOImp;

public class UserServiceImp implements UserService{
	public UserDAO userDao = new UserDAOImp();
	public static FormDAO formDao = new FormDAOImp();
	public static EventDAO eventDao = new EventDAOImp();
	
	
	@Override
	public User login(String username) {
		User user = userDao.getUser(username);
		
		List<Form> forms = formDao.getUserForms(username);
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
		
		return user;
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
		Integer empCost = formDao.getFormbyEmployee(employee).getCost();
		Integer maxTuition = 1000;
		
		reimburseCalc(eventType, maxTuition, currReimburse, empCost);
		
		
		
		
		
		
	}
	
	

	@Override
	public Inbox getUserInbox(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteUser(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateSupervisor(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateDepHead(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateBenCo(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateInbox(User user) {
		// TODO Auto-generated method stub
		
	}

public Long reimburseCalc(String eventType, Integer maxTuition, Long currReimburse, Integer empCost) {
		
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
			newAmount += coveredTuit.longValue();;
			
			
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
