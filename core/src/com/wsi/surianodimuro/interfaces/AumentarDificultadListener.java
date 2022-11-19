package com.wsi.surianodimuro.interfaces;

import com.wsi.surianodimuro.personajes.Infectado;

public interface AumentarDificultadListener {

	void aumentarDificultad();
	void aumentarVelocidadSpawn();
	void aumentarDuracionOleada();
	void aumentarVelocidadInfectado(Infectado infectado);
}
