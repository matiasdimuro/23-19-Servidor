package com.wsi.surianodimuro.pantallas.juego.hud.elementos;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.wsi.surianodimuro.interfaces.Renderizable;
import com.wsi.surianodimuro.utilidades.Globales;
import com.wsi.surianodimuro.utilidades.Imagen;

public class IndicadorVidas implements Renderizable {

	private Imagen emoji;
	private Imagen[] vidas = new Imagen[3];

	private String[] rutaSprites = { "mapa/assets/hud/heart.png", "mapa/assets/hud/no_heart.png" };

	public IndicadorVidas(float posEmojiX, float posEmojiY, float ANCHO, float ALTO) {

		emoji = new Imagen("mapa/assets/hud/cda_1_face.png", posEmojiX, posEmojiY, ANCHO, ALTO);

		final int ESPACIO = 10;

		for (int i = 0; i < vidas.length; i++) {
			vidas[i] = new Imagen((i < Globales.jugadores.get(0).vida) ? rutaSprites[0] : rutaSprites[1],
					posEmojiX + (ANCHO + ESPACIO) * (i + 1), posEmojiY, ANCHO, ALTO);
		}
	}

	@Override
	public void renderizar() {

		emoji.renderizar();
		for (Imagen vida : vidas) {
			vida.renderizar();
		}
	}

	@Override
	public void liberarMemoria() {
		emoji.liberarMemoria();
		for (Imagen vida : vidas) {
			vida.liberarMemoria();
		}
	}

	public void actualizar() {

		for (int i = 0; i < vidas.length; i++) {

			float x = vidas[i].getPosicion().x;
			float y = vidas[i].getPosicion().y;
			float ancho = vidas[i].getDimensiones()[0];
			float alto = vidas[i].getDimensiones()[1];

			vidas[i].sprite
					.set(new Sprite(new Texture((i < Globales.jugadores.get(0).vida) ? rutaSprites[0] : rutaSprites[1])));

			vidas[i].sprite.setX(x);
			vidas[i].sprite.setY(y);
			vidas[i].sprite.setSize(ancho, alto);
		}
	}
}
