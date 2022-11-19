package com.wsi.surianodimuro.personajes.agentes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.wsi.surianodimuro.enumeradores.DireccionesDisparo;
import com.wsi.surianodimuro.interfaces.comportamiento.PersonajeJugable;
import com.wsi.surianodimuro.personajes.ControladorPj;
import com.wsi.surianodimuro.personajes.Personaje;
import com.wsi.surianodimuro.personajes.agentes.armamento.Arma;
import com.wsi.surianodimuro.personajes.agentes.armamento.Boo2000;
import com.wsi.surianodimuro.personajes.agentes.armamento.Desintox;
import com.wsi.surianodimuro.personajes.agentes.armamento.proyectiles.ProyectilDisparado;
import com.wsi.surianodimuro.personajes.agentes.armamento.proyectiles.Ultimate;
import com.wsi.surianodimuro.utilidades.Globales;
import com.wsi.surianodimuro.utilidades.Utiles;

public abstract class Agente extends Personaje implements PersonajeJugable {

	/* ====== ATRIBUTOS ====== */

	private int sustoPuntos;

	/* ====== OBJETOS EQUIPABLES ====== */

	private Arma[] armamento;
	public int armaEnUso;

	/* ====== ANIMACIONES & FRAMES ====== */

	private Animation<Sprite> animDisparaDerecha;
	private Animation<Sprite> animDisparaIzquierda;
	private Animation<Sprite> animMareado;
	private Animation<Sprite> animNoqueado;

	protected Sprite[] framesDisparaDerecha;
	protected Sprite[] framesDisparaIzquierda;
	protected Sprite[] framesMareado;
	protected Sprite[] framesNoqueado;

	public ControladorPj controlador;

	public Agente(String rutaSpritesheet) {

		super(rutaSpritesheet, 1.5f, 3);

		velocidadX = 150;

		sustoPuntos = 0;

		armamento = new Arma[2];
		armamento[0] = new Boo2000();
		armamento[1] = new Desintox();

		armaEnUso = 0;

		cargarSpriteSheet(304, 378, 7, 8);

		animCaminDerecha = new Animation<Sprite>(0.13f, framesCaminDerecha);
		animCaminIzquierda = new Animation<Sprite>(0.13f, framesCaminIzquierda);
		animDisparaDerecha = new Animation<Sprite>(0.1f, framesDisparaDerecha);
		animDisparaIzquierda = new Animation<Sprite>(0.1f, framesDisparaIzquierda);
		animMareado = new Animation<Sprite>(0.1f, framesMareado);
		animNoqueado = new Animation<Sprite>(0.1f, framesNoqueado);

		controlador = new ControladorPj();
		controlador.mirandoIzquierda = true;
		frameActual.set(framesCaminIzquierda[0]);
	}

	@Override
	public void renderizar() {

		super.renderizar();
		if (controlador.caminando) {
			if (controlador.mirandoIzquierda) {
				caminarIzquierda();					
				if (controlador.disparando) {
					frameActual = animDisparaIzquierda.getKeyFrame(stateTime, true);
				}
			} else {
				caminarDerecha();					
				if (controlador.disparando) {
					frameActual = animDisparaDerecha.getKeyFrame(stateTime, true);
				}
			}
		}
		
		else if (controlador.disparando) {
			frameActual = ((controlador.mirandoIzquierda) ? framesDisparaIzquierda[0] : framesDisparaDerecha[0]);
		}

		else {
			frameActual = ((controlador.mirandoIzquierda) ? framesCaminIzquierda[0] : framesCaminDerecha[0]);
		}

		setPosicion(posicion.x, posicion.y);
		frameActual.draw(Utiles.batch);
	}

	@Override
	public void liberarMemoria() {

		super.liberarMemoria();

		for (int i = 0; i < framesDisparaDerecha.length; i++) {
			framesDisparaDerecha[i].getTexture().dispose();
			framesDisparaIzquierda[i].getTexture().dispose();
			animDisparaDerecha.getKeyFrames()[i].getTexture().dispose();
			animDisparaIzquierda.getKeyFrames()[i].getTexture().dispose();
		}

		for (int i = 0; i < framesNoqueado.length; i++) {
			framesNoqueado[i].getTexture().dispose();
			framesMareado[i].getTexture().dispose();
			animNoqueado.getKeyFrames()[i].getTexture().dispose();
			animMareado.getKeyFrames()[i].getTexture().dispose();
		}
	}

	@Override
	public void caminarDerecha() {
		super.caminarDerecha();
	}

	@Override
	public void caminarIzquierda() {
		super.caminarIzquierda();
	}

	@Override
	public void usarAscensor(float x, float y) {

		setPosicion(x, y);

		controlador.puedeDisparar = false;
		controlador.puedeInfectarse = false;
		controlador.caminando = false;
	}

	@Override
	public void dejarAscensor() {
		controlador.puedeDisparar = true;
		controlador.puedeInfectarse = true;
	}

	@Override
	public void dispararProyectil() {

		float x = (controlador.mirandoIzquierda) ? posicion.x : posicion.x + getDimensiones()[0];
		float y = posicion.y + getDimensiones()[1] / 2;

		Globales.proyectilesDisparados.add(new ProyectilDisparado(
				getArmamento()[armaEnUso].getTipoProyectil().retornarProyectil(),
				(controlador.mirandoIzquierda) ? DireccionesDisparo.IZQUIERDA : DireccionesDisparo.DERECHA, x, y));
		
//		Gdx.audio.newSound(Gdx.files.internal(armamento[armaEnUso].getTipoProyectil().getRutaSonidoDisparo())).play();
	}

	@Override
	public void dispararUltimate() {
		
		Ultimate ultimate = new Ultimate();

		float x = (controlador.mirandoIzquierda) ? posicion.x - ultimate.getDimensiones()[0]: posicion.x + getDimensiones()[0];
		float y = posicion.y + getDimensiones()[1] / 2 - ultimate.getDimensiones()[1] / 2;

		Globales.proyectilesDisparados.add(new ProyectilDisparado(new Ultimate(),
				(controlador.mirandoIzquierda) ? DireccionesDisparo.IZQUIERDA : DireccionesDisparo.DERECHA, x, y));
	}

	@Override
	public void actualizarSustoPuntos(int sustoPuntos) {
		this.sustoPuntos += sustoPuntos;
		if (sustoPuntos < 0) {
			sustoPuntos = 0;
		}
	}
	
	@Override
	public void sumarVida() {
		vida = (vida < 3) ? vida += 1 : 3;
	}

	public Arma[] getArmamento() {
		return armamento;
	}

	public Sprite getFrameActual() {
		return frameActual;
	}

	public int getSustoPuntos() {
		return sustoPuntos;
	}

	@Override
	public void cargarSpriteSheet(float ANCHO_SPRITESHEET, float ALTO_SPRITESHEET, int FILAS, int COLUMNAS) {

		final float ANCHO_FRAMES = ANCHO_SPRITESHEET / COLUMNAS;
		final float ALTO_FRAMES = ALTO_SPRITESHEET / FILAS;

		TextureRegion[][] frames = TextureRegion.split(new Texture(this.rutaSpritesheet), (int) ANCHO_FRAMES,
				(int) ALTO_FRAMES);

		ANCHO = ESCALA_TAMANIO * ANCHO_FRAMES;
		ALTO = ESCALA_TAMANIO * ALTO_FRAMES;

		framesCaminDerecha = new Sprite[8];
		framesCaminIzquierda = new Sprite[8];
		framesDisparaDerecha = new Sprite[6];
		framesDisparaIzquierda = new Sprite[6];
		framesNoqueado = new Sprite[3];
		framesMareado = new Sprite[3];

		int indice = 0;
		for (int i = 0; i < framesCaminIzquierda.length; i++) {

			framesCaminIzquierda[indice] = new Sprite(frames[0][i]);
			framesCaminDerecha[indice] = new Sprite(frames[1][(framesCaminIzquierda.length - 1) - i]);

			framesCaminIzquierda[indice].setSize(ANCHO, ALTO);
			framesCaminDerecha[indice++].setSize(ANCHO, ALTO);
		}

		indice = 0;
		for (int i = 0; i < framesDisparaIzquierda.length; i++) {

			framesDisparaIzquierda[indice] = new Sprite(frames[2][i]);
			framesDisparaDerecha[indice] = new Sprite(frames[3][i]);

			framesDisparaIzquierda[indice].setSize(ANCHO, ALTO);
			framesDisparaDerecha[indice++].setSize(ANCHO, ALTO);
		}

		indice = 0;
		for (int i = 0; i < framesMareado.length; i++) {

			framesNoqueado[indice] = new Sprite(frames[4][i]);
			framesMareado[indice] = new Sprite(frames[5][i]);

			framesNoqueado[indice].setSize(ANCHO, ALTO);
			framesMareado[indice++].setSize(ANCHO, ALTO);
		}
	}
}
