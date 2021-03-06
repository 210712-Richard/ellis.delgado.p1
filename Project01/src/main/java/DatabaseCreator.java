

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.beans.*;
import com.revature.data.EventDAO;
import com.revature.data.EventDAOImp;
import com.revature.data.FormDAO;
import com.revature.data.FormDAOImp;
import com.revature.data.InboxDAO;
import com.revature.data.InboxDAOImp;
import com.revature.data.UserDAO;
import com.revature.data.UserDAOImp;
import com.revature.util.CassandraUtil;



public class DatabaseCreator {
	public static UserDAO userDao = new UserDAOImp();
	public static FormDAO formDao = new FormDAOImp();
	public static InboxDAO inboxDAO = new InboxDAOImp();
	public static EventDAO eventDAO = new EventDAOImp();
	
	private static Logger log = LogManager.getLogger(DatabaseCreator.class);
	
	public static void dropTables() {
		StringBuilder stringBuild = new StringBuilder("DROP TABLE IF EXISTS user;");
		CassandraUtil.getInstance().getSession().execute(stringBuild.toString());
		
		stringBuild = new StringBuilder("DROP TABLE IF EXISTS form_db;");
		CassandraUtil.getInstance().getSession().execute(stringBuild.toString());
		
		stringBuild = new StringBuilder("DROP TABLE IF EXISTS event_db;");
		CassandraUtil.getInstance().getSession().execute(stringBuild.toString());
	}
	
	public static void createTables() {
		//event_db
		StringBuilder stringBuild = new StringBuilder("CREATE TABLE IF NOT EXISTS event_db(")
				.append("eventId uuid, startDate date, type text, title text, description text, "
						+ "primary key(eventId, title) );");
		CassandraUtil.getInstance().getSession().execute(stringBuild.toString());
		log.trace("Event table built");
				
		//form_db.
			 stringBuild = new StringBuilder("CREATE TABLE IF NOT EXISTS form_db(")
					.append("formId uuid, employee text, date date, description text, cost int, grade text, "
//							+ "event text, "
							+ "file text,")
					.append("status text, timeMissed int, urgency text, primary key (formId, employee));");
				CassandraUtil.getInstance().getSession().execute(stringBuild.toString());
				log.trace("Form table built");
		//user
		 stringBuild = new StringBuilder("CREATE TABLE IF NOT EXISTS user (")
				.append("username text PRIMARY KEY, email text, employeeId uuid, ")
				.append("userType text, reimbursement bigint, "
						+ "forms list<uuid>, "
						+ "supervisor text, departmentHead text, benCo text, inbox list<uuid>);");
		CassandraUtil.getInstance().getSession().execute(stringBuild.toString());
		log.trace("User table built");
		
		 stringBuild = new StringBuilder("CREATE TABLE IF NOT EXISTS inbox (")
					.append("messageid uuid PRIMARY KEY, title text, message text, alert text); ");
					
			CassandraUtil.getInstance().getSession().execute(stringBuild.toString());
			log.trace("Inbox table built");
		
	}
public static void populateEventTable() {
	UUID eventId = UUID.randomUUID();
	LocalDate startDate = LocalDate.of(2021, 05, 22);
//	EventType type = EventType.Certification;
	
	EventOp event = new EventOp(eventId, startDate, "certification", "exampleCert", 
			"An example certification event");
	
	//might have to use EnumNameCodec
//	event.setEventId(eventId);
//	event.setStartDate(startDate);
//	event.setType(type);
	
	eventDAO.addEvent(event);
}
public static void populateFormTable() {
		
		
		UUID formId = UUID.randomUUID();
		LocalDate date = LocalDate.of(2021, 04, 12);
		
		EventOp event = eventDAO.getEventbyTitleAndType("exampleCert", "certification");
		String file = "https://globalspex.com/wp-content"
				+ "/uploads/2019/10/support-form-example-540x600.png";
		Status status = Status.Pending;
		Integer cost = 500;
		Integer timeMiss = 0;
		
//		
//	"formId uuid, employee text, date date, description text, cost bigint, grade text, event text, file text,")
//		"status text, timeMissed bigint, urgency text, primary key (formId, employee));");

		Form form = new Form( );
		form.setEmployee("Logan");
//		form.setFormId(formId);
		form.setDate(date);
		form.setDescription("example");
		form.setCost(cost);
		form.setGrade("B");
//		form.setEvent(event);
		form.setFile(file);
		form.setStatus(status);
		form.setUrgency("true");
		form.setTimeMissed(timeMiss);
		
		System.out.println(form);
		formDao.addForm(form);
		
		 form = new Form();
			form.setEmployee("Scott");
//			form.setFormId(formId);
			form.setDate(date);
			form.setDescription("example");
			form.setCost(cost);
			form.setGrade("B");
//			form.setEvent(event);
			form.setFile(file);
			form.setStatus(status);
			form.setUrgency("true");
			form.setTimeMissed(timeMiss);
		formDao.addForm(form);
		
		 form = new Form( );
		 form.setEmployee("Jean Grey");
//		 form.setFormId(formId);
		 form.setDate(date);
			form.setDescription("example");
			form.setCost(cost);
			form.setGrade("B");
//			form.setEvent(event);
			form.setFile(file);
			form.setStatus(status);
			form.setUrgency("true");
			form.setTimeMissed(timeMiss);
		formDao.addForm(form);
		
		 form = new Form();
		 form.setEmployee("Xavier");
//		 form.setFormId(formId);
		 form.setDate(date);
			form.setDescription("example");
			form.setCost(cost);
			form.setGrade("B");
//			form.setEvent(event);
			form.setFile(file);
			form.setStatus(status);
			form.setUrgency("true");
			form.setTimeMissed(timeMiss);
			
			
		formDao.addForm(form);
		
	}

	public static void populateInboxTable() {
		
		UUID messageId = UUID.randomUUID();
		Inbox inbox = new Inbox();
		inbox.setMessageId(messageId);
		inbox.setTitle("Example Message");
		inbox.setMessage("Hello World");
		inbox.setAlert(AlertType.Neutral);
		
		inboxDAO.addInbox(inbox);
	}
	public static void populateUserTable() {
		//benco
		
		UUID userId = UUID.randomUUID();
		User user = new User();
		user.setUserType(UserType.Benefits_Coordinator);
		user.setUsername("Xavier");
		user.setEmail("profx@gmail.com");
		user.setEmployeeId(userId);
		user.setSupervisor(null);
		user.setDepartmentHead(null);
		user.setBenCo(null);
		
//		List<Form> form = userDao.getUserForms(user.getUsername());
//		System.out.println(form);
//		Form form = formDao.getFormbyEmployee(user.getUsername());
//		formDao.addForm(form);
//		user.setForms((List<Form>) form);
//		System.out.println(form);
//		
//		List<Inbox> inbox = userDao.getUserInbox(user.getUsername());
//		inboxDAO.addInbox((Inbox) inbox);
//		user.setInbox(inbox);
		userDao.addUser(user);
		
		//department head
		
		UUID userId2 = UUID.randomUUID();
		 user = new User();
		user.setUserType(UserType.Department_Head);
		user.setUsername("Jean Grey");
		user.setEmail("jgrey@gmail.com");
		user.setEmployeeId(userId2);
		user.setSupervisor(null);
		user.setDepartmentHead(null);
		user.setBenCo("Xavier");
//		
//		List<Form> form1 = userDao.getUserForms(user.getUsername());
//		formDao.addForm((Form) form1);
//		user.setForms(form1);
//		
//		List<Inbox> inbox1 = userDao.getUserInbox(user.getUsername());
//		inboxDAO.addInbox((Inbox) inbox1);
//		user.setInbox(inbox1);
		userDao.addUser(user);
		
		//DS
		UUID userId3= UUID.randomUUID();
		user = new User();
		user.setUserType(UserType.Direct_Supervisor);
		user.setUsername("Scott");
		user.setEmail("ssummers@gmail.com");
		user.setEmployeeId(userId3);
		user.setSupervisor(null);
		user.setDepartmentHead("Jean Grey");
		user.setBenCo("Xavier");
//		
//		List<Form> form2 = userDao.getUserForms(user.getUsername());
//		formDao.addForm((Form) form2);
////		user.setForms(form2);
//		List<Inbox> inbox2 = userDao.getUserInbox(user.getUsername());
//		inboxDAO.addInbox((Inbox) inbox2);
//		user.setInbox(inbox2);
		userDao.addUser(user);
		
		//Employee
		UUID userId4 = UUID.randomUUID();
		 user = new User();
		user.setUserType(UserType.Employee);
		user.setUsername("Logan");
		user.setEmail("wolverine@gmail.com");
		user.setEmployeeId(userId4);
		user.setSupervisor("Scott");
		user.setDepartmentHead("Jean Grey");
		user.setBenCo("Xavier");
		
//		List<Form> form3 = userDao.getUserForms(user.getUsername());
//		formDao.addForm((Form) form3);
//		user.setForms(form3);
//		List<Inbox> inbox3 = userDao.getUserInbox(user.getUsername());
//		inboxDAO.addInbox((Inbox) inbox3);
//		user.setInbox(inbox3);
		userDao.addUser(user);
	}
	
	

}
