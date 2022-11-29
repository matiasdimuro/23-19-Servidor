package com.wsi.surianodimuro.interfaces;

public interface Ente {

	void moverseIzquierda();
	void moverseDerecha();
	
	void caminarDerecha();
	void caminarIzquierda();
	
	void restarVida();
	void incrementarVelocidad();
	
	/**
	 * Este metodo crea los frames del personaje a partir de un spritesheet.
	 * @param ANCHO_SPRITESHEET Ancho Total
	 * @param ALTO_SPRITESHEET Altura Total
	 * @param FILAS Cantidad de filas a dividir
	 * @param COLUMNAS Cantidad de columnas a dividir
	 */
	void cargarSpriteSheet(float ANCHO_SPRITESHEET, float ALTO_SPRITESHEET, int FILAS, int COLUMNAS);
}
