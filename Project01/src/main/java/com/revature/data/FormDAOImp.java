package com.revature.data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.DefaultConsistencyLevel;
import com.datastax.oss.driver.api.core.cql.BoundStatement;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;
import com.datastax.oss.driver.api.core.cql.SimpleStatement;
import com.datastax.oss.driver.api.core.cql.SimpleStatementBuilder;
import com.revature.beans.EventOp;
import com.revature.beans.Form;
import com.revature.beans.Status;
import com.revature.util.CassandraUtil;
//import com.revature.beans.*;

public class FormDAOImp implements FormDAO{
	
	private CqlSession session = CassandraUtil.getInstance().getSession();
	private static Logger log = LogManager.getLogger(FormDAOImp.class);
	
	
	
	@Override
	public List<Form> getUserForms(String employee) {
		List<Form> forms = new ArrayList<Form>();
		String query = "Select * from form_db where employee = ?";
		
		BoundStatement boundStat = session.prepare(new SimpleStatementBuilder(query).build()).bind(employee);
		ResultSet result = session.execute(boundStat);
		
		result.forEach(row -> {
			Form f = new Form();
			f.setFormId(row.getUuid("id"));
			f.setEmployee(row.getString("employee"));
			f.setDate(row.getLocalDate("date"));
			f.setDescription(row.getString("description"));
			f.setCost(row.getInt("cost"));
//			f.setType(ReimbursementType.valueOf(row.getString("type")));
			f.setGrade(row.getString("grade"));
			f.setEvent(new EventOp (row.getUuid("eventId"),
					row.getLocalDate("startDate"), row.getString("type")
					,row.getString("title"), row.getString("description")));
			f.setFile(row.getString("file"));
			f.setStatus(Status.valueOf(row.getString("status")));
			f.setTimeMissed(row.getInt("timeMissed"));
			f.setUrgency(row.getBoolean("urgency"));
		
			forms.add(f);
		
		});
		
			log.trace("form retrieved");
			return forms;
			
		
	}

	@Override
	public void updateGrade(Form form) {
		String query = "update form_db set  grade = ? where formId = ?;";
		SimpleStatement simpState = new SimpleStatementBuilder(query).setConsistencyLevel(DefaultConsistencyLevel.LOCAL_QUORUM).build();
		BoundStatement boundStat = session.prepare(simpState)
				.bind(form.getGrade(), form.getFormId());
		session.execute(boundStat);
	}
	@Override
	public void updateStatus(Form form){
		String query = "update form_db set  status = ? where formId = ?;";
		SimpleStatement simpState = new SimpleStatementBuilder(query).setConsistencyLevel(DefaultConsistencyLevel.LOCAL_QUORUM).build();
		BoundStatement boundStat = session.prepare(simpState)
				.bind(form.getStatus(), form.getFormId());
		session.execute(boundStat);
	}

	@Override
	public void deleteForm(Form form) {
	String query = "Delete from form_db where formId = ?";
	
	BoundStatement boundStat = session.prepare(new SimpleStatementBuilder(query)
			.setConsistencyLevel(DefaultConsistencyLevel.LOCAL_QUORUM).build()).bind(form.getFormId());
	session.execute(boundStat);
		
	}

	@Override
	public UUID addForm(Form form) {
		String query = "Insert into form_db (formId, employee, date, description, cost, "
		+ "grade, event, file, status, timeMissed, urgency) values  (?,  ? ,?, ?, ? ,? ,?,?,?,?,?);";
	
		UUID formId = UUID.randomUUID();
		
		SimpleStatement simpState = new SimpleStatementBuilder(query).setConsistencyLevel(DefaultConsistencyLevel.LOCAL_QUORUM).build();
		BoundStatement boundStat = session.prepare(simpState)
				.bind(formId, form.getEmployee(), form.getDate(), form.getDescription(),form.getCost(), 
						form.getGrade(), form.getEvent().toString(), 
						form.getFile().toString(), form.getStatus().toString(), form.getTimeMissed(), form.getUrgency().toString() );
		session.execute(boundStat);
		
		return formId;
	}

	@Override
	public void updateFile(Form form) {
		
			String query = "update form_db set file = ? where formId = ?;";
			SimpleStatement simpState = new SimpleStatementBuilder(query).setConsistencyLevel(DefaultConsistencyLevel.LOCAL_QUORUM).build();
			BoundStatement boundStat = session.prepare(simpState)
					.bind(form.getFile(), form.getFormId());
			session.execute(boundStat);
		
		
	}

	@Override
	public Form getFormbyEmployee(String employee) {
		String query = "Select * from form_db where employee = ? ALLOW FILTERING";
		
		BoundStatement boundStat = session.prepare(new SimpleStatementBuilder(query).build()).bind(employee); 
		ResultSet result = session.execute(boundStat);
		
		Row row = result.one();
		if(row == null) {
			return null;
		}
		Form f = new Form();
		f.setFormId(row.getUuid("formId"));
		f.setEmployee(row.getString("employee"));
		f.setDate(row.getLocalDate("date"));
		f.setDescription(row.getString("description"));
		f.setCost(row.getInt("cost"));
//		f.setType(ReimbursementType.valueOf(row.getString("type")));
		f.setGrade(row.getString("grade"));
		f.setEvent(new EventOp (row.getUuid("eventId"),
				row.getLocalDate("startDate"), row.getString("type")
				,row.getString("title"), row.getString("description")));
		f.setFile(row.getString("file"));
		f.setStatus(Status.valueOf(row.getString("status")));
		f.setTimeMissed(row.getInt("timeMissed"));
		f.setUrgency(row.getBoolean("urgency"));
	
		return f;
	}
	@Override
	public Form getFormbyId(UUID formId) {
		String query = "Select * from form_db where formId = ? ALLOW FILTERING";
		
		BoundStatement boundStat = session.prepare(new SimpleStatementBuilder(query).build()).bind(formId); 
		ResultSet result = session.execute(boundStat);
		
		Row row = result.one();
		if(row == null) {
			return null;
		}
		Form f = new Form();
		f.setFormId(row.getUuid("formId"));
		f.setEmployee(row.getString("employee"));
		f.setDate(row.getLocalDate("date"));
		f.setDescription(row.getString("description"));
		f.setCost(row.getInt("cost"));
//		f.setType(ReimbursementType.valueOf(row.getString("type")));
		f.setGrade(row.getString("grade"));
		f.setEvent(new EventOp (row.getUuid("eventId"),
				row.getLocalDate("startDate"), row.getString("type")
				,row.getString("title"), row.getString("description")));
		f.setFile(row.getString("file"));
		f.setStatus(Status.valueOf(row.getString("status")));
		f.setTimeMissed(row.getInt("timeMissed"));
		f.setUrgency(row.getBoolean("urgency"));
	
		return f;
	}
}
