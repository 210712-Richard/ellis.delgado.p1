package com.revature.data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.DefaultConsistencyLevel;
import com.datastax.oss.driver.api.core.cql.BoundStatement;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;
import com.datastax.oss.driver.api.core.cql.SimpleStatement;
import com.datastax.oss.driver.api.core.cql.SimpleStatementBuilder;
import com.revature.beans.EventOp;
import com.revature.beans.EventType;
import com.revature.util.CassandraUtil;

public class EventDAOImp implements EventDAO{

	private CqlSession session = CassandraUtil.getInstance().getSession();
	private static Logger log = LogManager.getLogger(EventDAO.class);
	
	@Override
	public EventOp getEventbyId(UUID eventId) {
		String query = "Select * from event_db where eventId = ?";
		
		BoundStatement boundStat = session.prepare(new SimpleStatementBuilder(query)
				.build()).bind(eventId);
		
		ResultSet result = session.execute(boundStat);
		
		Row row = result.one();
		if (row == null) {
			log.error("row returned null");
			return null;
		}
		EventOp e = new EventOp();
		e.setEventId(row.getUuid("eventId"));
		e.setStartDate(row.getLocalDate("startDate"));
		e.setType(EventType.valueOf(row.getString("type")));
		e.setTitle(row.getString("title"));
		e.setDescription(row.getString("description"));
		
		log.trace("Event retrieved");
		return e; 
		
	}

	@Override
	public void updateEvent(EventOp event) {
		String query = "update event_db set startDate = ?, type = ?, "
				+ "title = ?, description = ? where eventId = ?;";
		SimpleStatement simpState = new SimpleStatementBuilder(query)
				.setConsistencyLevel(DefaultConsistencyLevel.LOCAL_QUORUM).build();
		BoundStatement boundStat = session.prepare(simpState)
				.bind(event.getEventId(), event.getStartDate(), event.getType(),
						event.getTitle(), event.getDescription());
		session.execute(boundStat);
	}

	@Override
	public List<EventOp> getAllEvents() {
		List<EventOp> events = new ArrayList<EventOp>();
		String query = "Select * from event_db";
		
		BoundStatement boundStat = session.prepare(new SimpleStatementBuilder(query).build()).bind();
		ResultSet result = session.execute(boundStat);
		
		result.forEach(row -> {
			EventOp e = new EventOp();
			e.setEventId(row.getUuid("eventId"));
			e.setStartDate(row.getLocalDate("startDate"));
			e.setType(EventType.valueOf(row.getString("type")));
			e.setTitle(row.getString("title"));
			e.setDescription(row.getString("description"));
			
			events.add(e);
		});
		log.trace("Events retrieved");
		return events; 
	}

	@Override
	public void deleteEvent(EventOp event) {
		String query = "Delete from form_db where formId = ?";
		
		BoundStatement boundStat = session.prepare(new SimpleStatementBuilder(query)
				.setConsistencyLevel(DefaultConsistencyLevel.LOCAL_QUORUM).build())
				.bind(event.getEventId());
		session.execute(boundStat);
			
		
	}

	@Override
	public UUID	addEvent(EventOp event) {
		String query = "Insert into event_db (eventId, startDate, type, title, description)"
				+ "values (?, ?, ?, ?, ?);";
		UUID eventId = UUID.randomUUID();
		SimpleStatement simpState = new SimpleStatementBuilder(query).setConsistencyLevel(DefaultConsistencyLevel.LOCAL_QUORUM).build();
		BoundStatement boundStat = session.prepare(simpState)
				.bind (eventId, event.getStartDate(), event.getType(),
						event.getTitle(), event.getDescription());
		session.execute(boundStat);
		
		return eventId;
		
	}

	@Override
	public EventOp getEventbyTitle(String title) {
String query = "Select * from event_db where title = ?";
		
		BoundStatement boundStat = session.prepare(new SimpleStatementBuilder(query)
				.build()).bind(title);
		
		ResultSet result = session.execute(boundStat);
		
		Row row = result.one();
		if (row == null) {
			log.error("row returned null");
			return null;
		}
		EventOp e = new EventOp();
		e.setEventId(row.getUuid("eventId"));
		e.setStartDate(row.getLocalDate("startDate"));
		e.setType(EventType.valueOf(row.getString("type")));
		e.setTitle(row.getString("title"));
		e.setDescription(row.getString("description"));
		
		log.trace("Event retrieved");
		return e; 
	}

}
