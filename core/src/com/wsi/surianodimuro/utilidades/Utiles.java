package com.wsi.surianodimuro.utilidades;

import java.util.Random;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public abstract class Utiles {

	public static ShapeRenderer shapeRender = new ShapeRenderer();
	public static SpriteBatch batch = new SpriteBatch();
	
	public static Random rand = new Random();
}
