package com.wsi.surianodimuro.personajes.monstruos;

import com.wsi.surianodimuro.enumeradores.Monstruos;

public final class Mike extends Monstruo {

	public Mike() {
		super("personajes/monstruos/mike.png", 2.5f, 156, 56, 2, 6, 4);
		tipoMonstruo = Monstruos.MIKE;
	}
}
