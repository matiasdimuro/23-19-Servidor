package com.wsi.surianodimuro.pantallas.juego.hud.elementos;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.maps.MapProperties;
import com.wsi.surianodimuro.pantallas.juego.TiempoProcesos;
import com.wsi.surianodimuro.utilidades.Globales;
import com.wsi.surianodimuro.utilidades.Texto;

public final class ContenedorIndicadorOleada extends ContenedorGritos {

	private Texto numOleada;
	
	public ContenedorIndicadorOleada(float x, float y, float ANCHO, float ALTO, MapProperties indProps) {
		
		super("mapa/assets/hud/contenedor_gritos_large.png", x, y, ANCHO, ALTO, indProps);
		
		numOleada = new Texto("fuentes/8-BITWONDER.TTF", "Oleada\n  Num " + Globales.oleadaInfo.numOleada, 14, Color.BLACK);
		numOleada.setPosicion(x + 20, y + ALTO / 2 + numOleada.getDimensiones()[1] / 2);
	}
	
	@Override
	public void renderizar() {
		
		super.renderizar();
		numOleada.renderizar();
		
		if (Globales.oleadaInfo.tiempoTranscurrido <= TiempoProcesos.duracionOleada) {
			indicador.width = Globales.oleadaInfo.tiempoTranscurrido * valorMaxIndicador / TiempoProcesos.duracionOleada;			
		}
	}
	
	@Override
	public void liberarMemoria() {
		super.liberarMemoria();
		numOleada.liberarMemoria();
	}
	
	@Override
	public void actualizarDatos() {
		numOleada.setTexto("Oleada\n  Num " + Globales.oleadaInfo.numOleada);
	}
}
