package com.wsi.surianodimuro.redes;

public enum MensajesCliente {

	SOLICITAR_CONEXION("Solicitar conexion"),
	CLIENTE_DESCONECTADO("El cliente se ha desconectado");
	
	private String mensaje;
	
	private MensajesCliente(String mensaje) {
		this.mensaje = mensaje;
	}
	
	public String getMensaje() {
		return mensaje;
	}
}
