package com.wsi.surianodimuro.pantallas.juego.hud;

import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.MapProperties;
import com.wsi.surianodimuro.pantallas.juego.hud.elementos.IndicadorVidas;
import com.wsi.surianodimuro.utilidades.ConfigGraficos;

public final class HudUnJug extends Hud {

	private IndicadorVidas indicadorVidasJugUno;
	
	public HudUnJug(MapObjects elemsHud) {
		
		super(elemsHud);
		
		MapProperties propsIndVidas = elemsHud.get("emoji_pj1").getProperties();
		indicadorVidasJugUno = new IndicadorVidas(Float.parseFloat(propsIndVidas.get("x").toString()),
				ConfigGraficos.ALTO_MAPA - Float.parseFloat(propsIndVidas.get("y").toString()),
				Float.parseFloat(propsIndVidas.get("ancho").toString()),
				Float.parseFloat(propsIndVidas.get("alto").toString()));
	}
	
	public void renderizar() {
		super.renderizar();
		indicadorVidasJugUno.renderizar();
	}

	public void liberarMemoria() {
		super.liberarMemoria();
		indicadorVidasJugUno.liberarMemoria();
	}

	public IndicadorVidas getIndicadorVidasJugUno() {
		return indicadorVidasJugUno;
	}
}
