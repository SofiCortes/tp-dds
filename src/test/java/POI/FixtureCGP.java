package POI;

import java.util.ArrayList;
import java.util.List;
import org.joda.time.DateTime;
import org.joda.time.LocalTime;
import org.uqbar.geodds.Point;

public class FixtureCGP {

	// Servicios
	private static List<FranjaHoraria> horariosRentas = new ArrayList<FranjaHoraria>() {
		{
			add(new FranjaHoraria(1, new LocalTime(9, 30), new LocalTime(14, 0)));
			add(new FranjaHoraria(2, new LocalTime(9, 30), new LocalTime(14, 0)));
			add(new FranjaHoraria(3, new LocalTime(9, 30), new LocalTime(14, 0)));
			add(new FranjaHoraria(4, new LocalTime(9, 30), new LocalTime(14, 0)));
			add(new FranjaHoraria(5, new LocalTime(9, 30), new LocalTime(14, 0)));
		}
	};
	private static List<FranjaHoraria> horariosAsesoramientoJuridico = new ArrayList<FranjaHoraria>() {
		{
			add(new FranjaHoraria(2, new LocalTime(9, 0), new LocalTime(16, 0)));
		}
	};
	private static List<FranjaHoraria> horariosAsesoramientoLegal = new ArrayList<FranjaHoraria>() {
		{
			add(new FranjaHoraria(1, new LocalTime(9, 0), new LocalTime(16, 0)));
		}
	};

	private static List<FranjaHoraria> horariosEcobici = new ArrayList<FranjaHoraria>() {
		{
			add(new FranjaHoraria(1, new LocalTime(10, 0), new LocalTime(15, 0)));
			add(new FranjaHoraria(2, new LocalTime(10, 0), new LocalTime(15, 0)));
			add(new FranjaHoraria(3, new LocalTime(10, 0), new LocalTime(15, 0)));
			add(new FranjaHoraria(4, new LocalTime(10, 0), new LocalTime(15, 0)));
			add(new FranjaHoraria(5, new LocalTime(10, 0), new LocalTime(15, 0)));
		}
	};

	private static Servicio rentas = new Servicio("Rentas", horariosRentas);
	private static Servicio asesoramientoJuridico = new Servicio("asesoramiento juridico",
			horariosAsesoramientoJuridico);
	private static Servicio asesoramientoLegal = new Servicio("asesoramiento legal", horariosAsesoramientoLegal);
	private static Servicio ecobici = new Servicio("Ecobici", horariosEcobici);

	// CGP10

	private static List<Servicio> serviciosCgp10 = new ArrayList<Servicio>() {
		{
			add(rentas);
			add(asesoramientoJuridico);
			add(ecobici);
		}
	};

	private static List<String> etiquetasCgp10 = new ArrayList<String>() {
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

	private static Comuna comuna10 = FixtureComuna.dameComuna10();

	private static Direccion direccionCgp10 = new Direccion("Bacacay", 3968, "Concordia", "Campana", null, null, 1417,
			"CABA", "Floresta", "Buenos Aires", "Argentina");

	private static Point posicionCgp10 = new Point(-34.6318455, -58.4857468);

	private static CGP cgp10 = new CGP(serviciosCgp10, comuna10, posicionCgp10, "CGP Comuna 10", direccionCgp10,
			etiquetasCgp10);

	// CGP5
	public static Point posicionCgp5 = new Point(-34.6229418, -58.4146764);

	private static List<Servicio> serviciosCGP5 = new ArrayList<Servicio>() {
		{
			add(rentas);
			add(asesoramientoLegal);
			add(ecobici);
		}
	};

	public static List<String> etiquetasCGP5 = new ArrayList<String>() {
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

	private static Comuna comuna5 = FixtureComuna.dameComuna5();

	private static Direccion direccionCgp5 = new Direccion("Carlos Calvo", 3307, "Virrey Liniers", "Sanchez de Loria",
			null, null, 1230, "CABA", "Boedo", "CABA", "Argentina");

	private static CGP cgp5 = new CGP(serviciosCGP5, comuna5, posicionCgp5, "CGP Comuna 5", direccionCgp5,
			etiquetasCGP5);

	// Posiciones
	private static Point posicionLejana = new Point(-34.6184994, -58.4368164);
	private static Point posicionCercana = new Point(-34.6327475, -58.4851585);

	// Horarios
	private static DateTime horarioValido = new DateTime(2016, 4, 4, 10, 0);
	private static DateTime horarioNoValido = new DateTime(2016, 4, 5, 2, 30);

	public static CGP dameCGPValido() {
		return cgp10;
	}

	public static CGP dameOtroCgpValido() {
		return cgp5;
	}

	public static DateTime dameHorarioValido() {
		return horarioValido;
	}

	public static DateTime dameHorarioNoValido() {
		return horarioNoValido;
	}

	public static Point damePosicionNoCercana() {
		return posicionLejana;
	}

	public static Point damePosicionCercana() {
		return posicionCercana;
	}
}
