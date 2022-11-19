package com.wsi.surianodimuro.enumeradores;

public enum Ascensores {

	UNO_A("ascensor_1_a", null, "ascensor_2_a"), UNO_B("ascensor_1_b", null, "ascensor_2_b"), 
	DOS_A("ascensor_2_a", "ascensor_1_a", "ascensor_3_a"), DOS_B("ascensor_2_b", "ascensor_1_b", "ascensor_3_b"), 
	TRES_A("ascensor_3_a", "ascensor_2_a", null), TRES_B("ascensor_3_b", "ascensor_2_b", null);
	
	private String nombre;
	private String arriba;
	private String abajo;
	
	private Ascensores(String nombre, String arriba, String abajo) {
		this.nombre = nombre;
		this.arriba = arriba;
		this.abajo = abajo;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public String getArriba() {
		return arriba;
	}
	
	public String getAbajo() {
		return abajo;
	}
	
	public static Ascensores obtenerTipoAscensor(String nombre) {
		
		if ((nombre == null) || (nombre.equals(""))) {
			return null;
		}
		
		int i = 0;
		boolean encontrado = false;
		
		do {
			if (nombre.equals(values()[i].getNombre())) {
				encontrado = true;
			}
		} while((!encontrado) && (++i < values().length));
		
		return values()[i];
	}
}
