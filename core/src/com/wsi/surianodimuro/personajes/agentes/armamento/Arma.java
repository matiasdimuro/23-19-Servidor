package com.wsi.surianodimuro.personajes.agentes.armamento;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.wsi.surianodimuro.enumeradores.Armamentos;
import com.wsi.surianodimuro.enumeradores.Proyectiles;
import com.wsi.surianodimuro.interfaces.InventarioListable;
import com.wsi.surianodimuro.utilidades.ConfigGraficos;
import com.wsi.surianodimuro.utilidades.Utiles;

public abstract class Arma implements InventarioListable {

	private Armamentos tipo;
	private Proyectiles proyectil;

	protected Sprite imagen;
	private int danio;
	private int alcance;
	private int velocidadDisparo;
	private float tiempoMuertoDisparo;
	
	public Arma(String rutaSprite, Armamentos tipo, int danio, Proyectiles tipoProyectil) {

		this.tipo = tipo;
		this.danio = danio;
		this.proyectil = tipoProyectil;
		
		alcance = 450;
		velocidadDisparo = 350;
		tiempoMuertoDisparo = 1;

		cargarSpritesheet(rutaSprite);
	}

	public void renderizar() {
		imagen.draw(Utiles.batch);
	}

	public void liberarMemoria() {
		imagen.getTexture().dispose();
	}
	
	protected void cargarSpritesheet(String rutaSprite) {
	}

	public float[] getDimensiones() {
		float[] dimensiones = { imagen.getWidth(), imagen.getHeight() };
		return dimensiones;
	}

	public void setDimensiones(float ANCHO, float ALTO) {
		imagen.setSize(ANCHO, ALTO);
	}

	public Vector2 getPosicion() {
		return new Vector2(imagen.getX(), imagen.getY());
	}

	public void setPosicion(float x, float y) {
		imagen.setPosition(x, y);
	}

	public int getAlcance() {
		return alcance;
	}

	public int getDanio() {
		return danio;
	}
	
	public int getVelocidadDisparo() {
		return velocidadDisparo;
	}

	public float getTiempoMuertoDisparo() {
		return tiempoMuertoDisparo;
	}
	
	public Armamentos getTipo() {
		return tipo;
	}
	
	public Proyectiles getTipoProyectil() {
		return proyectil;
	}
	
	public void aumentarAlcance() {
		this.alcance += 150; 
		if (this.alcance >= ConfigGraficos.ANCHO_MAPA / 2) {
			this.alcance = ConfigGraficos.ANCHO_MAPA / 2;
		}
	}
	
	public void aumentarVelocidadDisparo() {
		this.tiempoMuertoDisparo -= 0.2f;
		if (this.tiempoMuertoDisparo <= 0) {
			this.tiempoMuertoDisparo = 0.1f;
		}
	}
}
