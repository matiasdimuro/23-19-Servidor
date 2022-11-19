package com.wsi.surianodimuro.personajes.ninios;

import com.wsi.surianodimuro.enumeradores.Infectados;
import com.wsi.surianodimuro.enumeradores.Ninios;
import com.wsi.surianodimuro.enumeradores.Proyectiles;
import com.wsi.surianodimuro.personajes.Infectado;

public abstract class Ninio extends Infectado {

	protected Ninios tipoNinio;
	
	/**
	 * 
	 * @param rutaSpritesheet Ruta relativa dentro del proyecto del spritesheet.
	 * @param ANCHO_SPRITESHEET Ancho total del spritesheet
	 * @param ALTO_SPRITESHEET Alto total del spritesheet
	 * @param FILAS Que definiran los frames del spritesheet
	 * @param COLUMNAS Que definiran los frames del spritesheet
	 * @param vida
	 */
	
	public Ninio(String rutaSpritesheet, float ANCHO_SPRITESHEET, float ALTO_SPRITESHEET, int FILAS, int COLUMNAS, int vida) {
		super(rutaSpritesheet, 2.5f, vida, ANCHO_SPRITESHEET, ALTO_SPRITESHEET, FILAS, COLUMNAS);
		debilidad = Proyectiles.PROYECTIL_BOO2000;
		tipo = Infectados.NINIO;
	}
	
	public Ninios getTipoNinio() {
		return tipoNinio;
	}
}
