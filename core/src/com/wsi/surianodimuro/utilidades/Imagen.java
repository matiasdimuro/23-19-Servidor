package com.wsi.surianodimuro.utilidades;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.wsi.surianodimuro.interfaces.Dimensionable;

public class Imagen implements Dimensionable {

	private float x;
	private float y;
	private final float ANCHO;
	private final float ALTO;
	public Sprite sprite;

	/**
	 * Crea un Sprite a partir de un recurso gráfico, reuniendo
	 * metodos y atributos propios y adicionales.
	 * 
	 * @param ruta Direccion de la textura dentro del directorio.
	 * @param x Posicion en el eje de coordenadas "x".
	 * @param y Posicion en el eje de coordenadas "y".
	 * @param WIDTH El ancho que adoptará la imagen.
	 * @param HEIGHT El alto que adoptará la imagen.
	 * 
	 * @see Sprite
	 */
	
	public Imagen(String ruta, float x, float y, float WIDTH, float HEIGHT) {
		
		this.ANCHO = WIDTH;
		this.ALTO = HEIGHT;
		
		this.x = x;
		this.y = y;
		
		sprite = new Sprite(new Texture(ruta));
		sprite.setSize(WIDTH, HEIGHT);
		sprite.setPosition(x, y);
	}

	@Override
	public void renderizar() {
		sprite.draw(Utiles.batch);
	}
	
	@Override
	public void liberarMemoria() {
		sprite.getTexture().dispose();
	}
	
	@Override
	public Rectangle getRectangulo() {
		return new Rectangle(x, y, ANCHO, ALTO);
	}
	
	/* METODOS UTILES */
	
	public void cambiarAlpha(float a) {
		sprite.setAlpha(a);
	}
	
	/* GETTERS & SETTERS */
	
	@Override
	public float[] getDimensiones() {
		
		float[] dimensiones = { ANCHO, ALTO };
		return dimensiones;
	}

	@Override
	public Vector2 getPosicion() {
		return new Vector2(x, y);
	}
	
	public Texture getTextura() {
		return sprite.getTexture();
	}

	public void setPosicion(float x, float y) {
		this.x = x;
		this.y = y;
		sprite.setPosition(x, y);
	}
}

