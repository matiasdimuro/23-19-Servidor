package com.wsi.surianodimuro.personajes.monstruos;

import com.wsi.surianodimuro.enumeradores.Monstruos;

public final class Sullivan extends Monstruo {

	public Sullivan() {
		super("personajes/monstruos/sullivan.png", 3, 264, 68, 2, 8, 8);
		tipoMonstruo = Monstruos.SULLIVAN;
	}
}
