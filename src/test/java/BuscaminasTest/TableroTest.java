package BuscaminasTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

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
	public void testGenerarTablero() {

		int checksum = 0;
		for (int i = 0; i <= tablero.getNFilas(); i++) {
			for (int j = 0; j <= tablero.getNColumnas(); j++) {

				assertFalse(tablero.getCasillas(i, j).getAbierta());
				assertFalse(tablero.getCasillas(i, j).getBandera());
				checksum++;
			}
		}
		assertEquals(100, checksum);

		// accedemos a posiciones arroneas o extremos o negativas

		tablero = new Tablero(3);
		assertEquals("En caso de no cumplir parametros se definira com 10,10", 9, tablero.getNFilas());
		assertEquals("En caso de no cumplir parametros se definira com 10,10", 9, tablero.getNColumnas());

		tablero = new Tablero(-5);
		assertEquals("En caso de no cumplir parametros se definira com 10,10", 9, tablero.getNFilas());
		assertEquals("En caso de no cumplir parametros se definira com 10,10", 9, tablero.getNColumnas());

		tablero = new Tablero(8);
		assertEquals("En caso de no cumplir parametros se definira com 10,10", 9, tablero.getNFilas());
		assertEquals("En caso de no cumplir parametros se definira com 10,10", 9, tablero.getNColumnas());

	}

	@Test
	public void testPonerMinas() {

		// Comprobamos que se pongan el n�mero de minas correctas.
		tablero.ponerMinas(r);
		assertEquals("Dificultad 0= 10 minas", 15, tablero.getNumMinas());
		tablero = new Tablero(1);
		tablero.ponerMinas(r);
		assertEquals("Dificultad 0= 10 minas", 40, tablero.getNumMinas());

		tablero = new Tablero(2);
		tablero.ponerMinas(r);
		assertEquals("Dificultad 0= 10 minas", 99, tablero.getNumMinas());

		tablero = new Tablero(-1);
		tablero.ponerMinas(r);
		assertEquals("Dificultad 0= 10 minas", 15, tablero.getNumMinas());

		tablero = new Tablero(3);
		tablero.ponerMinas(r);
		assertEquals("Dificultad 0= 10 minas", 15, tablero.getNumMinas());

		tablero = new Tablero(81);
		tablero.ponerMinas(r);
		assertEquals("Dificultad 0= 10 minas", 15, tablero.getNumMinas());

	}

	@Test
	public void testPintarTablero() {

		tablero.pintarTablero();

	}

	@Test
	public void testMarcarCasilla() {
		int k;
		for (int i = -1; i < 4; i++) {
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
		tablero = new Tablero(0);

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

		tablero = new Tablero(0);
		tablero.ponerMinas(r);

		for (int w = 0; w <= tablero.getNFilas(); w++) {
			for (int j = 0; j <= tablero.getNColumnas(); j++) {
				if (tablero.getCasillas(w, j).esMina())
					System.out.print("X");
				else
					System.out.print("0");
			}
			System.out.println(" ");
		}

		System.out.println("----------------");
		for (int w = 0; w <= tablero.getNFilas(); w++) {
			for (int j = 0; j <= tablero.getNColumnas(); j++) {
				System.out.print(tablero.getCasillas(w, j).getminasCercanas());
			}
			System.out.println(" ");
		}

		k = tablero.marcarCasilla(0, 0);

		System.out.println("----------------");
		for (int w = 0; w <= tablero.getNFilas(); w++) {
			for (int j = 0; j <= tablero.getNColumnas(); j++) {
				if (tablero.getCasillas(w, j).esMina())
					System.out.print("X");
				else
					System.out.print("0");
			}
			System.out.println(" ");
		}

		System.out.println("----------------");
		for (int w = 0; w <= tablero.getNFilas(); w++) {
			for (int j = 0; j <= tablero.getNColumnas(); j++) {
				System.out.print(tablero.getCasillas(w, j).getminasCercanas());
			}
			System.out.println(" ");
		}

		System.out.println("----------------");
		for (int w = 0; w <= tablero.getNFilas(); w++) {
			for (int j = 0; j <= tablero.getNColumnas(); j++) {
				if (tablero.getCasillas(w, j).getAbierta())
					System.out.print("O");
				else
					System.out.print("C");
			}
			System.out.println(" ");
		}

	}

	@Test
	public void testNumMinasAlrededor() {

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

	}

	@Test
	public void testRestarMinasAlrededor() {

		int fila = 5;
		int col = 5;

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

		tablero.getCasillas(fila - 1, col).setEsMina(false);
		tablero.restarMinasAlrededor(fila - 1, col);

		assertEquals(tablero.getCasillas(fila, col).getminasCercanas(), 7);

	}

	@Test
	public void testPonerBandera() {

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
	public void testValorRandom() {
		MockRandom r = new MockRandom();
		tablero.ponerMinas(r);

		for (int i = -6; i <= 5; i++) {
			tablero = new Tablero(i);
			tablero.ponerMinas(r);

		}
	}

}
