package BuscaminasTest;

import static org.junit.Assert.*;

import org.junit.Test;

public class TableroTest {

	@Test
	public void testGenerarTablero() {
		Tablero tablero = new Tablero(10,10);
		
		tablero.generarTablero();
		int checksum = 0;
		for(int i = 0; i < tablero.getNFilas(); i++) {
			for(int j = 0; j < tablero.getNColumnas(); j++) {
				
				assertFalse(tablero.casillas[i][j].getEstado());
				checksum++;
			}
		}
		assertEquals(100, checksum);
		
	}
	
	@Test
	public void testPintarTablero() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testMarcarCasilla() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testPonerBandera() {
		
		//Al empezar no puede haber una bandera en ninguna casilla.
		
		Tablero tablero = new Tablero(10,10);
		assertFalse(tablero.casillas[1][1].getBandera());
		
		tablero.casillas[1][1].cambiarBandera();
		
		assertTrue(tablero.casillas[1][1].getBandera());
		
		//No se puede poner una bandera en una casilla abierta
		
		tablero.casillas[1][2].abrirCasella();
		tablero.casillas[1][2].cambiarBandera();
		assertFalse(tablero.casillas[1][1].getBandera());
		
		
		
	}

}
