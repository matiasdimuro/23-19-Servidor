package com.wsi.surianodimuro.interfaces;

import com.wsi.surianodimuro.objetos.Ascensor;

public interface MovimientoAgenteListener {

	boolean chequearColisiones();
	Ascensor chequearUbicacionEnAscensor();
	void procesarMovimientoVertical(Ascensor ascensorOrigen);
}
