package com.wsi.surianodimuro.personajes.agentes.armamento.proyectiles;

import com.wsi.surianodimuro.enumeradores.Proyectiles;

public final class Ultimate extends Proyectil {

	public Ultimate() {
		super("objetos/armamento/ultimate.png", 120, 120);
		tipo = Proyectiles.ULTIMATE;
	}
}
