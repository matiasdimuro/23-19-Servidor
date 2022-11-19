package com.wsi.surianodimuro.pantallas.juego.hud.elementos;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.maps.MapProperties;
import com.wsi.surianodimuro.utilidades.Globales;
import com.wsi.surianodimuro.utilidades.Texto;

public final class ContenedorIndicadorGrito extends ContenedorGritos {
	
	private Texto sustoPuntos;
	private Texto minPuntosParaUlti;

	public ContenedorIndicadorGrito(float x, float y, float ANCHO, float ALTO, MapProperties indProps) {
		
		super("mapa/assets/hud/contenedor_gritos.png", x, y, ANCHO, ALTO, indProps);
	
		sustoPuntos = new Texto("fuentes/8-BITWONDER.TTF", "Puntos: " + Integer.toString(Globales.jugadores.get(0).getSustoPuntos()), 14, Color.BLACK);
		sustoPuntos.setPosicion((x + ANCHO / 2) - (sustoPuntos.getDimensiones()[0] / 2), y + 10 + sustoPuntos.getDimensiones()[1]);
		
		minPuntosParaUlti = new Texto("fuentes/8-BITWONDER.TTF", "Ultimate: " + Globales.oleadaInfo.GRITOS_ULTIMATE, 14, Color.BLACK);
		minPuntosParaUlti.setPosicion(x + ANCHO / 2 - minPuntosParaUlti.getDimensiones()[0] / 2, (y + ALTO) - minPuntosParaUlti.getDimensiones()[1]);
	}
	
	@Override
	public void renderizar() {
		
		super.renderizar();
		sustoPuntos.renderizar();
		minPuntosParaUlti.renderizar();
		
		if (Globales.jugadores.get(0).getSustoPuntos() <= Globales.oleadaInfo.GRITOS_ULTIMATE) {
			indicador.width = Globales.jugadores.get(0).getSustoPuntos() * valorMaxIndicador / Globales.oleadaInfo.GRITOS_ULTIMATE;
		}			
	}
	
	@Override
	public void liberarMemoria() {
		super.liberarMemoria();
		sustoPuntos.liberarMemoria();
		minPuntosParaUlti.liberarMemoria();
	}
	
	@Override
	public void actualizarDatos() {
		sustoPuntos.setTexto("Puntos: " + Integer.toString(Globales.jugadores.get(0).getSustoPuntos()));
	}
}
