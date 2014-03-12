package com.ipartek.formacion.egunon.bean;

/**
 * Encapsula los mensajes para mostara en el front-end o capa de la Vista de la AppWeb
 * @author Ander Uraga Real
 * @version 1.0
 *
 */
public class Mensaje {

	private String msg;
	private int    codigo;
	private TIPO_MENSAJE tipo;
	
	
	
	public Mensaje(String msg, int codigo, TIPO_MENSAJE tipo) {
		super();
		this.msg = msg;
		this.codigo = codigo;
		this.tipo = tipo;
	}


	

	public String getMsg() {
		return msg;
	}




	public void setMsg(String msg) {
		this.msg = msg;
	}




	public int getCodigo() {
		return codigo;
	}




	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}




	public TIPO_MENSAJE getTipo() {
		return tipo;
	}




	public void setTipo(TIPO_MENSAJE tipo) {
		this.tipo = tipo;
	}




	@Override
	public String toString() {
		return "Mensaje [msg=" + msg + ", codigo=" + codigo + ", tipo=" + tipo + "]";
	}




	/**
	 * Clase interna para soporta el tipo de Mensajes a mostrar	 
	 */
	public enum TIPO_MENSAJE {

		INFO, WARNING, ERROR;	
	}
	
	
		
	
	
}

