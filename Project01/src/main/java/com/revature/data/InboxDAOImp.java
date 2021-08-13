package com.revature.data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.DefaultConsistencyLevel;
import com.datastax.oss.driver.api.core.cql.BoundStatement;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.SimpleStatement;
import com.datastax.oss.driver.api.core.cql.SimpleStatementBuilder;
import com.revature.beans.AlertType;
import com.revature.beans.Inbox;
import com.revature.util.CassandraUtil;

public class InboxDAOImp implements InboxDAO{

	private CqlSession session = CassandraUtil.getInstance().getSession();
	private static Logger log = LogManager.getLogger(FormDAOImp.class);
	
	
	@Override
	public List<Inbox> getInbox(String employee) {
		
		List<Inbox> inbox = new ArrayList<Inbox>();
		String query = "Select inbox from user where username = ?";
		
		BoundStatement boundStat = session.prepare(new SimpleStatementBuilder(query).build()).bind(employee);
		ResultSet result = session.execute(boundStat);
		
		result.forEach(row -> {
			Inbox i = new Inbox();
			i.setTitle(row.getString("title"));
			i.setMessage(row.getString("message"));
			i.setAlert(AlertType.valueOf(row.getString("alert")));
			
			inbox.add(i);
		});
		log.trace("Inbox received");
		return inbox;
	}


	@Override
	public UUID addInbox(Inbox inbox) {
		
		log.trace("Add to inbox");
		
		String query = 
				"Insert into inbox (mesaageId, title, message, alert) values (?, ?, ?);";
		UUID inboxId = UUID.randomUUID();
		SimpleStatement simpState = new SimpleStatementBuilder(query).setConsistencyLevel(DefaultConsistencyLevel.LOCAL_QUORUM).build();
		BoundStatement boundStat = session.prepare(simpState)
				.bind(inboxId, inbox.getTitle(), inbox.getMessage(), inbox.getAlert());
		session.execute(boundStat);
		
		return inboxId;
	}


	@Override
	public void updateAlert(Inbox inbox) {
		log.trace("Update alert");
		
		String query = "Update inbox alert = ? where title = ?;";
		
		SimpleStatement simpState = new SimpleStatementBuilder(query).setConsistencyLevel(DefaultConsistencyLevel.LOCAL_QUORUM).build();
		BoundStatement boundStat = session.prepare(simpState)
				.bind(inbox.getAlert(), inbox.getTitle());
		session.execute(boundStat);
		
	}

	

}
