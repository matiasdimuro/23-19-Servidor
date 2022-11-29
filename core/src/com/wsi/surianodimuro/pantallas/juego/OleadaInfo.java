package com.wsi.surianodimuro.pantallas.juego;

public class OleadaInfo {

	public int numOleada = 1;
	public float tiempoTranscurrido = 0;
	
	public boolean oleadaEnCurso = false;
	public boolean oleadaComenzada = false;
	
	public boolean libreDeEntes = true;
	
	public boolean actualizarIndicador = false;
	public boolean dificultadAumentada = false;
	public boolean mejoraEfectuada = false;
	
	public boolean aumentarSpawns = false;
	public boolean aumentarDuracionOleada = false;
	public boolean aumentarVelocidadInfectados = false;
	
	public final int INTERVALO_OLEADAS_AUMENTO_SPAWNS = 4;
	public final int INTERVALO_OLEADAS_AUMENTO_DURACION = 3;
	public final int INTERVALO_OLEADAS_AUMENTO_VELOCIDAD = 7;
	
	public boolean mejoraRapidez = false;
	public boolean mejoraVelDisparo = false;
	public boolean mejoraVida = false;
	public boolean mejoraAlcance = false;
	
	public final int INTERVALO_OLEADAS_MEJORA_RAPIDEZ = 5;
	public final int INTERVALO_OLEADAS_MEJORA_VEL_DISP = 5;
	public final int INTERVALO_OLEADAS_MEJORA_VIDA = 8;
	public final int INTERVALO_OLEADAS_MEJORA_ALCANCE = 8;
			
	public int aumentoDeVelocidad = 0;
	
	public final int GRITOS_ULTIMATE = 500;
}
