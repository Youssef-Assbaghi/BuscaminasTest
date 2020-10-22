package BuscaminasTest;

import static org.junit.Assert.*;

import org.junit.Test;

public class TableroTest {

	@Test
	public void testGenerarTablero() {
		fail("Not yet implemented");
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
		
		Tablero tablero = new Tablero(20,20);
		assertFalse(tablero.casillas[1][1].getBandera());
		
		tablero.casillas[1][1].cambiarBandera();
		
		assertTrue(tablero.casillas[1][1].getBandera());
		
		
	}

}
