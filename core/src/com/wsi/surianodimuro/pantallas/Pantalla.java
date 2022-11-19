package com.wsi.surianodimuro.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.wsi.surianodimuro.utilidades.ConfigGraficos;
import com.wsi.surianodimuro.utilidades.Helpers;
import com.wsi.surianodimuro.utilidades.Utiles;

public abstract class Pantalla implements Screen {
	
	protected OrthographicCamera cam;
	protected FitViewport viewport;
	protected InputProcessor entradas;

	@Override
	public void show() {

		cam = new OrthographicCamera(ConfigGraficos.ANCHO_PANTALLA, ConfigGraficos.ALTO_PANTALLA);
		cam.setToOrtho(false, ConfigGraficos.ANCHO_PANTALLA, ConfigGraficos.ALTO_PANTALLA);

		viewport = new FitViewport(ConfigGraficos.ANCHO_PANTALLA, ConfigGraficos.ALTO_PANTALLA);
		cam.update();
		
		entradas = null;
		Gdx.input.setInputProcessor(entradas);
	}

	@Override
	public void render(float delta) {

		Helpers.limpiarPantalla(0, 0, 0, 0);
		Utiles.batch.setProjectionMatrix(cam.combined);
		cam.update();
	}

	@Override
	public void resize(int width, int height) {

		cam.viewportWidth = width;
		cam.viewportHeight = height;
		viewport.update(width, height);
		cam.update();
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
	}

	@Override
	public void dispose() {

		Utiles.batch.dispose();
		Utiles.shapeRender.dispose();
	}
	
	public void procesarEntrada() {
		
	}
}
