package com.ipartek.formacion.egunon;

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

	public static ArrayList<UserLogin> listaUsuarios;

	/**
     * @see HttpSessionAttributeListener#attributeRemoved(HttpSessionBindingEvent)
     */
    public void attributeRemoved(HttpSessionBindingEvent se) {
    	System.out.println("Atributo eliminado " + se.getName());
    	String idSessionUsuario = se.getSession().getId();
    	//Comprobar que el Attributo a modificar sea "login"
    	if (se.getSession().getAttribute("login") != null){
    		listaUsuarios = (ArrayList<UserLogin>)se.getSession().getAttribute("listaUsuarios");
    		for (int i = 0; i< listaUsuarios.size(); i++){
				if (listaUsuarios.get(i).getId().equals( idSessionUsuario )){
					listaUsuarios.remove(i);
					se.getSession().setAttribute("listaUsuarios", listaUsuarios);   
					break;
				}
    		}//end for
    	}//end if	
    }

	/**
     * @see HttpSessionAttributeListener#attributeAdded(HttpSessionBindingEvent)
     */
    public void attributeAdded(HttpSessionBindingEvent se) {
    	System.out.println("Atributo añadido " + se.getName());
    	
    	//Comprobar que el Attributo a modificar sea "login"
    	if (se.getSession().getAttribute("login") != null){
    		
    		UserLogin userLogin = (UserLogin) se.getValue();
    		
    		System.out.println("Usuario: " + userLogin.toString());
    		if (listaUsuarios == null){
    			listaUsuarios = new ArrayList<UserLogin>();
    		}
    		//Añadismo el UserLogi a  la lista y despues la cargamos en Session
    		listaUsuarios.add(userLogin);
    		se.getSession().setAttribute("listaUsuarios", listaUsuarios);    		
    	}
    }

	/**
     * @see HttpSessionAttributeListener#attributeReplaced(HttpSessionBindingEvent)
     */
    public void attributeReplaced(HttpSessionBindingEvent se) {
    	System.out.println("Atributo reemplazado " + se.getName());
    }
	
}
