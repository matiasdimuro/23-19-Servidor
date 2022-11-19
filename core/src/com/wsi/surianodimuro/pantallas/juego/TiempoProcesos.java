package com.wsi.surianodimuro.pantallas.juego;

public abstract class TiempoProcesos {

	public static float tpoRetardoInicio = 3;
	public static float tpoEntreOleadas = 10;	
	public static float tpoRetardoSpawns = 5;
	public static float duracionOleada = 30;	
	
	public static float tpoRetardoInfecccion = 2.5f;
	
	public static void resetearTiempos() {
		tpoRetardoInicio = 3;
		tpoEntreOleadas = 10;	
		tpoRetardoSpawns = 5;
		duracionOleada = 30;	
		tpoRetardoInfecccion = 2.5f;
	}
}