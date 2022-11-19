package com.wsi.surianodimuro.enumeradores;

import java.lang.reflect.InvocationTargetException;
import com.wsi.surianodimuro.personajes.agentes.armamento.proyectiles.Proyectil;

public enum Proyectiles {

	PROYECTIL_DESINTOX("ProyectilDesintox", "sonidos/bloop.mp3", "sonidos/slimeSplash.mp3"), PROYECTIL_BOO2000("ProyectilBoo2000", "sonidos/waterDrop.mp3", "sonidos/waterSplash.mp3"), ULTIMATE("Ultimate", null, null);
	
	private String clase;
	private String rutaSonidoDisparo;
	private String rutaSonidoColision;
	
	private Proyectiles(String clase, String rutaSonidoDisparo, String rutaSonidoColision) {
		this.clase = clase;
		this.rutaSonidoDisparo = rutaSonidoDisparo;
		this.rutaSonidoColision = rutaSonidoColision;
	}
	
	public String getClase() {
		return clase;
	}
	
	public String getRutaSonidoColision() {
		return rutaSonidoColision;
	}
	
	public String getRutaSonidoDisparo() {
		return rutaSonidoDisparo;
	}
	
	public Proyectil retornarProyectil() {
		
		Class clase = null;
		Proyectil proyectil = null;
		try {
			clase = Class.forName("com.wsi.surianodimuro.personajes.agentes.armamento.proyectiles." + this.clase);
			proyectil = (Proyectil) clase.getDeclaredConstructor().newInstance();
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		
		return proyectil;
	};
}
