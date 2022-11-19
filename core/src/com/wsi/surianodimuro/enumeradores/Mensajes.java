package com.wsi.surianodimuro.enumeradores;

public enum Mensajes {

	FIN_OLEADA("\nLa oleada ha finalizado.\n\nRecarga fuerzas!"),
	PREVIA_OLEADA("\nUna nueva oleada esta\n\npor comenzar. Preparate!"),
	COMIENZO_OLEADA("\nVamos!\n\nDefiende Monstruopilis!"),
	
	INFECTADO("\nHas sido infectado!\n\nPierdes una vida"),
	ESCAPE_MONSTRUO("\nRayos! Se ha\n\nescapado un monstruo!"),
	ESCAPE_NINIO("\nRayos! Se ha\n\nescapado un niño!");
	
	private String mensaje;
	
	private Mensajes(String mensaje) {
		this.mensaje = mensaje;
	}
	
	public String getMensaje() {
		return mensaje;
	}
}