package com.wsi.surianodimuro.pantallas.juego.hud;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.MapProperties;
import com.wsi.surianodimuro.enumeradores.Mensajes;
import com.wsi.surianodimuro.pantallas.juego.hud.elementos.ContenedorIndicadorGrito;
import com.wsi.surianodimuro.pantallas.juego.hud.elementos.ContenedorIndicadorOleada;
import com.wsi.surianodimuro.pantallas.juego.hud.elementos.IndicadorEscapesMonstruos;
import com.wsi.surianodimuro.pantallas.juego.hud.elementos.IndicadorEscapesNinios;
import com.wsi.surianodimuro.pantallas.juego.hud.elementos.InventarioArmamento;
import com.wsi.surianodimuro.utilidades.ConfigGraficos;
import com.wsi.surianodimuro.utilidades.Texto;

public abstract class Hud {

	private Texto cajaMensajes;

	private ContenedorIndicadorOleada indicadorOleada;
	private ContenedorIndicadorGrito indicadorGrito;

	private InventarioArmamento inventarioArmamento;

	private IndicadorEscapesMonstruos indicadorEscMonstruos;
	private IndicadorEscapesNinios indicadorEscNinios;

	public Hud(MapObjects elemsHud) {

		MapProperties propsCajaDeMensajes = elemsHud.get("caja_de_mensajes").getProperties();
		cajaMensajes = new Texto("fuentes/8-BITWONDER.TTF", Mensajes.PREVIA_OLEADA.getMensaje(), 20, Color.WHITE);
		cajaMensajes.setPosicion(Float.parseFloat(propsCajaDeMensajes.get("x").toString()),
				ConfigGraficos.ALTO_MAPA - Float.parseFloat(propsCajaDeMensajes.get("y").toString())
						+ Float.parseFloat(propsCajaDeMensajes.get("alto").toString()));

		MapProperties propsInvArmamento = elemsHud.get("armamento").getProperties();
		inventarioArmamento = new InventarioArmamento(Float.parseFloat(propsInvArmamento.get("x").toString()),
				ConfigGraficos.ALTO_MAPA - Float.parseFloat(propsInvArmamento.get("y").toString()),
				Float.parseFloat(propsInvArmamento.get("ancho").toString()),
				Float.parseFloat(propsInvArmamento.get("alto").toString()));

		MapProperties propsIndOleada = elemsHud.get("contenedor_gritos_lg").getProperties();
		indicadorOleada = new ContenedorIndicadorOleada(Float.parseFloat(propsIndOleada.get("x").toString()),
				ConfigGraficos.ALTO_MAPA - Float.parseFloat(propsIndOleada.get("y").toString()),
				Float.parseFloat(propsIndOleada.get("ancho").toString()),
				Float.parseFloat(propsIndOleada.get("alto").toString()),
				elemsHud.get("indicador_contenedor_gritos_lg").getProperties());

		MapProperties propsIndGritos = elemsHud.get("contenedor_gritos_sm").getProperties();
		indicadorGrito = new ContenedorIndicadorGrito(Float.parseFloat(propsIndGritos.get("x").toString()),
				ConfigGraficos.ALTO_MAPA - Float.parseFloat(propsIndGritos.get("y").toString()),
				Float.parseFloat(propsIndGritos.get("ancho").toString()),
				Float.parseFloat(propsIndGritos.get("alto").toString()),
				elemsHud.get("indicador_contenedor_gritos_sm").getProperties());

		MapProperties propsIndEscMons = elemsHud.get("escape_mons_1").getProperties();
		indicadorEscMonstruos = new IndicadorEscapesMonstruos(Float.parseFloat(propsIndEscMons.get("x").toString()),
				ConfigGraficos.ALTO_MAPA - Float.parseFloat(propsIndEscMons.get("y").toString()),
				Float.parseFloat(propsIndEscMons.get("ancho").toString()),
				Float.parseFloat(propsIndEscMons.get("alto").toString()));

		MapProperties propsIndEscNinios = elemsHud.get("escape_nin_1").getProperties();
		indicadorEscNinios = new IndicadorEscapesNinios(Float.parseFloat(propsIndEscNinios.get("x").toString()),
				ConfigGraficos.ALTO_MAPA - (Float.parseFloat(propsIndEscNinios.get("y").toString())),
				Float.parseFloat(propsIndEscNinios.get("ancho").toString()),
				Float.parseFloat(propsIndEscNinios.get("alto").toString()));
	}

	public void renderizar() {

		cajaMensajes.renderizar();
		inventarioArmamento.renderizar();
		indicadorOleada.renderizar();
		indicadorGrito.renderizar();
		indicadorEscMonstruos.renderizar();
		indicadorEscNinios.renderizar();
	}

	public void liberarMemoria() {

		cajaMensajes.liberarMemoria();
		inventarioArmamento.liberarMemoria();
		indicadorOleada.liberarMemoria();
		indicadorGrito.liberarMemoria();
		indicadorEscMonstruos.liberarMemoria();
		indicadorEscNinios.liberarMemoria();
	}

	public Texto getCajaMensajes() {
		return cajaMensajes;
	}
	
	public InventarioArmamento getInventarioArmamento() {
		return inventarioArmamento;
	}

	public ContenedorIndicadorOleada getIndicadorOleada() {
		return indicadorOleada;
	}

	public ContenedorIndicadorGrito getIndicadorGrito() {
		return indicadorGrito;
	}

	public IndicadorEscapesMonstruos getIndicadorEscMonstruos() {
		return indicadorEscMonstruos;
	}

	public IndicadorEscapesNinios getIndicadorEscNinios() {
		return indicadorEscNinios;
	}
}
