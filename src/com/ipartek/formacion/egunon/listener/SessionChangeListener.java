package com.ipartek.formacion.egunon.listener;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import com.ipartek.formacion.egunon.bean.UserLogin;

/**
 * Application Lifecycle Listener implementation class SessionChangeListener
 *
 */
public class SessionChangeListener implements HttpSessionAttributeListener {



	/**
     * @see HttpSessionAttributeListener#attributeRemoved(HttpSessionBindingEvent)
     */
    public void attributeRemoved(HttpSessionBindingEvent se) {
        // TODO Auto-generated method stub
    }

	/**
     * @see HttpSessionAttributeListener#attributeAdded(HttpSessionBindingEvent)
     */
    public void attributeAdded(HttpSessionBindingEvent se) {
    	System.out.println("Atrbito añadido "+se.getName());
    	if(se.getSession().getAttribute("login")!=null){
    		
    		UserLogin ul = (UserLogin)se.getSession().getAttribute("login");
    		System.out.println("usuario "+ul.toString());
    		
    	}else{
    		System.out.println("Usuario no logeado");
    	}
    }

	/**
     * @see HttpSessionAttributeListener#attributeReplaced(HttpSessionBindingEvent)
     */
    public void attributeReplaced(HttpSessionBindingEvent se) {
    	System.out.println("Atrbito reemplazado "+se.getName());
    }
	
}
