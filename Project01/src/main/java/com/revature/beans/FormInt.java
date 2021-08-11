package com.revature.beans;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;
import java.time.LocalDate;


public interface FormInt extends Serializable{
	
	LocalDate getDate();
	void setDate(LocalDate date);
	
	LocalDateTime getTime();
	void setTime(LocalDateTime time);
	
	String getDescription();
	void setDescription(String description);
	
	Long getCost();
	void setCost(Long cost);
	
	String getGrade();
	void setGrade(String grade);
	
	EventOp getEvent();
	void setEvent(EventOp event);
	
	
	FileObject getFile();
	void setFile(FileObject file);
	
	Status getStatus();
	void setStatus(Status status);
	
	Integer getTimeMissed();
	
	void setTimeMissed(Integer timeMissed);
	
	Boolean getUrgency();
	void setUrgency(Boolean urgency);
	
	UUID getFormId();
	void setFormId(UUID formId);
}
