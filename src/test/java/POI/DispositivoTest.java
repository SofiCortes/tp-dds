package POI;

import org.joda.time.DateTime;
import org.joda.time.LocalTime;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uqbar.geodds.Point;

public class DispositivoTest {

	private Dispositivo dispositivo;
	private Point posicionDispositivo;

	private CGP cgpComuna10;
	private Direccion direccionCgpComuna10;
	private Point posicionCgpComuna10;
	private List<Servicio> serviciosCGP10;
	private List<String> etiquetasCGP10;

	private Servicio rentas;
	private Servicio asesoramientoJuridico;
	private Servicio ecobici;

	private Comuna comuna10;
	private List<Point> limitesComuna10;

	private Banco bancoProvincia;
	private Direccion direccionBancoProvincia;
	private Point posicionBancoProvincia;
	private List<String> etiquetasBancoProvincia;

	private Comercio elHalcon;
	private Direccion direccionElHalcon;
	private Point posicionElHalcon;
	private Rubro restaurant;
	private List<FranjaHoraria> horariosElHalcon;
	private List<String> etiquetasElHalcon;

	private ParadaColectivo parada114Segurola;
	private Direccion direccionParada114Segurola;
	private Point posicionParada114Segurola;
	private ParadaColectivo parada114Mercedes;
	private Direccion direccionParada114Mercedes;
	private Point posicionParada114Mercedes;
	private List<String> etiquetasParada114Segurola;

	private List<POI> listaPoisDispositivo;
	private DateTime lunes4abril10am;
	private DateTime martes5abril2am;
	private List<FranjaHoraria> horariosRentas;
	private List<FranjaHoraria> horariosAsesoramientoJuridico;
	private List<FranjaHoraria> horariosEcobici;
	private Point posicionMcDonalds;
	private Direccion direccionMcDonalds;
	private ArrayList<String> etiquetasMcDonalds;
	private Comercio mcDonalds;
	private ArrayList<FranjaHoraria> horariosAsesoramientoLegal;
	private Servicio asesoramientoLegal;
	private ArrayList<Point> limitesComuna5;
	private Direccion direccionCgpComuna5;
	private Point posicionCgpComuna5;
	private ArrayList<Servicio> serviciosCGP5;
	private ArrayList<String> etiquetasCGP5;
	private Comuna comuna5;
	private CGP cgpComuna5;
	private ArrayList<String> etiquetasParada114Mercedes;

	@Before
	public void init() {

		lunes4abril10am = new DateTime(2016, 4, 4, 10, 0);
		martes5abril2am = new DateTime(2016, 4, 5, 2, 30);

		posicionDispositivo = new Point(-34.631402, -58.488060);
		dispositivo = new Dispositivo(posicionDispositivo);

		posicionParada114Segurola = new Point(-34.631997, -58.484737);
		direccionParada114Segurola = new Direccion("Av. Segurola", 230, "Bacacay", "Bogota", null, null, 1407, "CABA",
				"Floresta", "CABA", "Argentina");
		etiquetasParada114Segurola = new ArrayList<String>() {
			{
				add("Parada");
				add("parada");
				add("114");
				add("colectivo");
				add("Segurola");
				add("segurola");
			}
		};
		
		etiquetasParada114Mercedes = new ArrayList<String>() {
			{
				add("Parada");
				add("parada");
				add("114");
				add("colectivo");
				add("Mercedes");
				add("mercedes");
			}
		};
		
		parada114Segurola = new ParadaColectivo(114, posicionParada114Segurola, "114", direccionParada114Segurola,
				etiquetasParada114Segurola);

		posicionParada114Mercedes = new Point(-34.6334512, -58.4839027);
		direccionParada114Mercedes = new Direccion("Mercedes", 17, "Av. Rivadavia", "Yerbal", null, null, 1407, "CABA",
				"Floresta", "CABA", "Argentina");

		parada114Mercedes = new ParadaColectivo(114, posicionParada114Mercedes, "114", direccionParada114Mercedes,
				etiquetasParada114Mercedes);

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

		posicionCgpComuna10 = new Point(-34.6369004, -58.4959096);
		direccionCgpComuna10 = new Direccion("Bacacay", 3968, "Campana", "Concordia", null, null, 1407, "CABA",
				"Floresta", "CABA", "Argentina");

		horariosRentas = new ArrayList<FranjaHoraria>() {
			{
				add(new FranjaHoraria(1, new LocalTime(9, 30), new LocalTime(14, 0)));
				add(new FranjaHoraria(2, new LocalTime(9, 30), new LocalTime(14, 0)));
				add(new FranjaHoraria(3, new LocalTime(9, 30), new LocalTime(14, 0)));
				add(new FranjaHoraria(4, new LocalTime(9, 30), new LocalTime(14, 0)));
				add(new FranjaHoraria(5, new LocalTime(9, 30), new LocalTime(14, 0)));
			}
		};

		horariosAsesoramientoJuridico = new ArrayList<FranjaHoraria>() {
			{
				add(new FranjaHoraria(2, new LocalTime(9, 0), new LocalTime(16, 0)));
			}
		};

		horariosEcobici = new ArrayList<FranjaHoraria>() {
			{
				add(new FranjaHoraria(1, new LocalTime(10, 0), new LocalTime(15, 0)));
				add(new FranjaHoraria(2, new LocalTime(10, 0), new LocalTime(15, 0)));
				add(new FranjaHoraria(3, new LocalTime(10, 0), new LocalTime(15, 0)));
				add(new FranjaHoraria(4, new LocalTime(10, 0), new LocalTime(15, 0)));
				add(new FranjaHoraria(5, new LocalTime(10, 0), new LocalTime(15, 0)));
			}
		};
		
		horariosAsesoramientoLegal = new ArrayList<FranjaHoraria>() {
			{
				add(new FranjaHoraria(1, new LocalTime(10, 0), new LocalTime(17, 0)));
				add(new FranjaHoraria(3, new LocalTime(10, 0), new LocalTime(17, 0)));
			}
		};

		rentas = new Servicio("Rentas", horariosRentas);
		asesoramientoJuridico = new Servicio("asesoramiento juridico", horariosAsesoramientoJuridico);
		ecobici = new Servicio("Ecobici", horariosEcobici);
		asesoramientoLegal = new Servicio("asesoramiento legal", horariosAsesoramientoLegal);

		serviciosCGP10 = new ArrayList<Servicio>() {
			{
				add(rentas);
				add(asesoramientoJuridico);
				add(ecobici);
			}
		};
		etiquetasCGP10 = new ArrayList<String>() {
			{
				add("CGP");
				add("Cgp");
				add("cgp");
				add("rentas");
				add("asesoramiento");
				add("juridico");
				add("ecobici");
			}
		};

		limitesComuna10 = new ArrayList<Point>() {
			{
				add(new Point(-34.611015, -58.529025));
				add(new Point(-34.615256, -58.531221));
				add(new Point(-34.634217, -58.529806));
				add(new Point(-34.634495, -58.510883));
				add(new Point(-34.639959, -58.509675));
				add(new Point(-34.645743, -58.502325));
				add(new Point(-34.643387, -58.497065));
				add(new Point(-34.644764, -58.495284));
				add(new Point(-34.636925, -58.478568));
				add(new Point(-34.638797, -58.476058));
				add(new Point(-34.636537, -58.471423));
				add(new Point(-34.622482, -58.477860));
				add(new Point(-34.624637, -58.482710));
				add(new Point(-34.614536, -58.495627));
				add(new Point(-34.609238, -58.500219));
				add(new Point(-34.616726, -58.513008));
				add(new Point(-34.620399, -58.516870));
			}
		};

		comuna10 = new Comuna(10, limitesComuna10);

		cgpComuna10 = new CGP(serviciosCGP10, comuna10, posicionCgpComuna10, "CGP Comuna 10", direccionCgpComuna10,
				etiquetasCGP10);
		
		posicionCgpComuna5 = new Point(-34.6229418, -58.4146764);
		direccionCgpComuna5 = new Direccion("Carlos Calvo", 3307, "Virrey Liniers", "Sanchez de Loria", null, null, 1230, "CABA",
				"Boedo", "CABA", "Argentina");
		
		limitesComuna5 = new ArrayList<Point>() {
			{
				add(new Point(-34.598322, -58.412213));
				add(new Point(-34.602632, -58.432984));
				add(new Point(-34.639930, -58.423477));
				add(new Point(-34.637734, -58.411375));
			}
		};
		
		serviciosCGP5 = new ArrayList<Servicio>() {
			{
				add(rentas);
				add(asesoramientoLegal);
				add(ecobici);
			}
		};
		
		etiquetasCGP5 = new ArrayList<String>() {
			{
				add("CGP");
				add("Cgp");
				add("cgp");
				add("rentas");
				add("asesoramiento");
				add("legal");
				add("ecobici");
			}
		};
		
		comuna5 = new Comuna(5, limitesComuna5);

		cgpComuna5 = new CGP(serviciosCGP5, comuna5, posicionCgpComuna5, "CGP Comuna 5", direccionCgpComuna5,
				etiquetasCGP5);

		posicionElHalcon = new Point(-34.6327106, -58.4877209);
		direccionElHalcon = new Direccion("Av. Rivadavia", 8451, "Mercedes", "Av. Segurola", null, null, 1407, "CABA",
				"Floresta", "CABA", "Argentina");
		horariosElHalcon = new ArrayList<FranjaHoraria>() {
			{
				add(new FranjaHoraria(1, new LocalTime(9, 30), new LocalTime(13, 0)));
				add(new FranjaHoraria(1, new LocalTime(14, 30), new LocalTime(19, 0)));
				add(new FranjaHoraria(2, new LocalTime(9, 30), new LocalTime(13, 0)));
				add(new FranjaHoraria(2, new LocalTime(14, 30), new LocalTime(19, 0)));
				add(new FranjaHoraria(3, new LocalTime(9, 30), new LocalTime(13, 0)));
				add(new FranjaHoraria(3, new LocalTime(14, 30), new LocalTime(19, 0)));
				add(new FranjaHoraria(4, new LocalTime(9, 30), new LocalTime(13, 0)));
				add(new FranjaHoraria(4, new LocalTime(14, 30), new LocalTime(19, 0)));
				add(new FranjaHoraria(5, new LocalTime(9, 30), new LocalTime(13, 0)));
				add(new FranjaHoraria(5, new LocalTime(14, 30), new LocalTime(19, 0)));
				add(new FranjaHoraria(6, new LocalTime(10, 0), new LocalTime(14, 0)));

			}
		};
		etiquetasElHalcon = new ArrayList<String>() {
			{
				add("almuerzo");
				add("cena");
				add("comida");
				add("el");
				add("halcon");
				add("efectivo");
				add("tarjeta");
				add("credito");
				add("debito");
			}
		};

		restaurant = new Rubro("Restaurant", 0.3);
		elHalcon = new Comercio(restaurant, horariosElHalcon, posicionElHalcon, "El Halcon", direccionElHalcon,
				etiquetasElHalcon);

		posicionMcDonalds = new Point(-34.6184994, -58.4368164);
		direccionMcDonalds = new Direccion("Av. Acoyte", 88, "Av. Rivadavia", "Yerbal", null, null, 1424, "CABA",
				"Caballito", "Buenos Aires", "Argentina");
		etiquetasMcDonalds = new ArrayList<String>() {
			{
				add("comida");
				add("rapida");
				add("local");
				add("hamburguesa");
				add("tarjeta");
				add("credito");
				add("debito");
			}
		};
		mcDonalds = new Comercio(restaurant, null, posicionMcDonalds, "McDonalds Acoyte", direccionMcDonalds,
				etiquetasMcDonalds);

		listaPoisDispositivo = new ArrayList<POI>() {
			{
				add(parada114Segurola);
				add(parada114Mercedes);
				add(elHalcon);
				add(mcDonalds);
				add(bancoProvincia);
				add(cgpComuna10);
				add(cgpComuna5);
			}
		};

		Dispositivo.setListaPois(listaPoisDispositivo);
	}

	@Test
	public void bancoProvinciaEstaDisponible() {
		Assert.assertTrue(bancoProvincia.estaDisponible(lunes4abril10am));
	}

	@Test
	public void bancoProvinciaNoEstaDisponible() {
		Assert.assertFalse(bancoProvincia.estaDisponible(martes5abril2am));
	}

	@Test
	public void colectivoEstaDisponible() {
		Assert.assertTrue(parada114Segurola.estaDisponible(martes5abril2am));
	}

	@Test
	public void estanLasDosParadasDel114() {
		Assert.assertEquals(2, (dispositivo.buscarPOIs("114").size()), 0);
		Assert.assertTrue((dispositivo.buscarPOIs("114")).contains(parada114Segurola));
		Assert.assertTrue((dispositivo.buscarPOIs("114")).contains(parada114Mercedes));
	}

	@Test
	public void macowinsNoEstaEnListaPois() {
		Assert.assertTrue(dispositivo.buscarPOIs("macowins").isEmpty());
	}

	@Test
	public void encuentraTodosLosRestaurants() {
		Assert.assertEquals(2, (dispositivo.buscarPOIs("Restaurant").size()), 0);
		Assert.assertTrue((dispositivo.buscarPOIs("Restaurant")).contains(mcDonalds));
		Assert.assertTrue((dispositivo.buscarPOIs("Restaurant")).contains(elHalcon));
	}
	
	@Test
	public void encuentraTodosLosPOIsEtiquetadosConPalabraClave() {
		Assert.assertEquals(3, (dispositivo.buscarPOIs("tarjeta de credito").size()), 0);
		Assert.assertTrue((dispositivo.buscarPOIs("tarjeta de credito")).contains(mcDonalds));
		Assert.assertTrue((dispositivo.buscarPOIs("tarjeta de credito")).contains(elHalcon));
		Assert.assertTrue((dispositivo.buscarPOIs("tarjeta de credito")).contains(bancoProvincia));
	}
	
	@Test
	public void noEncuentraPOIsCuandoNingunPOITieneLaEtiqueta() {
		Assert.assertTrue(dispositivo.buscarPOIs("negra").isEmpty());
	}
	
	@Test
	public void encuentraTodosLosCGPsEtiquetadosConPalabraClave() {
		Assert.assertEquals(2, (dispositivo.buscarPOIs("asesoramiento").size()), 0);
		Assert.assertTrue((dispositivo.buscarPOIs("asesoramiento")).contains(cgpComuna10));
		Assert.assertTrue((dispositivo.buscarPOIs("asesoramiento")).contains(cgpComuna5));
	}
}
