package com.brain.home.config.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtility {
	
	public static Session getHibernateSession() {

	    final SessionFactory sf = new Configuration()
	        .configure("hibernate.cfg.xml").buildSessionFactory();

	    final Session session = sf.openSession();
	    return session;
	    }

}
