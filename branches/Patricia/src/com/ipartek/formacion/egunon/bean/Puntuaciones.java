package com.ipartek.formacion.egunon.bean;
/**
 * Clase para encapsular las puntuaciones de los usuarios
 * 
 * @author Patricia Navascués
 * @version 1.0
 * 
 */

public class Puntuaciones {

	private final int id;
	private final String nombreUsuario;
	private final String juego;
	private final double puntuacion;

	public Puntuaciones(int id, String nombreUsuario, String juego, double puntuacion) {
		super();
		this.id = id;
		this.nombreUsuario = nombreUsuario;
		this.juego = juego;
		this.puntuacion = puntuacion;
	}

	public int getId() {
		return id;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public String getJuego() {
		return juego;
	}

	public double getPuntuacion() {
		return puntuacion;
	}

}
