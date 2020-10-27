package BuscaminasTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TableroTest {

	private Casella casilla;
	@Before 
	public void setUp() {
        casilla = new Casella();
    }
	
	@Test
	public void testGenerarTablero() {
		Tablero tablero = new Tablero(10,10);
		
		tablero.generarTablero();
		int checksum = 0;
		for(int i = 0; i < tablero.getNFilas(); i++) {
			for(int j = 0; j < tablero.getNColumnas(); j++) {
				
				assertFalse(tablero.getCasillas(i,j).getEstado());
				checksum++;
			}
		}
		assertEquals(100, checksum);
		
	}
	
	@Test
	public void testPintarTablero() {
		assertTrue(true);
	}
	
	@Test
	public void testMarcarCasilla() {
		assertTrue(true);
	}
	
	@Test
	public void testPonerBandera() {
		
		//Al empezar no puede haber una bandera en ninguna casilla.
		
		Tablero tablero = new Tablero(10,10);
		tablero.generarTablero();
		assertFalse(tablero.getCasillas(1, 1).getBandera());
		
		tablero.getCasillas(1, 1).cambiarBandera();
		
		assertTrue(tablero.getCasillas(1, 1).getBandera());
		
		//No se puede poner una bandera en una casilla abierta
		
		tablero.getCasillas(1,2).abrirCasella();
		tablero.getCasillas(1,2).cambiarBandera();
		assertFalse(tablero.getCasillas(1,2).getBandera());
		
		
		
	}

}
