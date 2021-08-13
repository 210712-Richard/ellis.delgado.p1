package com.revature.data;

import java.util.List;
import java.util.UUID;

import com.revature.beans.Form;

public interface FormDAO {
	List<Form> getUserForms(String employee);
	
	UUID addForm(Form form); 
	
	void updateGrade(Form form);
	
	void updateStatus(Form form);
	
	void deleteForm(Form form); 
	
	void updateFile(Form form);
	
	
}
