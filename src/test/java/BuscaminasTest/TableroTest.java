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
	public void testGenerarTablero() { // Statement coverage

		// VALORES FRONTERA
		// Tablero 0
		int checksum = 0;
		for (int i = 0; i <= tablero.getNFilas(); i++) {
			for (int j = 0; j <= tablero.getNColumnas(); j++) {

				assertFalse(tablero.getCasillas(i, j).getAbierta());
				assertFalse(tablero.getCasillas(i, j).getBandera());
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
		// (n-1), n pasadas por el loop (n es el n�mero m�ximo de pasadas)

		// n-1

		// n

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

		// (n-1), n pasadas por el loop (n es el n�mero m�ximo de pasadas)

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
	public void testMockValorRandom() {
		MockRandom r = new MockRandom();
		tablero.ponerMinas(r);

		for (int i = -6; i <= 5; i++) {
			tablero = new Tablero(i);
			tablero.ponerMinas(r);

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

		// Comprobamos que se abran m�ltiples casillas si se le da a una casilla con 0
		// minas alrededor.

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

	@Test
	public void testSumaMinasAlrededor() { // DECISION AND CONDITION COVERAGE.

		int fila = 5;
		int col = 5;

		// 0 minas

		assertEquals(tablero.getCasillas(fila - 1, col).getminasCercanas(), 0);
		assertEquals(tablero.getCasillas(fila + 1, col).getminasCercanas(), 0);
		assertEquals(tablero.getCasillas(fila, col + 1).getminasCercanas(), 0);
		assertEquals(tablero.getCasillas(fila, col - 1).getminasCercanas(), 0);
		assertEquals(tablero.getCasillas(fila - 1, col + 1).getminasCercanas(), 0);
		assertEquals(tablero.getCasillas(fila - 1, col - 1).getminasCercanas(), 0);
		assertEquals(tablero.getCasillas(fila + 1, col + 1).getminasCercanas(), 0);
		assertEquals(tablero.getCasillas(fila + 1, col - 1).getminasCercanas(), 0);

		// 8 minas
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

		// 1 minas
		tablero = new Tablero(0);
		tablero.getCasillas(fila, col).setEsMina(true);
		tablero.sumarMinasAlrededor(fila, col);

		assertEquals(tablero.getCasillas(fila - 1, col).getminasCercanas(), 1);
		assertEquals(tablero.getCasillas(fila, col + 1).getminasCercanas(), 1);
		assertEquals(tablero.getCasillas(fila, col - 1).getminasCercanas(), 1);
		assertEquals(tablero.getCasillas(fila - 1, col + 1).getminasCercanas(), 1);
		assertEquals(tablero.getCasillas(fila - 1, col - 1).getminasCercanas(), 1);
		assertEquals(tablero.getCasillas(fila + 1, col + 1).getminasCercanas(), 1);
		assertEquals(tablero.getCasillas(fila + 1, col - 1).getminasCercanas(), 1);

		// 7 MINAS
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

		assertEquals(tablero.getCasillas(fila + 1, col - 1).getminasCercanas(), 2);
		assertEquals(tablero.getCasillas(fila, col).getminasCercanas(), 7);

		fila = -1;
		col = -1;
		tablero = new Tablero(0);
		tablero.sumarMinasAlrededor(fila, col);
		assertEquals(tablero.getCasillas(fila, col).getminasCercanas(), 0);

		fila = 0;
		col = 0;
		tablero = new Tablero(0);
		tablero.sumarMinasAlrededor(fila, col);
		assertEquals(tablero.getCasillas(fila, col).getminasCercanas(), 1);

		fila = 9;
		col = 9;
		tablero = new Tablero(0);
		tablero.sumarMinasAlrededor(fila, col);
		assertEquals(tablero.getCasillas(fila, col).getminasCercanas(), 1);

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

			/*
			 * Esto ira en el informe FILA=0 Y LO DEMAS TRUE SI
			 * 
			 * FILA=9 Y LO DEMAS TRUE
			 * 
			 * 
			 * COLUMNA=0 Y LO DEMAS TRUE
			 * 
			 * COLUMNA=9 Y LO DEMAS TRUE
			 * 
			 * 
			 * FILA=-1 Y LO DEMAS TRUE SI FILA=10 Y LO DEMAS TRUE COLUMNA=10 Y O DEMAS TRUE
			 * SI COLUMNA=-1 Y LO DEMAS TRUE
			 */

		}

	}

	@Test
	public void testRestarMinasAlrededor() { // Decision coverage, condition coverage.

		int fila = 5;
		int col = 5;
		for (int i = 0; i < 3; i++) {
			tablero = new Tablero(i);
			tablero.getCasillas(0, tablero.getNColumnas()).setEsMina(true);
			tablero.sumarMinasAlrededor(0, tablero.getNColumnas());

			tablero.getCasillas(tablero.getNFilas(), 0).setEsMina(true);
			tablero.sumarMinasAlrededor(tablero.getNFilas(), 0);

			tablero.getCasillas(tablero.getNFilas() - 1, 0).setEsMina(true);
			tablero.sumarMinasAlrededor(tablero.getNFilas() - 1, 0);
		}

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

		// Restamos 9 minas (Tiene que dar 0 igual)

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
		tablero.getCasillas(fila + 1, col - 1).setEsMina(true);
		tablero.sumarMinasAlrededor(fila + 1, col - 1);

		assertEquals(tablero.getCasillas(fila, col).getminasCercanas(), 8);

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
		tablero.getCasillas(fila + 1, col - 1).setEsMina(false);
		tablero.restarMinasAlrededor(fila + 1, col - 1);

		tablero.getCasillas(fila + 1, col - 1).setEsMina(false);
		tablero.restarMinasAlrededor(fila + 1, col - 1);

		assertEquals(tablero.getCasillas(fila, col).getminasCercanas(), 0);

		tablero = new Tablero(0);
		fila = -1;
		col = -1;
		tablero.restarMinasAlrededor(fila, col);
		assertEquals(tablero.getCasillas(fila, col).getminasCercanas(), 0);

		// miramos extremos
		tablero = new Tablero(0);
		fila = 9;
		col = 9;
		tablero.restarMinasAlrededor(fila, col);
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
	public void testGetTablero() {
		Casella[][] casillas = tablero.getTablero();

		for (int i = 0; i < casillas.length; i++) {
			for (int j = 0; j < casillas.length; j++) {
				assertFalse(casillas[i][j].getAbierta());
			}

		}

	}
}
