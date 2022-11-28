package com.wsi.surianodimuro.pantallas.juego;

public abstract class TiempoProcesos {

	public static float tpoRetardoInicio = 3;
	public static float tpoEntreOleadas = 10;	
	public static float tpoRetardoSpawns = 3.5f;
	public static float duracionOleada = 45;	
	
	public static float tpoRetardoInfecccion = 2.5f;
	
	public static void resetearTiempos() {
		tpoRetardoInicio = 3;
		tpoEntreOleadas = 10;	
		tpoRetardoSpawns = 3.5f;
		duracionOleada = 45;	
		tpoRetardoInfecccion = 2.5f;
	}
}