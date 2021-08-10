package com.revature.data;

import java.util.ArrayList;
import java.util.List;
//import java.util.UUID;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.DefaultConsistencyLevel;
import com.datastax.oss.driver.api.core.cql.BoundStatement;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;
import com.datastax.oss.driver.api.core.cql.SimpleStatement;
import com.datastax.oss.driver.api.core.cql.SimpleStatementBuilder;
//import com.revature.beans.Form;
import com.revature.beans.User;
import com.revature.beans.UserType;
import com.revature.util.CassandraUtil;



public class UserDAOImp implements UserDAO{

	private CqlSession session = CassandraUtil.getInstance().getSession();
	private static Logger log = LogManager.getLogger(UserDAOImp.class);
	
	
	@Override
	public void addUser(User user) {
		log.trace("Adding User");
		String query = "Insert into user (username, email, employeeId, userType, pending, approved, "
//				+ "forms, "
				+ "supervisor, departmentHead, benCo, inbox) values (?, ?, ?, ?, ?, ?, "
//				+ "?, "
				+ "?, ?, ?, ?);";
		SimpleStatement simpStatement = new SimpleStatementBuilder(query).setSerialConsistencyLevel(DefaultConsistencyLevel.LOCAL_QUORUM).build();
		BoundStatement boundStatement = session.prepare(simpStatement)
				.bind(user.getUsername(), user.getEmail(), user.getEmployeeId(), user.getUserType(), 
						user.getPending(), user.getApproved(), 
//						user.getForms(),
						user.getSupervisor(),
						user.getDepartmentHead(), user.getBenCo(), user.getInbox());
		session.execute(boundStatement);
			
	}

	@Override
	public List<User> getUsers() {
		String query = "Select username, email, employeeId, userType, pending, approved, "
//				+ "forms, "
				+ "supervisor, departmentHead, benCo, inbox from user";
		SimpleStatement simpStatement =  new SimpleStatementBuilder(query).build();
		ResultSet results = session.execute(simpStatement);
		List<User> users = new ArrayList<>();
		
		results.forEach(row -> {
			User user = new User();
			user.setUsername(row.getString("username"));
			user.setEmail(row.getString("email"));
			user.setEmployeeId(row.getUuid("employeeId"));
			user.setUserType(UserType.valueOf(row.getString("userType")));
			user.setPending(row.getLong("pending"));
			user.setApproved(row.getLong("approved"));
//			user.setForm(getUserForms("username"));
			user.setSupervisor(row.getString("supervisor"));
			user.setDepartmentHead(row.getString("departmentHead"));
			user.setBenCo(row.getString("benCo"));
			//change once inbox is done
			user.setInbox(null);
			
			users.add(user);
		});
		return users;
	}

	@Override
	public User getUser(String username) {
		String query = "Select username, email, employeeId, userType, pending, approved, forms, supervisor, departmentHead, benCo, inbox from user where username=?";
		SimpleStatement simpStatement =  new SimpleStatementBuilder(query).build();
		BoundStatement boundStatement = session.prepare(simpStatement).bind(username);	
		ResultSet results = session.execute(boundStatement);
		Row row = results.one();
		if (row == null) {
			log.trace("no values");
			return null;
		}
		User user = new User();
		user.setUsername(row.getString("username"));
		user.setEmail(row.getString("email"));
		user.setEmployeeId(row.getUuid("employeeId"));
		user.setUserType(UserType.valueOf(row.getString("userType")));
		user.setPending(row.getLong("pending"));
		user.setApproved(row.getLong("approved"));
//		user.setForm(getUserForms(username));
		user.setSupervisor(row.getString("supervisor"));
		user.setDepartmentHead(row.getString("departmentHead"));
		user.setBenCo(row.getString("benCo"));
		//change once inbox is done
		user.setInbox(null);
		
		return user;
	}

	@Override
	public void updateUser(User user) {
		String query = "Update user set email = ?, employeeId = ?, userType = ?, pending = ?, approved = ?, forms = ?, supervisor = ?, departmentHead = ?, benCo = ?, inbox = ? where username=?";
//		List<UUID> forms = formDAO.getForms()
//				.stream()
//				.filter(f -> f!=null)
//				.map(f -> f.getFormId())
//				.collect(Collectors.toList());
		
		List<Object> inbox = user.getInbox()
				.stream()
				.filter(i -> i!=null)
				//change this once were done with the inbox list
				.map(null)
				.collect(Collectors.toList());
		SimpleStatement simpStatement = new SimpleStatementBuilder(query).setSerialConsistencyLevel(DefaultConsistencyLevel.LOCAL_QUORUM).build();
		BoundStatement boundStatement = session.prepare(simpStatement)
				.bind(user.getUsername(), user.getEmail(), user.getEmployeeId(), user.getUserType(), 
						user.getPending(), user.getApproved(), 
//						forms, 
						user.getSupervisor(),
						user.getDepartmentHead(), user.getBenCo(), inbox);
		session.execute(boundStatement);
	}

//	@Override
//	public List<Form> getUserForms(String username) {
//		String query = "Select form from user where username = ?";
//		SimpleStatement simpStatement = new SimpleStatementBuilder(query).build();
//		BoundStatement boundStatement = session.prepare(simpStatement).bind(username);
//		
//		ResultSet results = session.execute(boundStatement);
//		Row row = results.one();
//		if(row == null) {
//			return null;
//		}
//		List<Form> forms = row.getList("forms", Form.class);
//		return forms;
//	}


}
