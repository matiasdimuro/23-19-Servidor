package com.wsi.surianodimuro.objetos;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.wsi.surianodimuro.enumeradores.Ascensores;
import com.wsi.surianodimuro.interfaces.Dimensionable;
import com.wsi.surianodimuro.utilidades.Utiles;

public final class Ascensor implements Dimensionable {

	private Sprite frameActual;

	private Ascensores tipo;
	private Ascensores abajo;
	private Ascensores arriba;
	
	/**
	 * Objeto que permite pasar de plataforma en plataforma de forma vertical.
	 * @param x
	 * @param y
	 * @param ANCHO
	 * @param ALTO
	 * @param tipo
	 * @param arriba Ascensor destino superior
	 * @param abajo Ascensor destino inferior
	 */
	
	public Ascensor(float x, float y, float ANCHO, float ALTO, Ascensores tipo, Ascensores arriba, Ascensores abajo) {

		frameActual = new Sprite(new Texture("objetos/puertas/ascensor/puerta_plataforma_abierta.png"));
		frameActual.setSize(ANCHO, ALTO);
		frameActual.setPosition(x, y);

		this.tipo = tipo;
		this.arriba = arriba;
		this.abajo = abajo;
	}

	@Override
	public void renderizar() {
		frameActual.draw(Utiles.batch);
	}

	@Override
	public void liberarMemoria() {
		frameActual.getTexture().dispose();
	}

	@Override
	public float[] getDimensiones() {
		float[] dimensiones = { frameActual.getWidth(), frameActual.getHeight() };
		return dimensiones;
	}

	@Override
	public Vector2 getPosicion() {
		return new Vector2(frameActual.getX(), frameActual.getY());
	}

	@Override
	public void setPosicion(float x, float y) {
		frameActual.setPosition(x, y);
	}

	@Override
	public Rectangle getRectangulo() {
		return new Rectangle(frameActual.getX(), frameActual.getY(), frameActual.getWidth(), frameActual.getHeight());
	}
	
	public Ascensores getTipo() {
		return tipo;
	}
	
	public Ascensores getArriba() {
		return arriba;
	}
	
	public Ascensores getAbajo() {
		return abajo;
	}
}