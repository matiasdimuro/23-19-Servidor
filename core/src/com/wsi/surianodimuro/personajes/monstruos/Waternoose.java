package com.wsi.surianodimuro.personajes.monstruos;

import com.wsi.surianodimuro.enumeradores.Monstruos;

public final class Waternoose extends Monstruo {

	public Waternoose() {
		super("personajes/monstruos/waternoose.png", 3.3f, 155, 40, 2, 7, 7);
		tipoMonstruo = Monstruos.WATERNOOSE;
	}
}
