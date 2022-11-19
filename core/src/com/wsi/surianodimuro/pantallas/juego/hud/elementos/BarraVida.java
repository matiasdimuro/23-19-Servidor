package com.wsi.surianodimuro.pantallas.juego.hud.elementos;

import java.awt.Rectangle;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.wsi.surianodimuro.personajes.Infectado;
import com.wsi.surianodimuro.utilidades.ConfigGraficos;
import com.wsi.surianodimuro.utilidades.Utiles;

public class BarraVida {

	private Rectangle contenedor;
	private Rectangle indicador;

	private int vidasIniciales;
	private int anchoInicial;
	private Infectado infectado;
	
	private int ancho;
	private int alto;
	public int x;
	public int y;
	
	public BarraVida(Infectado infectado) {
	
		ancho = (int) infectado.getDimensiones()[0];
		alto = 8;

		this.vidasIniciales = infectado.vida;
		this.anchoInicial = ancho;
		this.infectado = infectado;
		
		contenedor = new Rectangle(x, y, ancho, alto);
		indicador = new Rectangle(x, y, ancho, alto);
	}
	
	public void renderizar() {
		
		x = (int) infectado.getPosicion().x;
		y = (int) (infectado.getPosicion().y + infectado.getDimensiones()[1]);
		
		contenedor.x = x;
		contenedor.y = y;
		indicador.x = x;
		indicador.y = y;
		indicador.width = (infectado.vida * anchoInicial / vidasIniciales);
		
		 mostrarContenedor();
		 mostrarIndicador();
	}
	
	private void mostrarContenedor() {
		
		Utiles.shapeRender.begin(ShapeType.Line);
		Utiles.shapeRender.setColor(Color.RED);
		Utiles.shapeRender.rect((float) contenedor.getX() / ConfigGraficos.COCIENTE_ENTRE_ANCHOS, (float) (contenedor.getY() / ConfigGraficos.COCIENTE_ENTRE_ALTOS) + 5, (float) contenedor.getWidth() / ConfigGraficos.COCIENTE_ENTRE_ANCHOS, (float) contenedor.getHeight() / ConfigGraficos.COCIENTE_ENTRE_ALTOS);
		Utiles.shapeRender.end();
	}
	
	private void mostrarIndicador() {
		
		Utiles.shapeRender.begin(ShapeType.Filled);
		Utiles.shapeRender.setColor(Color.RED);
		Utiles.shapeRender.rect((float) indicador.getX() / ConfigGraficos.COCIENTE_ENTRE_ANCHOS, (float) (indicador.getY() / ConfigGraficos.COCIENTE_ENTRE_ALTOS) + 5, (float) indicador.getWidth() / ConfigGraficos.COCIENTE_ENTRE_ANCHOS, (float) indicador.getHeight() / ConfigGraficos.COCIENTE_ENTRE_ALTOS);
		Utiles.shapeRender.end();
	}
}
