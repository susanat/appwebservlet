package com.ipartek.formacion.egunon.bean;

/**
 * Usuario de logeo para la AppWeb
 * 
 * @author Patricia Navascués
 * @version 1.0
 * 
 *          Atributos
 *          <ol>
 *          	<li>nombre: nombre del usuario</li>
 *          	<li>password: pass del usuario</li>
 *          	<li>anteriorConexion: Tiempo de la ultima conexión del usuario a la
 *          AppWeb en la BBDD</li>
 *          	<li>conexion: Tiempo de conexión actual al logearse a la AppWeb</li>
 *          </ol>
 * 
 * 
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
		
		
		// TODO crear tabla o usar cookies
		this.anteriorConexion = System.currentTimeMillis();
		this.conexion= System.currentTimeMillis();
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

	@Override
	public String toString() {
		return "UserLogin [nombre=" + nombre + ", password=" + password + ", anteriorConexion=" + anteriorConexion + ", conexion=" + conexion + "]";
	}
	

}
