package com.revature.data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
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
import com.revature.beans.Form;
import com.revature.beans.Inbox;
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
		String query = "Insert into user (username, email, employeeId, userType, "
				+ "reimbursement,  "
				+ "supervisor, departmentHead, benCo,) values ( ?, ?, "
				+ "?, ?, ?, ?, ?, ?);";
		SimpleStatement simpStatement = new SimpleStatementBuilder(query)
				.setConsistencyLevel(DefaultConsistencyLevel.LOCAL_QUORUM).build();
		BoundStatement boundStatement = session.prepare(simpStatement)
				.bind(user.getUsername(), user.getEmail(), user.getEmployeeId(), user.getUserType().toString(), 
						user.getReimbursement(), 
						user.getForms(),
						user.getSupervisor(),
						user.getDepartmentHead(), user.getBenCo(), user.getInbox());
		session.execute(boundStatement);
			
	}

	@Override
	public List<User> getUsers() {
		String query = "Select username, email, employeeId, userType, reimbursement, "
//			+ "forms, "
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
			user.setReimbursement(row.getLong("reimbursement"));
//			user.setForms(getUserForms("username"));
			user.setSupervisor(row.getString("supervisor"));
			user.setDepartmentHead(row.getString("departmentHead"));
			user.setBenCo(row.getString("benCo"));
			//change once inbox is done
//			user.setInbox(getUserInbox(user.getUsername().toString()));
			
			users.add(user);
		});
		return users;
	}

	@Override
	public User getUser(String username) {
		String query = "Select username, email, employeeId, userType, reimbursement,  supervisor, departmentHead, benCo from user where username=?";
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
		user.setReimbursement(row.getLong("reimbursement"));
//		user.setForms(getUserForms(username));
		user.setSupervisor(row.getString("supervisor"));
		user.setDepartmentHead(row.getString("departmentHead"));
		user.setBenCo(row.getString("benCo"));
		//change once inbox is done
//		user.setInbox(getUserInbox(username));
		
		return user;
	}

	@Override
	public void updateUser(User user) {
		String query = "Update user set email = ?, employeeId = ?, userType = ?, reimbursement=?, forms = ?, supervisor = ?, departmentHead = ?, benCo = ?, inbox = ? where username=?";
		List<UUID> forms = user.getForms()
				.stream()
				.filter(f -> f!=null)
				.map(f -> f.getFormId())
				.collect(Collectors.toList());
		
		List<Object> inbox = user.getInbox()
				.stream()
				.filter(i -> i!=null)
				//change this once were done with the inbox list
				.map(i -> i.getMessageId())
				.collect(Collectors.toList());
		SimpleStatement simpStatement = new SimpleStatementBuilder(query).setConsistencyLevel(DefaultConsistencyLevel.LOCAL_QUORUM).build();
		BoundStatement boundStatement = session.prepare(simpStatement)
				.bind(user.getUsername(), user.getEmail(), user.getEmployeeId(), user.getUserType(), 
						user.getReimbursement(), 
						user.getForms(), 
						user.getSupervisor(),
						user.getDepartmentHead(), user.getBenCo(), forms, inbox);
		session.execute(boundStatement);
	}

	@Override
	public List<UUID> getUserForms(String username) {
		String query = "Select forms from user where username = ?";
		SimpleStatement simpStatement = new SimpleStatementBuilder(query).build();
		BoundStatement boundStatement = session.prepare(simpStatement).bind(username);
		
		ResultSet results = session.execute(boundStatement);
		Row row = results.one();
		if(row == null) {
			return null;
		}
		List<UUID> forms = row.getList("forms", UUID.class);
		return forms;
	}

	@Override
	public List<UUID> getUserInbox(String username) {
		String query = "Select inbox from user where username = ?";
		
		SimpleStatement simpStatement = new SimpleStatementBuilder(query).build();
		BoundStatement boundStatement = session.prepare(simpStatement).bind(username);
		
		ResultSet results = session.execute(boundStatement);
		Row row = results.one();
		if(row == null) {
			return null;
		}
		List<UUID> inbox = row.getList("inbox", UUID.class);
		return inbox;
	
	}


}
