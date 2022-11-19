package com.wsi.surianodimuro.redes;

public enum MensajesServidor {

	SOLICITUD_ACEPTADA("Conexion aceptada"),
	SOLICITUD_RECHAZADA("No puede conectarse"),
	
	DESCONECTAR_CLIENTE("Desconectar cliente"),
	CERRAR_SERVIDOR("Cerrar servidor"),
	
	EMPEZAR_JUEGO("Empezar juego");
	
	private String mensaje;
	
	private MensajesServidor(String mensaje) {
		this.mensaje = mensaje;
	}
	
	public String getMensaje() {
		return mensaje;
	}
}
