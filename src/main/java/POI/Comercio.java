package POI;

import java.util.*;
import org.uqbar.geodds.Point;

public class Comercio extends POI {

	private Rubro rubro;

	public Comercio(Rubro unRubro, List<FranjaHoraria> losHorarios, Point posicion, String nombre, Direccion direccion,
			List<String> etiquetas) {
		this.rubro = unRubro;
		this.setPosicion(posicion);
		this.setHorarios(losHorarios);
		this.setNombre(nombre);
		this.setDireccion(direccion);
		this.setEtiquetas(etiquetas);
	}

	@Override
	public Double condicionDeCercania() {
		return rubro.getCondicionDeCercania();
	}

	
	@Override
	public Boolean contiene(String descripcion) {
		return super.contiene(descripcion) || (descripcion.contains(rubro.getNombreRubro()));
	}
}
