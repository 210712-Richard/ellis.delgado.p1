package com.revature.data;


import java.util.List;
import java.util.UUID;

import com.revature.beans.EventOp;


public interface EventDAO {

	EventOp getEventbyId(UUID eventId);
	
	EventOp getEventbyTitle(String title);
	
	UUID addEvent(EventOp event);
	
	public void updateEvent(EventOp event);
	
	List<EventOp> getAllEvents();
	
	public void deleteEvent (EventOp event);
}
