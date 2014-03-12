package com.ipartek.formacion.egunon.listener;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import org.apache.log4j.Logger;

import com.ipartek.formacion.egunon.bean.UserLogin;
import com.ipartek.formacion.egunon.controller.AlumnoServlet;

/**
 * Application Lifecycle Listener implementation class SessionChangeListener
 *
 */
public class SessionChangeListener implements HttpSessionAttributeListener {

	static HashMap listaUsuarios;
	protected static final Logger log = Logger.getLogger(SessionChangeListener.class);


	/**
     * @see HttpSessionAttributeListener#attributeRemoved(HttpSessionBindingEvent)
     */
    public void attributeRemoved(HttpSessionBindingEvent se) {
    	/*log.info("Atributo eliminado " + se.getName());
    	listaUsuarios = (HashMap) se.getSession().getAttribute("listaUsuarios");
    	listaUsuarios.remove(se.getSession().getId());
    	se.getSession().setAttribute("listaUsuarios", listaUsuarios);*/
    }
    
	/**
     * @see HttpSessionAttributeListener#attributeAdded(HttpSessionBindingEvent)
     */
    public void attributeAdded(HttpSessionBindingEvent se) {
    	/*log.info("Atributo añadido " + se.getName());
    	if (se.getSession().getAttribute("login") != null){
    		UserLogin userLogin = (UserLogin) se.getValue();
    		System.out.println("Usuario: " + userLogin.toString());
    		if (listaUsuarios == null){
    			listaUsuarios = new HashMap();
    		}
    		listaUsuarios.put(se.getSession().getId(), userLogin);
    		se.getSession().setAttribute("listaUsuarios", listaUsuarios);
    		
    	}*/
    }

	/**
     * @see HttpSessionAttributeListener#attributeReplaced(HttpSessionBindingEvent)
     */
    public void attributeReplaced(HttpSessionBindingEvent se) {
    	log.info("Atributo reemplazado " + se.getName());
    }
	
}
