package com.wsi.surianodimuro.pantallas.juego.hud.elementos;

import com.wsi.surianodimuro.enumeradores.Infectados;

public class IndicadorEscapesMonstruos extends IndicadorEscapes {

	public IndicadorEscapesMonstruos(float posEmojiX, float posEmojiY, float ANCHO, float ALTO) {
		super("mapa/assets/hud/monster_face.png", "mapa/assets/hud/no_monster_face.png", posEmojiX, posEmojiY, ANCHO, ALTO);
		sujetoDeEscape = Infectados.MONSTRUO;
	}
}
