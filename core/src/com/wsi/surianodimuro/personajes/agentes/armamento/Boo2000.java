package com.wsi.surianodimuro.personajes.agentes.armamento;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.wsi.surianodimuro.enumeradores.Armamentos;
import com.wsi.surianodimuro.enumeradores.Proyectiles;

public final class Boo2000 extends Arma {

	public Boo2000() {
		super("objetos/armamento/boo2000.png", Armamentos.BOO2000, 1, Proyectiles.PROYECTIL_BOO2000);
	}
	
	@Override
	protected void cargarSpritesheet(String rutaSprite) {
		
		final int ANCHO_FRAME = 294;
		final int ALTO_FRAME = 114;
		
		Texture spritesheet = new Texture(rutaSprite);
		TextureRegion[][] frames = TextureRegion.split(spritesheet, ANCHO_FRAME, ALTO_FRAME);
		
		imagen = new Sprite(frames[0][0]);
	}
}
