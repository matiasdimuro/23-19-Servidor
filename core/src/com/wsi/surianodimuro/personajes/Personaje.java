package com.wsi.surianodimuro.personajes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.wsi.surianodimuro.interfaces.Dimensionable;
import com.wsi.surianodimuro.interfaces.Ente;

public abstract class Personaje implements Ente, Dimensionable {

	public int vida;
	public int velocidadX;
	
	protected float ESCALA_TAMANIO;
	protected float ANCHO;
	protected float ALTO;
	protected Vector2 posicion;
	
	protected String rutaSpritesheet;
	protected Sprite frameActual;
	
	protected Animation<Sprite> animCaminDerecha;
	protected Animation<Sprite> animCaminIzquierda;
	
	protected Sprite[] framesCaminDerecha;
	protected Sprite[] framesCaminIzquierda;
	
	protected float stateTime = 0;
	
	/**
	 * Define un personaje dentro del videojuego pudiendo ser Agente, Monstruo o Ninio.
	 * @param rutaSpritesheet Ruta relativa dentro del proyecto del spritesheet.
	 * @param ESCALA_TAMANIO Coeficiente que determina la escala del Sprite
	 * @param vida
	 */
	
	public Personaje(String rutaSpritesheet, float ESCALA_TAMANIO, int vida) {
		
		this.vida = vida;
		
		this.ESCALA_TAMANIO = ESCALA_TAMANIO;
		this.rutaSpritesheet = rutaSpritesheet;
		
		posicion = new Vector2();
		frameActual = new Sprite();
	}
	
	@Override
	public void moverseIzquierda() {
		posicion.x -= velocidadX * Gdx.graphics.getDeltaTime();
		setPosicion(posicion.x, posicion.y);
	}

	@Override
	public void moverseDerecha() {
		posicion.x += velocidadX * Gdx.graphics.getDeltaTime();
		setPosicion(posicion.x, posicion.y);
	}
	
	@Override
	public void caminarDerecha() {
		stateTime += Gdx.graphics.getDeltaTime();
		frameActual = animCaminDerecha.getKeyFrame(stateTime, true);
	}
	
	@Override
	public void caminarIzquierda() {
		stateTime += Gdx.graphics.getDeltaTime();
		frameActual = animCaminIzquierda.getKeyFrame(stateTime, true);
	}
	
	@Override
	public void restarVida() {
		vida = (vida > 0) ? vida -= 1 : 0;
	}
	
	@Override
	public void incrementarVelocidad() {}
	
	@Override
	public void renderizar() {}
	
	@Override
	public void liberarMemoria() {
		
		frameActual.getTexture().dispose();
		for (int i = 0; i < framesCaminDerecha.length; i++) {
			framesCaminDerecha[i].getTexture().dispose();
			framesCaminIzquierda[i].getTexture().dispose();
			animCaminDerecha.getKeyFrames()[i].getTexture().dispose();
			animCaminIzquierda.getKeyFrames()[i].getTexture().dispose();
		}
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
		frameActual.setPosition(x, y);
	}
	
	@Override
	public Rectangle getRectangulo() {
		return new Rectangle(frameActual.getX(), frameActual.getY(), frameActual.getWidth(), frameActual.getHeight());
	}
}
