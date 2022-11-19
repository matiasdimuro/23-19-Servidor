package com.wsi.surianodimuro.personajes.agentes.armamento.proyectiles;

import com.wsi.surianodimuro.enumeradores.Proyectiles;

public final class ProyectilDesintox extends Proyectil {

	public ProyectilDesintox() {
		super("objetos/armamento/desintox_bullet.png", 30, 15);
		tipo = Proyectiles.PROYECTIL_DESINTOX;
	}
}
