package com.wsi.surianodimuro.interfaces;

import com.wsi.surianodimuro.objetos.Ascensor;
import com.wsi.surianodimuro.personajes.Infectado;

public interface ProcesosJugabilidad extends MejorarEstadisticasListener, AumentarDificultadListener, ActividadInfectadosListener, ActividadProyectilesListener, MovimientoAgenteListener {

	/* MejorarEstadisticasListener */
	
	@Override
	void aumentarVida();
	
	@Override
	void aumentarRapidez();
	
	@Override
	void aumentarAlcance();
	
	@Override
	void aumentarVelDisparo();
	
	@Override
	void chequearAumentoEstadisticas();
	
	/* MovimientoAgenteListener */
	
	@Override
	boolean chequearColisiones();

	@Override
	Ascensor chequearUbicacionEnAscensor();

	@Override
	void procesarMovimientoVertical(Ascensor ascensorOrigen);

	/* ActividadProyectilesListener */
	
	@Override
	void chequearColisionProyectiles();

	@Override
	void chequearProyectilesImpactados();

	@Override
	void procesarTrayectoriaProyectiles();

	/* ActividadInfectadosListener */
	
	@Override
	void detectarEscapes();

	@Override
	void spawnearInfectado();

	@Override
	void detectarInfecciones();

	@Override
	void chequearVidaInfectados();

	@Override
	void chequearInfectadosEnMapa();
	
	/* AumentarDificultadListener */
	
	@Override
	void aumentarDificultad();

	@Override
	void aumentarVelocidadSpawn();

	@Override
	void aumentarDuracionOleada();

	@Override
	void aumentarVelocidadInfectado(Infectado infectado);
}
