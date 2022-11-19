package com.wsi.surianodimuro.pantallas.juego.hud.elementos;

import com.wsi.surianodimuro.enumeradores.Infectados;

public class IndicadorEscapesNinios extends IndicadorEscapes {

	public IndicadorEscapesNinios(float posEmojiX, float posEmojiY, float ANCHO, float ALTO) {
		super("mapa/assets/hud/child_face.png", "mapa/assets/hud/no_child_face.png", posEmojiX, posEmojiY, ANCHO, ALTO);
		sujetoDeEscape = Infectados.NINIO;
	}
}
