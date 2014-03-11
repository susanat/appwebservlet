package com.ipartek.formacion.egunon.bean;

import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpSession;

/**
 * Usuario de logeo para la AppWeb
 * 
 * @author Eduardo Monterrubio Robledo
 * @version 1.0
 * 
 * Atributos
 * <ol>
 * 	<li>nombre: nombre del usuario</li>
 * 	<li>password: pass del usuario</li>
 * 	<li>anteriorConexion: tiempo de la ultima coenxion del usuario a la aplicacion en BBDD</li>
 * 	<li>conexion: tiempo de conexion al logearse</li>
 * </ol>
 */
public class UserLogin {
	
	private String nombre;
	private String password; //dni
	private String id; //identificador de seso¡ion creado por el servidor
	private int maxInactiveInterval; //tiempo de expiracion en segundos
	private long anteriorConexion; //lastAccessedTime
	private long conexion; //CreationTime
	HttpSession session;
	
	public UserLogin(String nombre, String password, HttpSession session) {
		super();
		this.nombre = nombre;
		this.password = password;
		//TODO crear tabla o usar cookie
		this.anteriorConexion = session.getLastAccessedTime();
		this.conexion = session.getCreationTime();
		this.maxInactiveInterval = session.getMaxInactiveInterval();
		this.id = session.getId();
		this.session = session;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public long getAnteriorConexion() {
		return anteriorConexion;
	}

	public void setAnteriorConexion(long anteriorConexion) {
		this.anteriorConexion = anteriorConexion;
	}

	public long getConexion() {
		return conexion;
	}

	public void setConexion(long conexion) {
		this.conexion = conexion;
	}
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getMaxInactiveInterval() {
		return maxInactiveInterval;
	}

	public void setMaxInactiveInterval(int maxInactiveInterval) {
		this.maxInactiveInterval = maxInactiveInterval;
	}
	
	public String getAnteriorConexionString(){
		return new Date(anteriorConexion).toString();
	}
	
	public String getConexionString(){
		return new Date(conexion).toString();
	}

	//Tiempo en segundos para que exppire la sesion del usuario
	public long getExpireTime(){
		return (this.maxInactiveInterval - ((new GregorianCalendar().getTimeInMillis() - this.session.getLastAccessedTime())/1000));
	}

	//Tiempo en segudnos que lleva conectado el usuario
	public long getConnectedTime(){
		return 0;
	}

	
	@Override
	public String toString() {
		return "UserLogin [nombre=" + nombre + ", password=" + password + ", anteriorConexion=" + anteriorConexion + ", conexion=" + conexion + "]";
	}


	
	

}
