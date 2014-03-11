package com.ipartek.formacion.egunon.bean;

/**
 * Usuario de logeo para la appWeb
 * @author Curso
 *
 * Atributos
 * <ol>
 * 	<li>nombre: nombre del usuario</li>
 * 	<li>password: Pass del usuario</li>
 * 	<li>anteriorConexion: tiempo de la ultima conexion a la AppWeb en BBDD</li>
 * 	<li>conexion: tiempo de la ultima conexion a la AppWeb </li>
 * </ol>
 */
public class UserLogin {

	private String nombre;
	private String password; // sera el dni
	private String id; //identificador de sesion creado por Tomcat
	private int maxInactiveInterval; // tiempo de expiracion en segundos
	

	private long anteriorConexion; //lastAccessedTime
	private long conexion;   // reationTime
	
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
	/*
	 * Tiempo en segundos para que expire la sesion del usuario 
	 */
	public long getExpireTime(){
		return 0;
	}
	/*
	 * Tiempo en segundos que lleva conectado el usuario 
	 */
	public long getConnectedTime(){
		return 0;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public UserLogin(String nombre, String password) {
		super();
		this.nombre = nombre;
		this.password = password;
		//TODO crear tabla o usar cookie
		this.anteriorConexion = System.currentTimeMillis();
		this.conexion = System.currentTimeMillis();
	}

	@Override
	public String toString() {
		return "UserLogin [nombre=" + nombre + ", password=" + password
				+ ", anteriorConexion=" + anteriorConexion + ", conexion="
				+ conexion + "]";
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
	
	
	
}
