package com.revature.beans;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

public class EventOp implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	UUID eventId;
	LocalDate startDate;
	String type;
	String title; 
	String description;
	
	public EventOp(UUID eventId) {
		super();
		this.eventId = eventId;
		
	}
	public EventOp( LocalDate startDate, String type, String title, String description) {
		
		this.startDate= startDate;
		this.type = type;
		this.title = title;
		this.description = description;
	}
	
	public UUID getEventId() {
		return eventId;
	}
	public void setEventId(UUID eventId) {
		this.eventId = eventId;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public int hashCode() {
		return Objects.hash(description, eventId, startDate, title, type);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EventOp other = (EventOp) obj;
		return Objects.equals(description, other.description) && Objects.equals(eventId, other.eventId)
				&& Objects.equals(startDate, other.startDate) && Objects.equals(title, other.title)
				&& Objects.equals(type, other.type);
	}
	@Override
	public String toString() {
		return "EventOp [eventId=" + eventId + ", startDate=" + startDate + ", type=" + type + ", title=" + title
				+ ", description=" + description + "]";
	}
	
	
	
}
