package com.wsi.surianodimuro.pantallas.juego;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.Array;
import com.wsi.surianodimuro.enumeradores.Ascensores;
import com.wsi.surianodimuro.objetos.Ascensor;
import com.wsi.surianodimuro.objetos.PuertaSpawn;
import com.wsi.surianodimuro.objetos.PuertaSpawnDos;
import com.wsi.surianodimuro.objetos.PuertaSpawnTres;
import com.wsi.surianodimuro.objetos.PuertaSpawnUno;
import com.wsi.surianodimuro.utilidades.ConfigGraficos;
import com.wsi.surianodimuro.utilidades.Imagen;

public class Mapa {

	private TmxMapLoader mapLoader;
	private TiledMap map;
	private OrthogonalTiledMapRenderer mapRenderer;

	private MapLayer capaColision;
	private RectangleMapObject[] rectangulosColisiones;
	private RectangleMapObject zonaEscape;

	private MapLayer capaAscensores;
	private Ascensor[] ascensores;

	private MapLayer capaPuertas;
	private PuertaSpawn[] puertasSpawn;

	private MapLayer capaFondos;
	private Imagen subsuelo;
	private Imagen exterior;

	private MapLayer capaTienda;
	private Imagen tienda;

	private MapLayer capaLogos;
	private Imagen logoGrande;
	private Imagen logoChico;

	private MapLayer capaPlataformas;
	private Imagen plataformaUno;
	private Imagen plataformaDos;
	private Imagen plataformaTres;

	private MapLayer capaHud;
	private MapObjects elemsHud;

	private OrthographicCamera cam;

	public Mapa(OrthographicCamera cam) {

		mapLoader = new TmxMapLoader();
		map = mapLoader.load("mapa/mapa.tmx");
		mapRenderer = new OrthogonalTiledMapRenderer(map);

		capaPlataformas = map.getLayers().get("RectangulosPlataformas");
		capaColision = map.getLayers().get("Colisiones");
		capaAscensores = map.getLayers().get("Ascensores");
		capaPuertas = map.getLayers().get("Puertas");
		capaFondos = map.getLayers().get("Fondos");
		capaTienda = map.getLayers().get("Tienda");
		capaHud = map.getLayers().get("Hud");
		capaLogos = map.getLayers().get("Logos");

		puertasSpawn = new PuertaSpawn[3];

		cargarPlataformas();
		cargarColisiones();
		cargarAscensores();
		cargarPuertasSpawn();
		cargarFondos();
		cargarTienda();
		cargarElemsHud();
		cargarLogos();

		this.cam = cam;
	}

	public void renderizar() {

		mapRenderer.setView(cam);
		mapRenderer.render();

		exterior.renderizar();
		subsuelo.renderizar();
		tienda.renderizar();

		plataformaUno.renderizar();
		plataformaDos.renderizar();
		plataformaTres.renderizar();

		for (PuertaSpawn puertaSpawn : puertasSpawn) {
			puertaSpawn.renderizar();
		}
		
		logoChico.renderizar();
		logoGrande.renderizar();
		
		for (Ascensor ascensor : ascensores) {
			ascensor.renderizar();
		}
	}

	public void liberarMemoria() {

		map.dispose();
		mapRenderer.dispose();

		exterior.liberarMemoria();
		subsuelo.liberarMemoria();
		tienda.liberarMemoria();

		plataformaUno.liberarMemoria();
		plataformaDos.liberarMemoria();
		plataformaTres.liberarMemoria();

		for (PuertaSpawn puertaSpawn : puertasSpawn) {
			puertaSpawn.liberarMemoria();
		}
		
		logoChico.liberarMemoria();
		logoGrande.liberarMemoria();

		for (Ascensor ascensor : ascensores) {
			ascensor.liberarMemoria();
		}
	}

	private void cargarPlataformas() {

		RectangleMapObject platUno = capaPlataformas.getObjects().getByType(RectangleMapObject.class).get(0);
		RectangleMapObject platDos = capaPlataformas.getObjects().getByType(RectangleMapObject.class).get(1);
		RectangleMapObject platTres = capaPlataformas.getObjects().getByType(RectangleMapObject.class).get(2);

		plataformaUno = new Imagen("mapa/assets/backgrounds/plataformaUno.png", platUno.getRectangle().getX(),
				platUno.getRectangle().getY(), platUno.getRectangle().getWidth(), platUno.getRectangle().getHeight());

		plataformaDos = new Imagen("mapa/assets/backgrounds/plataformaDos.png", platDos.getRectangle().getX() + 2,
				platDos.getRectangle().getY(), platDos.getRectangle().getWidth(), platDos.getRectangle().getHeight());

		plataformaTres = new Imagen("mapa/assets/backgrounds/plataformaTres.png", platTres.getRectangle().getX() + 2,
				platTres.getRectangle().getY(), platTres.getRectangle().getWidth(),
				platTres.getRectangle().getHeight());
	}

	private void cargarPuertasSpawn() {

		MapObjects objetosPuertasSpawn = new MapObjects();

		for (int i = 0; i < capaPuertas.getObjects().getCount(); i++) {
			MapObject objeto = capaPuertas.getObjects().get(i);
			objetosPuertasSpawn.add(objeto);
		}

		MapObject puertaUno = objetosPuertasSpawn.get(0);

		puertasSpawn[0] = new PuertaSpawnUno(Float.parseFloat(puertaUno.getProperties().get("x").toString()),
				ConfigGraficos.ALTO_MAPA - Float.parseFloat(puertaUno.getProperties().get("y").toString()),
				Float.parseFloat(puertaUno.getProperties().get("ancho").toString()),
				Float.parseFloat(puertaUno.getProperties().get("alto").toString()));

		MapObject puertaDos = objetosPuertasSpawn.get(1);
		puertasSpawn[1] = new PuertaSpawnDos(Float.parseFloat(puertaDos.getProperties().get("x").toString()),
				ConfigGraficos.ALTO_MAPA - Float.parseFloat(puertaDos.getProperties().get("y").toString()),
				Float.parseFloat(puertaDos.getProperties().get("ancho").toString()),
				Float.parseFloat(puertaDos.getProperties().get("alto").toString()));

		MapObject puertaTres = objetosPuertasSpawn.get(2);
		puertasSpawn[2] = new PuertaSpawnTres(Float.parseFloat(puertaTres.getProperties().get("x").toString()),
				ConfigGraficos.ALTO_MAPA - Float.parseFloat(puertaTres.getProperties().get("y").toString()),
				Float.parseFloat(puertaTres.getProperties().get("ancho").toString()),
				Float.parseFloat(puertaTres.getProperties().get("alto").toString()));
	}

	private void cargarFondos() {

		MapObject objetoSubsuelo = capaFondos.getObjects().get("fondo_subsuelo");
		subsuelo = new Imagen("mapa/assets/backgrounds/degrade.png",
				Float.parseFloat(objetoSubsuelo.getProperties().get("x").toString()),
				ConfigGraficos.ALTO_MAPA - Float.parseFloat(objetoSubsuelo.getProperties().get("y").toString()),
				Float.parseFloat(objetoSubsuelo.getProperties().get("ancho").toString()),
				Float.parseFloat(objetoSubsuelo.getProperties().get("alto").toString()));

		MapObject objetoExterior = capaFondos.getObjects().get("fondo_exterior");
		exterior = new Imagen("mapa/assets/backgrounds/background.png",
				Float.parseFloat(objetoExterior.getProperties().get("x").toString()),
				ConfigGraficos.ALTO_MAPA - Float.parseFloat(objetoExterior.getProperties().get("y").toString()),
				Float.parseFloat(objetoExterior.getProperties().get("ancho").toString()),
				Float.parseFloat(objetoExterior.getProperties().get("alto").toString()));
	}

	private void cargarLogos() {

		MapObject objetoLogoGrande = capaLogos.getObjects().get("logo_grande");
		logoGrande = new Imagen("mapa/assets/hud/Monsters,_Inc._logo.png",
				Float.parseFloat(objetoLogoGrande.getProperties().get("x").toString()),
				ConfigGraficos.ALTO_MAPA - Float.parseFloat(objetoLogoGrande.getProperties().get("y").toString()),
				Float.parseFloat(objetoLogoGrande.getProperties().get("ancho").toString()),
				Float.parseFloat(objetoLogoGrande.getProperties().get("alto").toString()));

		MapObject objetoLogoChico = capaLogos.getObjects().get("logo_chico");
		logoChico = new Imagen("mapa/assets/hud/logo.png",
				Float.parseFloat(objetoLogoChico.getProperties().get("x").toString()),
				ConfigGraficos.ALTO_MAPA - Float.parseFloat(objetoLogoChico.getProperties().get("y").toString()),
				Float.parseFloat(objetoLogoChico.getProperties().get("ancho").toString()),
				Float.parseFloat(objetoLogoChico.getProperties().get("alto").toString()));
	}

	private void cargarTienda() {

		MapObject objetoTienda = capaTienda.getObjects().get("tienda");
		tienda = new Imagen("mapa/assets/oficina/Oficina_Roz_Completa.png",
				Float.parseFloat(objetoTienda.getProperties().get("x").toString()),
				ConfigGraficos.ALTO_MAPA - Float.parseFloat(objetoTienda.getProperties().get("y").toString()),
				Float.parseFloat(objetoTienda.getProperties().get("ancho").toString()),
				Float.parseFloat(objetoTienda.getProperties().get("alto").toString()));
	}

	private void cargarElemsHud() {
		elemsHud = capaHud.getObjects();
	}

	private void cargarColisiones() {

		Array<RectangleMapObject> arrColisiones = capaColision.getObjects().getByType(RectangleMapObject.class);
		rectangulosColisiones = new RectangleMapObject[arrColisiones.size];

		for (int i = 0; i < rectangulosColisiones.length; i++) {
			rectangulosColisiones[i] = arrColisiones.get(i);
			if (arrColisiones.get(i).getName().equals("pared_der")) {
				zonaEscape = arrColisiones.get(i);
			}
		}
	}

	private void cargarAscensores() {

		MapObjects zonasAscensores = capaAscensores.getObjects();
		ascensores = new Ascensor[zonasAscensores.getCount()];

		for (int i = 0; i < ascensores.length; i++) {

			MapObject rect = zonasAscensores.get(i);
			float x = Float.parseFloat(rect.getProperties().get("x").toString());
			float y = Float.parseFloat(rect.getProperties().get("y").toString());
			float ancho = Float.parseFloat(rect.getProperties().get("ancho").toString());
			float alto = Float.parseFloat(rect.getProperties().get("alto").toString());
			Ascensores tipo = Ascensores.obtenerTipoAscensor(rect.getProperties().get("name").toString());

			ascensores[i] = new Ascensor(x, ConfigGraficos.ALTO_MAPA - y, ancho, alto, tipo,
					Ascensores.obtenerTipoAscensor(tipo.getArriba()), Ascensores.obtenerTipoAscensor(tipo.getAbajo()));
		}

	}

	public PuertaSpawn[] getPuertasSpawn() {
		return puertasSpawn;
	}

	public RectangleMapObject[] getColisiones() {
		return rectangulosColisiones;
	}

	public MapObjects getElemsHud() {
		return elemsHud;
	}

	public RectangleMapObject getZonaEscape() {
		return zonaEscape;
	}

	public Ascensor[] getAscensores() {
		return ascensores;
	}

	public Imagen getTienda() {
		return tienda;
	}
}
