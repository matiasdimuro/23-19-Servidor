package com.wsi.surianodimuro.interfaces;

import com.wsi.surianodimuro.objetos.Ascensor;

public interface MovimientoAgenteListener {

	boolean chequearColisiones(int numAgente);
	Ascensor chequearUbicacionEnAscensor(int numAgente);
	void procesarMovimientoVertical(int numAgente, Ascensor ascensorOrigen);
}
