package com.wsi.surianodimuro.personajes.monstruos;

import com.wsi.surianodimuro.enumeradores.Monstruos;

public final class Ronnie extends Monstruo {

	public Ronnie() {
		super("personajes/monstruos/ronnie.png", 3.5f, 196, 38, 2, 7, 6);
		tipoMonstruo = Monstruos.RONNIE;
	}
}
