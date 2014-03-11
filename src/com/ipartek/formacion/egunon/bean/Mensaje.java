package com.ipartek.formacion.egunon.bean;

/**
 * Encapsula los mensajes para mostrar en el front-end o capa Vista de la AppWeb
 * @author Curso
 *
 */
public class Mensaje {
	
	private String msg;
	private int codigo;
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




	/** 
	 * Clase interna para soportar el tipo de imagen a mostrar
	 * @author Curso
	 *
	 */
	
	public enum TIPO_MENSAJE {
		
		INFO, WARNING, ERROR;

	}
	
	


}
