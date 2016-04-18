package POI;

import org.joda.time.DateTime;
import org.uqbar.geodds.Point;

public class Banco extends POI {
	
	private static final int VIERNES = 5;
	private static final int LUNES = 1;

	public boolean estaDiponibleEn(DateTime momento) {
		if (momento.getDayOfWeek() >= LUNES && momento.getDayOfWeek() <= VIERNES && momento.getHourOfDay() >= 10
				&& momento.getHourOfDay() <= 15) {
			return true;
		} else
			return false;
	}
	
	/*public Boolean estasCerca(Point unaPosicion){
		return .isInside(unaPosicion);
	}
	*/
}
