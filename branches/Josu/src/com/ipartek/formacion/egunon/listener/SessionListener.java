package com.ipartek.formacion.egunon.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.ipartek.formacion.egunon.bean.UserLogin;

/**
 * Application Lifecycle Listener implementation class SessionListener
 *
 */
public class SessionListener implements HttpSessionListener {

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
