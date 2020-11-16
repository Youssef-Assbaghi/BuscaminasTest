package BuscaminasTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import Controlador.Buscaminas;
import Modelo.Casella;
import Modelo.Tablero;
import Modelo.ValorRandom;

public class BuscaminasTest {

	@Test
	public void testMain() {
		MockView prueba = new MockView();
		Buscaminas juego = new Buscaminas(prueba);
		String[] hey = null;
		Buscaminas.main(hey);
		Tablero testeable = juego.getTablero();

		assertEquals("Debe dar diez", 9, testeable.getNFilas());
		assertEquals("Debe dar diez", 9, testeable.getNColumnas());
		assertEquals("Debe ser zero", 0, testeable.getDificultad());
		assertTrue("Esta casilla se abre", testeable.getCasillas(0, 0).getAbierta());

		// juego=new Buscaminas(null);
		// Buscaminas.main(hey);

	}

	@Test
	public void testEsVictoria() {
		MockView prueba = new MockView();
		Buscaminas juego = new Buscaminas(prueba);
		String[] hey = null;
		Buscaminas.main(hey);
		Buscaminas.detectarVictoria();
		// Expected false
		assertFalse(juego.getVictoria());

		// Expect ganar partida
		ValorRandom r = new ValorRandom();
		juego = new Buscaminas(prueba);
		Tablero tablero = juego.getTablero();
		tablero.ponerMinas(r);
		Casella casillas[][] = tablero.getMatrizCasillas();

		for (int i = 0; i < casillas.length; i++) {
			for (int j = 0; j < casillas[i].length; j++) {
				if (!casillas[i][j].esMina()) {
					tablero.marcarCasilla(i, j);
				}
			}
		}

		Buscaminas.detectarVictoria();
		assertTrue(juego.getVictoria());

	}

	@Test
	public void detectarDerrota() {
		MockView prueba = new MockView();
		Buscaminas juego = new Buscaminas(prueba);
		String[] hey = null;
		Buscaminas.main(hey);
		Buscaminas.detectarDerrota();
		// Expected false
		assertFalse(juego.getSalir());

		// Expect perder partida
		ValorRandom r = new ValorRandom();
		juego = new Buscaminas(prueba);
		Tablero tablero = juego.getTablero();
		tablero.ponerMinas(r);
		Casella casillas[][] = tablero.getMatrizCasillas();

		for (int i = 0; i < casillas.length; i++) {
			for (int j = 0; j < casillas[i].length; j++) {
				if (casillas[i][j].esMina()) {
					tablero.marcarCasilla(i, j);
				}
			}
		}

		Buscaminas.detectarDerrota();
		assertTrue(juego.getVictoria());
	}
}
