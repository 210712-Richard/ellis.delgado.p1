package com.revature.data;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.datastax.oss.driver.api.core.CqlSession;
import com.revature.beans.Form;
import com.revature.util.CassandraUtil;

public class FormDAOImp implements FormDAO{
	
	private CqlSession session = CassandraUtil.getInstance().getSession();
	private static Logger log = LogManager.getLogger(FormDAOImp.class);
	
	
	@Override
	public List<Form> getForms() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateForm(Form form) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteForm(Form form) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Form addForm(Form form) {
		// TODO Auto-generated method stub
		return null;
	}

}
