package com.wsi.surianodimuro.pantallas.juego.hud.elementos;

import java.awt.Rectangle;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.wsi.surianodimuro.interfaces.InventarioListable;
import com.wsi.surianodimuro.interfaces.Renderizable;
import com.wsi.surianodimuro.utilidades.Globales;
import com.wsi.surianodimuro.utilidades.Imagen;
import com.wsi.surianodimuro.utilidades.Utiles;

public abstract class Inventario implements Renderizable {

	private Imagen contenedor;
	protected InventarioListable[] elementos;
	
	private Sprite seleccion;

	/**
	 * Contenedor con items dentro de el.
	 * 
	 * @param rutaImagen
	 * @param x
	 * @param y
	 * @param ANCHO
	 * @param ALTO
	 */

	public Inventario(String rutaImagen, float x, float y, float ANCHO, float ALTO, int cantListables) {
		
		contenedor = new Imagen(rutaImagen, x, y, ANCHO, ALTO);
		elementos = new InventarioListable[cantListables];
		
		seleccion = new Sprite(new Texture("hud/itemSeleccionado.png"));
	}

	@Override
	public void renderizar() {

		contenedor.renderizar();

		final int BORDE = 15;
		final int ESPACIADO = 25;
		
		final int ANCHO_TOTAL = (int) contenedor.getDimensiones()[0];
		final int ALTO_TOTAL = (int) contenedor.getDimensiones()[1];
		
		final int ANCHO_DISPONIBLE = ANCHO_TOTAL - BORDE * 2 - ESPACIADO * (elementos.length - 1);
		final int ALTO_DISPONIBLE = ALTO_TOTAL - BORDE * 2;
		
		final int ANCHO_ITEM = (int) ((ANCHO_DISPONIBLE / elementos.length) * 0.75f);
		final int ALTO_ITEM = (int) (ALTO_DISPONIBLE * 0.75f);
		
		final int ANCHO_OCUPADO = (ANCHO_ITEM * elementos.length) + (ESPACIADO * (elementos.length - 1));
		Rectangle subContenedor = new Rectangle(
				(int) ((contenedor.getPosicion().x + (contenedor.getDimensiones()[0] / 2)) - (ANCHO_OCUPADO / 2)),
				(int) (contenedor.getPosicion().y + BORDE), ANCHO_OCUPADO, ALTO_DISPONIBLE);
		
		for (int i = 0; i < elementos.length; i++) {

			InventarioListable elemento = elementos[i];


			elemento.setDimensiones(ANCHO_ITEM, ALTO_ITEM);
			elemento.setPosicion((float) subContenedor.getX() + ANCHO_ITEM * i + ESPACIADO * i, (float) (subContenedor.getY() + subContenedor.getHeight() / 2 - ALTO_ITEM / 2));
			elemento.renderizar();
			
			seleccion.setSize(ANCHO_ITEM, 2);
		}
		
		seleccion.setPosition(elementos[Globales.jugadores.get(0).armaEnUso].getPosicion().x, elementos[Globales.jugadores.get(0).armaEnUso].getPosicion().y - 7);
		seleccion.draw(Utiles.batch);
	}

	@Override
	public void liberarMemoria() {
		contenedor.liberarMemoria();
		for (InventarioListable elemento : elementos) {
			elemento.liberarMemoria();
		}
		seleccion.getTexture().dispose();
	}
}