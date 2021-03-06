package tests;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import adapters.BancoAdapter;
import adapters.CentroDTO;
import adapters.CgpAdapter;
import adapters.ComponenteExternoAdapter;
import adapters.ServicioExternoBancos;
import adapters.ServicioExternoCGP;
import static org.mockito.Mockito.*;
import fixtures.FixtureBanco;
import fixtures.FixtureBancoAdapter;
import fixtures.FixtureCGP;
import fixtures.FixtureCentroDTO;
import fixtures.FixtureComercio;
import fixtures.FixtureParadaColectivo;
import herramientas.EntityManagerHelper;
import herramientas.JedisHelper;
import herramientas.ManejadorDeFechas;
import pois.Banco;
import pois.CGP;
import pois.Comercio;
import pois.FranjaHoraria;
import pois.ManejadorDePois;
import pois.POI;
import pois.ParadaColectivo;
import redis.clients.jedis.Jedis;

@SuppressWarnings("all")
public class ManejadorDePoisTest {

	private CGP cgpValido;
	private CGP otroCgpValido;

	private Banco bancoValido;

	private Comercio comercioValido;

	private ParadaColectivo parada114Valida;
	private ParadaColectivo otraParada114Valida;

	private List<POI> listaPoisInternos;
	private DateTime horarioValidoParaRentas;
	private DateTime horarioNoValidoParaNingunServicio;
	private List<POI> CGPsConRentas;

	private ServicioExternoCGP servicioExternoCgpMockeado;
	private ArrayList<CentroDTO> centrosDTO;
	private CentroDTO centroDTO1;

	private String listaBancoJson;
	private String listaVaciaBancoJson;
	private ServicioExternoBancos servicioExternoBancoMockeado;

	private ParadaColectivo paradaQueNoEstaEnLaLista;
	private ParadaColectivo parada114ValidaConMasEtiquetas;

	private CgpAdapter cgpAdapter;
	private BancoAdapter bancoAdapter;
	private List<ComponenteExternoAdapter> listaAdapters;

	private int tamanioListaPoisInternos;
	private ManejadorDePois manejadorDePois;

	private Jedis jedis;
	private Banco bancoValidoConMismaPosicion;
	private ComponenteExternoAdapter componenteExternoAdapter;

	@Before
	public void init() {
		horarioValidoParaRentas = new DateTime(2016, 4, 4, 10, 0);
		horarioNoValidoParaNingunServicio = new DateTime(2016, 4, 5, 2, 30);

		parada114Valida = FixtureParadaColectivo.dameUnaParada114Valida();
		otraParada114Valida = FixtureParadaColectivo.dameOtraParada114Valida();

		bancoValido = FixtureBanco.dameUnBancoValido();
		bancoValidoConMismaPosicion = FixtureBanco.dameOtroBancoValidoConLaMismaPosicion();

		cgpValido = FixtureCGP.dameCGPValido();
		otroCgpValido = FixtureCGP.dameOtroCgpValido();

		comercioValido = FixtureComercio.dameComercioValido();

		listaPoisInternos = new ArrayList<POI>() {
			{
				add(parada114Valida);
				add(otraParada114Valida);
				add(bancoValido);
				add(cgpValido);
				add(otroCgpValido);
				add(comercioValido);
			}
		};

		manejadorDePois = ManejadorDePois.getInstance();

		manejadorDePois.setListaPoisInternos(listaPoisInternos);

		tamanioListaPoisInternos = listaPoisInternos.size();

		CGPsConRentas = new ArrayList<POI>();

		servicioExternoCgpMockeado = mock(ServicioExternoCGP.class);
		servicioExternoBancoMockeado = mock(ServicioExternoBancos.class);

		paradaQueNoEstaEnLaLista = FixtureParadaColectivo.dameUnaTercerParadaValida();
		parada114ValidaConMasEtiquetas = FixtureParadaColectivo.dameUnaParadaValidaConMasEtiquetas();

		// CGP Adapter
		cgpAdapter = new CgpAdapter(servicioExternoCgpMockeado);

		// Banco Adapter

		bancoAdapter = new BancoAdapter(servicioExternoBancoMockeado);

		listaAdapters = new ArrayList<ComponenteExternoAdapter>();

		manejadorDePois.setListaAdapters(listaAdapters);

		// Servicio Externo
		centroDTO1 = FixtureCentroDTO.dameCentroDTO1();
		centrosDTO = new ArrayList<CentroDTO>() {
			{
				add(centroDTO1);
			}
		};

		listaBancoJson = FixtureBancoAdapter.devolverListaBancoJsonNoVacia();
		listaVaciaBancoJson = FixtureBancoAdapter.devolverListaBancoJsonVacia();
		JedisHelper.conectarARedis();

	}

	@After
	public void after() {
		JedisHelper.limpiarBaseDeDatosRedis();
		JedisHelper.desconectarRedis();
	}

	@Test
	public void SiPersistoUnPOILuegoLoEncuentro() {

		EntityManagerHelper.persistir(cgpValido);

		Assert.assertTrue(EntityManagerHelper.contains(cgpValido));

		CGP cgpEncontrado = EntityManagerHelper.find(CGP.class, cgpValido.getId());

		Assert.assertTrue(cgpValido.getNombre().equals(cgpEncontrado.getNombre()));

	}

	@Test
	public void SiBuscoParadaQueEstaEnLaListaDePoisPorEtiquetaLaEncuentra() {
		manejadorDePois.agregarPoiInterno(parada114Valida);
		Assert.assertTrue((manejadorDePois.buscarPOIs("114")).contains(parada114Valida));
		Assert.assertTrue(EntityManagerHelper.contains(parada114Valida));
	}

	@Test
	public void SiBuscoParadasPorEtiquetaEncuentraTodasLasQueEstanEnLaListaConEsaEtiqueta() {
		List<POI> poisEncontrados = manejadorDePois.buscarPOIs("114");

		Assert.assertTrue(poisEncontrados.contains(parada114Valida));
		Assert.assertTrue(poisEncontrados.contains(otraParada114Valida));

		Assert.assertTrue(EntityManagerHelper.contains(parada114Valida));
		Assert.assertTrue(EntityManagerHelper.contains(otraParada114Valida));
	}

	@Test
	public void SiBuscoPOIsPorPalabraClaveDevuelveTodosLosQueLaTienen() {
		Assert.assertEquals(2, manejadorDePois.buscarPOIs("tarjeta de credito").size(), 0);

	}

	@Test
	public void SiBuscoCGPsPorPalabraClaveYPreguntoCuantosSonDevuelveLaCantidadDeCGPsQueLaTienen() {
		Assert.assertEquals(2, (manejadorDePois.buscarPOIs("asesoramiento").size()), 0);
	}

	@Test
	public void SiBuscoPOIsPorUnaEtiquetaDevuelveLosPOIsQueTienenEsaEtiqueta() {

		EntityManagerHelper.persistir(cgpValido);
		EntityManagerHelper.persistir(bancoValido);
		EntityManagerHelper.persistir(comercioValido);
		EntityManagerHelper.persistir(parada114Valida);

		@SuppressWarnings("unchecked")
		List<POI> poisEncontrados = (List<POI>) EntityManagerHelper
				.createQuery("FROM POI WHERE :etiqueta in elements(etiquetas)").setParameter("etiqueta", "tarjeta")
				.getResultList();

		Assert.assertEquals(2, poisEncontrados.size(), 0);
	}

	@Test
	public void SiBuscoPOIsPorEtiquetaQueNingunoTieneNoEncuentraNinguno() {
		Assert.assertTrue(manejadorDePois.buscarPOIs("negra").isEmpty());
	}

	@Test
	public void SiBuscoUnServicioQueSeEncuentraDisponibleEn2CGPEnUnHorarioDisponibleParaEseServicioEncuentraLos2CGP() {
		CGPsConRentas = manejadorDePois.buscarServicioDisponible("Rentas", horarioValidoParaRentas);
		Assert.assertEquals(2, CGPsConRentas.size(), 0);
		Assert.assertTrue(CGPsConRentas.contains(cgpValido));
		Assert.assertTrue(CGPsConRentas.contains(otroCgpValido));
	}

	@Test
	public void SiBuscoSiUnServicioEstaDisponibleEnUnHorarioEnQueEstaCerradoNoEncuentraNinguno() {
		CGPsConRentas = manejadorDePois.buscarServicioDisponible("Rentas", horarioNoValidoParaNingunServicio);
		Assert.assertEquals(0, CGPsConRentas.size(), 0);
	}

	// Servicios
	// Externos------------------------------------------------------------------

	@Test
	public void SiBuscoEnElServicioExternoConZonaValidaSeAgreganLosCGPsCorrespondientesEnLaListaDePOIs() {
		listaAdapters.clear();
		listaAdapters.add(cgpAdapter);
		manejadorDePois.setListaAdapters(listaAdapters);
		when(servicioExternoCgpMockeado.buscar("balvanera")).thenReturn(centrosDTO);

		manejadorDePois.buscarPOIs("balvanera");

		verify(servicioExternoCgpMockeado).buscar("balvanera");

		Assert.assertEquals(tamanioListaPoisInternos, listaPoisInternos.size(), 0);
		Assert.assertEquals(1, JedisHelper.obtenerCantidadPersistida(), 0);

	}

	@Test
	public void SiBuscoEnElServicioExternoConUnaZonaInvalidaNoSeAgregaNingunCGPALaListaDePOIs() {
		listaAdapters.clear();
		listaAdapters.add(cgpAdapter);
		manejadorDePois.setListaAdapters(listaAdapters);
		centrosDTO.clear();
		when(servicioExternoCgpMockeado.buscar("manchester")).thenReturn(centrosDTO);

		manejadorDePois.buscarPOIs("manchester");

		verify(servicioExternoCgpMockeado).buscar("manchester");

		Assert.assertEquals(tamanioListaPoisInternos, listaPoisInternos.size(), 0);
		Assert.assertEquals(0, JedisHelper.obtenerCantidadPersistida(), 0);
	}

	@Test
	public void SiBuscoEnElServicioExternoConServicioDeBancoDisponibleSeAgreganLosBancosCorrespondientesEnLaListaDePOIs() {
		listaAdapters.clear();
		listaAdapters.add(bancoAdapter);
		manejadorDePois.setListaAdapters(listaAdapters);
		when(servicioExternoBancoMockeado.buscar("Banco de la Plaza", "extracciones")).thenReturn(listaBancoJson);

		manejadorDePois.buscarPOIs("Banco de la Plaza,extracciones");

		verify(servicioExternoBancoMockeado).buscar("Banco de la Plaza", "extracciones");

		Assert.assertEquals(1, JedisHelper.obtenerCantidadPersistida(), 0);
		Assert.assertEquals(tamanioListaPoisInternos, listaPoisInternos.size(), 0);

	}

	@Test
	public void SiBuscoEnElServicioExternoConServicioDeBancoNoDisponibleNoSeAgregaNingunBancoEnLaListaDePOIs() {
		listaAdapters.clear();
		listaAdapters.add(bancoAdapter);
		manejadorDePois.setListaAdapters(listaAdapters);
		when(servicioExternoBancoMockeado.buscar("", "")).thenReturn(listaVaciaBancoJson);

		manejadorDePois.buscarPOIs(",");

		verify(servicioExternoBancoMockeado).buscar("", "");
		Assert.assertEquals(tamanioListaPoisInternos, listaPoisInternos.size(), 0);
		Assert.assertEquals(0, JedisHelper.obtenerCantidadPersistida(), 0);
	}

	// ------------------------------------------------------------------------

	@Test
	public void SiEliminoUnaParadaDeLaListaDePoisEntoncesLaElimina() {
		manejadorDePois.eliminarPOIInterno(parada114Valida);
		Assert.assertFalse(listaPoisInternos.contains(parada114Valida));
		Assert.assertFalse(EntityManagerHelper.contains(parada114Valida));
	}

	@Test
	public void SiAgregoUnaParadaQueNoEstaEnLaListaLaAgrega() {
		Assert.assertFalse(listaPoisInternos.contains(paradaQueNoEstaEnLaLista));
		manejadorDePois.agregarPoiInterno(paradaQueNoEstaEnLaLista);
		Assert.assertTrue(listaPoisInternos.contains(paradaQueNoEstaEnLaLista));
		Assert.assertTrue(EntityManagerHelper.contains(paradaQueNoEstaEnLaLista));

	}

	@Test
	public void SiAgregoUnaParadaExistenteLaActualiza() {
		manejadorDePois.agregarPoiInterno(parada114ValidaConMasEtiquetas);
		Assert.assertFalse(listaPoisInternos.contains(parada114Valida));
		Assert.assertTrue(listaPoisInternos.contains(parada114ValidaConMasEtiquetas));

		Assert.assertTrue(EntityManagerHelper.contains(parada114Valida));

		ParadaColectivo paradaEncontrada = EntityManagerHelper.find(ParadaColectivo.class, parada114Valida.getId());

		Assert.assertEquals(parada114ValidaConMasEtiquetas.getEtiquetas(), paradaEncontrada.getEtiquetas());

	}

	@Test
	public void SiPersistoUnPoiExternoSePersisteCorrectamenteYAlTraerloDeLaBDSeObtieneUnPoiConElMismoNombre() {

		JedisHelper.persistirPoiExterno(cgpValido);
		List<POI> lista = JedisHelper.obtenerPoisExternosDeRedis();
		Assert.assertTrue(lista.get(0).getNombre().equals(cgpValido.getNombre()));

	}

	@Test
	public void SiPersistoUnBancoYUnCgpYLuegoBuscoConDescripcionBancoObtengoElBanco() {
		JedisHelper.persistirPoiExterno(bancoValido);
		JedisHelper.persistirPoiExterno(cgpValido);

		List<POI> poisEncontrados = JedisHelper.buscarPoisEnRedis("Banco");

		Assert.assertTrue(poisEncontrados.get(0).getNombre().equals(bancoValido.getNombre()));
		Assert.assertEquals(1, poisEncontrados.size());

	}

	@Test
	public void SiBuscoPoisExternosEnBDPorDescripcionQueNingunoCumpleDevuelveUnaListaVacia() {
		List<POI> poisEncontrados = JedisHelper.buscarPoisEnRedis("Banco");
		Assert.assertTrue(poisEncontrados.isEmpty());
	}

	@Test
	public void SiPersistoUnCGPExternoYBuscoBancoDevuelveVacio() {
		JedisHelper.persistirPoiExterno(cgpValido);
		List<POI> poisEncontrados = JedisHelper.buscarPoisEnRedis("Banco");

		Assert.assertTrue(poisEncontrados.isEmpty());
	}

	@Test
	public void SiPersistoSoloCGPLoEncuentra() {
		JedisHelper.persistirPoiExterno(cgpValido);
		List<POI> poisEncontrados = JedisHelper.buscarPoisEnRedis("CGP");

		Assert.assertEquals(1, poisEncontrados.size());
		Assert.assertTrue(poisEncontrados.get(0).esIgualA(cgpValido));
	}

	@Test
	public void SiPersistoPrimeroCGPYDespuesBancoLoEncuentra() {
		JedisHelper.persistirPoiExterno(cgpValido);
		JedisHelper.persistirPoiExterno(bancoValido);
		List<POI> poisEncontrados = JedisHelper.buscarPoisEnRedis("CGP");

		Assert.assertEquals(1, poisEncontrados.size());
		Assert.assertTrue(poisEncontrados.get(0).esIgualA(cgpValido));
	}

	@Test
	public void SiPersistoPrimeroBancoYDespuesCGPTambienLoEncuentra() {
		JedisHelper.persistirPoiExterno(bancoValido);
		JedisHelper.persistirPoiExterno(cgpValido);
		List<POI> poisEncontrados = JedisHelper.buscarPoisEnRedis("CGP");

		Assert.assertEquals(1, poisEncontrados.size());
		Assert.assertTrue(poisEncontrados.get(0).esIgualA(cgpValido));
	}

	@Test
	public void SiBuscoPoisExternosYHayUnPoiEnBDQueCumpleConLaDescripcionMeDevuelveSoloPoisDeBD() {
		JedisHelper.persistirPoiExterno(bancoValido);
		JedisHelper.persistirPoiExterno(cgpValido);

		when(servicioExternoCgpMockeado.buscar("CGP")).thenReturn(centrosDTO);
		listaAdapters.clear();
		listaAdapters.add(cgpAdapter);
		manejadorDePois.clearListaPoisInternos();

		List<POI> poisEncontrados = manejadorDePois.buscarPOIs("CGP");

		Assert.assertEquals(1, poisEncontrados.size());
		Assert.assertTrue(poisEncontrados.get(0).esIgualA(cgpValido));
	}

	@Test
	public void SiBuscoPoisExternosYEnBDNingunoCumpleConLaDescripcionDevuelvePoisDelServicioExterno() {
		JedisHelper.persistirPoiExterno(bancoValido);

		when(servicioExternoCgpMockeado.buscar("CGP")).thenReturn(centrosDTO);
		listaAdapters.clear();
		listaAdapters.add(cgpAdapter);
		manejadorDePois.clearListaPoisInternos();

		List<POI> poisEncontrados = manejadorDePois.buscarPOIs("CGP");

		Assert.assertEquals(1, poisEncontrados.size());
		CGP cgpEncontrado = (CGP) poisEncontrados.get(0);
		Assert.assertTrue(cgpEncontrado.dameNumeroComuna().equals(centroDTO1.getNumeroComunaCentroDTO()));
	}
}
