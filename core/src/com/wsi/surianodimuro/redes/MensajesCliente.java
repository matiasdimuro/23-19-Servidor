package com.wsi.surianodimuro.redes;

public enum MensajesCliente {

	SOLICITAR_CONEXION("Solicitar conexion"),
	CLIENTE_DESCONECTADO("El cliente se ha desconectado"),
	
	CAMINAR_IZQUIERDA("Caminar izquierda"),
	CAMINAR_DERECHA("Caminar derecha"),
	MANTENERSE_QUIETO("Quieto"),
	SUBIR_ASCENSOR("Subir ascensor"),
	BAJAR_ASCENSOR("Bajar ascensor"),
	CAMBIAR_ARMA("Cambiar arma"),
	DISPARAR_PROYECTIL("Disparar proyectil"),
	DISPARAR_ULTIMATE("Disparar ultimate"),
	PARAR_FUEGO("Dejar de disparar"),
	RESETEAR_ESTADOS("Resetear controlador");
	
	private String mensaje;
	
	private MensajesCliente(String mensaje) {
		this.mensaje = mensaje;
	}
	
	public String getMensaje() {
		return mensaje;
	}
}
