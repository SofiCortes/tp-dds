package POI;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uqbar.geodds.Point;

public class ComunaTest {	

	private Comuna comunaValida;
	private Point posicionIncluidaEnComunaValida;
	private Point posicionNoIncluidaEnComunaValida;

	@Before
	public void init() {
		comunaValida = FixtureComuna.dameComunaValida();
		posicionIncluidaEnComunaValida=FixtureComuna.damePosicionIncluidaComunaValida();
		posicionNoIncluidaEnComunaValida=FixtureComuna.damePosicionNoIncluidaComunaValida();

	}

	@Test
	public void comunaIncluyeAUnaPosicionQueEstaIncluida() {
		Assert.assertTrue(comunaValida.incluyeA(posicionIncluidaEnComunaValida));
	}

	@Test
	public void comunaNoIncluyeAUnaPosicionQueNoEstaIncluida() {
		Assert.assertFalse(comunaValida.incluyeA(posicionNoIncluidaEnComunaValida));
	}

}
