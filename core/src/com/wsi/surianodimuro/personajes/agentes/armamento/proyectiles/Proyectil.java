package com.wsi.surianodimuro.personajes.agentes.armamento.proyectiles;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.wsi.surianodimuro.enumeradores.Proyectiles;
import com.wsi.surianodimuro.interfaces.Dimensionable;
import com.wsi.surianodimuro.utilidades.Imagen;

public abstract class Proyectil implements Dimensionable {

	protected Proyectiles tipo;
	private Imagen imagen;

	public Proyectil(String rutaSprite, float ANCHO, float ALTO) {
		imagen = new Imagen(rutaSprite, 0, 0, ANCHO, ALTO);
	}

	@Override
	public void renderizar() {
		imagen.renderizar();
	}

	@Override
	public void liberarMemoria() {
		imagen.liberarMemoria();
	}

	@Override
	public float[] getDimensiones() {
		float[] dimensiones = { imagen.getDimensiones()[0], imagen.getDimensiones()[1] };
		return dimensiones;
	}

	@Override
	public Vector2 getPosicion() {
		return new Vector2(imagen.getPosicion().x, imagen.getPosicion().y);
	}

	@Override
	public void setPosicion(float x, float y) {
		imagen.setPosicion(x, y);
	}

	@Override
	public Rectangle getRectangulo() {
		return new Rectangle(imagen.getPosicion().x, imagen.getPosicion().y, imagen.getDimensiones()[0],
				imagen.getDimensiones()[1]);
	}
	
	public Proyectiles getTipo() {
		return tipo;
	}
}
