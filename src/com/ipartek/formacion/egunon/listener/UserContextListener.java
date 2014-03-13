package com.ipartek.formacion.egunon.listener;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import org.apache.log4j.Logger;

import com.ipartek.formacion.egunon.bean.UserLogin;



/**
 * Application Lifecycle Listener implementation class UserCounterListener
 *
 */
public class UserContextListener implements ServletContextListener, HttpSessionAttributeListener {
	
	
	private final transient Logger log = Logger.getLogger(UserContextListener.class);
	
	public static final String COUNT_KEY = "userCounter";
	public static final String USERS_KEY = "listaSession";
	public static final String LOGIN_KEY = "login";
	
	private transient ServletContext servletContext;
	private int counter;
	private Set<UserLogin> users;

	
	
	
	@Override
	public synchronized void contextInitialized(ServletContextEvent sce) {
		servletContext = sce.getServletContext();
		servletContext.setAttribute((COUNT_KEY), Integer.toString(counter));
	}

	@Override
	public synchronized void contextDestroyed( ServletContextEvent sce) {
		servletContext = null;
		users = null;
		counter = 0;
	}
	
	
	/**
	 * This method is designed to catch when user's login and record their name
	 * 
	 * @see javax.servlet.http.HttpSessionAttributeListener#attributeAdded(javax.servlet.http.HttpSessionBindingEvent)
	 */
	@Override
	public void attributeAdded(HttpSessionBindingEvent event) {
		if (event.getName().equals(LOGIN_KEY)) {
			addUsername((UserLogin) event.getValue());
		}
	}

	/**
	 * When user's logout, remove their name from the hashMap
	 * 
	 * @see javax.servlet.http.HttpSessionAttributeListener#attributeRemoved(javax.servlet.http.HttpSessionBindingEvent)
	 */
	@Override
	public void attributeRemoved(HttpSessionBindingEvent event) {
		if (event.getName().equals(LOGIN_KEY)) {
			removeUsername((UserLogin) event.getValue());
		}
	}

	/**
	 * @see javax.servlet.http.HttpSessionAttributeListener#attributeReplaced(javax.servlet.http.HttpSessionBindingEvent)
	 */
	@Override
	public void attributeReplaced(HttpSessionBindingEvent event) {
		// I don't really care if the user changes their information
	}

	

	synchronized void incrementUserCounter() {
		counter = Integer.parseInt((String) servletContext.getAttribute(COUNT_KEY));
		counter++;
		servletContext.setAttribute(COUNT_KEY, Integer.toString(counter));

		if (log.isDebugEnabled()) {
			log.debug("User Count: " + counter);
		}
	}

	synchronized void decrementUserCounter() {
		int counter = Integer.parseInt((String) servletContext.getAttribute(COUNT_KEY));
		counter--;
		if (counter < 0) {
			counter = 0;
		}
		servletContext.setAttribute(COUNT_KEY, Integer.toString(counter));		
		log.debug("User Count: " + counter);
		
	}

	synchronized void addUsername(UserLogin user) {
		users = (Set<UserLogin>) servletContext.getAttribute(USERS_KEY);

		if (users == null) {
			users = new HashSet<UserLogin>();
		}	
		users.add(user);
		servletContext.setAttribute(USERS_KEY, users);
		incrementUserCounter();
		log.info("Nuevo usuario conectado: " + user.toString() );
	}

	synchronized void removeUsername(UserLogin user) {
		users = (Set) servletContext.getAttribute(USERS_KEY);

		if (users != null) {
			users.remove(user);
		}

		servletContext.setAttribute(USERS_KEY, users);
		decrementUserCounter();
		log.info("Usuario DESconectado: " + user.toString() );
	}
	
}
