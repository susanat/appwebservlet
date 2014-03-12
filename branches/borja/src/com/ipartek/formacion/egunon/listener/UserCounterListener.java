package com.ipartek.formacion.egunon.listener;

import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.ipartek.formacion.egunon.bean.UserLogin;
import com.ipartek.pruebas.bbdd.model.ModeloAlumno;

/**
 * Application Lifecycle Listener implementation class UserCounterListener
 * 
 */
public class UserCounterListener implements ServletContextListener, HttpSessionAttributeListener {

	public static final String COUNT_KEY = "userCounter";
	public static final String USERS_KEY = "userNames";
	private final Logger log =  Logger.getLogger (UserCounterListener.class);
	private transient ServletContext servletContext;
	private int counter;
	private HashMap <String, UserLogin> listaUsuarios;
	private static ModeloAlumno ma;
	String idSessionUsuario;

	/**
	 * @see ServletContextListener#contextInitialized(ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent sce) {

		
		servletContext = sce.getServletContext();
		servletContext.setAttribute((COUNT_KEY), Integer.toString(counter));

		ma = new ModeloAlumno();
    	String prefix = servletContext.getRealPath("/");
    	String log4jpath = servletContext.getInitParameter("log4j-config");
        if (log4jpath != null)
        {    	
        	PropertyConfigurator.configure(prefix+log4jpath);
        }
	}

	/**
	 * @see ServletContextListener#contextDestroyed(ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent sce) {

		servletContext = null;
		listaUsuarios = null;
		counter = 0;

	}
	
	 synchronized void addUsername(Object user) {
	        listaUsuarios = (Set) servletContext.getAttribute(USERS_KEY);

	        if (listaUsuarios == null) {
	           listaUsuarios = new HashMap<idSessionUsuario , UserLogin> >();
	        }

	        if (log.isDebugEnabled()) {
	            if (users.contains(user)) {
	                log.debug("User already logged in, adding anyway...");
	            }
	        }

	        listaUsuarios.add(user);
	        servletContext.setAttribute(USERS_KEY, listaUsuarios);
	        
	    }

	    synchronized void removeUsername(Object user) {
	        listaUsuarios = (HashMap) servletContext.getAttribute(USERS_KEY);

	        if (listaUsuarios != null) {
	        	listaUsuarios.remove(user);
	        }

	        servletContext.setAttribute(USERS_KEY, listaUsuarios);
	      
	    }

	    /**
	    * This method is designed to catch when user's login and record their name
	     * @see javax.servlet.http.HttpSessionAttributeListener#attributeAdded(javax.servlet.http.HttpSessionBindingEvent)
	     */
	    
	    @Override
	    public void attributeAdded(HttpSessionBindingEvent event) {
	    	idSessionUsuario = se.getSession().getId();
	    	
	    	if (event.getName().equals(Constants.USER_KEY)) {
	            addUsername(event.getValue());
	        }
	    }

	    /**
	    * When user's logout, remove their name from the hashMap
	     * @see javax.servlet.http.HttpSessionAttributeListener#attributeRemoved(javax.servlet.http.HttpSessionBindingEvent)
	     */
	    
	    @Override
	    public void attributeRemoved(HttpSessionBindingEvent event) {
	        if (event.getName().equals(Constants.USER_KEY)) {
	            removeUsername(event.getValue());
	        }
	    }

	
		
		@Override
		public void attributeReplaced(HttpSessionBindingEvent se) {
		
			
		}

}
