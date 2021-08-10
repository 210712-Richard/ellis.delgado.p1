package com.revature.beans;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Event implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	LocalDate startDate;
	EventType type;
	
	public Event() {
		super();
		
	}
	public Event(LocalDate startDate, EventType type) {
		this.startDate= startDate;
		this.type = type;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public EventType getType() {
		return type;
	}
	public void setType(EventType type) {
		this.type = type;
	}
	@Override
	public int hashCode() {
		return Objects.hash(startDate, type);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Event other = (Event) obj;
		return Objects.equals(startDate, other.startDate) && type == other.type;
	}
	@Override
	public String toString() {
		return "Event [startDate=" + startDate + ", type=" + type + "]";
	}
	
}
