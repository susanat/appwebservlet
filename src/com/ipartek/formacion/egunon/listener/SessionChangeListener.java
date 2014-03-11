package com.ipartek.formacion.egunon.listener;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import com.ipartek.formacion.egunon.bean.UserLogin;

/**
 * Application Lifecycle Listener implementation class SessionChangeListener
 *
 */
public class SessionChangeListener implements HttpSessionAttributeListener {

	static ArrayList<UserLogin> listaUsuarios;

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
    	if (se.getSession().getAttribute("login") != null){
    		UserLogin userLogin = (UserLogin) se.getSession().getAttribute("login");
    		System.out.println("Usuario: " + userLogin.toString());
    		if (listaUsuarios == null){
    			listaUsuarios = new ArrayList<UserLogin>();
    		}
    		boolean insertar = true;
    		if(se.getSession().getAttribute("listaUsuarios") != null)
    		{
        		listaUsuarios = (ArrayList<UserLogin>) se.getSession().getAttribute("listaUsuarios");
        		for (int i = 0; i< listaUsuarios.size(); i++){
        			if (listaUsuarios.get(i).getId().equals(userLogin.getId())){
        				insertar = false;
        			}
        		}
    		}
    		if (insertar)
    			listaUsuarios.add(userLogin);
    		se.getSession().setAttribute("listaUsuarios", listaUsuarios);
    		
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
