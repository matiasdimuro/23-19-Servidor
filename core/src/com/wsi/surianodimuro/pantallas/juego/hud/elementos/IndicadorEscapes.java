package com.wsi.surianodimuro.pantallas.juego.hud.elementos;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.wsi.surianodimuro.enumeradores.Infectados;
import com.wsi.surianodimuro.interfaces.Renderizable;
import com.wsi.surianodimuro.utilidades.Globales;
import com.wsi.surianodimuro.utilidades.Imagen;

public abstract class IndicadorEscapes implements Renderizable {

	private Imagen[] emojis = new Imagen[3];
	private String[] rutaSprites;
	
	protected Infectados sujetoDeEscape;
	
	public IndicadorEscapes(String rutaEmojiUno, String rutaEmojiDos, float posEmojiX, float posEmojiY, float ANCHO, float ALTO) {
		
		rutaSprites = new String[2];
		rutaSprites[0] = rutaEmojiUno;
		rutaSprites[1] = rutaEmojiDos;
		
		final int ESPACIO = 10;
		
		for (int i = 0; i < emojis.length; i++) {
			emojis[i] = new Imagen(rutaSprites[0], posEmojiX + (ANCHO + ESPACIO) * (i + 1), posEmojiY, ANCHO, ALTO);
		}
	}

	@Override
	public void renderizar() {
		for (Imagen emoji : emojis) {
			emoji.renderizar();
		}
	}

	@Override
	public void liberarMemoria() {
		for (Imagen emoji : emojis) {
			emoji.liberarMemoria();
		}
	}
	
	public void actualizar() {
		
		for (int i = 0; i < emojis.length; i++) {

			float x = emojis[i].getPosicion().x;
			float y = emojis[i].getPosicion().y;
			float ancho = emojis[i].getDimensiones()[0];
			float alto = emojis[i].getDimensiones()[1];
			
			if (sujetoDeEscape == Infectados.MONSTRUO) {
				emojis[i].sprite.set(new Sprite(new Texture((i < Globales.datosPartida.escapesRestantesMonstruos) ? rutaSprites[0] : rutaSprites[1])));				
			}

			else if (sujetoDeEscape == Infectados.NINIO) {
				emojis[i].sprite.set(new Sprite(new Texture((i < Globales.datosPartida.escapesRestantesNinios) ? rutaSprites[0] : rutaSprites[1])));				
			}
			
			emojis[i].sprite.setX(x);
			emojis[i].sprite.setY(y);
			emojis[i].sprite.setSize(ancho, alto);
		}
	}
}
