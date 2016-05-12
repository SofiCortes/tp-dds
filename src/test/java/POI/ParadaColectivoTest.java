package POI;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uqbar.geodds.Point;

public class ParadaColectivoTest {
	DateTime unHorarioCualquiera;
	Banco bancoValido;
	ParadaColectivo paradaValida;
	Point posicionLejanaParadaValida;
	Point posicionCercanaParadaValida;
	
	@Before
	public void init() {
		
		unHorarioCualquiera=FixtureParadaColectivo.dameUnHorarioCualquiera();
		paradaValida=FixtureParadaColectivo.dameUnaParadaValida();
		posicionLejanaParadaValida = FixtureParadaColectivo.dameUnaPosicionLejanaParadaValida();
		posicionCercanaParadaValida = FixtureParadaColectivo.dameUnaPosicionCercanaParadaValida();
	}
	

	@Test
	public void paradaValidaEstaDisponibleEnCualquierHorario() {
		Assert.assertTrue(paradaValida.estaDisponible(unHorarioCualquiera));
	}

	@Test
	public void paradaValidaNoEstaCercaDeUnaPosicionLejana() {
		Assert.assertFalse(paradaValida.estasCerca(posicionLejanaParadaValida));
	}
	@Test
	public void paradaValidaEstasCercaDeUnaPosicionCercana(){
		Assert.assertTrue(paradaValida.estasCerca(posicionCercanaParadaValida));
	}

    
}
