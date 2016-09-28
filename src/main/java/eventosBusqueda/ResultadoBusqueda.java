package eventosBusqueda;

import java.util.List;

import javax.persistence.*;

import org.joda.time.DateTime;

import pois.POI;
import pois.POIDTO;

@Entity
public class ResultadoBusqueda {
	
	@Id @GeneratedValue
	private Integer id;

	private String nombreTerminal;
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	private List<POIDTO> poisEncontrados;
	
	private DateTime fecha;	
	private Double demoraEnSegundos;
	private String descripcionBuscada;	

	@SuppressWarnings("unused")
	private ResultadoBusqueda(){}
	
	public ResultadoBusqueda(String nombreTerminal, List<POIDTO> listaPoisEncontrados, DateTime fecha, Double demoraEnSegundos,
			String descripcionBuscada) {
		this.nombreTerminal = nombreTerminal;
		this.poisEncontrados = listaPoisEncontrados;
		this.fecha = fecha;
		this.demoraEnSegundos = demoraEnSegundos;
		this.descripcionBuscada = descripcionBuscada;
	}
	
	public Integer getId() {
		return id;
	}

	public List<POIDTO> getPoisEncontrados() {
		return poisEncontrados;
	}

	public void setPoisEncontrados(List<POIDTO> poisEncontrados) {
		this.poisEncontrados = poisEncontrados;
	}

	public void setNombreTerminal(String nombreTerminal) {
		this.nombreTerminal = nombreTerminal;
	}

	public void setFecha(DateTime fecha) {
		this.fecha = fecha;
	}

	public void setDemoraEnSegundos(Double demoraEnSegundos) {
		this.demoraEnSegundos = demoraEnSegundos;
	}

	public void setDescripcionBuscada(String descripcionBuscada) {
		this.descripcionBuscada = descripcionBuscada;
	}
	
	public String getNombreTerminal() {
		return nombreTerminal;
	}

	public Integer getCantResultados() {
		return poisEncontrados.size();
	}

	public DateTime getFecha() {
		return fecha;
	}

	public Double getDemoraEnSegundos() {
		return demoraEnSegundos;
	}

	public String getDescripcionBuscada() {
		return descripcionBuscada;
	}
		
}
