package com.revature.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.config.DriverConfigLoader;

public class CassandraUtil {

		private static CassandraUtil instance = null;
		private static final Logger log = LogManager.getLogger(CassandraUtil.class);

		private CqlSession session = null;
		
		private CassandraUtil() {
			log.trace("Beginning Cassandra Connection");
			
			DriverConfigLoader loader = DriverConfigLoader.fromClasspath("application.conf");
			
			try {
				this.session = CqlSession.builder()
						.withConfigLoader(loader)
						.withKeyspace("p1_keyspace")
						.build();
				
			}catch(Exception e){
				log.error("Build unsuccessful"+ e);
				throw e;
			}
		}
		
		public static synchronized CassandraUtil getInstance(){
			if (instance == null) {
				instance = new CassandraUtil();
			}
			log.trace("Cassandra instance: "+ instance);
			return instance;
		}
		public CqlSession getSession() {
			log.trace("CQL session:"+ session);
			return session;
		}
		
}
