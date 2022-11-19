package com.wsi.surianodimuro.objetos;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.wsi.surianodimuro.interfaces.Dimensionable;
import com.wsi.surianodimuro.utilidades.Utiles;

public abstract class Objeto implements Dimensionable {
	
	private Sprite imagen;
	private Vector2 posicion;
	
	private float ANCHO;
	private float ALTO;
	
	/**
	 * Los objetos abarcan elementos generales, de los cuales determina sus propiedades graficas y de renderizado.
	 * @param rutaSprite Ruta dentro del proyecto de la imagen.
	 * @param x
	 * @param y
	 * @param ANCHO
	 * @param ALTO
	 */
	public Objeto(String rutaSprite, float x, float y, float ANCHO, float ALTO) {
		
		imagen = new Sprite(new Texture(rutaSprite));
		imagen.setSize(ANCHO, ALTO);
		posicion = new Vector2();
		this.setPosicion(x, y);
		this.ANCHO = ANCHO;
		this.ALTO = ALTO;
	}

	@Override
	public void renderizar() {
		imagen.draw(Utiles.batch);
	}

	@Override
	public void liberarMemoria() {
		imagen.getTexture().dispose();
	}

	@Override
	public float[] getDimensiones() {
		float[] dimensiones = { ANCHO, ALTO };
		return dimensiones;
	}

	@Override
	public Vector2 getPosicion() {
		return posicion;
	}

	@Override
	public void setPosicion(float x, float y) {
		posicion.x = x;
		posicion.y = y;
		imagen.setPosition(x, y);
	}

	@Override
	public Rectangle getRectangulo() {
		return new Rectangle(posicion.x, posicion.y, ANCHO, ALTO);
	}
}
