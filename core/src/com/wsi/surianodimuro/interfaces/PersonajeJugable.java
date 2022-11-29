package com.wsi.surianodimuro.interfaces;

public interface PersonajeJugable extends Ente {

	void usarAscensor(float x, float y);
	void dejarAscensor();
	
	void dispararProyectil();
	void dispararUltimate();
	
	void actualizarSustoPuntos(int sustoPuntos);
	void sumarVida();
}
