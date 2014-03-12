package com.ipartek.formacion.egunon.bean;
/**
 * Usuario de logeo para la AppWeb
 *  
 * @author Ander Uraga Real
 * @version 1.0
 *
 *	Atributos
 *  <ol>
 *  	<li>nombre: nombre del usuario</li>
 *  	<li>password: pass del usuario</li>
 *  	<li>anteriorConexion: Tiempo de la ultima conexion del usario a la AppWeb en BBDD</li>
 *  	<li>conexion: Tiempo de conexion actual al logearse a la AppWeb</li>
 *  </ol>
 *
 *
 */
public class UserLogin {

	private String id; //Identificador session creado por tomcat
	private int maxInactiveInterval; //tiempo expiracion en segundos
	private String nombre;
	private String password; //dni
	
	private long anteriorConexion;	// lastAccessedTime
	private long conexion;         //  creationTime
	
	public UserLogin(String nombre, String password) {
		super();
		this.nombre = nombre;
		this.password = password;
		
		//TODO crear tabla o usar cookie
		this.anteriorConexion = System.currentTimeMillis();
		this.conexion = System.currentTimeMillis();
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

	public void setConexion(long conexion) {
		this.conexion = conexion;
	}

	//Tiempo en segundos para que expire la session del usuario
	public long getExpireTime(){
		return 0;
	}
	
	//Tiempo en segundos que lleva conectado el usuario
	public long getConnectedTime(){
		return 0;
	}
	
	@Override
	public String toString() {
		return "UserLogin [nombre=" + nombre + ", password=" + password + ", anteriorConexion=" + anteriorConexion + ", conexion=" + conexion + "]";
	}
	
	
	
	
}
