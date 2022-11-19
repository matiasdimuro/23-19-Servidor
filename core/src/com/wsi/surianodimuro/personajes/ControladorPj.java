package com.wsi.surianodimuro.personajes;


public final class ControladorPj extends Controlador {

	public boolean izquierda = false;
	public boolean derecha = false;
	public boolean arriba = false;
	public boolean abajo = false;
	
	public boolean caminando = false;
	public boolean disparando = false;
	
	public boolean puedeDisparar = true;
	public boolean puedeInfectarse = true;
	
	public boolean disparandoUltimate = false;
	
	@Override
	public void resetearEstados() {
		super.resetearEstados();
		izquierda = false;
		derecha = false;
		arriba = false;
		abajo = false;
	}
}
