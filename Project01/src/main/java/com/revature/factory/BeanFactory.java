package com.revature.factory;

import java.lang.reflect.Constructor;
import java.lang.reflect.Proxy;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BeanFactory {
	private static BeanFactory beanFact = null;
	private static Logger log = LogManager.getLogger(BeanFactory.class);
	private BeanFactory() {}
		
		public static synchronized BeanFactory getFactory() {
			
			if(beanFact == null) {
				beanFact = new BeanFactory();
			}
			return beanFact;
	}
	
	public Object get(Class<?> inter, Class<?> clazz) {
		Object obj = null;
		Constructor<?> con;
		try {
			con = clazz.getConstructor();
			
			obj = Proxy.newProxyInstance(inter.getClassLoader(), 
					new Class [] {inter}, new LogProxy(con.newInstance()));
		}catch (Exception e) {
			log.error("Exception thrown: " + e);
			for(StackTraceElement s : e.getStackTrace()) {
				log.warn(s);
			}
			throw new BeanCreationFailureException(e);
		}
		return obj;
	}
	
}
class BeanCreationFailureException extends RuntimeException{


	private static final long serialVersionUID = 1L;
	public BeanCreationFailureException (Exception e) {
		super(e);
	}
}
