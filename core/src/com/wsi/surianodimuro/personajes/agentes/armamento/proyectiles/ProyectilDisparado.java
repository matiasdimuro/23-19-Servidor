package com.wsi.surianodimuro.personajes.agentes.armamento.proyectiles;

import com.badlogic.gdx.math.Vector2;
import com.wsi.surianodimuro.enumeradores.DireccionesDisparo;

public class ProyectilDisparado {

	public Proyectil proyectil;
	private DireccionesDisparo direccion;
	public Vector2 posicionInicial;	
	
	public ProyectilDisparado(Proyectil proyectil, DireccionesDisparo direccion, float xi, float yi) {
		
		this.proyectil = proyectil;
		this.direccion = direccion;
		this.posicionInicial = new Vector2(xi, yi);
		
		this.proyectil.setPosicion(xi, yi);
	}
	
	public DireccionesDisparo getDireccion() {
		return direccion;
	}
}
