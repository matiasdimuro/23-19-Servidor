package com.wsi.surianodimuro.utilidades;

import com.badlogic.gdx.Gdx;

public abstract class ConfigGraficos {

	public static final int ANCHO_PANTALLA = Gdx.graphics.getWidth();
	public static final int ALTO_PANTALLA = Gdx.graphics.getHeight();
	
	private static final int TAMANIO_TILES = 32;
	private static final int ANCHO_TILES_MAPA = 60;
	private static final int ALTO_TILES_MAPA = 34;
	
	public static final int ANCHO_MAPA = TAMANIO_TILES * ANCHO_TILES_MAPA;
	public static final int ALTO_MAPA = TAMANIO_TILES * ALTO_TILES_MAPA;
	
	public static final float COCIENTE_ENTRE_ANCHOS = ConfigGraficos.ANCHO_MAPA / (float) ConfigGraficos.ANCHO_PANTALLA;
	public static final float COCIENTE_ENTRE_ALTOS = ConfigGraficos.ALTO_MAPA / (float)  ConfigGraficos.ALTO_PANTALLA;
	
}
