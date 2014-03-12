package com.ipartek.formacion.egunon.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.ipartek.formacion.egunon.bean.UserLogin;

/**
 * Application Lifecycle Listener implementation class SessionListener
 *
 */
public class SessionListener implements HttpSessionListener {
private int sessionCount=0;
    /**
     * Default constructor. 
     */
    public SessionListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    public void sessionCreated(HttpSessionEvent se) {
        // TODO Auto-generated method stub
    	synchronized (this) {
			sessionCount++;
			}
    	
    	System.out.println("Sesion Created " + se.getSession().getId());
		System.out.println("Total Sesiones: " + sessionCount);
		System.out.println("Tiempo de expiracion: " + se.getSession().getMaxInactiveInterval());
    	if(se.getSession().getAttribute("login")!=null){
    		UserLogin userLogin=(UserLogin)se.getSession().getAttribute("login");
    		System.out.println("Usuario: " + userLogin.toString());
    	}else{
    		System.out.println("Usuario no logeado");
    	}
	
    }

	/**
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    public void sessionDestroyed(HttpSessionEvent se) {
        // TODO Auto-generated method stub
    }
	
}
