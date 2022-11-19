package com.wsi.surianodimuro;

import com.badlogic.gdx.Game;
import com.wsi.surianodimuro.pantallas.juego.PantallaOleadas;
import com.wsi.surianodimuro.redes.Servidor;
import com.wsi.surianodimuro.utilidades.Globales;

public class Juego extends Game {
	
	@Override
	public void create () {
		Globales.servidor = new Servidor();
		setScreen(new PantallaOleadas());
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		super.dispose();
		Globales.servidor.dispose();
	}
}
