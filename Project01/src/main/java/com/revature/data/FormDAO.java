package com.revature.data;

import java.util.List;
import java.util.UUID;

import com.revature.beans.Form;
import com.revature.beans.Status;

public interface FormDAO {
	List<Form> getUserForms(String employee);
	
	UUID addForm(Form form); 
	
	Form getFormbyEmployee(String employee);
	
	Form getFormbyId(UUID formId);
	
	void updateGrade(Form form);
	
//	void updateStatus(Form form);
	
	void updateDescription(Form form, String employee, String description);
	
	void deleteForm(Form form); 
	
	void updateFile(Form form);

	void updateStatus(Form form, Status status, String employee);
	
	
}
