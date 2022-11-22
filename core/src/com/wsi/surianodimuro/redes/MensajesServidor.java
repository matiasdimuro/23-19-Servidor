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
	HABILITAR_DISPAROS("Habilitar disparos"),
	HABILITAR_POSIBILIDAD_INFECCION("Habilitar posibilidad infeccion"),
	RESETEAR_ESTADOS_AGENTE("Resetear estados agente"),
	
	SPAWNEAR_INFECTADO("Spawnear infectado"),
	MOVER_INFECTADO_IZQUIERDA("Mover infectado izquierda"),
	MOVER_INFECTADO_DERECHA("Mover infectado derecha"),
	
	ACTUALIZAR_TPO_SUPERVIVENCIA("Actualizar tpo supervivencia"),
	ACTUALIZAR_TPO_TRANSCURRIDO("Actualizar tpo transcurrido"),
	
	SONAR_MUSICA("Sonar musica"),
	TERMINAR_MUSICA("Terminar musica"),
	
	PROCESAR_TPO_ENTRE_OLEADAS("Procesar tpo entre oleadas"),
	PROCESAR_TPO_OLEADA_TERMINADA("Procesar oleada terminada"),
	PROCESAR_RETARDO_INICIO_OLEADAS("Procesar retardo inicio oleadas"),
	
	ACTUALIZAR_CAJA_MENSAJES("Actualizar caja mensajes"),
	
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
