package com.reavature.beans;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class Form implements FormInt{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public LocalDate date;
	public LocalDateTime time;
	public String description;
	public Long cost;
	public String grade;
	public Event event;
	public FileObject file;
	public Boolean status;
	
	public Form() {
		super();
		this.cost=0L;
	}
	public Form(LocalDate date, LocalDateTime time, String description, 
			Long Cost, String grade, Event event, FileObject file, Boolean status) {
		this.date = date;
		this.time = time;
		this.description = description;
		this.grade = grade;
		this.event= event;
		this.file=file;
		this.status= status;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Long getCost() {
		return cost;
	}
	public void setCost(Long cost) {
		this.cost = cost;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public Event getEvent() {
		return event;
	}
	public void setEvent(Event event) {
		this.event = event;
	}
	public FileObject getFile() {
		return file;
	}
	public void setFile(FileObject file) {
		this.file = file;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public LocalDateTime getTime() {
		return time;
	}
	public void setTime(LocalDateTime time) {
		this.time= time;
		
	}
	@Override
	public int hashCode() {
		return Objects.hash(cost, date, description, event, file, grade, status, time);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Form other = (Form) obj;
		return Objects.equals(cost, other.cost) && Objects.equals(date, other.date)
				&& Objects.equals(description, other.description) && Objects.equals(event, other.event)
				&& Objects.equals(file, other.file) && Objects.equals(grade, other.grade)
				&& Objects.equals(status, other.status) && Objects.equals(time, other.time);
	}
	@Override
	public String toString() {
		return "Form [date=" + date + ", time=" + time + ", description=" + description + ", cost=" + cost + ", grade="
				+ grade + ", event=" + event + ", file=" + file + ", status=" + status + "]";
	}
	
	
}
