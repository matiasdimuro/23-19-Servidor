package com.wsi.surianodimuro.utilidades;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

public abstract class Helpers {
	
	/**
	 * Se encarga de limpiar la pantalla luego de cada renderizado en la misma.
	 * A su vez, pinta la misma con un color dado por el usuario de formato RGBA.
	 * 
	 * @param rojo Valor del color rojo (0 - 1).
	 * @param verde Valor del color verde (0 - 1).
	 * @param azul Valor del color azul (0 - 1).
	 * @param alfa Valor del alfa (0 - 1).
	 */
	
	public static void limpiarPantalla(float rojo, float verde, float azul, float alfa) {
		
		Gdx.gl.glClearColor(rojo, verde, azul, alfa);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}
	
	/**
	 * Da por finalizada la ejecución del programa.
	 */
	
	public static void cerrarJuego() {
		Gdx.app.exit();
	}
}
