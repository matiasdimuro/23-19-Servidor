package com.wsi.surianodimuro.redes;

public enum MensajesServidor {

	SOLICITUD_ACEPTADA("Conexion aceptada"),
	SOLICITUD_RECHAZADA("No puede conectarse"),
	
	DESCONECTAR_CLIENTE("Desconectar cliente"),
	CERRAR_SERVIDOR("Cerrar servidor"),
	
	MOVER_AGENTE_IZQUIERDA("Mover agente izquierda"),
	MOVER_AGENTE_DERECHA("Mover agente derecha"),
	SUBIR_AGENTE_POR_ASCENSOR("Subir agente por ascensor"),
	BAJAR_AGENTE_POR_ASCENSOR("Bajar agente por ascensor"),
	CAMBIAR_ARMA_AGENTE("Cambiar arma agente"),
	DISPARAR_PROYECTIL("Disparar proyectil agente"),
	DISPARAR_ULTIMATE("Disparar ultimate agente"),
	MANTENER_AGENTE_QUIETO("Mantener agente quieto"),
	PARAR_FUEGO_AGENTE("Parar fuego agente"),
	RESETEAR_ESTADOS_AGENTE("Resetear estados agente"),
	
	SPAWNEAR_INFECTADO("Spawnear infectado"),
	MOVER_INFECTADO_IZQUIERDA("Mover infectado izquierda"),
	MOVER_INFECTADO_DERECHA("Mover infectado derecha"),
	
	
	TERMINAR_JUEGO("Terminar juego"),
	EMPEZAR_JUEGO("Empezar juego");
	
	private String mensaje;
	
	private MensajesServidor(String mensaje) {
		this.mensaje = mensaje;
	}
	
	public String getMensaje() {
		return mensaje;
	}
}
