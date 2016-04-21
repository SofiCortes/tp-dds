package POI;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uqbar.geodds.Point;

public class BancoTest {
		
	private DateTime lunes4abril10am;
	private DateTime martes5abril2am;
	private Banco bancoProvincia;
	private Direccion direccionBancoProvincia;
	private Point posicionBancoProvincia;
	private List<String> etiquetasBancoProvincia;
	
	@Before
	public void init(){
	
	lunes4abril10am = new DateTime(2016, 4, 4, 10, 0);
	martes5abril2am = new DateTime(2016, 4, 5, 2, 30);
	
	posicionBancoProvincia = new Point(-34.6327475, -58.4851585);
	direccionBancoProvincia = new Direccion("Av. Rivadavia", 8468, "Benedetti", "Mariano Acosta", null, null, 1407,
			"CABA", "Floresta", "CABA", "Argentina");
	etiquetasBancoProvincia = new ArrayList<String>() {
			{
			add("banco");
			add("provincia");
			add("depositos");
			add("extracciones");
			add("cajero");
			add("tarjeta");
			add("credito");
			add("debito");
		}
	};

	bancoProvincia = new Banco(posicionBancoProvincia, "Banco Provincia", direccionBancoProvincia,
			etiquetasBancoProvincia);
	
	}


	
	@Test
	public void bancoProvinciaEstaDisponibleAlas10am() {
		Assert.assertTrue(bancoProvincia.estaDisponible(lunes4abril10am));
	}

	@Test
	public void bancoProvinciaNoEstaDisponibleAlas2am() {
		Assert.assertFalse(bancoProvincia.estaDisponible(martes5abril2am));
	}
	

}
