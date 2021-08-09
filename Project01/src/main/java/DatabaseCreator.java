

import com.revature.beans.*;
import com.revature.data.UserDAO;
import com.revature.data.UserDAOImp;
import com.revature.util.CassandraUtil;

public class DatabaseCreator {
	public static UserDAO userDao = new UserDAOImp();
	
	public static void dropTables() {
		StringBuilder stringBuild = new StringBuilder("DROP TABLE IF EXISTS User;");
		CassandraUtil.getInstance().getSession().execute(stringBuild.toString());
		
	}
	
	public static void createTables() {
		StringBuilder stringBuild = new StringBuilder("CREATE TABLE IF NOT EXISTS User (")
				.append("username text PRIMARY KEY, email text, employeeId bigint, ")
				.append("userType text, pending bigint, approved bigint, forms list<forms>, supervisor text, departmentHead text, benCo text, inbox list<Inbox>);");
		CassandraUtil.getInstance().getSession().execute(stringBuild.toString());
	}
	
	public static void populateUserTable() {
		User user = new User("Xavier", "profx@gmail.com", null, null, null);
		user.setUserType(UserType.Benefits_Coordinator);
		userDao.addUser(user);
		
		User user2 = new User("Jean Grey", "jgrey@gmail.com", null, null, null);
		user2.setUserType(UserType.Department_Head);
		userDao.addUser(user2);
		

		User user3 = new User("Scott", "ssummers@gmail.com", null, null, null);
		user3.setUserType(UserType.Direct_Supervisor);
		userDao.addUser(user3);
		

		User user4 = new User("Logan", "wolverine@gmail.com", null, null, null);
		user4.setUserType(UserType.Employee);
		userDao.addUser(user4);
	}

}
