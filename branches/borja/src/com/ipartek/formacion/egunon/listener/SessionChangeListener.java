package com.ipartek.formacion.egunon.listener;

import java.util.ArrayList;

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
    	
    	if (se.getSession().getAttribute("login") != null){
    	//log.info("Atributo eliminado " + se.getName());
    	String idSessionUsuario = se.getSession().getId();
    	listaUsuarios = (ArrayList<UserLogin>) se.getSession().getAttribute("listaUsuarios");
    	for (int i = 0; i< listaUsuarios.size(); i++){
			if (listaUsuarios.get(i).getId().equals(idSessionUsuario)){
				listaUsuarios.remove(i);
				break;
			}//end if
		}//end for
    	}
    }

	/**
     * @see HttpSessionAttributeListener#attributeAdded(HttpSessionBindingEvent)
     */
    public void attributeAdded(HttpSessionBindingEvent se) {
      	
    	//log.info("Atributo añadido " + se.getName());
      	//comprobar que el atributo añadido a session sea el login
    	if (se.getSession().getAttribute("login") != null){
    		
    		UserLogin userLogin = (UserLogin) se.getSession().getAttribute("login");
    		
    		//log.info("Usuario: " + userLogin.toString());
    		if (listaUsuarios == null){
    			listaUsuarios = new ArrayList<UserLogin>();
    		}
    		//Añadimos el UserLogin a la lista y despues la cargamos en Session
    		listaUsuarios.add(userLogin);
    		se.getSession().setAttribute("listaUsuarios", listaUsuarios);
    		
    	}
    }

	/**
     * @see HttpSessionAttributeListener#attributeReplaced(HttpSessionBindingEvent)
     */
    public void attributeReplaced(HttpSessionBindingEvent se) {
    	System.out.println("Atrbito reemplazado "+se.getName());
    }
	
}
