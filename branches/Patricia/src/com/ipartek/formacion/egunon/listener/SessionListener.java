package com.ipartek.formacion.egunon.listener;

import java.util.ArrayList;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.ipartek.formacion.egunon.bean.UserLogin;

/**
 * Application Lifecycle Listener implementation class SessionListener
 * 
 */
public class SessionListener implements HttpSessionListener {
// Esta clase se ejecuta cada vez que se inicie una nueva sesión de la apliccación, es decir cada vez que se abra una ventana de la aplicación
	private int sessionCount = 0;

	/**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    public void sessionCreated(HttpSessionEvent se) {
    	synchronized (this) {
            sessionCount++;
        }
 
    	   	
        System.out.println("Session Created: " + se.getSession().getId());
        System.out.println("Total Sessions: " + sessionCount);
        System.out.println("Tiempo Expiración en segundos : " + se.getSession().getMaxInactiveInterval());
       
        
        
        
        
        
        
    }

	/**
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    public void sessionDestroyed(HttpSessionEvent se) {
    	 synchronized (this) {
             sessionCount--;
         }
         System.out.println("Session Destroyed: " + se.getSession().getId());
         System.out.println("Total Sessions: " + sessionCount);
    }
}
