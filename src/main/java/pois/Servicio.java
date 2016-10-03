package pois;

import java.util.List;

import javax.persistence.*;

import org.joda.time.DateTime;

import herramientas.ManejadorDeFechas;
import herramientas.ManejadorDeStrings;
import poisBusqueda.ServicioDTO;

@Entity
@Table(name = "servicios")

public class Servicio {

	@Id
	@GeneratedValue
	private Integer id;

	private String nombre;

	@CollectionTable(name= "franjas_horarias_servicios")
	@ElementCollection
	private List<FranjaHoraria> horarios;
	
	//-------------------------

	@SuppressWarnings("unused")
	private Servicio() {
	}

	public Servicio(String nombre, List<FranjaHoraria> horarios) {
		this.nombre = nombre;
		this.horarios = horarios;
	}

	public Boolean nombreSimilarA(String palabra) {
		return ManejadorDeStrings.estaIncluido(palabra, nombre);
	}

	public Boolean estaDisponible(DateTime fecha) {
		return horarios.stream().anyMatch(franjaHoraria -> ManejadorDeFechas.estaEnFranjaHoraria(fecha, franjaHoraria));
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setHorarios(List<FranjaHoraria> horarios) {
		this.horarios = horarios;
	}

	public String getNombre() {
		return nombre;
	}

	public List<FranjaHoraria> getHorarios() {
		return horarios;
	}

	public ServicioDTO dameTuDTO() {
		return new ServicioDTO(nombre, FranjaHoraria.obtenerFranjasHorariasDTO(horarios));
	}

}
