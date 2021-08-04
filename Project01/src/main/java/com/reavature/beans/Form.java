package com.reavature.beans;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class Form implements FormInt{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public UUID formId;
	public LocalDate date;
	public LocalDateTime time;
	public String description;
	public Long cost;
	public String grade;
	public Event event;
	
	//Optional stuff
	public FileObject file;
	public Boolean status;
	public Integer timeMissed;
	public Boolean urgency;
	public Form() {
		super();
		this.cost=0L;
	}
	public Form(UUID formId, LocalDate date, 
			LocalDateTime time, String description, 
			Long Cost, String grade, Event event, FileObject file, 
			Boolean status, Integer timeMissed, Boolean urgency) {
		this.formId = formId;
		this.date = date;
		this.time = time;
		this.description = description;
		this.grade = grade;
		this.event= event;
		this.file=file;
		this.status= status;
		this.timeMissed = timeMissed;
		this.urgency = urgency;
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
	public Integer getTimeMissed() {
		return timeMissed;
	}
	public void setTimeMissed(Integer timeMissed) {
		this.timeMissed = timeMissed;
	}
	public Boolean getUrgency() {
		return urgency;
	}
	public void setUrgency(Boolean urgency) {
		this.urgency = urgency;
	}
	public UUID getFormId() {
		return formId;
	}
	public void setFormId(UUID formId) {
		this.formId = formId;
	}
	@Override
	public int hashCode() {
		return Objects.hash(cost, date, description, event, file, formId, grade, status, time, timeMissed, urgency);
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
				&& Objects.equals(file, other.file) && Objects.equals(formId, other.formId)
				&& Objects.equals(grade, other.grade) && Objects.equals(status, other.status)
				&& Objects.equals(time, other.time) && Objects.equals(timeMissed, other.timeMissed)
				&& Objects.equals(urgency, other.urgency);
	}
	@Override
	public String toString() {
		return "Form [formId=" + formId + ", date=" + date + ", time=" + time + ", description=" + description
				+ ", cost=" + cost + ", grade=" + grade + ", event=" + event + ", file=" + file + ", status=" + status
				+ ", timeMissed=" + timeMissed + ", urgency=" + urgency + "]";
	}
	
	
	
	
}
