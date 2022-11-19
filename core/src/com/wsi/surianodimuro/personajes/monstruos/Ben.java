package com.wsi.surianodimuro.personajes.monstruos;

import com.wsi.surianodimuro.enumeradores.Monstruos;

public final class Ben extends Monstruo {

	public Ben() {
		super("personajes/monstruos/ben.png", 3f, 140, 42, 2, 7, 4);
		tipoMonstruo = Monstruos.BEN;
	}
}
