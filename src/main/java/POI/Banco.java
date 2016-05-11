package POI;

import java.util.ArrayList;
import java.util.List;
import org.joda.time.LocalTime;
import org.uqbar.geodds.Point;

public class Banco extends POI {

	public Banco(Point posicion, String nombre, Direccion direccion, List<String> etiquetas) {
		this.posicion = posicion;
		this.nombre = nombre;
		this.setHorarios(new ArrayList<FranjaHoraria>() {
			{
				add(new FranjaHoraria(1, new LocalTime(9, 00), new LocalTime(14, 59)));
				add(new FranjaHoraria(2, new LocalTime(9, 00), new LocalTime(14, 59)));
				add(new FranjaHoraria(3, new LocalTime(9, 00), new LocalTime(14, 59)));
				add(new FranjaHoraria(4, new LocalTime(9, 00), new LocalTime(14, 59)));
				add(new FranjaHoraria(5, new LocalTime(9, 00), new LocalTime(14, 59)));
				
			}
		});
		this.direccion = direccion;
		this.etiquetas = etiquetas;
	}
	
	//si hacemos el constructor privado, no le podemos mandar el mensaje new
	
	
}
