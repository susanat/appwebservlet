package com.ipartek.formacion.egunon.bean;

/**
 * Usuario de logeo para la AppWeb
 * 
 * @author Erlantz Romero Parra
 * @version 1.0
 * 
 *          Atributos
 *          <ol>
 *          <li>nombre: Nombre del usuaio</li>
 *          <li>password: Contraseña del usario</li>
 *          <li>anteriorConexion: Tiempo de la ultima conexion del usuario al AppWeb en BBDD</li>
 *          <li>conexion: Tiempo de conexion actual al logearse a la AppWeb</li>
 *          </ol>
 */
public class UserLogin {

	private String nombre;
	private String password;
	private long anteriorConexion;

	private final long conexion;

	public UserLogin(final String nombre, final String password) {
		super();
		this.nombre = nombre;
		this.password = password;
		// TODO crear tabla o usar cookie
		this.anteriorConexion = System.currentTimeMillis();
		this.conexion = System.currentTimeMillis();
	}

	public String getNombre() {
		return nombre;
	}

	public String getPassword() {
		return password;
	}

	public long getAnteriorConexion() {
		return anteriorConexion;
	}

	public long getConexion() {
		return conexion;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setAnteriorConexion(long anteriorConexion) {
		this.anteriorConexion = anteriorConexion;
	}

	@Override
	public String toString() {
		return "UserLogin [nombre=" + nombre + ", password=" + password
				+ ", anteriorConexion=" + anteriorConexion + ", conexion="
				+ conexion + "]";
	}

}
