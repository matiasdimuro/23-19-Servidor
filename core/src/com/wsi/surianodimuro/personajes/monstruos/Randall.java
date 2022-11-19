package com.wsi.surianodimuro.personajes.monstruos;

import com.wsi.surianodimuro.enumeradores.Monstruos;

public final class Randall extends Monstruo {

	public Randall() {
		super("personajes/monstruos/randall.png", 3, 104, 36, 2, 5, 5);
		tipoMonstruo = Monstruos.RANDALL;
	}
}
