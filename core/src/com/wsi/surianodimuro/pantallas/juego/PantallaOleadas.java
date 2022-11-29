package com.wsi.surianodimuro.pantallas.juego;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.wsi.surianodimuro.enumeradores.Ascensores;
import com.wsi.surianodimuro.enumeradores.DireccionesDisparo;
import com.wsi.surianodimuro.enumeradores.Infectados;
import com.wsi.surianodimuro.enumeradores.Mensajes;
import com.wsi.surianodimuro.enumeradores.Monstruos;
import com.wsi.surianodimuro.enumeradores.Ninios;
import com.wsi.surianodimuro.enumeradores.Proyectiles;
import com.wsi.surianodimuro.interfaces.InfectadosListables;
import com.wsi.surianodimuro.interfaces.ProcesosJugabilidad;
import com.wsi.surianodimuro.objetos.Ascensor;
import com.wsi.surianodimuro.objetos.PuertaSpawn;
import com.wsi.surianodimuro.pantallas.Pantalla;
import com.wsi.surianodimuro.pantallas.juego.hud.HudMultiJug;
import com.wsi.surianodimuro.personajes.Infectado;
import com.wsi.surianodimuro.personajes.agentes.Agente;
import com.wsi.surianodimuro.personajes.agentes.AgenteDos;
import com.wsi.surianodimuro.personajes.agentes.AgenteUno;
import com.wsi.surianodimuro.personajes.agentes.armamento.proyectiles.Proyectil;
import com.wsi.surianodimuro.personajes.agentes.armamento.proyectiles.ProyectilDisparado;
import com.wsi.surianodimuro.redes.InfoRed;
import com.wsi.surianodimuro.redes.MensajesServidor;
import com.wsi.surianodimuro.redes.RedListener;
import com.wsi.surianodimuro.utilidades.ConfigGraficos;
import com.wsi.surianodimuro.utilidades.Globales;
import com.wsi.surianodimuro.utilidades.Utiles;
import com.wsi.surianodimuro.utilidades.timers.Timer;

public final class PantallaOleadas extends Pantalla implements ProcesosJugabilidad, RedListener {

	private OleadaInfo oleadaInfo;
	private DatosPartida datosPartida;

	private Mapa mapa;

	private Agente jugadorUno;
	private Agente jugadorDos;
	private HudMultiJug hud;

	private ArrayList<Infectado> infectados;
	private ArrayList<ProyectilDisparado> proyectilesDisparados;

	private Timer timer;

	public PantallaOleadas() {

		oleadaInfo = new OleadaInfo();
		datosPartida = new DatosPartida();
		infectados = new ArrayList<Infectado>();
		proyectilesDisparados = new ArrayList<ProyectilDisparado>();

		jugadorUno = new AgenteUno();
		jugadorDos = new AgenteDos();
		Globales.jugadores.add(jugadorUno);
		Globales.jugadores.add(jugadorDos);

		Globales.redListener = this;
		Globales.oleadaInfo = oleadaInfo;
		Globales.datosPartida = datosPartida;

		Globales.infectados = infectados;
		Globales.proyectilesDisparados = proyectilesDisparados;

		Globales.mejorarEstadisticasListener = this;
		Globales.aumentarDificultadListener = this;
		Globales.actividadInfectadosListener = this;
		Globales.actividadProyectilesListener = this;
		Globales.movimientoAgenteListener = this;

		Globales.servidor.start();
	}

	@Override
	public void show() {

		super.show();

		cam = new OrthographicCamera(ConfigGraficos.ANCHO_MAPA, ConfigGraficos.ALTO_MAPA);
		cam.setToOrtho(false, ConfigGraficos.ANCHO_MAPA, ConfigGraficos.ALTO_MAPA);

		mapa = new Mapa(cam);
		cam.update();

		viewport = new FitViewport(ConfigGraficos.ANCHO_MAPA, ConfigGraficos.ALTO_MAPA, cam);
		cam.update();

		jugadorUno.setPosicion(ConfigGraficos.ANCHO_MAPA - jugadorUno.getDimensiones()[0] - 10,
				mapa.getTienda().getPosicion().y);
		jugadorDos.setPosicion(ConfigGraficos.ANCHO_MAPA - jugadorUno.getDimensiones()[0] - 10,
				mapa.getTienda().getPosicion().y);

		timer = new Timer();
		hud = new HudMultiJug(mapa.getElemsHud());

		Globales.cajaMensajes = hud.getCajaMensajes();
	}

	@Override
	public void render(float delta) {

		super.render(delta);

		if (InfoRed.conexionGlobalEstablecida) {

			if (!datosPartida.terminada) {

				timer.run();
				Utiles.batch.begin();

				mapa.renderizar();
//				hud.renderizar();

				if (oleadaInfo.oleadaEnCurso) {
					oleadaInfo.libreDeEntes = (infectados.size() > 0) ? false : true;
					if (!oleadaInfo.libreDeEntes) {
						for (Infectado infectado : infectados) {
							infectado.renderizar();
							int indice = infectados.indexOf(infectado);
							if (infectado.controlador.mirandoIzquierda) {
								infectado.moverseIzquierda();
								infectado.caminarIzquierda();
								Globales.servidor.enviarMensajeATodos(
										MensajesServidor.MOVER_INFECTADO_IZQUIERDA.getMensaje() + "#" + indice);
							} else if (infectado.controlador.mirandoDerecha) {
								infectado.moverseDerecha();
								infectado.caminarDerecha();
								Globales.servidor.enviarMensajeATodos(
										MensajesServidor.MOVER_INFECTADO_DERECHA.getMensaje() + "#" + indice);
							}
						}
					}
				}

				jugadorUno.renderizar();
				jugadorDos.renderizar();

				for (ProyectilDisparado proyectilDisparado : proyectilesDisparados) {
					proyectilDisparado.proyectil.renderizar();
				}

				Utiles.batch.end();
				mostrarIndicadores();

				if ((oleadaInfo.oleadaEnCurso) && (infectados.size() > 0)) {
					detectarInfecciones();
					detectarEscapes();
					chequearVidaInfectados();
					chequearInfectadosEnMapa();
				}

				if (proyectilesDisparados.size() > 0) {
					procesarTrayectoriaProyectiles();
					chequearColisionProyectiles();
				}

				if ((proyectilesDisparados.size() > 0) && ((oleadaInfo.oleadaEnCurso) && (infectados.size() > 0))) {
					chequearProyectilesImpactados();
				}

				if (oleadaInfo.actualizarIndicador) {
					hud.getIndicadorOleada().actualizarDatos();
					oleadaInfo.actualizarIndicador = false;
				}

				if ((oleadaInfo.oleadaEnCurso) && (oleadaInfo.oleadaComenzada)) {
					hud.getIndicadorGrito().actualizarDatos();
					Globales.servidor.enviarMensajeATodos(MensajesServidor.ACTUALIZAR_INDICADOR_GRITO.getMensaje() + "#" + Globales.jugadores.get(0).getSustoPuntos());
				}

				if (!oleadaInfo.dificultadAumentada) {
					aumentarDificultad();
				}

				if (!oleadaInfo.mejoraEfectuada) {
					chequearAumentoEstadisticas();
				}

				if (!((datosPartida.escapesRestantesMonstruos > 0) && (datosPartida.escapesRestantesNinios > 0)
						&& (jugadorUno.vida > 0) && (jugadorDos.vida > 0))) {
					terminarPartida();
				}
			}
		}
	}


	@Override
	public void dispose() {

		super.dispose();

		mapa.liberarMemoria();
		hud.liberarMemoria();

		jugadorUno.liberarMemoria();
		jugadorDos.liberarMemoria();

		for (ProyectilDisparado proyectilDisparado : proyectilesDisparados) {
			proyectilDisparado.proyectil.liberarMemoria();
		}

		for (Infectado infectado : infectados) {
			infectado.liberarMemoria();
		}
	}

	
	
	
	
	private void mostrarIndicadores() {

		for (Infectado infectado : infectados) {
			infectado.getIndicadorVida().renderizar();
		}

		if (oleadaInfo.oleadaEnCurso) {
			hud.getIndicadorOleada().mostrarIndicador();
		}

		hud.getIndicadorGrito().mostrarIndicador();
	}

	
	
	
	@Override
	public boolean chequearColisiones(int numAgente) {

		int i = 0;
		boolean colisiona = false;

		do {
			RectangleMapObject obj = mapa.getColisiones()[i];
			if (Globales.jugadores.get(numAgente).getRectangulo().overlaps(obj.getRectangle())) {
				colisiona = true;
			}
		} while ((!colisiona) && (++i < mapa.getColisiones().length));

		return colisiona;
	}

	@Override
	public Ascensor chequearUbicacionEnAscensor(int numAgente) {

		int i = 0;
		boolean colisiona = false;

		do {

			Ascensor ascensor = mapa.getAscensores()[i];
			Rectangle rectJug = Globales.jugadores.get(numAgente).getRectangulo();

			// Xo del jugador entre Xo y Xf del ascensor (colisiona) ???
			if (((rectJug.getX() > ascensor.getRectangulo().getX())
					&& (rectJug.getX() < ascensor.getRectangulo().getX() + ascensor.getRectangulo().getWidth())
					&& (rectJug.getY() >= ascensor.getRectangulo().getY())
					&& (rectJug.getY() < ascensor.getRectangulo().getY() + ascensor.getRectangulo().getHeight()))
					// Jugador sobrepasa la mitad del ascensor ???
					&& (rectJug.getX() <= ascensor.getRectangulo().getX()
							+ ascensor.getRectangulo().getWidth() * 0.5f)) {
				colisiona = true;
			}

			// Xf del jugador entre Xo y Xf del ascensor (colisiona) ???
			else if (((rectJug.getX() + rectJug.getWidth() > ascensor.getRectangulo().getX())
					&& (rectJug.getX() + rectJug.getWidth() < ascensor.getRectangulo().getX()
							+ ascensor.getRectangulo().getWidth())
					&& (rectJug.getY() > ascensor.getRectangulo().getY())
					&& (rectJug.getY() < ascensor.getRectangulo().getY() + ascensor.getRectangulo().getHeight()))
					// Jugador sobrepasa la mitad del ascensor ???
					&& (rectJug.getX() + rectJug.getWidth() >= ascensor.getRectangulo().getX()
							+ ascensor.getRectangulo().getWidth() * 0.5f)) {
				colisiona = true;
			}

		} while ((!colisiona) && (++i < mapa.getAscensores().length));

		return (colisiona) ? mapa.getAscensores()[i] : null;
	}

	@Override
	public void procesarMovimientoVertical(int numAgente, Ascensor ascensorOrigen) {

		Ascensores tipoDestino = null;
		Ascensor ascensorDestino = null;

		if ((Globales.jugadores.get(numAgente).controlador.arriba) && (ascensorOrigen.getArriba() != null)) {
			tipoDestino = ascensorOrigen.getArriba();
		} else if ((Globales.jugadores.get(numAgente).controlador.abajo) && (ascensorOrigen.getAbajo() != null)) {
			tipoDestino = ascensorOrigen.getAbajo();
		}

		if (tipoDestino != null) {

			int i = 0;
			boolean encontrado = false;
			do {
				Ascensor asc = mapa.getAscensores()[i];
				if (tipoDestino.getNombre().equals(asc.getTipo().getNombre())) {
					encontrado = true;
					ascensorDestino = asc;
				}
			} while ((!encontrado) && (++i < mapa.getAscensores().length));

			float nuevaPosX = ascensorDestino.getPosicion().x + ascensorDestino.getDimensiones()[0] / 2
					- jugadorUno.getDimensiones()[0] / 2;
			float nuevaPosY = ascensorDestino.getPosicion().y;

			Globales.jugadores.get(numAgente).usarAscensor(nuevaPosX, nuevaPosY);

			if (Globales.jugadores.get(numAgente).controlador.arriba) {

				Globales.servidor.enviarMensajeATodos(MensajesServidor.SUBIR_AGENTE_POR_ASCENSOR.getMensaje() + "#"
						+ numAgente + "#" + nuevaPosX + "#" + nuevaPosY);

			} else if (Globales.jugadores.get(numAgente).controlador.abajo) {

				Globales.servidor.enviarMensajeATodos(MensajesServidor.BAJAR_AGENTE_POR_ASCENSOR.getMensaje() + "#"
						+ numAgente + "#" + nuevaPosX + "#" + nuevaPosY);
			}
		}
	}

	
	
	
	@Override
	public void spawnearInfectado() {
		
		Infectado infectado;
		
		int randomInfectado = 0;
		int randomTipo = Utiles.rand.nextInt(Infectados.values().length);

		if (randomTipo == 0) {
			randomInfectado = Utiles.rand.nextInt(Ninios.values().length);
			infectado = Ninios.retornarNinio(randomInfectado);
		}

		else {
			randomInfectado = Utiles.rand.nextInt(Monstruos.values().length);
			infectado = Monstruos.retornarMonstruo(randomInfectado);
		}

		int numPuerta = Utiles.rand.nextInt(mapa.getPuertasSpawn().length);
		float x = mapa.getPuertasSpawn()[numPuerta].getPosicion().x
				+ mapa.getPuertasSpawn()[numPuerta].getDimensiones()[0] / 2;
		float y = mapa.getPuertasSpawn()[numPuerta].getPosicion().y;

		if (oleadaInfo.aumentarVelocidadInfectados) {
			aumentarVelocidadInfectado(infectado);
		}
		Globales.servidor.enviarMensajeATodos(MensajesServidor.SPAWNEAR_INFECTADO.getMensaje() + "#"
				+ infectado.getTipo().toString() + "#" + randomInfectado + "#" + x + "#" + y);
		infectado.setPosicion(x, y);
		infectados.add(infectado);
	}

	@Override
	public void chequearInfectadosEnMapa() {

		ArrayList<Infectado> infectadosFueraDeMapa = new ArrayList<Infectado>();
		ArrayList<Integer> indicesFueraDeMapa = new ArrayList<Integer>();

		Rectangle zonaEscape = mapa.getZonaEscape().getRectangle();
		PuertaSpawn puerta = mapa.getPuertasSpawn()[0];

		for (Infectado infectado : infectados) {
			int indiceInfectado = infectados.indexOf(infectado);
			float x = infectado.getPosicion().x;
			if (((infectado.controlador.mirandoDerecha) && (x >= zonaEscape.getX() + zonaEscape.getWidth()))
					|| ((infectado.controlador.mirandoIzquierda)
							&& (x + infectado.getDimensiones()[0] <= puerta.getPosicion().x))) {
				infectadosFueraDeMapa.add(infectado);
				indicesFueraDeMapa.add(indiceInfectado);
			}
		}

		for (Infectado infectadoFueraDeMapa : infectadosFueraDeMapa) {
			infectados.remove(infectadoFueraDeMapa);
		}
		
		for (Integer indice : indicesFueraDeMapa) {
			Globales.servidor.enviarMensajeATodos(MensajesServidor.ELIMINAR_INFECTADO.getMensaje() + "#" + indice);			
		}
	}

	
	@Override
	public void procesarTrayectoriaProyectiles() {

		for (ProyectilDisparado proyectilDisparado : proyectilesDisparados) {

			Proyectil proyectil = proyectilDisparado.proyectil;

			float caminoRecorrido = Globales.jugadores.get(0).getArmamento()[Globales.jugadores.get(0).armaEnUso]
					.getVelocidadDisparo() * Gdx.graphics.getDeltaTime();
			float x = proyectil.getPosicion().x
					+ ((proyectilDisparado.getDireccion() == DireccionesDisparo.IZQUIERDA) ? -caminoRecorrido
							: caminoRecorrido);
			float y = proyectil.getPosicion().y;

			proyectil.setPosicion(x, y);
			
			int indice = proyectilesDisparados.indexOf(proyectilDisparado);
			Globales.servidor.enviarMensajeATodos(MensajesServidor.ACTUALIZAR_POS_PROYECTIL.getMensaje() + "#" + indice + "#" + x + "#" + y);
		}
	}

	@Override
	public void chequearColisionProyectiles() {

		ArrayList<ProyectilDisparado> proyectilesColisionados = new ArrayList<ProyectilDisparado>();

		for (ProyectilDisparado proyectilDisparado : proyectilesDisparados) {

			boolean colision = false;
			int indiceProyectil = proyectilesDisparados.indexOf(proyectilDisparado);

			if (proyectilDisparado.proyectil.getTipo() != Proyectiles.ULTIMATE) {

				float alcance = Globales.jugadores.get(0).getArmamento()[Globales.jugadores.get(0).armaEnUso]
						.getAlcance();
				float posAlcanceX = proyectilDisparado.posicionInicial.x
						+ ((proyectilDisparado.getDireccion() == DireccionesDisparo.IZQUIERDA) ? -alcance : alcance);

				if ((proyectilDisparado.getDireccion() == DireccionesDisparo.IZQUIERDA)
						&& (proyectilDisparado.proyectil.getPosicion().x <= posAlcanceX)) {
					colision = true;
				} else if ((proyectilDisparado.getDireccion() == DireccionesDisparo.DERECHA)
						&& (proyectilDisparado.proyectil.getPosicion().x >= posAlcanceX)) {
					colision = true;
				}
			}

			else {
				if ((proyectilDisparado.getDireccion() == DireccionesDisparo.IZQUIERDA)
						&& (proyectilDisparado.proyectil.getPosicion().x
								+ proyectilDisparado.proyectil.getDimensiones()[0] < 0)) {
					colision = true;
				} else if ((proyectilDisparado.getDireccion() == DireccionesDisparo.DERECHA)
						&& (proyectilDisparado.proyectil.getPosicion().x > ConfigGraficos.ANCHO_MAPA)) {
					colision = true;
				}
			}

			if (colision) {
				proyectilesColisionados.add(proyectilDisparado);
				Globales.servidor.enviarMensajeATodos(MensajesServidor.ELIMINAR_PROYECTIL.getMensaje() + "#" + indiceProyectil);
			}
		}

		for (ProyectilDisparado proyectilColisionado : proyectilesColisionados) {
			proyectilesDisparados.remove(proyectilColisionado);
		}
	}

	@Override
	public void chequearVidaInfectados() {

		for (Infectado infectado : infectados) {

			if ((!infectado.controlador.desinfectado) && (infectado.vida == 0)) {

				infectado.controlador.desinfectado = true;
				infectado.controlador.mirandoDerecha = false;
				infectado.controlador.mirandoIzquierda = true;

				String tipo = (infectado.getTipo() == Infectados.NINIO) ? "Ninio" : "Monstruo";
				String subPaquete = (tipo.equals("Ninio")) ? "ninios" : "monstruos";

				InfectadosListables infectadoListable = null;

				try {
					infectadoListable = (InfectadosListables) Class
							.forName("com.wsi.surianodimuro.personajes." + subPaquete + "." + tipo)
							.getDeclaredMethod("getTipo" + tipo).invoke(infectado);
					jugadorUno.actualizarSustoPuntos(infectadoListable.getSustoPuntos());
					jugadorDos.actualizarSustoPuntos(infectadoListable.getSustoPuntos());
					datosPartida.puntajeTotal += infectadoListable.getSustoPuntos();
				} catch (NoSuchMethodException | SecurityException | ClassNotFoundException | IllegalAccessException
						| IllegalArgumentException | InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void chequearProyectilesImpactados() {

		ArrayList<ProyectilDisparado> proyectilesDisparadosImpactados = new ArrayList<ProyectilDisparado>();

		for (Infectado infectado : infectados) {

			int i = 0;
			boolean impactado = false;
			
			int indiceInfectado = infectados.indexOf(infectado);

			do {

				ProyectilDisparado proyectilDisparado = proyectilesDisparados.get(i);
				int indiceProyectil = proyectilesDisparados.indexOf(proyectilDisparado);
				
				if (infectado.getRectangulo().overlaps(proyectilDisparado.proyectil.getRectangulo())) {

					if (infectado.getDebilidad() == proyectilDisparado.proyectil.getTipo()) {
						proyectilesDisparadosImpactados.add(proyectilDisparado);
						Globales.servidor.enviarMensajeATodos(MensajesServidor.ELIMINAR_PROYECTIL.getMensaje() + "#" + indiceProyectil);
						if (!infectado.controlador.desinfectado) {
							Globales.servidor.enviarMensajeATodos(MensajesServidor.RESTAR_VIDA_INFECTADO.getMensaje() + "#" + indiceInfectado + "#" + (infectado.vida - 1) + "#" + proyectilDisparado.proyectil.getTipo().getRutaSonidoColision());
							infectado.restarVida();
						}
						impactado = true;
					} else if ((proyectilDisparado.proyectil.getTipo() == Proyectiles.ULTIMATE) && (!infectado.controlador.desinfectado)) {
						infectado.vida = 0;
						Globales.servidor.enviarMensajeATodos(MensajesServidor.RESTAR_VIDA_INFECTADO.getMensaje() + "#" + indiceInfectado + "#" + 0 + "#" + proyectilDisparado.proyectil.getTipo().getRutaSonidoColision());
					}
				}

			} while ((!impactado) && (++i < proyectilesDisparados.size()));
		}

		for (ProyectilDisparado proyectilDisparadoImpactado : proyectilesDisparadosImpactados) {
			proyectilesDisparados.remove(proyectilDisparadoImpactado);
		}
	}

	@Override
	public void detectarEscapes() {

		Rectangle zonaEscape = mapa.getZonaEscape().getRectangle();
		for (Infectado infectado : infectados) {
			if ((infectado.getPosicion().x) > (zonaEscape.getX() + zonaEscape.getWidth())) {
				if (infectado.getDebilidad() == Proyectiles.PROYECTIL_DESINTOX) {
					datosPartida.escapesRestantesMonstruos -= 1;
					hud.getIndicadorEscMonstruos().actualizar();
					Globales.cajaMensajes.setTexto(Mensajes.ESCAPE_MONSTRUO.getMensaje());
					Globales.servidor.enviarMensajeATodos(MensajesServidor.ESCAPE_MONSTRUO.getMensaje() + "#" + Infectados.MONSTRUO.toString());
				} else if (infectado.getDebilidad() == Proyectiles.PROYECTIL_BOO2000) {
					datosPartida.escapesRestantesNinios -= 1;
					hud.getIndicadorEscNinios().actualizar();
					Globales.cajaMensajes.setTexto(Mensajes.ESCAPE_NINIO.getMensaje());
					Globales.servidor.enviarMensajeATodos(MensajesServidor.ESCAPE_NINIOS.getMensaje() + "#" + Infectados.NINIO.toString());
				}
			}
		}
	}

	@Override
	public void detectarInfecciones() {
		
		int i = 0;
		
		do {
			Infectado infectado = infectados.get(i);
			if (infectado.vida > 0) {
				
				if ((jugadorUno.controlador.puedeInfectarse) && (infectado.getRectangulo().overlaps(jugadorUno.getRectangulo()))) {
					jugadorUno.restarVida();
					jugadorUno.controlador.puedeInfectarse = false;
					Globales.servidor.enviarMensajeATodos(MensajesServidor.INFECCION_AGENTE.getMensaje() + "#" + 0);
				}
				
				if ((jugadorDos.controlador.puedeInfectarse) && (infectado.getRectangulo().overlaps(jugadorDos.getRectangulo()))) {
					jugadorDos.restarVida();
					jugadorDos.controlador.puedeInfectarse = false;
					Globales.servidor.enviarMensajeATodos(MensajesServidor.INFECCION_AGENTE.getMensaje() + "#" + 1);
				}
			}
		} while (++i < infectados.size());
	}

	
	
	
	
	@Override
	public void aumentarVida() {
		jugadorUno.sumarVida();
		jugadorDos.sumarVida();
		hud.getIndicadorVidasJugUno().actualizar();
		hud.getIndicadorVidasJugDos().actualizar();
	}

	@Override
	public void aumentarRapidez() {
		jugadorUno.incrementarVelocidad();
		jugadorDos.incrementarVelocidad();
	}

	@Override
	public void aumentarAlcance() {
		for (int i = 0; i < jugadorUno.getArmamento().length; i++) {
			jugadorUno.getArmamento()[i].aumentarAlcance();
		}
		for (int i = 0; i < jugadorDos.getArmamento().length; i++) {
			jugadorDos.getArmamento()[i].aumentarAlcance();
		}
	}

	@Override
	public void aumentarVelDisparo() {
		for (int i = 0; i < jugadorUno.getArmamento().length; i++) {
			jugadorUno.getArmamento()[i].aumentarVelocidadDisparo();
		}
		for (int i = 0; i < jugadorDos.getArmamento().length; i++) {
			jugadorDos.getArmamento()[i].aumentarVelocidadDisparo();
		}
	}

	
	
	
	@Override
	public void aumentarDuracionOleada() {
		TiempoProcesos.duracionOleada += 10;
	}

	@Override
	public void aumentarVelocidadSpawn() {
		if (TiempoProcesos.tpoRetardoSpawns >= .5f) {
			TiempoProcesos.tpoRetardoSpawns -= .5f;
		}
	}

	@Override
	public void aumentarVelocidadInfectado(Infectado infectado) {
		for (int i = 0; i < oleadaInfo.aumentoDeVelocidad; i++) {
			infectado.incrementarVelocidad();
		}
	}

	@Override
	public void aumentarDificultad() {

		oleadaInfo.aumentarSpawns = (oleadaInfo.numOleada % oleadaInfo.INTERVALO_OLEADAS_AUMENTO_SPAWNS == 0);
		oleadaInfo.aumentarDuracionOleada = (oleadaInfo.numOleada % oleadaInfo.INTERVALO_OLEADAS_AUMENTO_DURACION == 0);
		oleadaInfo.aumentarVelocidadInfectados = (oleadaInfo.numOleada
				% oleadaInfo.INTERVALO_OLEADAS_AUMENTO_VELOCIDAD == 0);

		if (oleadaInfo.aumentarVelocidadInfectados) {
			oleadaInfo.aumentoDeVelocidad = oleadaInfo.numOleada / oleadaInfo.INTERVALO_OLEADAS_AUMENTO_VELOCIDAD;
		}

		if (oleadaInfo.aumentarSpawns) {
			aumentarVelocidadSpawn();
			oleadaInfo.dificultadAumentada = true;
		}
		if (oleadaInfo.aumentarDuracionOleada) {
			aumentarDuracionOleada();
			oleadaInfo.dificultadAumentada = true;
		}
	}

	@Override
	public void chequearAumentoEstadisticas() {

		oleadaInfo.mejoraRapidez = (oleadaInfo.numOleada % oleadaInfo.INTERVALO_OLEADAS_MEJORA_RAPIDEZ == 0);
		oleadaInfo.mejoraVelDisparo = (oleadaInfo.numOleada % oleadaInfo.INTERVALO_OLEADAS_MEJORA_VEL_DISP == 0);
		oleadaInfo.mejoraAlcance = (oleadaInfo.numOleada % oleadaInfo.INTERVALO_OLEADAS_MEJORA_ALCANCE == 0);
		oleadaInfo.mejoraVida = (oleadaInfo.numOleada % oleadaInfo.INTERVALO_OLEADAS_MEJORA_VIDA == 0);

		if (oleadaInfo.mejoraRapidez) {
			aumentarRapidez();
			oleadaInfo.mejoraEfectuada = true;
		}
		if (oleadaInfo.mejoraVelDisparo) {
			aumentarVelDisparo();
			oleadaInfo.mejoraEfectuada = true;
		}
		if (oleadaInfo.mejoraAlcance) {
			aumentarAlcance();
			oleadaInfo.mejoraEfectuada = true;
		}
		if (oleadaInfo.mejoraVida) {
			aumentarVida();
			oleadaInfo.mejoraEfectuada = true;
		}
	}
	
	
	
	
	@Override
	public void comenzarJuego() {
		InfoRed.conexionGlobalEstablecida = true;
	}

	@Override
	public void cerrarJuego() {
		InfoRed.conexionGlobalEstablecida = false;
	}
	
	@Override
	public void reiniciarJuego() {

		oleadaInfo = new OleadaInfo();
		datosPartida = new DatosPartida();
		infectados = new ArrayList<Infectado>();
		proyectilesDisparados = new ArrayList<ProyectilDisparado>();

		Globales.jugadores.remove(jugadorUno);
		Globales.jugadores.remove(jugadorDos);

		jugadorUno = new AgenteUno();
		jugadorDos = new AgenteDos();
		Globales.jugadores.add(jugadorUno);
		Globales.jugadores.add(jugadorDos);

		Globales.oleadaInfo = oleadaInfo;
		Globales.datosPartida = datosPartida;

		Globales.infectados = infectados;
		Globales.proyectilesDisparados = proyectilesDisparados;

		jugadorUno.setPosicion(ConfigGraficos.ANCHO_MAPA - jugadorUno.getDimensiones()[0] - 10,
				mapa.getTienda().getPosicion().y);
		jugadorDos.setPosicion(ConfigGraficos.ANCHO_MAPA - jugadorUno.getDimensiones()[0] - 10,
				mapa.getTienda().getPosicion().y);

		timer = new Timer();
		hud = new HudMultiJug(mapa.getElemsHud());

		Globales.cajaMensajes = hud.getCajaMensajes();
		
		TiempoProcesos.resetearTiempos();
	}
	
	@Override
	public void terminarPartida() {
//		System.out.println("-> Juego terminado");
		datosPartida.terminada = true;
		Globales.servidor.enviarMensajeATodos(MensajesServidor.ACTUALIZAR_PUNTOS_TOTALES.getMensaje() + "#" + datosPartida.puntajeTotal);
		Globales.servidor.enviarMensajeATodos(MensajesServidor.TERMINAR_JUEGO.getMensaje());
	}
	
	
	

	@Override
	public void moverAgenteIzquierda(int numAgente) {

		Globales.jugadores.get(numAgente).controlador.caminando = true;
		Globales.jugadores.get(numAgente).controlador.mirandoIzquierda = true;
		Globales.jugadores.get(numAgente).controlador.mirandoDerecha = false;
		Globales.jugadores.get(numAgente).controlador.izquierda = true;
		Globales.jugadores.get(numAgente).moverseIzquierda();

		if (chequearColisiones(numAgente)) {
			Globales.jugadores.get(numAgente).moverseDerecha();
		} else {
			Globales.servidor.enviarMensajeATodos(MensajesServidor.MOVER_AGENTE_IZQUIERDA.getMensaje() + "#" + numAgente);
		}
	}
	
	@Override
	public void moverAgenteDerecha(int numAgente) {

		Globales.jugadores.get(numAgente).controlador.caminando = true;
		Globales.jugadores.get(numAgente).controlador.mirandoDerecha = true;
		Globales.jugadores.get(numAgente).controlador.mirandoIzquierda = false;
		Globales.jugadores.get(numAgente).controlador.derecha = true;
		Globales.jugadores.get(numAgente).moverseDerecha();

		if (chequearColisiones(numAgente)) {
			Globales.jugadores.get(numAgente).moverseIzquierda();
		} else {
			Globales.servidor.enviarMensajeATodos(MensajesServidor.MOVER_AGENTE_DERECHA.getMensaje() + "#" + numAgente);
		}
	}
	
	@Override
	public void subirAgentePorAscensor(int numAgente) {

		Globales.jugadores.get(numAgente).controlador.arriba = true;

		Ascensor ascensorOrigen = chequearUbicacionEnAscensor(numAgente);
		if (ascensorOrigen != null) {
			procesarMovimientoVertical(numAgente, ascensorOrigen);
		}
	}
	
	@Override
	public void bajarAgentePorAscensor(int numAgente) {

		Globales.jugadores.get(numAgente).controlador.abajo = true;

		Ascensor ascensorOrigen = chequearUbicacionEnAscensor(numAgente);
		if (ascensorOrigen != null) {
			procesarMovimientoVertical(numAgente, ascensorOrigen);
		}
	}
	
	@Override
	public void cambiarArmaAgente(int numAgente, int numArmaNueva) {

		Globales.jugadores.get(numAgente).armaEnUso = numArmaNueva;
		Globales.servidor.enviarMensajeATodos(
				MensajesServidor.CAMBIAR_ARMA_AGENTE.getMensaje() + "#" + numAgente + "#" + numArmaNueva);
	}
	
	@Override
	public void dispararProyectil(int numAgente) {

		Globales.jugadores.get(numAgente).controlador.disparando = true;
		Globales.jugadores.get(numAgente).dispararProyectil();
		Globales.jugadores.get(numAgente).controlador.puedeDisparar = false;
		Globales.servidor.enviarMensajeATodos(MensajesServidor.DISPARAR_PROYECTIL.getMensaje() + "#" + numAgente);
	}

	@Override
	public void dispararUltimate(int numAgente) {

		Globales.jugadores.get(numAgente).controlador.disparando = true;
		Globales.jugadores.get(numAgente).dispararUltimate();
		Globales.jugadores.get(numAgente).actualizarSustoPuntos(-Globales.oleadaInfo.GRITOS_ULTIMATE);
		Globales.jugadores.get(numAgente).controlador.puedeDisparar = false;
		Globales.servidor.enviarMensajeATodos(MensajesServidor.DISPARAR_ULTIMATE.getMensaje() + "#" + numAgente);
	}

	@Override
	public void mantenerAgenteQuieto(int numAgente) {
		Globales.jugadores.get(numAgente).controlador.caminando = false;
		Globales.servidor.enviarMensajeATodos(MensajesServidor.MANTENER_AGENTE_QUIETO.getMensaje() + "#" + numAgente);
	}

	@Override
	public void pararFuegoAgente(int numAgente) {
		Globales.jugadores.get(numAgente).controlador.disparando = false;
		Globales.servidor.enviarMensajeATodos(MensajesServidor.PARAR_FUEGO_AGENTE.getMensaje() + "#" + numAgente);
	}

	@Override
	public void resetearEstadosAgente(int numAgente) {
		Globales.jugadores.get(numAgente).controlador.resetearEstados();
		Globales.servidor.enviarMensajeATodos(MensajesServidor.RESETEAR_ESTADOS_AGENTE.getMensaje() + "#" + numAgente);
	}
}
