package com.ipartek.formacion.egunon.listener;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import com.ipartek.formacion.egunon.bean.UserLogin;

/**
 * Application Lifecycle Listener implementation class SessionChange
 *
 */
public class SessionChange implements HttpSessionAttributeListener {

    /**
     * Default constructor. 
     */
    public SessionChange() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see HttpSessionAttributeListener#attributeRemoved(HttpSessionBindingEvent)
     */
    public void attributeRemoved(HttpSessionBindingEvent se) {
    	System.out.println("Atributo eliminado " + se.getName());
    }

	/**
     * @see HttpSessionAttributeListener#attributeAdded(HttpSessionBindingEvent)
     */
    public void attributeAdded(HttpSessionBindingEvent se) {
    	System.out.println("Atributo añadido " + se.getName());
    	if(se.getSession().getAttribute("login")!=null){
    		UserLogin userLogin=(UserLogin)se.getSession().getAttribute("login");
    		System.out.println("Usuario: " + userLogin.toString());
    	}else{
    		System.out.println("Usuario no logeado");
    	}
    }

	/**
     * @see HttpSessionAttributeListener#attributeReplaced(HttpSessionBindingEvent)
     */
    public void attributeReplaced(HttpSessionBindingEvent se) {
    	System.out.println("Atributo reemplazado " + se.getName());
    }
	
}
