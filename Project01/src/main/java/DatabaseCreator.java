

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.revature.beans.*;
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
	
	public static void dropTables() {
		StringBuilder stringBuild = new StringBuilder("DROP TABLE IF EXISTS user;");
		CassandraUtil.getInstance().getSession().execute(stringBuild.toString());
		
	}
	
	public static void createTables() {
		//user
		StringBuilder stringBuild = new StringBuilder("CREATE TABLE IF NOT EXISTS User (")
				.append("username text PRIMARY KEY, email text, employeeId bigint, ")
				.append("userType text, reimbursement bigint, forms list<forms>, supervisor text, departmentHead text, benCo text, inbox list<Inbox>);");
		CassandraUtil.getInstance().getSession().execute(stringBuild.toString());
		
		//form_db
		stringBuild = new StringBuilder("CREATE TABLE IF NOT EXIST form_db")
			.append("formId bigint PRIMARY KEY, employee text, date date, time date,")
			.append("description text, type text, grade text, event text, file text,")
			.append("status text, timeMissed bigint, urgency text;");
		CassandraUtil.getInstance().getSession().execute(stringBuild.toString());
	}
	
	public static void populateUserTable() {
		User user = new User("Xavier", "profx@gmail.com", null, null, null, null, null );
		user.setUserType(UserType.Benefits_Coordinator);
		List<Form> form = formDao.getUserForms(user.getUsername());
		formDao.addForm((Form) form);
		List<Inbox> inbox = inboxDAO.getInbox(user.getUsername());
		inboxDAO.addInbox((Inbox) inbox);
		userDao.addUser(user);
		
		User user2 = new User("Jean", "jgrey@gmail.com",  null, null, null, null, null );
		user2.setUserType(UserType.Department_Head);
		List<Form> form1 = formDao.getUserForms(user.getUsername());
		formDao.addForm((Form) form1);
		List<Inbox> inbox1 = inboxDAO.getInbox(user.getUsername());
		inboxDAO.addInbox((Inbox) inbox1);
		userDao.addUser(user2);
		

		User user3 = new User("Scott", "ssummers@gmail.com", null, null, null, null, null);
		user3.setUserType(UserType.Direct_Supervisor);
		List<Form> form2 = formDao.getUserForms(user.getUsername());
		formDao.addForm((Form) form2);
		List<Inbox> inbox2 = inboxDAO.getInbox(user.getUsername());
		inboxDAO.addInbox((Inbox) inbox2);
		userDao.addUser(user3);
		

		User user4 = new User("Logan", "lhowlett@gmail.com",  null, null, null, null, null);
		user4.setUserType(UserType.Employee);
		List<Form> form3 = formDao.getUserForms(user.getUsername());
		formDao.addForm((Form) form3);
		List<Inbox> inbox3 = inboxDAO.getInbox(user.getUsername());
		inboxDAO.addInbox((Inbox) inbox3);
		userDao.addUser(user4);
	}
	
	public static void populateFormTable() {
		
		
		UUID formId = UUID.randomUUID();
		LocalDate date = LocalDate.of(2021, 04, 12);
		LocalDateTime time = LocalDateTime.now();
//		EventOp event = EventOp.

		Form form = new Form(formId, "Logan", date, time, "Example", 500L, "B",   );
		
	}

}
