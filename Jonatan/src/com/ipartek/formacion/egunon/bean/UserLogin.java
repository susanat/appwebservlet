package com.ipartek.formacion.egunon.bean;

/**
 * Usuario de logeo para la AppWeb
 * 
 * @author Ioritz Bereikua Etxebarria
 * @version 1.0
 * 
 *          Atributos
 *          <ol>
 *          <li>nombre: Nombre del usuario</li>
 *          <li>password: pas del usuario</li>
 *          <li>anteriorConexion: tiempo de la ultima conexion del usuario a la
 *          AppWeb en BBDD</li>
 *          <li>conexion: tiempo de conexion al logearse en la ap</li>
 *          <li></li>
 *          </ol>
 */
public class UserLogin {
	private String nombre;
	private String password;
	private long anteriorConexion;

	private long consexion;

	public UserLogin(String nombre, String pass) {
		super();
		this.nombre = nombre;
		this.password = pass;
		// TODO crear tabla o usar cookie
		this.anteriorConexion = System.currentTimeMillis();
		this.consexion = System.currentTimeMillis();
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public long getAnteriorConexion() {
		return this.anteriorConexion;
	}

	public void setAnteriorConexion(long anteriorConexion) {
		this.anteriorConexion = anteriorConexion;
	}

	public long getConexion() {
		return this.consexion;
	}

	public void setConsexion(long consexion) {
		this.consexion = consexion;
	}

	@Override
	public String toString() {
		return "UserLogin [nombre=" + this.nombre + ", password=" + this.password + ", anteriorConexion=" + this.anteriorConexion + ", consexion="
				+ this.consexion + "]";
	}
}
