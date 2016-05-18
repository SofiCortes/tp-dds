package fixtures;

import java.util.ArrayList;
import java.util.List;

import org.uqbar.geodds.Point;

import tpaPOIs.Comuna;

public class FixtureComuna {

	public FixtureComuna() {
	}

	public static Point damePosicionIncluidaComunaValida() {
		return new Point(-34.6327475, -58.4851585);
	}

	public static Point damePosicionNoIncluidaComunaValida() {
		return new Point(-34.6184994, -58.4368164);
	}

	static Point damePosicionIncluidaOtraComunaValida() {
		return new Point(-34.6229418, -58.4146764);
	}

	static Point damePosicionNoIncluidaOtraComunaValida() {
		return new Point(-34.6327475, -58.4851585);
	}

	private static List<Point> limitesComuna10= new ArrayList<Point>() {
		{
			add(new Point(-34.611015,-58.529025));add(new Point(-34.615256,-58.531221));add(new Point(-34.634217,-58.529806));add(new Point(-34.634495,-58.510883));add(new Point(-34.639959,-58.509675));add(new Point(-34.645743,-58.502325));add(new Point(-34.643387,-58.497065));add(new Point(-34.644764,-58.495284));add(new Point(-34.636925,-58.478568));add(new Point(-34.638797,-58.476058));add(new Point(-34.636537,-58.471423));add(new Point(-34.622482,-58.477860));add(new Point(-34.624637,-58.482710));add(new Point(-34.614536,-58.495627));add(new Point(-34.609238,-58.500219));add(new Point(-34.616726,-58.513008));add(new Point(-34.620399,-58.516870));}};

	private static List<Point> limitesComuna5 = new ArrayList<Point>() {
		{
			add(new Point(-34.598322,-58.412213));add(new Point(-34.602632,-58.432984));add(new Point(-34.639930,-58.423477));add(new Point(-34.637734,-58.411375));}};

	public static Comuna comuna10 = new Comuna(10, limitesComuna10);

	public static Comuna comuna5 = new Comuna(5, limitesComuna5);

	public static Comuna dameComunaValida() {
		return comuna10;
	}

	public static Comuna dameOtraComunaValida() {
		return comuna5;
	}

	public static Comuna dameComuna10() {
		return comuna10;
	}

	public static Comuna dameComuna5(){
		return comuna5;
	}
}
