package com.ipartek.formacion.egunon.bean;
/**
 * Encapsula lso mensajes para motrar en el front-end o capa de la vista de la AppWeb
 * @author Curso
 *
 */
public class Mensaje {


private String msg;
private int codigo;
private TIPO_MENSAJE tipo;

/**
 * Clase interna para soportar el tipo de mensaje a mostrar 
 * @author Curso
 *
 */
public enum TIPO_MENSAJE {
INFO, WARNING,ERROR;
}



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
	return "Mensaje [msg=" + msg + ", codigo=" + codigo + ", tipo=" + tipo
			+ "]";
}
}
