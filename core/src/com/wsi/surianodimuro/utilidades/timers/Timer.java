package com.wsi.surianodimuro.utilidades.timers;

import com.badlogic.gdx.Gdx;
import com.wsi.surianodimuro.enumeradores.Mensajes;
import com.wsi.surianodimuro.pantallas.juego.TiempoProcesos;
import com.wsi.surianodimuro.personajes.agentes.Agente;
import com.wsi.surianodimuro.redes.MensajesServidor;
import com.wsi.surianodimuro.utilidades.Globales;

public class Timer extends Thread {

	private Agente jugadorUno;
	private Agente jugadorDos;

	private float tiempoMuertoDisparoJugUno;
	private float tiempoMuertoInfeccionJugUno;

	private float tiempoMuertoDisparoJugDos;
	private float tiempoMuertoInfeccionJugDos;

	private float supervivencia;
	private float duracionOleada;
	private float cronometro;

	public float timerMusicaFinal;

	public Timer() {

		cronometro = 0;
		supervivencia = 0;
		duracionOleada = 0;
		timerMusicaFinal = 0;

		tiempoMuertoDisparoJugUno = 0;
		tiempoMuertoDisparoJugDos = 0;
		tiempoMuertoInfeccionJugUno = 0;
		tiempoMuertoInfeccionJugDos = 0;
		jugadorUno = Globales.jugadores.get(0);
		jugadorDos = Globales.jugadores.get(1);
	}

	@Override
	public void run() {

		super.run();

		if (!Globales.datosPartida.terminada) {

			contarTiempoSupervivencia();

			/* Tiempo muerto entre cada disparo del Agente */

			if (!jugadorUno.controlador.puedeDisparar) {
				tiempoMuertoDisparoJugUno += Gdx.graphics.getDeltaTime();
				if (tiempoMuertoDisparoJugUno >= jugadorUno.getArmamento()[jugadorUno.armaEnUso]
						.getTiempoMuertoDisparo()) {
					tiempoMuertoDisparoJugUno = 0;
					jugadorUno.controlador.puedeDisparar = true;
					Globales.servidor.enviarMensajeATodos(MensajesServidor.HABILITAR_DISPAROS.getMensaje() + "#" + 0);
				}
			}

			if (!jugadorDos.controlador.puedeDisparar) {
				tiempoMuertoDisparoJugDos += Gdx.graphics.getDeltaTime();
				if (tiempoMuertoDisparoJugDos >= jugadorDos.getArmamento()[jugadorDos.armaEnUso]
						.getTiempoMuertoDisparo()) {
					tiempoMuertoDisparoJugDos = 0;
					jugadorDos.controlador.puedeDisparar = true;
					Globales.servidor.enviarMensajeATodos(MensajesServidor.HABILITAR_DISPAROS.getMensaje() + "#" + 1);
				}
			}

			/* Tiempo de retardo para volver a infectarse */

			if (!jugadorUno.controlador.puedeInfectarse) {
				tiempoMuertoInfeccionJugUno += Gdx.graphics.getDeltaTime();
				if (tiempoMuertoInfeccionJugUno >= TiempoProcesos.tpoRetardoInfecccion) {
					jugadorUno.controlador.puedeInfectarse = true;
					tiempoMuertoInfeccionJugUno = 0;
					Globales.servidor.enviarMensaje(MensajesServidor.HABILITAR_POSIBILIDAD_INFECCION.getMensaje(),
							Globales.servidor.getDirecciones()[0]);
				}
			}

			if (!jugadorDos.controlador.puedeInfectarse) {
				tiempoMuertoInfeccionJugDos += Gdx.graphics.getDeltaTime();
				if (tiempoMuertoInfeccionJugDos >= TiempoProcesos.tpoRetardoInfecccion) {
					jugadorDos.controlador.puedeInfectarse = true;
					tiempoMuertoInfeccionJugDos = 0;
					Globales.servidor.enviarMensaje(MensajesServidor.HABILITAR_POSIBILIDAD_INFECCION.getMensaje(),
							Globales.servidor.getDirecciones()[1]);
				}
			}

			/* (1) Tiempo de retardo para el inicio de cada oleada. */
			procesarRetardoInicioOleadas();

			/* (2) Tiempo transcurrido hasta el fin de la oleada. */
			procesarTpoTranscurridoOleadas();

			/*
			 * (3) Tiempo medio transcurrido luego de cada oleada antes del comienzo de la
			 * siguiente.
			 */
			procesarEntreTiempoOleadas();
		}
	}

	public void contarTiempoSupervivencia() {
		supervivencia += Gdx.graphics.getDeltaTime();
		Globales.datosPartida.tiempoSupervivencia = supervivencia;
		Globales.servidor
				.enviarMensajeATodos(MensajesServidor.ACTUALIZAR_TPO_SUPERVIVENCIA.getMensaje() + "#" + supervivencia);
	}

	public void procesarEntreTiempoOleadas() {

		if ((Globales.oleadaInfo.oleadaComenzada) && (!Globales.oleadaInfo.oleadaEnCurso)) {
			cronometro += Gdx.graphics.getDeltaTime();
//			if (!Globales.sonidos.musicaEntreRondaSonando) {
//				Globales.sonidos.sonarMusicaEntreRonda();
			// TODO msg a todos: sonar musica
//			}
			if (cronometro >= TiempoProcesos.tpoEntreOleadas) {
				Globales.oleadaInfo.numOleada += 1;
				Globales.oleadaInfo.mejoraEfectuada = false;
				Globales.oleadaInfo.oleadaComenzada = false;
				Globales.oleadaInfo.actualizarIndicador = true;
				Globales.oleadaInfo.dificultadAumentada = false;
				Globales.servidor.enviarMensajeATodos(MensajesServidor.PROCESAR_TPO_ENTRE_OLEADAS.getMensaje());
//				Globales.sonidos.terminarMusicaEntreRonda();
				// TODO msg a todos: terminar musica
				cronometro = 0;
			}
			Globales.cajaMensajes.setTexto(Mensajes.FIN_OLEADA.getMensaje());
			Globales.servidor.enviarMensajeATodos(
					MensajesServidor.ACTUALIZAR_CAJA_MENSAJES.getMensaje() + "#" + Mensajes.FIN_OLEADA.getMensaje());
		}
	}

	public void procesarTpoTranscurridoOleadas() {

		if ((Globales.oleadaInfo.oleadaComenzada) && (Globales.oleadaInfo.oleadaEnCurso)) {

			duracionOleada += Gdx.graphics.getDeltaTime();
			cronometro += Gdx.graphics.getDeltaTime();

			Globales.oleadaInfo.tiempoTranscurrido = duracionOleada;
			Globales.servidor.enviarMensajeATodos(
					MensajesServidor.ACTUALIZAR_TPO_TRANSCURRIDO.getMensaje() + "#" + duracionOleada);

			/* (A) Spawn de Entes */
			if ((cronometro >= TiempoProcesos.tpoRetardoSpawns) && (duracionOleada < TiempoProcesos.duracionOleada)) {
				cronometro = 0;
				Globales.actividadInfectadosListener.spawnearInfectado();
				Globales.cajaMensajes.setTexto(Mensajes.COMIENZO_OLEADA.getMensaje());
				Globales.servidor.enviarMensajeATodos(MensajesServidor.ACTUALIZAR_CAJA_MENSAJES.getMensaje() + "#"
						+ Mensajes.COMIENZO_OLEADA.getMensaje());
			}

			/* (B) Oleada Terminada */
			if ((duracionOleada >= TiempoProcesos.duracionOleada) && (Globales.oleadaInfo.libreDeEntes)) {
				Globales.oleadaInfo.oleadaEnCurso = false;
				Globales.servidor.enviarMensajeATodos(MensajesServidor.PROCESAR_TPO_OLEADA_TERMINADA.getMensaje());
				duracionOleada = 0;
				cronometro = 0;
//				Globales.sonidos.terminarMusicaDeFondo();
				// TODO msg a todos: terminar musica
			}
		}
	}

	public void procesarRetardoInicioOleadas() {

		if (!Globales.oleadaInfo.oleadaComenzada) {
			cronometro += Gdx.graphics.getDeltaTime();
			if (cronometro >= TiempoProcesos.tpoRetardoInicio) {
				Globales.oleadaInfo.oleadaComenzada = true;
				Globales.oleadaInfo.oleadaEnCurso = true;
				Globales.servidor.enviarMensajeATodos(MensajesServidor.PROCESAR_RETARDO_INICIO_OLEADAS.getMensaje());
				cronometro = 0;
			}
//			if (!Globales.sonidos.musicaDeFondoSonando) {
//				Globales.sonidos.sonarMusicaDeFondo();
//			}			
			// TODO msg a todos: sonar musica
			Globales.cajaMensajes.setTexto(Mensajes.PREVIA_OLEADA.getMensaje());
			Globales.servidor.enviarMensajeATodos(MensajesServidor.ACTUALIZAR_CAJA_MENSAJES.getMensaje() + "#"
					+ Mensajes.PREVIA_OLEADA.getMensaje());
		}
	}
}
