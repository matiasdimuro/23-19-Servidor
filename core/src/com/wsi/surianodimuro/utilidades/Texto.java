package com.wsi.surianodimuro.utilidades;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.wsi.surianodimuro.interfaces.Dimensionable;

public class Texto implements Dimensionable {
	
	private BitmapFont fuente;
	private GlyphLayout layout;
	private Vector2 pos;
	private String texto;
	private final float ANCHO;
	private final float ALTO;
	
	/**
	 * Esta clase genera un texto en funcion de una fuente dada por el usuario.
	 * 
	 * @param ruta Direccion de la fuente en el directorio.
	 * @param texto Mensaje a generar.
	 * @param tamanio Dimensiones de la fuente.
	 * @param color Color del texto.
	 */

	public Texto(String ruta, String texto, int tamanio, Color color) {
	
		FreeTypeFontGenerator generador = new FreeTypeFontGenerator(Gdx.files.internal(ruta));
		FreeTypeFontParameter params = new FreeTypeFontParameter();
		
		this.layout = new GlyphLayout();
		this.pos = new Vector2();
		
		params.size = tamanio;
		params.color = color;

		this.texto = texto;
		this.fuente = generador.generateFont(params);
		layout.setText(fuente, texto);

		this.ANCHO = layout.width;
		this.ALTO = layout.height;
	}
	
	@Override
	public void renderizar() {
		fuente.draw(Utiles.batch, texto, pos.x, pos.y);
	}

	@Override
	public void liberarMemoria() {
		fuente.dispose();
	}
	
	@Override
	public Rectangle getRectangulo() {
		return new Rectangle(pos.x, pos.y, ANCHO, ALTO);
	}
	
	/* GETTERS & SETTERS */
	
	@Override
	public float[] getDimensiones() {
		float[] dimensiones = { ANCHO, ALTO };
		return dimensiones;
	}

	@Override
	public Vector2 getPosicion() {
		return pos;
	}
	
	public void setTexto(String texto) {
		
		this.texto = texto;
		layout.setText(fuente, texto);
//		this.alto = layout.height;
//		this.ancho = layout.width;
	}

	@Override
	public void setPosicion(float x, float y) {
		this.pos.x = x;
		this.pos.y = y;
	}
}
