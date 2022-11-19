package com.wsi.surianodimuro.personajes.monstruos;

import com.wsi.surianodimuro.enumeradores.Infectados;
import com.wsi.surianodimuro.enumeradores.Monstruos;
import com.wsi.surianodimuro.enumeradores.Proyectiles;
import com.wsi.surianodimuro.personajes.Infectado;

public abstract class Monstruo extends Infectado {

	protected Monstruos tipoMonstruo;
	
	/**
	 * 
	 * @param rutaSpritesheet Ruta relativa dentro del proyecto del spritesheet.
	 * @param ESCALA_TAMANIO Coeficiente que determina la escala del Sprite
	 * @param ANCHO_SPRITESHEET Ancho total del spritesheet
	 * @param ALTO_SPRITESHEET Alto total del spritesheet
	 * @param FILAS Que definiran los frames del spritesheet
	 * @param COLUMNAS Que definiran los frames del spritesheet
	 * @param vida
	 */
	
	public Monstruo(String rutaSpritesheet, float ESCALA_TAMANIO, float ANCHO_SPRITESHEET, float ALTO_SPRITESHEET, int FILAS, int COLUMNAS, int vida) {
		super(rutaSpritesheet, ESCALA_TAMANIO, vida, ANCHO_SPRITESHEET, ALTO_SPRITESHEET, FILAS, COLUMNAS);
		debilidad = Proyectiles.PROYECTIL_DESINTOX;
		tipo = Infectados.MONSTRUO;
	}
	
	public Monstruos getTipoMonstruo() {
		return tipoMonstruo;
	}
}
