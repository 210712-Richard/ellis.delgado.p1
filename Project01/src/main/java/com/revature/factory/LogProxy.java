package com.revature.factory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogProxy implements InvocationHandler {
	
	private Logger log; 
	private Object object;
	
	public LogProxy(Object obj) {
		object = obj;
		log = LogManager.getLogger(obj.getClass());
	}
	
	
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Object result = null;
		
		try {
			
			if (args == null) {
				log.trace("Method"+ method + " Has no args");
				result = method.invoke(object);
			}else {
				log.trace("Method"+method+ " has args" + Arrays.toString(args));
				result = method.invoke(object, args);
			}
			log.trace("Method returned: "+ result);
			
		}catch (Exception e) {
			log.error("Method threw exception: "+e);
			for(StackTraceElement s: e.getStackTrace()) {
				log.warn(s);
			}
			if (e.getCause() != null) {
				Throwable t = e.getCause();
				log.error("Method threw error: " + t);
				for(StackTraceElement s: t.getStackTrace()) {
					log.warn(s);
				}
			}
			throw e;
		}
		return result;
	}
	

}
