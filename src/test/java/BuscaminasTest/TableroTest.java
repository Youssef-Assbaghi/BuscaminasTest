package BuscaminasTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TableroTest {

	private Tablero tablero;
	
	@Before 
	public void setUp() {
         tablero = new Tablero(0);
    }
	
	
	@Test
	public void testGenerarTablero() {
		
		int checksum = 0;
		for(int i = 0; i <= tablero.getNFilas()-1; i++) {
			for(int j = 0; j <= tablero.getNColumnas()-1; j++) {
				
				assertFalse(tablero.getCasillas(i,j).getAbierta());
				assertFalse(tablero.getCasillas(i,j).getBandera());
				checksum++;
			}
		}
		assertEquals(100, checksum);
		
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
	public void testPonerMinas(){
		
		tablero.ponerMinas();
		assertEquals("Dificultad 0= 10 minas" ,10,tablero.getNumMinas());
		tablero = new Tablero(1);
		tablero.ponerMinas();
		assertEquals("Dificultad 0= 10 minas" ,40,tablero.getNumMinas());
		
		tablero = new Tablero(2);
		tablero.ponerMinas();
		assertEquals("Dificultad 0= 10 minas" ,99,tablero.getNumMinas());
		
		
		tablero = new Tablero(-1);
		tablero.ponerMinas();
		assertEquals("Dificultad 0= 10 minas" ,10,tablero.getNumMinas());
		
		tablero = new Tablero(3);
		tablero.ponerMinas();
		assertEquals("Dificultad 0= 10 minas" ,10,tablero.getNumMinas());
		
		tablero = new Tablero(81);
		tablero.ponerMinas();
		assertEquals("Dificultad 0= 10 minas" ,10,tablero.getNumMinas());
		
	}
	
	@Test
	public void testPintarTablero() {
		
		tablero.pintarTablero();
		
		
	}
	
	@Test
	public void testMarcarCasilla() {
		assertTrue(true);
	}
	
	@Test
	public void testPonerBandera() {
		
		//Al empezar no puede haber una bandera en ninguna casilla.
		
		
		assertFalse(tablero.getCasillas(1, 1).getBandera());
		
		tablero.getCasillas(1, 1).cambiarBandera();
		
		assertTrue(tablero.getCasillas(1, 1).getBandera());
		
		//No se puede poner una bandera en una casilla abierta
		
		tablero.getCasillas(1,2).abrirCasella();
		tablero.getCasillas(1,2).cambiarBandera();
		assertFalse(tablero.getCasillas(1,2).getBandera());
		
		
		
	}

}
