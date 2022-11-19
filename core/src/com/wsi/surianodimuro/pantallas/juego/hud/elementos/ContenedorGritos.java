package com.wsi.surianodimuro.pantallas.juego.hud.elementos;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.math.Rectangle;
import com.wsi.surianodimuro.interfaces.Renderizable;
import com.wsi.surianodimuro.utilidades.ConfigGraficos;
import com.wsi.surianodimuro.utilidades.Imagen;
import com.wsi.surianodimuro.utilidades.Utiles;

public abstract class ContenedorGritos implements Renderizable {

	private Imagen imagen;
	
	protected float valorMaxIndicador;
	protected Rectangle indicador;
	
	/**
	 * Indicador que aumenta dada una circunstancia (puntos, nivel avanzado, etc)
	 * 
	 * @param rutaImagen
	 * @param x
	 * @param y
	 * @param ANCHO
	 * @param ALTO
	 */
	
	public ContenedorGritos(String rutaImagen, float x, float y, float ANCHO, float ALTO, MapProperties indProps) {
		
		imagen = new Imagen(rutaImagen, x, y, ANCHO, ALTO);
		
		float anchoInd = Float.parseFloat(indProps.get("ancho").toString());
		float altoInd = Float.parseFloat(indProps.get("alto").toString());
		float xInd = Float.parseFloat(indProps.get("x").toString());
		float yInd = ConfigGraficos.ALTO_MAPA - Float.parseFloat(indProps.get("y").toString());
		
		valorMaxIndicador = anchoInd;
		indicador = new Rectangle(xInd, yInd, 0, altoInd);
	}

	@Override
	public void renderizar() {
		imagen.renderizar();
	}
	
	public void mostrarIndicador() {
		
		Utiles.shapeRender.begin(ShapeType.Filled);
		Utiles.shapeRender.setColor(Color.RED);
		Utiles.shapeRender.rect(indicador.getX() / ConfigGraficos.COCIENTE_ENTRE_ANCHOS, (indicador.getY() / ConfigGraficos.COCIENTE_ENTRE_ALTOS) + 5, indicador.getWidth() / ConfigGraficos.COCIENTE_ENTRE_ANCHOS, indicador.getHeight() / ConfigGraficos.COCIENTE_ENTRE_ALTOS);
		Utiles.shapeRender.end();
	}

	@Override
	public void liberarMemoria() {
		imagen.liberarMemoria();
	}
	
	public void actualizarDatos() {}
}
