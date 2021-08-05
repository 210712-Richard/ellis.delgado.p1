package com.reavature.data;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.DefaultConsistencyLevel;
import com.datastax.oss.driver.api.core.cql.BoundStatement;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.SimpleStatement;
import com.datastax.oss.driver.api.core.cql.SimpleStatementBuilder;
import com.reavature.beans.Form;
import com.reavature.beans.User;
import com.reavature.beans.UserType;
import com.revature.util.CassandraUtil;



public class UserDAOImp implements UserDAO{

	private CqlSession session = CassandraUtil.getInstance().getSession();
	private static Logger log = LogManager.getLogger(UserDAOImp.class);
	
	
	@Override
	public void addUser(User user) {
		log.trace("Adding User");
		String query = "Insert into user (username, email, employeeId, userType, pending, approved, forms, supervisor, departmentHead, benCo) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
		SimpleStatement simpStatement = new SimpleStatementBuilder(query).setSerialConsistencyLevel(DefaultConsistencyLevel.LOCAL_QUORUM).build();
		BoundStatement boundStatement = session.prepare(simpStatement)
				.bind(user.getUsername(), user.getEmail(), user.getEmployeeId(), user.getUserType(), 
						user.getPending(), user.getApproved(), user.getForms(), user.getSupervisor(),
						user.getDepartmentHead(), user.getBenCo());
		session.execute(boundStatement);
			
	}

	@Override
	public List<User> getUsers() {
		String query = "Select username, email, employeeId, userType, pending, approved, forms, supervisor, departmentHead, benCo from user";
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
			user.setForm(getUserForms());
			user.setSupervisor(row.getString("supervisor"));
			user.setDepartmentHead(row.getString("departmentHead"));
			user.setBenCo(row.getString("benCo"));
			
			users.add(user);
		});
		return users;
	}

	@Override
	public User getUser(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Form> getUserForms() {
		// TODO Auto-generated method stub
		return null;
	}

}
