package com.revature.data;

import java.util.List;
import java.util.UUID;

import com.revature.beans.EventOp;

public interface EventDAO {

	EventOp getEventbyId(UUID eventId);
	
	public void updateEvent(EventOp event);
	
	List<EventOp> getAllEvents();
	
	public void deleteEvent (EventOp event);
}
