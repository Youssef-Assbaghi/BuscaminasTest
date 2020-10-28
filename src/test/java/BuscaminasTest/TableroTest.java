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
		Tablero tablero = new Tablero(0);
		
		int checksum = 0;
		int numMinas=0;
		for(int i = 0; i <= tablero.getNFilas()-1; i++) {
			for(int j = 0; j <= tablero.getNColumnas()-1; j++) {
				
				if(tablero.getCasillas(i,j).getMina()){
					numMinas++;
				}
				assertFalse(tablero.getCasillas(i,j).getEstado());
				assertFalse(tablero.getCasillas(i,j).getBandera());
				checksum++;
			}
		}
		assertEquals(100, checksum);
		assertEquals(10,numMinas);
		//accedemos a posiciones arroneas o extremos o negativas
		
		tablero=new Tablero(3);
		assertEquals("En caso de no cumplir parametros se definira com 10,10",10,tablero.getNFilas());    
		assertEquals("En caso de no cumplir parametros se definira com 10,10",10,tablero.getNColumnas());    
		
		tablero=new Tablero(-5);
		assertEquals("En caso de no cumplir parametros se definira com 10,10",10,tablero.getNFilas());    
		assertEquals("En caso de no cumplir parametros se definira com 10,10",10,tablero.getNColumnas());    
		
		tablero=new Tablero(8);
		assertEquals("En caso de no cumplir parametros se definira com 10,10",10,tablero.getNFilas());    
		assertEquals("En caso de no cumplir parametros se definira com 10,10",10,tablero.getNColumnas());    
		
	}
	
	@Test
	public void testPintarTablero() {
		Tablero tablero = new Tablero(0);
		tablero.pintarTablero();
		
		
	}
	
	@Test
	public void testMarcarCasilla() {
		assertTrue(true);
	}
	
	@Test
	public void testPonerBandera() {
		
		//Al empezar no puede haber una bandera en ninguna casilla.
		
		Tablero tablero = new Tablero(0);

		assertFalse(tablero.getCasillas(1, 1).getBandera());
		
		tablero.getCasillas(1, 1).cambiarBandera();
		
		assertTrue(tablero.getCasillas(1, 1).getBandera());
		
		//No se puede poner una bandera en una casilla abierta
		
		tablero.getCasillas(1,2).abrirCasella();
		tablero.getCasillas(1,2).cambiarBandera();
		assertFalse(tablero.getCasillas(1,2).getBandera());
		
		
		
	}

}
