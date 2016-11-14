package pois;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.joda.time.LocalTime;

import herramientas.ManejadorDeStrings;
import poisBusqueda.POIDTO;

@Entity
@Table(name = "paradas_colectivo")
public class ParadaColectivo extends POI {
	
	private Integer linea;
	
	@SuppressWarnings("unused")
	private ParadaColectivo(){}

	public ParadaColectivo(Integer unaLinea, Posicion posicion, String nombre, Direccion direccion,
			List<String> etiquetas) {
		this.linea = unaLinea;
		this.setNombre(nombre);
		this.setHorarios(new ArrayList<FranjaHoraria>() {
			{
				add(new FranjaHoraria(1, new LocalTime(0, 0), new LocalTime(23, 59)));
				add(new FranjaHoraria(2, new LocalTime(0, 0), new LocalTime(23, 59)));
				add(new FranjaHoraria(3, new LocalTime(0, 0), new LocalTime(23, 59)));
				add(new FranjaHoraria(4, new LocalTime(0, 0), new LocalTime(23, 59)));
				add(new FranjaHoraria(5, new LocalTime(0, 0), new LocalTime(23, 59)));
				add(new FranjaHoraria(6, new LocalTime(0, 0), new LocalTime(23, 59)));
				add(new FranjaHoraria(7, new LocalTime(0, 0), new LocalTime(23, 59)));
			}
		});
		this.setDireccion(direccion);
		this.setEtiquetas(etiquetas);
		this.setPosicion(posicion);
	}

	@Override
	protected Double condicionDeCercania() {
		return 0.1;
	}
	
	@Override
	public Boolean condicionDeBusqueda(String descripcion) {
		return ManejadorDeStrings.estaIncluido(linea.toString(), descripcion);
	}

	public Integer getLinea() {
		return linea;
	}

	public void setLinea(Integer linea) {
		this.linea = linea;
	}
	
	@Override
	protected void agregarDatosEspecificosDelPOI(POIDTO poiDto) {
		poiDto.setLineaColectivo(linea);
		poiDto.setTipoPOI("PARADACOLECTIVO");
	}

	@Override
	public String getTipo() {
		return "Parada Colectivo";
	}
}
