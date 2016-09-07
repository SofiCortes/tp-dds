package pois;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.uqbar.geodds.Point;

import eventosBusqueda.ResultadoBusqueda;
import eventosBusqueda.InteresadoEnBusquedas;
import herramientas.ManejadorDeFechas;

public class Dispositivo {
	private String nombre;
	private Point posicion;
	private ManejadorDePois manejadorDePois;
	private List<InteresadoEnBusquedas> observers;

	public Dispositivo(String unNombre, Point unaPosicion) {
		this.nombre = unNombre;
		this.posicion = unaPosicion;
		this.manejadorDePois = ManejadorDePois.getInstance();
		this.observers = new ArrayList<InteresadoEnBusquedas>();
	}

	public List<InteresadoEnBusquedas> getObservers() {
		return observers;
	}

	public Boolean estoyCercaDe(POI poi) {
		return poi.estasCerca(this.posicion);
	}

	public List<POI> buscarPOIs(String descripcion) {
		DateTime inicio = DateTime.now();
		
		List<POI> listaPoisEncontrados = manejadorDePois.buscarPOIs(descripcion);
		
		DateTime fin = DateTime.now();
		
		Double demoraEnSegundos = ManejadorDeFechas.obtenerDuracionIntervaloEnSegundos(inicio,fin);
		
		ResultadoBusqueda unaBusqueda = new ResultadoBusqueda(this.nombre, listaPoisEncontrados, DateTime.now(), demoraEnSegundos,
				descripcion);
		
		this.notificarBusqueda(unaBusqueda);
		
		return listaPoisEncontrados;
	}
	
	private void notificarBusqueda(ResultadoBusqueda unaBusqueda) {
		observers.stream().forEach(observer -> observer.notificarBusqueda(unaBusqueda));
		
	}

	public void agregarInteresadoEnBusquedas(InteresadoEnBusquedas unInteresado){
		observers.add(unInteresado);
	}
	
	public void eliminarInteresadoEnBusquedas(InteresadoEnBusquedas unInteresado){
		observers.remove(unInteresado);
	}

	public Point getPosicion() {
		return posicion;
	}
	
	
}




















