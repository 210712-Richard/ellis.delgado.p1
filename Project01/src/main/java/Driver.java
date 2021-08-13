
import org.apache.logging.log4j.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.revature.controller.FormContImp;
import com.revature.controller.FormController;
import com.revature.controller.UserContImp;
import com.revature.controller.UserController;
import com.revature.factory.BeanFactory;

import io.javalin.Javalin;
import io.javalin.plugin.json.JavalinJackson;

public class Driver {
	private static Logger log = LogManager.getLogger(Driver.class);
	
	public static void main (String[] args) {
//	startDatabase();
		javalin();
	
	}
	

	private static void startDatabase() {
	DatabaseCreator.dropTables();
	
	try {
		Thread.sleep(40000); //database might be big so im gonna wait 40 seconds
	}catch(Exception e) {
		log.error("Database failed to build. Error thrown: "+ e);
		e.printStackTrace();
	}
	DatabaseCreator.createTables();
	try {
		Thread.sleep(40000);
	}catch(Exception e) {
		log.error("Database failed to build. Error thrown: "+ e);
		e.printStackTrace();
		}
	DatabaseCreator.populateEventTable();
	DatabaseCreator.populateFormTable();
	DatabaseCreator.populateInboxTable();
	DatabaseCreator.populateUserTable();
	System.exit(0);
		
	}

	private static void javalin() {
		ObjectMapper jackson = new ObjectMapper();
		jackson.registerModule(new JavaTimeModule());
		jackson.disable(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS);
		JavalinJackson.configure(jackson);
		
		
		Javalin app = Javalin.create().start(8080);
		
		UserController uc = (UserController) BeanFactory.getFactory()
				.get(UserController.class, UserContImp.class);
		FormController fc = (FormController) BeanFactory.getFactory()
				.get(FormController.class, FormContImp.class);
		
		//delete
		app.delete("/users", uc::logout);
		app.delete("/users/:username/byeForm", fc::deleteForm);
		
		//get
		app.get("/", (ctx)-> ctx.html("Hello world"));
		app.get("/users/:username/forms", fc::getForm);
		app.get("/users/:username/file", fc::getFile);
		app.get("/users/:username/inbox", uc::getInbox);
		app.get("/users/:username/event/:title/:type", uc::getEvent);
		
		
		
		//post
		app.post("/users", uc::login);
		app.post("/users/:username/newForm", fc::addForm);
		app.post("/users/:username/newFile/", fc::addFile);
		app.post("/users/:username/newEvent/:title/:type/:description/:year/:month/:day", uc::addEvent);
		
		//put
		app.put("/users/:username", uc::register);
		app.put("/users/:username/:grade", fc::updateGrade);
		app.put("/users/:username/:status", fc::updateStatus);
		app.put("/users/:username/reimburse/:employee/:event/:type", uc::updateReim);
		
		
		
	}
	
}