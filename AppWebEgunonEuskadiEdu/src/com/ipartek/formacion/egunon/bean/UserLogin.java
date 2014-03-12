package com.ipartek.formacion.egunon.bean;

import java.sql.Date;
import java.text.SimpleDateFormat;

/**
 * Usuario de lofeo para la Appweb
 * 
 * @author Curso
 *Atributos
 *<ol>
 *<li>nombre: nombre del usuario</li>
 *<li>password: las pass del usuario</li>
 *<li>ultimaConexion: sera la ultima conexion del usuario a  la AppWeb en BBDD</li>
 *<li>conexion: sera conexion actual </li>
 *<li></li>
 *
 *</ol>
 */
public class UserLogin {
	private String id;
	private int maxInactiveInterval;//Tiempo expiracion en segundos
	private String nombre;
	private String password;
	private long ultimaConexion; //lastAccesTime
	private long conexion;		//creationTiem
	private String conexionString;

	public void setConexionString(String conexionString) {
		this.conexionString = conexionString;
	}

	public UserLogin(String nombre, String password) {
		super();
		this.nombre = nombre;
		this.password = password;
		
		//TODO crear tabla o usar cookie
		this.ultimaConexion=System.currentTimeMillis();
		this.conexion=System.currentTimeMillis();
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
	
	public long getUltimaConexion() {
		return ultimaConexion;
	}
	
	public void setUltimaConexion(long ultimaConexion) {
		this.ultimaConexion = ultimaConexion;
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
	public  int getExpireTiem(){
		return 0;
	}
	
	
	@Override
	public String toString() {
		return "UserLogin [nombre=" + nombre + ", password=" + password
				+ ", ultimaConexion=" + ultimaConexion + ", conexion=" + conexion
				+ "]";
	}
	
	public String getConexionString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");		
		return sdf.format( new Date(conexion));
	}
	
	
}
