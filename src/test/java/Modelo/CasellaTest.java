package Modelo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import Modelo.Casella;

public class CasellaTest {

	private Casella casilla;

	/**
	 * PRUEBA TEST DE LOS GETTERS
	 * 
	 * @Test public void testCreate() { assertEquals(23, new MyClass(23).getX()); }
	 */

	@Before
	public void setUp() {
		casilla = new Casella();
	}

	@Test
	public void constrcutorCasellaTest() {
		assertEquals("Al empezar debe tener 0 minas cercanas", 0, casilla.getminasCercanas());
		assertFalse("Al crear la variable la casilla debe estar cerrada osea False", casilla.getAbierta());
		assertFalse("Al principio de la ejecucion no es  bandera por lo tanto debe dar False", casilla.getBandera());
		assertFalse("Al principio de la ejecucion no es mina por lo tanto debe dar False", casilla.esMina());
	}

	@Test
	// (int nMinas,boolean mina,boolean state,boolean flag)
	public void constructorParametroCAsellaTest() {
		// Particion equivalente[0,8] Valido, fuera de esto invalido
		// Valores Interiores
		casilla = new Casella(5, false, false, false);
		assertEquals("Al setear puede tener 5 minas", 5, casilla.getminasCercanas());
		// Valores Frontera
		casilla = new Casella(8, false, true, false);
		assertEquals("Al setear no puede tener mas de 8 minas cercanas", 8, casilla.getminasCercanas());
		casilla = new Casella(0, false, true, true);
		assertEquals("Al setear  puede tener 0 minas cercanas", 0, casilla.getminasCercanas());
		// Valores interiores a frontera
		casilla = new Casella(1, false, true, true);
		assertEquals("Al setear  puede tener 0 minas cercanas", 1, casilla.getminasCercanas());
		casilla = new Casella(7, false, true, true);
		assertEquals("Al setear  puede tener 0 minas cercanas", 7, casilla.getminasCercanas());

		// Valores exteriores a frontera
		casilla = new Casella(9, true, true, true);
		assertEquals("Al setear no puede tener mas de 8 minas cercanas", 8, casilla.getminasCercanas());
		casilla = new Casella(-1, true, true, false);
		assertEquals("Al setear no puede tener minas cercanas negativas", 0, casilla.getminasCercanas());

	}

	@Test
	public void testCambiarBandera() {

		// Comprobacion inicial de que las casillas no tienen bandera
		assertFalse("Al principio de la ejecucion debe dar False", casilla.getBandera()); //true true	
		casilla.cambiarBandera();

		// Comprobamos de que se ha puesto la bandera
		assertTrue("Como se inicializa a zero debe devolver un True conforme hay Bandera", casilla.getBandera()); //true false

		// Comprobamos que se quita
		casilla.cambiarBandera(); //true true
		assertFalse("Una vez cambia de bandera al volver a darle se debe quitar y devolver False",
				casilla.getBandera());

		// Comprobamos que si esta abierta no se puede poner banderas.
		casilla.setAbierta(true);
		casilla.cambiarBandera(); //false false
		assertFalse("Si la mina es abierta no se puede poner bandera a la casilla", casilla.getBandera());

	}

	@Test
	public void testAbrirCasilla() {
		//PARWISE TESTING

		// Comprobamos que al inicializar esta cerrada
		assertFalse("Al crear la variable la casilla debe estar cerrada osea False", casilla.getAbierta());

		// Abrimos casilla
		casilla.abrirCasilla();
		assertTrue("Como se inicializa a false, al cambiar de estado debe ser true", casilla.getAbierta());

		// No se puede abrir lo abierto
		casilla.abrirCasilla();
		assertTrue("Como  ya se ha abierto no se puede volver a abrir", casilla.getAbierta());

		// Casilla abierta y con bandera
		casilla.setAbierta(true);
		casilla.setBandera(true);
		casilla.abrirCasilla();
		assertFalse("Si esta abierta y tenemos bandera debemos cambiarla a false", casilla.getBandera());
		assertTrue("El estado seria TRUE", casilla.getAbierta());

		// Casilla cerrada y con bandera
		casilla.setAbierta(false);
		casilla.setBandera(true);
		casilla.abrirCasilla();
		assertFalse("El estado seria false", casilla.getAbierta());
		assertFalse("El estado seria TRUE", casilla.getBandera());

	}

	@Test
	public void testSumarMinaCercana() {
		assertEquals("Al principio no hay minas cercanas", 0, casilla.getminasCercanas());
		for (int i = 1; i < 10; i++) {
			casilla.sumarMinaCercana();
			if (casilla.getminasCercanas() >= 8)
				assertEquals("El maximo de minas son 8", 8, casilla.getminasCercanas());
			else
				assertEquals("El numero de minas cercanas debe ser : " + i, i, casilla.getminasCercanas());
		}
	}

	@Test
	public void testRestarMinaCercana() {

		assertEquals("Al principio no hay minas cercanas", 0, casilla.getminasCercanas());
		for (int i = 1; i < 15; i++) {
			casilla.sumarMinaCercana();
			if (casilla.getminasCercanas() >= 8)
				assertEquals("El maximo de minas son 8", 8, casilla.getminasCercanas());
			else
				assertEquals("El numero de minas cercanas debe ser : " + i, i, casilla.getminasCercanas());
		}

		for (int i = 8; i > -15; i--) {
			if (casilla.getminasCercanas() <= 0)
				assertEquals("El maximo de minas son 0", 0, casilla.getminasCercanas());
			else
				assertEquals("El numero de minas cercanas debe ser : " + i, i, casilla.getminasCercanas());
			casilla.restarMinaCercana();
		}

	}

	@Test
	public void testSetMina() {
		assertFalse(casilla.esMina());
		casilla.setEsMina(false);
		assertFalse(casilla.esMina());
		casilla.setEsMina(true);
		assertTrue(casilla.esMina());
	}

}
