package com.wsi.surianodimuro.redes;

import java.net.InetAddress;

public final class DireccionCliente {

	private InetAddress IP;
	private int PUERTO;
	
	public DireccionCliente(InetAddress IP, int PUERTO) {
		this.IP = IP;
		this.PUERTO = PUERTO;
	}

	public InetAddress getIP() {
		return IP;
	}

	public int getPUERTO() {
		return PUERTO;
	}
}
