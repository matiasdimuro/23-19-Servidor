package com.wsi.surianodimuro.pantallas.juego.hud.elementos;

import com.wsi.surianodimuro.utilidades.Globales;

public final class InventarioArmamento extends Inventario {

	public InventarioArmamento(float x, float y, float ANCHO, float ALTO) {

		super("hud/inventarioArmamento.png", x, y, ANCHO, ALTO, Globales.jugadores.get(0).getArmamento().length);
		elementos[0] = Globales.jugadores.get(0).getArmamento()[0];
		elementos[1] = Globales.jugadores.get(0).getArmamento()[1];
	}
}
