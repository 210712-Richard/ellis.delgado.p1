package com.reavature.data;

import java.util.List;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.DefaultConsistencyLevel;
import com.datastax.oss.driver.api.core.cql.BoundStatement;
import com.datastax.oss.driver.api.core.cql.SimpleStatement;
import com.datastax.oss.driver.api.core.cql.SimpleStatementBuilder;
import com.reavature.beans.Form;
import com.reavature.beans.User;
import com.revature.util.CassandraUtil;

public class UserDAOImp implements UserDAO{

	private CqlSession session = CassandraUtil.getInstance().getSession();
	
	
	
	@Override
	public void addUser(User user) {
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
		// TODO Auto-generated method stub
		return null;
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
