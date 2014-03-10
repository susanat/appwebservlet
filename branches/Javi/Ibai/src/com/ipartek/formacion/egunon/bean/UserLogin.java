package com.ipartek.formacion.egunon.bean;

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
	private String password;
	private long anteriorConexion;
	private long conexion;
	
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

	public void setConexion(long conexion) {
		this.conexion = conexion;
	}

	@Override
	public String toString() {
		return "UserLogin [nombre=" + nombre + ", password=" + password + ", anteriorConexion=" + anteriorConexion + ", conexion=" + conexion + "]";
	}


	
	

}
