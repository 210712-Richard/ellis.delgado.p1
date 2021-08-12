package com.revature.beans;

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
	public String employee;
	public LocalDate date;
	public LocalDateTime time;
	public String description;
	public Long cost;
//	public ReimbursementType type;
	public String grade;
	public EventOp event;
	
	//Optional stuff
	public String file;
	public Status status;
	public Integer timeMissed;
	public Boolean urgency;
	public Form() {
		super();
		this.cost=0L;
	}
	public Form(UUID formId, String employee, LocalDate date, 
			LocalDateTime time, String description, 
			Long Cost, String grade, EventOp event, String file, 
			Status status, Integer timeMissed, Boolean urgency) {
		this.formId = formId;
		this.employee = employee;
		this.date = date;
		this.time = time;
		this.description = description;
//		this.type = ReimbursementType.UNIVERSITY;
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
	
	public String getEmployee() {
		return employee;
	}
	public void setEmployee(String employee) {
		this.employee = employee;
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
	public EventOp getEvent() {
		return event;
	}
	public void setEvent(EventOp event) {
		this.event = event;
	}
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
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
		return Objects.hash(cost, date, description, employee, event, file, formId, grade, status, time, timeMissed,
				urgency);
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
				&& Objects.equals(description, other.description) && Objects.equals(employee, other.employee)
				&& Objects.equals(event, other.event) && Objects.equals(file, other.file)
				&& Objects.equals(formId, other.formId) && Objects.equals(grade, other.grade) && status == other.status
				&& Objects.equals(time, other.time) && Objects.equals(timeMissed, other.timeMissed)
				&& Objects.equals(urgency, other.urgency);
	}
	@Override
	public String toString() {
		return "Form [formId=" + formId + ", employee=" + employee + ", date=" + date + ", time=" + time
				+ ", description=" + description + ", cost=" + cost + ", grade=" + grade + ", event=" + event
				+ ", file=" + file + ", status=" + status + ", timeMissed=" + timeMissed + ", urgency=" + urgency + "]";
	}
	
	
	
	
}
