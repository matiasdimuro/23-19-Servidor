package com.wsi.surianodimuro.interfaces;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public interface Dimensionable extends Renderizable {
	
	float[] getDimensiones();
	Vector2 getPosicion();
	
	void setPosicion(float x, float y);
	
	Rectangle getRectangulo();
}
