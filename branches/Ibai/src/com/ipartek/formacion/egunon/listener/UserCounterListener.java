package com.ipartek.formacion.egunon.listener;

import java.util.HashMap;

import javax.servlet.ServletContext;
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
public class UserCounterListener implements ServletContextListener, HttpSessionAttributeListener {

	private HashMap listaUsuarios;
	protected static final Logger log = Logger.getLogger(SessionChangeListener.class);
	private transient ServletContext servletContext;
	
    /**
     * Default constructor. 
     */
    public UserCounterListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see HttpSessionAttributeListener#attributeRemoved(HttpSessionBindingEvent)
     */
    public void attributeRemoved(HttpSessionBindingEvent se) {
    	log.info("Atributo eliminado " + se.getName());
    	listaUsuarios = (HashMap) servletContext.getAttribute("listaUsuarios");
    	listaUsuarios.remove(se.getSession().getId());
    	servletContext.setAttribute("listaUsuarios", listaUsuarios);
    }

	/**
     * @see HttpSessionAttributeListener#attributeAdded(HttpSessionBindingEvent)
     */
    public void attributeAdded(HttpSessionBindingEvent se) {
    	log.info("Atributo añadido " + se.getName());
    	if (se.getSession().getAttribute("login") != null){
    		UserLogin userLogin = (UserLogin) se.getValue();
    		System.out.println("Usuario: " + userLogin.toString());
    		if (listaUsuarios == null){
    			listaUsuarios = new HashMap();
    		}else{
    			listaUsuarios = (HashMap) servletContext.getAttribute("listaUsuarios");
    		}
    		listaUsuarios.put(se.getSession().getId(), userLogin);
    		servletContext.setAttribute("listaUsuarios", listaUsuarios);   		
    	}
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce) {
    	servletContext = sce.getServletContext();
    }

	/**
     * @see HttpSessionAttributeListener#attributeReplaced(HttpSessionBindingEvent)
     */
    public void attributeReplaced(HttpSessionBindingEvent se) {
        // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce) {
    	 servletContext = null;
    }
	
}
