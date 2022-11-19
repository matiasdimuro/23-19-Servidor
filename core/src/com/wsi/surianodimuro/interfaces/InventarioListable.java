package com.wsi.surianodimuro.interfaces;

import com.badlogic.gdx.math.Vector2;

public interface InventarioListable extends Renderizable {

	@Override
	void renderizar();
	
	@Override
	void liberarMemoria();
	
	float[] getDimensiones();
	public void setDimensiones(float ANCHO, float ALTO);
	
	public Vector2 getPosicion();
	public void setPosicion(float x, float y);
}
