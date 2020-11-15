package BuscaminasTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import Modelo.Casella;
import Modelo.Tablero;
import Modelo.ValorRandom;

public class TableroTest {

	private Tablero tablero;
	private ValorRandom r;

	@Before
	public void setUp() {
		tablero = new Tablero(0);
		r = new ValorRandom();
	}

	@Test
	public void testGenerarTablero() { // Statement coverage && loop aniuat testing

		// VALORES FRONTERA
		// Tablero 0
		int checksum = 0;
		for (int i = 0; i <= tablero.getNFilas(); i++) {
			for (int j = 0; j <= tablero.getNColumnas(); j++) {

				assertFalse(tablero.getCasillas(i, j).getAbierta());
				assertFalse(tablero.getCasillas(i, j).getBandera());
				assertEquals(tablero.getCasillas(i, j).getminasCercanas(), 0);
				assertFalse(tablero.getCasillas(i, j).esMina());
				checksum++;
			}
		}
		assertEquals(100, checksum);

		tablero = new Tablero(2);
		assertEquals("En caso de no cumplir parametros se definira com 10,10", 23, tablero.getNFilas());
		assertEquals("En caso de no cumplir parametros se definira com 10,10", 23, tablero.getNColumnas());
		checksum = 0;
		for (int i = 0; i <= tablero.getNFilas(); i++) {
			for (int j = 0; j <= tablero.getNColumnas(); j++) {

				assertFalse(tablero.getCasillas(i, j).getAbierta());
				assertFalse(tablero.getCasillas(i, j).getBandera());
				assertEquals(tablero.getCasillas(i, j).getminasCercanas(), 0);
				assertFalse(tablero.getCasillas(i, j).esMina());
				checksum++;
			}
		}
		assertEquals(576, checksum);

		// VALORES INTERIORES

		checksum = 0;
		tablero = new Tablero(1);
		for (int i = 0; i <= tablero.getNFilas(); i++) {
			for (int j = 0; j <= tablero.getNColumnas(); j++) {

				assertFalse(tablero.getCasillas(i, j).getAbierta());
				assertFalse(tablero.getCasillas(i, j).getBandera());
				assertEquals(tablero.getCasillas(i, j).getminasCercanas(), 0);
				assertFalse(tablero.getCasillas(i, j).esMina());
				checksum++;
			}
		}
		assertEquals(256, checksum);

		// Valores exteriores a frontera
		tablero = new Tablero(3);
		assertEquals("En caso de no cumplir parametros se definira com 10,10", 9, tablero.getNFilas());
		assertEquals("En caso de no cumplir parametros se definira com 10,10", 9, tablero.getNColumnas());
		checksum = 0;
		for (int i = 0; i <= tablero.getNFilas(); i++) {
			for (int j = 0; j <= tablero.getNColumnas(); j++) {

				assertFalse(tablero.getCasillas(i, j).getAbierta());
				assertFalse(tablero.getCasillas(i, j).getBandera());
				assertEquals(tablero.getCasillas(i, j).getminasCercanas(), 0);
				assertFalse(tablero.getCasillas(i, j).esMina());
				checksum++;
			}
		}
		assertEquals(100, checksum);

		tablero = new Tablero(-1);
		assertEquals("En caso de no cumplir parametros se definira com 10,10", 9, tablero.getNFilas());
		assertEquals("En caso de no cumplir parametros se definira com 10,10", 9, tablero.getNColumnas());
		checksum = 0;
		for (int i = 0; i <= tablero.getNFilas(); i++) {
			for (int j = 0; j <= tablero.getNColumnas(); j++) {

				assertFalse(tablero.getCasillas(i, j).getAbierta());
				assertFalse(tablero.getCasillas(i, j).getBandera());
				assertEquals(tablero.getCasillas(i, j).getminasCercanas(), 0);
				assertFalse(tablero.getCasillas(i, j).esMina());
				checksum++;
			}
		}
		assertEquals(100, checksum);

		// LOOP TESTING
		// FOR FILA
		// FOR COLUMNA
		// LOOP Testing

		tablero = new Tablero(0);
		tablero.setnFilas(1);

		// Evitar loop
		tablero.setnColumnas(0);
		tablero.generarTablero();
		try {
			tablero.getCasillas(0, 0).getAbierta();
		} catch (IndexOutOfBoundsException e) {
			assertTrue(true);
		}
		// Una pasada por el loop
		tablero.setnColumnas(1);
		tablero.generarTablero();
		for (int i = 0; i < tablero.getNFilas(); i++) {
			for (int j = 0; j < tablero.getNColumnas(); j++) {
				assertFalse(tablero.getCasillas(i, j).getAbierta());
			}
		}

		// Dues pasadas por el loop
		tablero.setnColumnas(2);
		tablero.generarTablero();
		for (int i = 0; i < tablero.getNFilas(); i++) {
			for (int j = 0; j < tablero.getNColumnas(); j++) {
				assertFalse(tablero.getCasillas(i, j).getAbierta());
			}
		}
		// m pasadas por el loop m<n
		tablero.setnColumnas(5);
		tablero.generarTablero();

		for (int i = 0; i < tablero.getNFilas(); i++) {
			for (int j = 0; j < tablero.getNColumnas(); j++) {
				assertFalse(tablero.getCasillas(i, j).getAbierta());
			}
		}
		// (n-1), n pasadas por el loop (n es el nï¿½mero mï¿½ximo de pasadas)
		tablero.setnColumnas(tablero.getNColumnas());
		tablero.generarTablero();

		for (int i = 0; i < tablero.getNFilas(); i++) {
			for (int j = 0; j < tablero.getNColumnas(); j++) {
				assertFalse(tablero.getCasillas(i, j).getAbierta());
			}
		}

		// n
		tablero.setnColumnas(tablero.getNColumnas() + 1);
		tablero.generarTablero();

		for (int i = 0; i < tablero.getNFilas(); i++) {
			for (int j = 0; j < tablero.getNColumnas(); j++) {
				assertFalse(tablero.getCasillas(i, j).getAbierta());
			}
		}

		// Testejar un loop mÃ©s extern (com si fosun loop simple)
		// EVITAR LOOP
		tablero = new Tablero(0);

		tablero.setnFilas(0);
		tablero.generarTablero();
		try {
			tablero.getCasillas(0, 0).getAbierta();
		} catch (IndexOutOfBoundsException e) {
			assertTrue(true);
		}
		// Una pasada por el loop
		tablero.setnFilas(1);
		tablero.generarTablero();
		for (int i = 0; i < tablero.getNFilas(); i++) {
			for (int j = 0; j < tablero.getNColumnas(); j++) {
				assertFalse(tablero.getCasillas(i, j).getAbierta());
			}
		}

		// Dues pasadas por el loop
		tablero.setnFilas(2);
		tablero.generarTablero();
		for (int i = 0; i < tablero.getNFilas(); i++) {
			for (int j = 0; j < tablero.getNColumnas(); j++) {
				assertFalse(tablero.getCasillas(i, j).getAbierta());
			}
		}
		// m pasadas por el loop m<n
		tablero.setnFilas(5);
		tablero.generarTablero();

		for (int i = 0; i < tablero.getNFilas(); i++) {
			for (int j = 0; j < tablero.getNColumnas(); j++) {
				assertFalse(tablero.getCasillas(i, j).getAbierta());
			}
		}
		// (n-1), n pasadas por el loop (n es el nï¿½mero mï¿½ximo de pasadas)
		tablero.setnFilas(tablero.getNColumnas());
		tablero.generarTablero();

		for (int i = 0; i < tablero.getNFilas(); i++) {
			for (int j = 0; j < tablero.getNColumnas(); j++) {
				assertFalse(tablero.getCasillas(i, j).getAbierta());
			}
		}

		// n
		tablero.setnFilas(tablero.getNFilas() + 1);
		tablero.generarTablero();

		for (int i = 0; i < tablero.getNFilas() + 1; i++) {
			for (int j = 0; j < tablero.getNColumnas(); j++) {
				assertFalse(tablero.getCasillas(i, j).getAbierta());
			}
		}

		// FINAL TESTING

	}

	@Test
	public void testEsMina() { // Decision coverage, Condition coverage
		for (int i = 0; i < 3; i++) {
			tablero = new Tablero(i);
			tablero.getCasillas(0, 0).setEsMina(true);
			assertTrue("Como es mina debe dar true", tablero.esMina(0, 0));
			assertFalse("Como es mina debe dar true", tablero.esMina(-1, 0));
			assertFalse("Como es mina debe dar true", tablero.esMina(1, tablero.getNColumnas() + 1));
			assertFalse("Como esta fuerda de rango debe dar false",
					tablero.esMina(tablero.getNFilas() + 1, tablero.getNColumnas()));
			assertFalse("Como no es mina debe dar false", tablero.esMina(0, 1));
		}

		tablero = new Tablero(0);

		// Valors interiors
		assertFalse("Como es mina debe dar true", tablero.esMina(4, 6));

		// Valors frontera
		assertFalse("Como es mina debe dar true", tablero.esMina(0, 9));
		assertFalse("Como es mina debe dar true", tablero.esMina(9, 0));
		// Valors interiors a frontera
		assertFalse("Como es mina debe dar true", tablero.esMina(1, 8));
		assertFalse("Como es mina debe dar true", tablero.esMina(8, 1));

		// Valors exteriors a frontera
		assertFalse("Como es mina debe dar true", tablero.esMina(-1, 10));
		assertFalse("Como es mina debe dar true", tablero.esMina(10, -1));
	}

	@Test
	public void testPonerMinas() { // Decision coverage, condition coverage, loop simple

		int minasPuestas;
		// Comprobamos que se pongan el numero de minas correctas.
		// Valores frontera i interiores
		minasPuestas = tablero.ponerMinas(r);
		assertEquals("Dificultad 0= 10 minas", minasPuestas, tablero.getNumMinas());

		tablero = new Tablero(1);
		minasPuestas = tablero.ponerMinas(r);
		assertEquals("Dificultad 0= 10 minas", minasPuestas, tablero.getNumMinas());

		tablero = new Tablero(2);
		minasPuestas = tablero.ponerMinas(r);
		assertEquals("Dificultad 0= 10 minas", minasPuestas, tablero.getNumMinas());

		// Valores exteriores a frontera
		tablero = new Tablero(-1);
		minasPuestas = tablero.ponerMinas(r);
		assertEquals("Dificultad 0= 10 minas", minasPuestas, tablero.getNumMinas());

		tablero = new Tablero(3);
		minasPuestas = tablero.ponerMinas(r);
		assertEquals("Dificultad 0= 10 minas", minasPuestas, tablero.getNumMinas());

		// LOOP Testing
		// Evitar loop
		tablero = new Tablero(0);
		tablero.setNumMinas(0);
		minasPuestas = tablero.ponerMinas(r);
		assertEquals("Dificultad 0= 10 minas", 0, minasPuestas);

		// Una pasada por el loop
		tablero = new Tablero(0);
		tablero.setNumMinas(1);
		minasPuestas = tablero.ponerMinas(r);
		assertEquals("Dificultad 0= 10 minas", 1, minasPuestas);

		// Dues pasadas por el loop

		tablero = new Tablero(0);
		tablero.setNumMinas(2);
		minasPuestas = tablero.ponerMinas(r);
		assertEquals("Dificultad 0= 10 minas", 2, minasPuestas);

		// m pasadas por el loop m<n

		tablero = new Tablero(0);
		tablero.setNumMinas(5);
		minasPuestas = tablero.ponerMinas(r);
		assertEquals("Dificultad 0= 10 minas", 5, minasPuestas);

		// (n-1), n pasadas por el loop (n es el nï¿½mero mï¿½ximo de pasadas)

		// n-1
		tablero = new Tablero(0);
		tablero.setNumMinas((tablero.getNMinasPorDificultad(tablero.getDificultad())) - 1);
		minasPuestas = tablero.ponerMinas(r);
		assertEquals("Dificultad 0= 10 minas", 14, minasPuestas);

		// n
		tablero = new Tablero(0);
		tablero.setNumMinas((tablero.getNMinasPorDificultad(tablero.getDificultad())));
		minasPuestas = tablero.ponerMinas(r);
		assertEquals("Dificultad 0= 10 minas", 15, minasPuestas);
	}

	@Test
	public void testMockValorRandom() { // Mock del valor random para testear todos los casos de poner minas

		for (int i = -6; i <= 5; i++) {
			tablero = new Tablero(i);
			MockRandom r = new MockRandom();
			tablero.ponerMinas(r);
			assertTrue(tablero.getCasillas(0, 0).esMina());
			assertTrue(tablero.getCasillas(1, 1).esMina());
			assertTrue(tablero.getCasillas(9, 9).esMina());

		}
	}

	@Test
	public void testMarcarCasilla() { // Condition coverage, Decision coverage.
		int k;

		// Testeamos valores limite y frontera de tablero
		for (int i = -1; i < 3; i++) {
			tablero = new Tablero(i);
			tablero.ponerMinas(r);
			k = tablero.marcarCasilla(0, 0); // static
			assertTrue("La casilla se abre", tablero.getCasillas(0, 0).getAbierta());
			assertEquals(0, k);
			k = tablero.marcarCasilla(0, 0); // static
			assertTrue("La casilla no se puede cerrar", tablero.getCasillas(0, 0).getAbierta());
			assertEquals(0, k);
			k = tablero.marcarCasilla(0, tablero.getNColumnas());
			assertEquals(0, k);
			assertTrue("La casilla se abre", tablero.getCasillas(0, tablero.getNColumnas()).getAbierta());
			k = tablero.marcarCasilla(0, tablero.getNColumnas() + 1);
			assertEquals(-1, k);
			k = tablero.marcarCasilla(tablero.getNFilas(), 0);
			assertEquals(0, k);
			assertTrue("La casilla se abre", tablero.getCasillas(0, tablero.getNColumnas()).getAbierta());
			k = tablero.marcarCasilla(tablero.getNFilas(), tablero.getNColumnas() + 1);
			assertEquals(-1, k);
			k = tablero.marcarCasilla(tablero.getNFilas(), -8);
			assertEquals(-1, k);
			k = tablero.marcarCasilla(tablero.getNFilas(), tablero.getNColumnas());
			assertEquals(0, k);
			assertTrue("La casilla se abre", tablero.getCasillas(0, tablero.getNColumnas()).getAbierta());
			k = tablero.marcarCasilla(tablero.getNFilas() + 1, tablero.getNColumnas() + 1);
			assertEquals(-1, k);
			k = tablero.marcarCasilla(-1, -1); // static
			assertEquals(-1, k);
			k = tablero.marcarCasilla(100, -100); // static
			assertEquals(-1, k);

		}

		// Comprobamos que se abran mï¿½ltiples casillas si se le da a una casilla con 0
		// minas alrededor.
		// PATH COVERAGE

		for (int i = 0; i < 3; i++) {
			tablero = new Tablero(i);
			// ARRIBA IZQUIERDA
			tablero.getCasillas(0, 2).setEsMina(true);
			tablero.sumarMinasAlrededor(0, 2);
			tablero.getCasillas(1, 2).setEsMina(true);
			tablero.sumarMinasAlrededor(1, 2);
			tablero.getCasillas(2, 2).setEsMina(true);
			tablero.sumarMinasAlrededor(2, 2);
			tablero.getCasillas(2, 0).setEsMina(true);
			tablero.sumarMinasAlrededor(2, 0);
			tablero.getCasillas(2, 1).setEsMina(true);
			tablero.sumarMinasAlrededor(2, 1);
			tablero.marcarCasilla(0, 0);
			assertTrue("La casilla se abre", tablero.getCasillas(0, 0).getAbierta());
			assertTrue("La casilla se abre", tablero.getCasillas(0, 1).getAbierta());
			assertTrue("La casilla se abre", tablero.getCasillas(1, 0).getAbierta());
			assertTrue("La casilla se abre", tablero.getCasillas(1, 1).getAbierta());
			// ARRIBA DERECHA
			tablero.getCasillas(0, tablero.getNFilas() - 2).setEsMina(true); // en posicion 0, final-2
			tablero.sumarMinasAlrededor(0, tablero.getNFilas() - 2);
			tablero.getCasillas(1, tablero.getNFilas() - 2).setEsMina(true); // en posicion 1, final-2
			tablero.sumarMinasAlrededor(1, tablero.getNFilas() - 2);
			tablero.getCasillas(2, tablero.getNFilas() - 2).setEsMina(true); // en posicion 2, final-2
			tablero.sumarMinasAlrededor(2, tablero.getNFilas() - 2);
			tablero.getCasillas(2, tablero.getNFilas() - 1).setEsMina(true); // en posicion 2, final-1
			tablero.sumarMinasAlrededor(2, tablero.getNFilas() - 1);
			tablero.getCasillas(2, tablero.getNFilas()).setEsMina(true); // en posicion 2, final
			tablero.sumarMinasAlrededor(2, tablero.getNFilas());
			tablero.marcarCasilla(0, tablero.getNFilas());
			assertTrue("La casilla se abre", tablero.getCasillas(0, tablero.getNFilas()).getAbierta());
			assertTrue("La casilla se abre", tablero.getCasillas(0, tablero.getNFilas() - 1).getAbierta());
			assertTrue("La casilla se abre", tablero.getCasillas(1, tablero.getNFilas()).getAbierta());
			assertTrue("La casilla se abre", tablero.getCasillas(1, tablero.getNFilas() - 1).getAbierta());

			// ABAJO IZQUIERDA
			tablero.getCasillas(tablero.getNFilas() - 2, 2).setEsMina(true);
			tablero.sumarMinasAlrededor(tablero.getNFilas() - 2, 2);
			tablero.getCasillas(tablero.getNFilas() - 1, 2).setEsMina(true);
			tablero.sumarMinasAlrededor(tablero.getNFilas() - 1, 2);
			tablero.getCasillas(tablero.getNFilas(), 2).setEsMina(true);
			tablero.sumarMinasAlrededor(tablero.getNFilas(), 2);
			tablero.getCasillas(tablero.getNFilas() - 2, 0).setEsMina(true);
			tablero.sumarMinasAlrededor(tablero.getNFilas() - 2, 0);
			tablero.getCasillas(tablero.getNFilas() - 2, 1).setEsMina(true);
			tablero.sumarMinasAlrededor(tablero.getNFilas() - 2, 1);
			tablero.marcarCasilla(tablero.getNFilas(), 0);// Marcamos casilla
			assertTrue("La casilla se abre", tablero.getCasillas(tablero.getNFilas(), 0).getAbierta());
			assertTrue("La casilla se abre", tablero.getCasillas(tablero.getNFilas(), 1).getAbierta());
			assertTrue("La casilla se abre", tablero.getCasillas(tablero.getNFilas() - 1, 0).getAbierta());
			assertTrue("La casilla se abre", tablero.getCasillas(tablero.getNFilas() - 1, 1).getAbierta());

			// ABAJO IZQUIERDA
			tablero.getCasillas(tablero.getNFilas() - 2, tablero.getNColumnas()).setEsMina(true); // -2/ac
			tablero.sumarMinasAlrededor(tablero.getNFilas() - 2, tablero.getNColumnas());
			tablero.getCasillas(tablero.getNFilas() - 1, tablero.getNColumnas() - 2).setEsMina(true); // -1/-2
			tablero.sumarMinasAlrededor(tablero.getNFilas() - 1, tablero.getNColumnas() - 2);
			tablero.getCasillas(tablero.getNFilas(), tablero.getNColumnas() - 2).setEsMina(true); // ac/-2
			tablero.sumarMinasAlrededor(tablero.getNFilas(), tablero.getNColumnas() - 2);
			tablero.getCasillas(tablero.getNFilas() - 2, tablero.getNColumnas() - 1).setEsMina(true); // -2/-1
			tablero.sumarMinasAlrededor(tablero.getNFilas() - 2, tablero.getNColumnas() - 1);
			tablero.getCasillas(tablero.getNFilas() - 2, tablero.getNColumnas() - 2).setEsMina(true); // -2/-2
			tablero.sumarMinasAlrededor(tablero.getNFilas() - 2, tablero.getNColumnas() - 2);
			tablero.marcarCasilla(tablero.getNFilas(), tablero.getNColumnas());// Marcamos casilla
			assertTrue("La casilla se abre",
					tablero.getCasillas(tablero.getNFilas(), tablero.getNColumnas()).getAbierta());
			assertTrue("La casilla se abre",
					tablero.getCasillas(tablero.getNFilas(), tablero.getNColumnas() - 1).getAbierta());
			assertTrue("La casilla se abre",
					tablero.getCasillas(tablero.getNFilas() - 1, tablero.getNColumnas()).getAbierta());
			assertTrue("La casilla se abre",
					tablero.getCasillas(tablero.getNFilas() - 1, tablero.getNColumnas() - 1).getAbierta());

		}

	}

	public void testPathMarcarCasilla() {
		// PATH COVERAGE
		// 9 ARCOS 6 NODOS
		// 9-6+2=5 PATHS DEBEN SALIR
		// SE INCLUYE AUTOMATIZACION EN LOS TESTS

		// PATH DONDE LA POSCION NO ES VALIDA
		int k;
		k = tablero.marcarCasilla(tablero.getNFilas(), -8);
		assertEquals(-1, k);

		// PATH DONDE POSICION ES VALIDA PERO ESTA ABIERTA
		tablero.getCasillas(0, 0).setAbierta(true);
		k = tablero.marcarCasilla(0, 0);
		assertTrue("La casilla no se abre porque ya esta abierta", tablero.getCasillas(0, 0).getAbierta());
		assertEquals(0, k);

		// PATH DONDE POS VALIDA, NO ESTA ABIERTA ES PRIMER MOV,
		// NO TIENE MINAS CERCANAS
		tablero = new Tablero(0);
		assertTrue("La casilla se abre porque porque no tiene minas cercanas", tablero.getCasillas(0, 0).getAbierta());
		assertEquals(0, k);

		// PATH DONDE POS VALIDA, NO ESTA ABIERTA _NO_ ES PRIMER MOV,
		// NO TIENE MINAS CERCANAS
		tablero.getCasillas(0, 0).setAbierta(false);
		assertTrue("La casilla se abre porque aunque no sea el primer movimiento no tiene minas cercanas",
				tablero.getCasillas(0, 0).getAbierta());
		assertEquals(0, k);

		// PATH DONDE POS VALIDA, NO ESTA ABIERTA _NO_ ES PRIMER MOV,
		// TIENE MINAS CERCANAS
		tablero.getCasillas(0, 0).setAbierta(false);
		tablero.getCasillas(0, 1).setEsMina(true);
		tablero.sumarMinasAlrededor(0, 1);
		tablero.getCasillas(1, 1).setEsMina(true);
		tablero.sumarMinasAlrededor(1, 1);
		tablero.getCasillas(1, 0).setEsMina(true);
		tablero.sumarMinasAlrededor(1, 0);
		assertTrue("La casilla se abre porque aunque no sea el primer movimiento tiene minas cercanas y se abren",
				tablero.getCasillas(0, 0).getAbierta());
		assertFalse("La casilla no se abre porque es mina", tablero.getCasillas(0, 1).getAbierta());
		assertFalse("La casilla no se abre porque es mina", tablero.getCasillas(1, 0).getAbierta());
		assertFalse("La casilla no se abre porque es mina", tablero.getCasillas(1, 1).getAbierta());
		assertEquals(0, k);

	}

	@Test
	public void testSumaMinasAlrededor() { // DECISION AND CONDITION COVERAGE.

		// Valores válidos [0,9], Qualquier valor no válido estara fuera del tablero de
		// dificultad 0, por lo que
		// getminasCercanas devolvera 0.

		int fila;
		int col;

		// Valores interiores
		fila = 4;
		col = 4;
		tablero = new Tablero(0);
		tablero.sumarMinasAlrededor(fila, col);
		assertEquals(tablero.getCasillas(fila, col).getminasCercanas(), 1);

		// Valores frontera
		fila = 0;
		col = 9;
		tablero = new Tablero(0);
		tablero.sumarMinasAlrededor(fila, col);
		assertEquals(tablero.getCasillas(fila, col).getminasCercanas(), 1);

		fila = 9;
		col = 0;
		tablero = new Tablero(0);
		tablero.sumarMinasAlrededor(fila, col);
		assertEquals(tablero.getCasillas(fila, col).getminasCercanas(), 1);

		// Valors interiors a frontera
		fila = 1;
		col = 9;
		tablero = new Tablero(0);
		tablero.sumarMinasAlrededor(fila, col);
		assertEquals(tablero.getCasillas(fila, col).getminasCercanas(), 1);

		fila = 9;
		col = 1;
		tablero = new Tablero(0);
		tablero.sumarMinasAlrededor(fila, col);
		assertEquals(tablero.getCasillas(fila, col).getminasCercanas(), 1);

		// Valores exteriores a frontera

		fila = -1;
		col = 10;
		tablero = new Tablero(0);
		tablero.sumarMinasAlrededor(fila, col);
		assertEquals(tablero.getCasillas(fila, col).getminasCercanas(), 0);

		fila = 10;
		col = -1;
		tablero = new Tablero(0);
		tablero.sumarMinasAlrededor(fila, col);
		assertEquals(tablero.getCasillas(fila, col).getminasCercanas(), 0);

//		// 8 minas
//		tablero = new Tablero(0);
//		tablero.getCasillas(fila - 1, col).setEsMina(true);
//		tablero.sumarMinasAlrededor(fila - 1, col);
//		tablero.getCasillas(fila + 1, col).setEsMina(true);
//		tablero.sumarMinasAlrededor(fila + 1, col);
//		tablero.getCasillas(fila, col + 1).setEsMina(true);
//		tablero.sumarMinasAlrededor(fila, col + 1);
//		tablero.getCasillas(fila, col - 1).setEsMina(true);
//		tablero.sumarMinasAlrededor(fila, col - 1);
//
//		tablero.getCasillas(fila - 1, col + 1).setEsMina(true);
//		tablero.sumarMinasAlrededor(fila - 1, col + 1);
//		tablero.getCasillas(fila - 1, col - 1).setEsMina(true);
//		tablero.sumarMinasAlrededor(fila - 1, col - 1);
//		tablero.getCasillas(fila + 1, col + 1).setEsMina(true);
//		tablero.sumarMinasAlrededor(fila + 1, col + 1);
//		tablero.getCasillas(fila + 1, col - 1).setEsMina(true);
//		tablero.sumarMinasAlrededor(fila + 1, col - 1);
//		assertEquals(tablero.getCasillas(fila, col).getminasCercanas(), 8);

//		// 1 minas
//		tablero = new Tablero(0);
//		tablero.getCasillas(fila, col).setEsMina(true);
//		tablero.sumarMinasAlrededor(fila, col);
//
//		assertEquals(tablero.getCasillas(fila - 1, col).getminasCercanas(), 1);
//		assertEquals(tablero.getCasillas(fila, col + 1).getminasCercanas(), 1);
//		assertEquals(tablero.getCasillas(fila, col - 1).getminasCercanas(), 1);
//		assertEquals(tablero.getCasillas(fila - 1, col + 1).getminasCercanas(), 1);
//		assertEquals(tablero.getCasillas(fila - 1, col - 1).getminasCercanas(), 1);
//		assertEquals(tablero.getCasillas(fila + 1, col + 1).getminasCercanas(), 1);
//		assertEquals(tablero.getCasillas(fila + 1, col - 1).getminasCercanas(), 1);
//
//		// 7 MINAS
//		tablero = new Tablero(0);
//		tablero.getCasillas(fila - 1, col).setEsMina(true);
//		tablero.sumarMinasAlrededor(fila - 1, col);
//		tablero.getCasillas(fila + 1, col).setEsMina(true);
//		tablero.sumarMinasAlrededor(fila + 1, col);
//		tablero.getCasillas(fila, col + 1).setEsMina(true);
//		tablero.sumarMinasAlrededor(fila, col + 1);
//		tablero.getCasillas(fila, col - 1).setEsMina(true);
//		tablero.sumarMinasAlrededor(fila, col - 1);
//
//		tablero.getCasillas(fila - 1, col + 1).setEsMina(true);
//		tablero.sumarMinasAlrededor(fila - 1, col + 1);
//		tablero.getCasillas(fila - 1, col - 1).setEsMina(true);
//		tablero.sumarMinasAlrededor(fila - 1, col - 1);
//		tablero.getCasillas(fila + 1, col + 1).setEsMina(true);
//		tablero.sumarMinasAlrededor(fila + 1, col + 1);
//
//		assertEquals(tablero.getCasillas(fila + 1, col - 1).getminasCercanas(), 2);
//		assertEquals(tablero.getCasillas(fila, col).getminasCercanas(), 7);

	}

	@Test
	public void testPosicionValida() { // Decision coverage, Condition Coverage.
		for (int i = 0; i < 3; i++) {
			tablero = new Tablero(i);
			// Particion equivalente [0,Nfila]-[0,nColumna]
			// Valores internos
			assertTrue("Estamos dentro de la matriz", tablero.posicionValida(5, 5));

			// Valores frontera
			assertTrue("Estamos dentro de la matriz", tablero.posicionValida(0, 0));
			assertTrue("Estamos dentro de la matriz", tablero.posicionValida(0, tablero.getNColumnas()));
			assertTrue("Estamos dentro de la matriz",
					tablero.posicionValida(tablero.getNFilas(), tablero.getNColumnas()));
			assertTrue("Estamos dentro de la matriz", tablero.posicionValida(tablero.getNFilas(), 0));

			// Valores interiores a frontera
			assertTrue("Estamos dentro de la matriz", tablero.posicionValida(1, 1));
			assertTrue("Estamos dentro de la matriz", tablero.posicionValida(1, tablero.getNColumnas() - 1));
			assertTrue("Estamos dentro de la matriz",
					tablero.posicionValida(tablero.getNFilas() - 1, tablero.getNColumnas() - 1));
			assertTrue("Estamos dentro de la matriz", tablero.posicionValida(tablero.getNFilas() - 1, 1));

			// Valores exteriores a frontera
			assertFalse("Estamos dentro de la matriz", tablero.posicionValida(-1, 1));

			assertFalse("Estamos dentro de la matriz", tablero.posicionValida(1, tablero.getNColumnas() + 1));
			assertFalse("Estamos dentro de la matriz",
					tablero.posicionValida(tablero.getNFilas() + 1, tablero.getNColumnas() + 1));
			assertFalse("Estamos dentro de la matriz", tablero.posicionValida(tablero.getNFilas(), -1));

		}

	}

	@Test
	public void testRestarMinasAlrededor() { // Decision coverage, condition coverage.

//		for (int i = 0; i < 3; i++) {
//			tablero = new Tablero(i);
//			tablero.getCasillas(0, tablero.getNColumnas()).setEsMina(true);
//			tablero.sumarMinasAlrededor(0, tablero.getNColumnas());
//
//			tablero.getCasillas(tablero.getNFilas(), 0).setEsMina(true);
//			tablero.sumarMinasAlrededor(tablero.getNFilas(), 0);
//
//			tablero.getCasillas(tablero.getNFilas() - 1, 0).setEsMina(true);
//			tablero.sumarMinasAlrededor(tablero.getNFilas() - 1, 0);
//		}
//
		int fila = 5;
		int col = 5;

		// Valores límite fila,col
		// Valores interiores
		fila = 4;
		col = 4;
		tablero = new Tablero(0);
		tablero.sumarMinasAlrededor(fila, col);
		tablero.restarMinasAlrededor(fila, col);
		assertEquals(tablero.getCasillas(fila, col).getminasCercanas(), 0);

		// Valores frontera
		fila = 0;
		col = 9;
		tablero = new Tablero(0);
		tablero.sumarMinasAlrededor(fila, col);
		tablero.restarMinasAlrededor(fila, col);
		assertEquals(tablero.getCasillas(fila, col).getminasCercanas(), 0);

		fila = 9;
		col = 0;
		tablero = new Tablero(0);
		tablero.sumarMinasAlrededor(fila, col);
		tablero.restarMinasAlrededor(fila, col);
		assertEquals(tablero.getCasillas(fila, col).getminasCercanas(), 0);

		// Valors interiors a frontera
		fila = 1;
		col = 9;
		tablero = new Tablero(0);
		tablero.sumarMinasAlrededor(fila, col);
		tablero.restarMinasAlrededor(fila, col);
		assertEquals(tablero.getCasillas(fila, col).getminasCercanas(), 0);

		fila = 9;
		col = 1;
		tablero = new Tablero(0);
		tablero.sumarMinasAlrededor(fila, col);
		tablero.restarMinasAlrededor(fila, col);
		assertEquals(tablero.getCasillas(fila, col).getminasCercanas(), 0);

		// Valores exteriores a frontera

		fila = -1;
		col = 10;
		tablero = new Tablero(0);
		tablero.sumarMinasAlrededor(fila, col);
		tablero.restarMinasAlrededor(fila, col);
		assertEquals(tablero.getCasillas(fila, col).getminasCercanas(), 0);

		fila = 10;
		col = -1;
		tablero = new Tablero(0);
		tablero.sumarMinasAlrededor(fila, col);
		tablero.restarMinasAlrededor(fila, col);
		assertEquals(tablero.getCasillas(fila, col).getminasCercanas(), 0);

		fila = 5;
		col = 5;
		// 0 minas- No puede dar menos de 0.

		tablero.restarMinasAlrededor(fila, col);
		assertEquals(tablero.getCasillas(fila, col).getminasCercanas(), 0);

		// 1 minas
		tablero.getCasillas(fila, col).setEsMina(true);
		tablero.sumarMinasAlrededor(fila, col);

		assertEquals(tablero.getCasillas(fila - 1, col).getminasCercanas(), 1);
		assertEquals(tablero.getCasillas(fila, col + 1).getminasCercanas(), 1);
		assertEquals(tablero.getCasillas(fila, col - 1).getminasCercanas(), 1);
		assertEquals(tablero.getCasillas(fila - 1, col + 1).getminasCercanas(), 1);
		assertEquals(tablero.getCasillas(fila - 1, col - 1).getminasCercanas(), 1);
		assertEquals(tablero.getCasillas(fila + 1, col + 1).getminasCercanas(), 1);
		assertEquals(tablero.getCasillas(fila + 1, col - 1).getminasCercanas(), 1);

		tablero.getCasillas(fila, col).setEsMina(false);
		tablero.restarMinasAlrededor(fila, col);

		assertEquals(tablero.getCasillas(fila - 1, col).getminasCercanas(), 0);
		assertEquals(tablero.getCasillas(fila, col + 1).getminasCercanas(), 0);
		assertEquals(tablero.getCasillas(fila, col - 1).getminasCercanas(), 0);
		assertEquals(tablero.getCasillas(fila - 1, col + 1).getminasCercanas(), 0);
		assertEquals(tablero.getCasillas(fila - 1, col - 1).getminasCercanas(), 0);
		assertEquals(tablero.getCasillas(fila + 1, col + 1).getminasCercanas(), 0);
		assertEquals(tablero.getCasillas(fila + 1, col - 1).getminasCercanas(), 0);

		// Restamos 7 minas

		fila = 1;
		col = 1;
		tablero = new Tablero(0);

		tablero.getCasillas(fila - 1, col).setEsMina(true);
		tablero.sumarMinasAlrededor(fila - 1, col);
		tablero.getCasillas(fila + 1, col).setEsMina(true);
		tablero.sumarMinasAlrededor(fila + 1, col);
		tablero.getCasillas(fila, col + 1).setEsMina(true);
		tablero.sumarMinasAlrededor(fila, col + 1);
		tablero.getCasillas(fila, col - 1).setEsMina(true);
		tablero.sumarMinasAlrededor(fila, col - 1);

		tablero.getCasillas(fila - 1, col + 1).setEsMina(true);
		tablero.sumarMinasAlrededor(fila - 1, col + 1);
		tablero.getCasillas(fila - 1, col - 1).setEsMina(true);
		tablero.sumarMinasAlrededor(fila - 1, col - 1);
		tablero.getCasillas(fila + 1, col + 1).setEsMina(true);
		tablero.sumarMinasAlrededor(fila + 1, col + 1);

		assertEquals(tablero.getCasillas(fila, col).getminasCercanas(), 7);

		tablero.getCasillas(fila - 1, col).setEsMina(false);
		tablero.restarMinasAlrededor(fila - 1, col);
		tablero.getCasillas(fila + 1, col).setEsMina(false);
		tablero.restarMinasAlrededor(fila + 1, col);
		tablero.getCasillas(fila, col + 1).setEsMina(false);
		tablero.restarMinasAlrededor(fila, col + 1);
		tablero.getCasillas(fila, col - 1).setEsMina(false);
		tablero.restarMinasAlrededor(fila, col - 1);

		tablero.getCasillas(fila - 1, col + 1).setEsMina(false);
		tablero.restarMinasAlrededor(fila - 1, col + 1);
		tablero.getCasillas(fila - 1, col - 1).setEsMina(false);
		tablero.restarMinasAlrededor(fila - 1, col - 1);
		tablero.getCasillas(fila + 1, col + 1).setEsMina(false);
		tablero.restarMinasAlrededor(fila + 1, col + 1);

		assertEquals(tablero.getCasillas(fila, col).getminasCercanas(), 0);

		// Restamos 8 minas
		tablero = new Tablero(0);

		tablero.getCasillas(fila - 1, col).setEsMina(true);
		tablero.sumarMinasAlrededor(fila - 1, col);
		tablero.getCasillas(fila + 1, col).setEsMina(true);
		tablero.sumarMinasAlrededor(fila + 1, col);
		tablero.getCasillas(fila, col + 1).setEsMina(true);
		tablero.sumarMinasAlrededor(fila, col + 1);
		tablero.getCasillas(fila, col - 1).setEsMina(true);
		tablero.sumarMinasAlrededor(fila, col - 1);

		tablero.getCasillas(fila - 1, col + 1).setEsMina(true);
		tablero.sumarMinasAlrededor(fila - 1, col + 1);
		tablero.getCasillas(fila - 1, col - 1).setEsMina(true);
		tablero.sumarMinasAlrededor(fila - 1, col - 1);
		tablero.getCasillas(fila + 1, col + 1).setEsMina(true);
		tablero.sumarMinasAlrededor(fila + 1, col + 1);
		tablero.getCasillas(fila + 1, col - 1).setEsMina(true);
		tablero.sumarMinasAlrededor(fila + 1, col - 1);

		assertEquals(tablero.getCasillas(fila, col).getminasCercanas(), 8);

		tablero.getCasillas(fila - 1, col).setEsMina(true);
		tablero.restarMinasAlrededor(fila - 1, col);
		tablero.getCasillas(fila + 1, col).setEsMina(true);
		tablero.restarMinasAlrededor(fila + 1, col);
		tablero.getCasillas(fila, col + 1).setEsMina(true);
		tablero.restarMinasAlrededor(fila, col + 1);
		tablero.getCasillas(fila, col - 1).setEsMina(true);
		tablero.restarMinasAlrededor(fila, col - 1);

		tablero.getCasillas(fila - 1, col + 1).setEsMina(true);
		tablero.restarMinasAlrededor(fila - 1, col + 1);
		tablero.getCasillas(fila - 1, col - 1).setEsMina(true);
		tablero.restarMinasAlrededor(fila - 1, col - 1);
		tablero.getCasillas(fila + 1, col + 1).setEsMina(true);
		tablero.restarMinasAlrededor(fila + 1, col + 1);
		tablero.getCasillas(fila + 1, col - 1).setEsMina(true);
		tablero.restarMinasAlrededor(fila + 1, col - 1);

		assertEquals(tablero.getCasillas(fila, col).getminasCercanas(), 0);

	}

	@Test
	public void testCasillaCerrada() { // Decision coverage.

		tablero.getCasillas(0, 0).setAbierta(true);
		assertFalse("Esta abierta, no cerrada", tablero.casillaCerrada(0, 0));

		tablero.getCasillas(0, 0).setAbierta(false);
		assertTrue("Esta cerrada, no abierta", tablero.casillaCerrada(0, 0));

		assertFalse("Esta cerrada, no abierta", tablero.casillaCerrada(-89, 3));

	}

	@Test
	public void testPonerBandera() { // decision coverage condition coverage

		assertFalse(tablero.getCasillas(1, 1).getBandera());
		tablero.getCasillas(1, 1).cambiarBandera();
		assertTrue(tablero.getCasillas(1, 1).getBandera());

		// No se puede poner una bandera en una casilla abierta

		tablero.getCasillas(1, 2).abrirCasilla();
		tablero.getCasillas(1, 2).cambiarBandera();
		assertFalse(tablero.getCasillas(1, 2).getBandera());

		int k;
		for (int i = -1; i < 4; i++) {
			tablero = new Tablero(i);
			k = tablero.ponerBandera(0, 0);

			assertTrue("La casilla se abre", tablero.getCasillas(0, 0).getBandera());
			assertEquals(0, k);
			k = tablero.ponerBandera(0, 0);
			assertFalse("La casilla no se puede cerrar", tablero.getCasillas(0, 0).getBandera());
			assertEquals(0, k);
			k = tablero.ponerBandera(0, tablero.getNColumnas());
			assertEquals(0, k);
			assertTrue("La casilla se abre", tablero.getCasillas(0, tablero.getNColumnas()).getBandera());
			k = tablero.ponerBandera(0, tablero.getNColumnas() + 1);
			assertEquals(-1, k);
			k = tablero.ponerBandera(tablero.getNFilas(), 0);
			assertEquals(0, k);
			assertTrue("La casilla se abre", tablero.getCasillas(0, tablero.getNColumnas()).getBandera());
			k = tablero.ponerBandera(tablero.getNFilas(), tablero.getNColumnas() + 1);
			assertEquals(-1, k);
			k = tablero.ponerBandera(tablero.getNFilas(), -8);
			assertEquals(-1, k);
			k = tablero.ponerBandera(tablero.getNFilas(), tablero.getNColumnas());
			assertEquals(0, k);
			assertTrue("La casilla se abre", tablero.getCasillas(0, tablero.getNColumnas()).getBandera());
			k = tablero.ponerBandera(tablero.getNFilas() + 1, tablero.getNColumnas() + 1);
			assertEquals(-1, k);
			k = tablero.ponerBandera(-1, -1); // static
			assertEquals(-1, k);
			k = tablero.ponerBandera(100, -100); // static
			assertEquals(-1, k);
		}

	}

	@Test
	public void testGetTablero() { // Test getter para obtener la matriz de casillas
		Casella[][] casillas = tablero.getTablero();

		for (int i = 0; i < casillas.length; i++) {
			for (int j = 0; j < casillas.length; j++) {
				assertFalse(casillas[i][j].getAbierta());
			}

		}

	}
}
