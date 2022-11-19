package com.wsi.surianodimuro.redes;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

import com.badlogic.gdx.utils.Disposable;
import com.wsi.surianodimuro.utilidades.Globales;

public class Servidor extends Thread implements Disposable {

	private DatagramSocket socket;

	private int cantClientes = 0;
	private DireccionCliente[] direcciones = new DireccionCliente[2];

	private boolean offline = false;
	public final int PUERTO = 9001;

	public Servidor() {
		System.out.println("Esperando conexiones ...");
		try {
			socket = new DatagramSocket(PUERTO);
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		do {
			byte[] datos = new byte[1024];
			DatagramPacket datagrama = new DatagramPacket(datos, datos.length);
			try {
				socket.receive(datagrama);
				procesarMensaje(datagrama);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} while (!offline);
	}

	public void enviarMensajeATodos(String msg) {
		for (DireccionCliente direccion : direcciones) {
			enviarMensaje(msg, direccion);
		}
	}

	public void enviarMensaje(String mensaje, DireccionCliente direccion) {
		byte[] datos = mensaje.getBytes();
		DatagramPacket datagrama = new DatagramPacket(datos, datos.length, direccion.getIP(), direccion.getPUERTO());
		try {
			socket.send(datagrama);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void procesarMensaje(DatagramPacket datagrama) {

		String mensaje = new String(datagrama.getData()).trim();
		System.out.println(mensaje);

		String[] mensajeParametrizado = mensaje.split("#");

		if (mensajeParametrizado[0].equals(MensajesCliente.SOLICITAR_CONEXION.getMensaje())) {
			System.out.println("Se ha solicitado una conexion.");
			procesarSolicitudConexion(datagrama);
		}

		else if (mensajeParametrizado[0].equals(MensajesCliente.CLIENTE_DESCONECTADO.getMensaje())) {
			System.out.println("Se ha desconectado un cliente. Vuelan todos!");
			DireccionCliente direccion = new DireccionCliente(datagrama.getAddress(), datagrama.getPort());
			desconectarCliente(datagrama, direccion);
		}
	}

	private void desconectarCliente(DatagramPacket datagrama, DireccionCliente direccion) {
		;
		for (DireccionCliente dir : direcciones) {
			System.out.println("   Chauuuu " + dir);
			if (dir != null) {
				if (!(dir.getIP().equals(direccion.getIP())) && !(dir.getPUERTO() == direccion.getPUERTO())) {
					enviarMensaje(MensajesServidor.DESCONECTAR_CLIENTE.getMensaje(), dir);
				} 
				direcciones[getNroCliente(direccion)] = null;
				cantClientes -= 1;
			}
		}
	}
	
	private void cerrarServidor() {
		
		offline = true;
		InfoRed.conexionGlobalEstablecida = false;
		
		for (DireccionCliente dir : direcciones) {
			if (dir != null) {
				enviarMensaje("", dir);
				dir = null;
				cantClientes -= 1;
			}
		}
	}

	private void procesarSolicitudConexion(DatagramPacket datagrama) {

		DireccionCliente direccion = new DireccionCliente(datagrama.getAddress(), datagrama.getPort());

		if (cantClientes < direcciones.length) {
			direcciones[cantClientes] = direccion;
			enviarMensaje(MensajesServidor.SOLICITUD_ACEPTADA.getMensaje() + "#" + (getNroCliente(direccion) + 1),
					direccion);

			System.out.println("Se ha registrado al cliente " + (getNroCliente(direccion) + 1) + "!");
			cantClientes++;
			System.out.println("CLIENTES: " + cantClientes);

			if (cantClientes == direcciones.length) {
				System.out.println("El servidor ha comenzado el juego!");
				Globales.redListener.comenzarJuego();
				enviarMensajeATodos(MensajesServidor.EMPEZAR_JUEGO.getMensaje());
			}
		}

		else {
			enviarMensaje(MensajesServidor.SOLICITUD_RECHAZADA.getMensaje(), direccion);
		}
	}

	private int getNroCliente(DireccionCliente direccion) {

		int i = 0;
		boolean encontrado = false;

		do {
			if ((direccion.getIP().equals(direcciones[i].getIP()))
					&& (direccion.getPUERTO() == direcciones[i].getPUERTO())) {
				encontrado = true;
			}
		} while ((!encontrado) && (++i < direcciones.length));

		return (encontrado) ? i : -1;
	}

	@Override
	public void dispose() {
		
		cerrarServidor();
		this.interrupt();
	}
}
