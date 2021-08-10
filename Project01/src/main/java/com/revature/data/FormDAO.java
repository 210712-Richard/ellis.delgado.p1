package com.revature.data;

import java.util.List;

import com.revature.beans.Form;

public interface FormDAO {
	List<Form> getForms();
	
	Form addForm(Form form); 
	
	void updateForm(Form form);
	
	void deleteForm(Form form); 
	
	
}
