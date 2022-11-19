package com.wsi.surianodimuro.personajes;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.wsi.surianodimuro.enumeradores.Infectados;
import com.wsi.surianodimuro.enumeradores.Proyectiles;
import com.wsi.surianodimuro.pantallas.juego.hud.elementos.BarraVida;
import com.wsi.surianodimuro.utilidades.Utiles;

public abstract class Infectado extends Personaje {

	protected Infectados tipo;
	protected Proyectiles debilidad;
	
	public ControladorNpc controlador;
	
	private BarraVida indicadorVida;
	
	public Infectado(String rutaSpritesheet, float ESCALA_TAMANIO, int vida, float ANCHO_SPRITESHEET, float ALTO_SPRITESHEET, int FILAS, int COLUMNAS) {
		
		super(rutaSpritesheet, ESCALA_TAMANIO, vida);
		
		velocidadX = 100;
		
		cargarSpriteSheet(ANCHO_SPRITESHEET, ALTO_SPRITESHEET, FILAS, COLUMNAS);
		
		animCaminDerecha = new Animation<Sprite>(0.1f, framesCaminDerecha);
		animCaminIzquierda = new Animation<Sprite>(0.1f, framesCaminIzquierda);
		
		controlador = new ControladorNpc();
		controlador.mirandoDerecha = true;
		frameActual.set(framesCaminDerecha[0]);
		
		indicadorVida = new BarraVida(this);
	}
	
	@Override
	public void renderizar() {
	
		super.renderizar();
		
		setPosicion(posicion.x, posicion.y);
		frameActual.draw(Utiles.batch);
	}

	@Override
	public void cargarSpriteSheet(float ANCHO_SPRITESHEET, float ALTO_SPRITESHEET, int FILAS, int COLUMNAS) {
		
		final float ANCHO_FRAMES = ANCHO_SPRITESHEET / COLUMNAS;
		final float ALTO_FRAMES = ALTO_SPRITESHEET / FILAS;
		
		TextureRegion[][] frames = TextureRegion.split(new Texture(this.rutaSpritesheet), (int) ANCHO_FRAMES, (int) ALTO_FRAMES);
		
		ANCHO = ESCALA_TAMANIO * ANCHO_FRAMES;
		ALTO = ESCALA_TAMANIO * ALTO_FRAMES;
		
		framesCaminDerecha = new Sprite[COLUMNAS];
		framesCaminIzquierda = new Sprite[COLUMNAS];
		
		int indice = 0;
		for (int i = 0; i < framesCaminIzquierda.length; i++) {
			
			framesCaminIzquierda[indice] = new Sprite(frames[1][i]);
			framesCaminDerecha[indice] = new Sprite(frames[0][i]);
			
			framesCaminIzquierda[indice].setSize(ANCHO, ALTO);
			framesCaminDerecha[indice++].setSize(ANCHO, ALTO);
		}
	}
	
	public Proyectiles getDebilidad() {
		return debilidad;
	}
	
	public BarraVida getIndicadorVida() {
		return indicadorVida;
	}
	
	public Infectados getTipo() {
		return tipo;
	}
}
