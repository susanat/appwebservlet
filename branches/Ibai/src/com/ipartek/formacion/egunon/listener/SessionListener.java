package com.ipartek.formacion.egunon.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.ipartek.formacion.egunon.bean.UserLogin;

/**
 * Application Lifecycle Listener implementation class SessionListener
 *
 */
public class SessionListener implements HttpSessionListener {

   private int sesionCount = 0;

	/**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    public void sessionCreated(HttpSessionEvent se) {
        synchronized (this) {
			sesionCount++;
		}

        System.out.println("Session Created: " + se.getSession().getId());
        System.out.println("Total session: " + sesionCount);
        System.out.println("Tiempo Expiracion: "+ se.getSession().getMaxInactiveInterval());
    }

	/**
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    public void sessionDestroyed(HttpSessionEvent se) {
    	synchronized (this) {
			sesionCount--;
		}
        System.out.println("Total session: " + sesionCount);
    }
	
}
