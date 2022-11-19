package com.wsi.surianodimuro.enumeradores;

import java.lang.reflect.InvocationTargetException;

import com.wsi.surianodimuro.interfaces.InfectadosListables;
import com.wsi.surianodimuro.personajes.Infectado;

public enum Ninios implements InfectadosListables {

	NINIO_UNO("NinioUno", 5),
	NINIO_DOS("NinioDos", 5);

	private String clase;
	private int sustoPuntos;
	
	private Ninios(String clase, int sustoPuntos) {
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
	
	public static Infectado retornarNinio(int num) {
		
		Class clase = null;
		Infectado ninio = null;
		
		try {
			clase = Class.forName("com.wsi.surianodimuro.personajes.ninios." + Ninios.values()[num].getClase());
			ninio = (Infectado) clase.getDeclaredConstructor().newInstance();
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		
		return ninio;
	}
}
