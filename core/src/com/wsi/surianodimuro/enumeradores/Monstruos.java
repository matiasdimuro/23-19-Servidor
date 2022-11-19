package com.wsi.surianodimuro.enumeradores;

import java.lang.reflect.InvocationTargetException;

import com.wsi.surianodimuro.interfaces.InfectadosListables;
import com.wsi.surianodimuro.personajes.Infectado;

public enum Monstruos implements InfectadosListables {

	BEN("Ben", 10),
	MIKE("Mike", 10),
	RANDALL("Randall", 15),
	RONNIE("Ronnie", 15),
	WATERNOOSE("Waternoose", 20),
	SULLIVAN("Sullivan", 25);
	
	private String clase;
	private int sustoPuntos;
	
	private Monstruos(String clase, int sustoPuntos) {
		this.clase = clase;
		this.sustoPuntos = sustoPuntos;
	}
	
	@Override
	public String getClase() {
		return clase;
	}
	
	@Override
	public int getSustoPuntos() {
		return sustoPuntos;
	}
	
	public static Infectado retornarMonstruo(int num) {
		
		Class clase = null;
		Infectado monstruo = null;
		
		try {
			clase = Class.forName("com.wsi.surianodimuro.personajes.monstruos." + Monstruos.values()[num].getClase());
			monstruo = (Infectado) clase.getDeclaredConstructor().newInstance();
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		
		return monstruo;
	}
}
