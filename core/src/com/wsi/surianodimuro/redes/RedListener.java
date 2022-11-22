package com.wsi.surianodimuro.redes;

public interface RedListener {

	void comenzarJuego();
	void cerrarJuego();
	void reiniciarJuego();
	void terminarPartida();
	
	void moverAgenteIzquierda(int numCliente);
	void moverAgenteDerecha(int numCliente);
	
	void subirAgentePorAscensor(int numCliente);
	void bajarAgentePorAscensor(int numCliente);
	
	void cambiarArmaAgente(int numCliente, int numArmaNueva);
	void dispararProyectil(int numCliente);
	void dispararUltimate(int numCliente);
	
	void mantenerAgenteQuieto(int numCliente);
	void pararFuegoAgente(int numCliente);
	
	void resetearEstadosAgente(int numCliente);
}
