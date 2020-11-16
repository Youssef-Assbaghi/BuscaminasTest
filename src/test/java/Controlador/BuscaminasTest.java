package Controlador;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import Controlador.Buscaminas;
import Modelo.Casella;
import Modelo.Tablero;
import Modelo.ValorRandom;

public class BuscaminasTest {

	@Test
	public void testJugar() {
		MockView prueba = new MockView();
		Buscaminas juego = new Buscaminas(prueba);
		juego.jugar();
		Tablero testeable = juego.getTablero();
		
		assertEquals("Debe dar 9", 9, testeable.getNFilas()); // de 0 a 9 filas
		assertEquals("Debe dar 9", 9, testeable.getNColumnas()); // de 0 a 9 columnas
		assertEquals("Debe ser zero", 0, testeable.getDificultad());
		assertTrue("Esta casilla se abre", testeable.getCasillas(0, 0).getAbierta());

	}

	@Test
	public void testInitVista() {
		
		MockView prueba = new MockView();
		Buscaminas juego = new Buscaminas(prueba);

		juego.jugar();

		
		juego = new Buscaminas(null);
		juego.initVista();

		assertNotNull(juego.getVista());

	}

	@Test
	public void testEsVictoria() {
		MockView prueba = new MockView();
		Buscaminas juego = new Buscaminas(prueba);
		
		juego.jugar();
		juego.detectarVictoria();
		// Expected false
		assertFalse(juego.getVictoria());

		// Expect ganar partida
		ValorRandom r = new ValorRandom();
		juego = new Buscaminas(prueba);
		Tablero tablero = juego.getTablero();
		tablero.ponerMinas(r);
		Casella casillas[][] = tablero.getTablero();

		for (int i = 0; i < casillas.length; i++) {
			for (int j = 0; j < casillas[i].length; j++) {
				if (!casillas[i][j].esMina()) {
					tablero.marcarCasilla(i, j);
				}
			}
		}

		juego.detectarVictoria();
		assertTrue(juego.getVictoria());

	}

	@Test
	public void detectarDerrota() {

		MockView prueba = new MockView();
		ValorRandom r = new ValorRandom();
		Buscaminas juego = new Buscaminas(prueba);
		Tablero tablero = juego.getTablero();
		juego.detectarDerrota(0, 0);

		// Expected false
		assertFalse(juego.getSalir());

		// Expect perder partida
		r = new ValorRandom();
		juego = new Buscaminas(prueba);
		tablero = juego.getTablero();
		tablero.ponerMinas(r);
		Casella casillas[][] = tablero.getTablero();

		int i = 0;
		int j = 0;

		tablero.marcarCasilla(0, 0);
		outerloop: for (i = 0; i < casillas.length; i++) {
			for (j = 0; j < casillas[i].length; j++) {
				if (casillas[i][j].esMina()) {
					tablero.marcarCasilla(i, j);
					break outerloop;
				}
			}
		}

		juego.detectarDerrota(i, j);
		assertTrue(juego.getSalir());
	}
}
