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
		for(int i = 0; i <= tablero.getNFilas(); i++) {
			for(int j = 0; j <= tablero.getNColumnas(); j++) {
				
				assertFalse(tablero.getCasillas(i,j).getAbierta());
				assertFalse(tablero.getCasillas(i,j).getBandera());
				checksum++;
			}
		}
		assertEquals(100, checksum);
		
		//accedemos a posiciones arroneas o extremos o negativas
		
		tablero=new Tablero(3);
		assertEquals("En caso de no cumplir parametros se definira com 10,10",9,tablero.getNFilas());    
		assertEquals("En caso de no cumplir parametros se definira com 10,10",9,tablero.getNColumnas());    
		
		tablero=new Tablero(-5);
		assertEquals("En caso de no cumplir parametros se definira com 10,10",9,tablero.getNFilas());    
		assertEquals("En caso de no cumplir parametros se definira com 10,10",9,tablero.getNColumnas());    
		
		tablero=new Tablero(8);
		assertEquals("En caso de no cumplir parametros se definira com 10,10",9,tablero.getNFilas());    
		assertEquals("En caso de no cumplir parametros se definira com 10,10",9,tablero.getNColumnas());    
		
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
		int k;
		for(int i=-1;i<4;i++){
			tablero=new Tablero(i);
			tablero.ponerMinas();
			k=tablero.marcarCasilla(0,0); // static
			assertTrue("La casilla se abre",tablero.getCasillas(0,0).getAbierta());
			assertEquals(0,k);
			k=tablero.marcarCasilla(0,0); //static
			assertTrue("La casilla no se puede cerrar",tablero.getCasillas(0,0).getAbierta());
			assertEquals(0,k);
			k=tablero.marcarCasilla(0,tablero.getNColumnas());
			assertEquals(0,k);
			assertTrue("La casilla se abre",tablero.getCasillas(0,tablero.getNColumnas()).getAbierta());
			k=tablero.marcarCasilla(0,tablero.getNColumnas()+1);
			assertEquals(-1,k);
			k=tablero.marcarCasilla(tablero.getNFilas(),0);
			assertEquals(0,k);
			assertTrue("La casilla se abre",tablero.getCasillas(0,tablero.getNColumnas()).getAbierta());
			k=tablero.marcarCasilla(tablero.getNFilas(),tablero.getNColumnas()+1);	
			assertEquals(-1,k);
			k=tablero.marcarCasilla(tablero.getNFilas(),-8);	
			assertEquals(-1,k);
			k=tablero.marcarCasilla(tablero.getNFilas(),tablero.getNColumnas());
			assertEquals(0,k);
			assertTrue("La casilla se abre",tablero.getCasillas(0,tablero.getNColumnas()).getAbierta());
			k=tablero.marcarCasilla(tablero.getNFilas()+1,tablero.getNColumnas()+1);
			assertEquals(-1,k);
			k=tablero.marcarCasilla(-1,-1); //static
			assertEquals(-1,k);
			k=tablero.marcarCasilla(100,-100); //static
			assertEquals(-1,k);
		}
		

	}
	/**
            N.W   N   N.E 
              \   |   / 
               \  |  / 
            W----Cell----E 
                /  | \ 
               /   |  \ 
            S.W    S   S.E 
  
        Cell-->Current Cell (row, col) 
        N -->  North        (row-1, col) 
        S -->  South        (row+1, col) 
        E -->  East         (row, col+1) 
        W -->  West            (row, col-1) 
        N.E--> North-East   (row-1, col+1) 
        N.W--> North-West   (row-1, col-1) 
        S.E--> South-East   (row+1, col+1) 
        S.W--> South-West   (row+1, col-1)
	 */
	@Test
	public void testNumMinasAlrededor() {
		
		int fila = 5;
		int col = 5;
		
		//0 minas
		
		tablero.numMinasAlrededor(fila-1,col);
		assertEquals(tablero.getCasillas(fila-1,col).getminasCercanas(), 0);
		
		tablero.numMinasAlrededor(fila+1,col);
		assertEquals(tablero.getCasillas(fila+1, col).getminasCercanas(), 0);
		
		tablero.numMinasAlrededor(fila,col+1);
		assertEquals(tablero.getCasillas(fila, col+1).getminasCercanas(), 0);
		
		tablero.numMinasAlrededor(fila,col-1);
		assertEquals(tablero.getCasillas(fila,col-1).getminasCercanas(), 0);
		
		tablero.numMinasAlrededor(fila-1,col+1);
		assertEquals(tablero.getCasillas(fila-1,col+1).getminasCercanas(), 0);
		
		tablero.numMinasAlrededor(fila-1,col-1);
		assertEquals(tablero.getCasillas(fila-1,col+1).getminasCercanas(), 0);
		
		tablero.numMinasAlrededor(fila+1,col+1);
		assertEquals(tablero.getCasillas(fila,col).getminasCercanas(), 0);
		
		tablero.numMinasAlrededor(fila+1,col-1);
		assertEquals(tablero.getCasillas(fila,col).getminasCercanas(), 0);
		
		//1 mina
		
		tablero.getCasillas(fila,col).setEsMina(true);
		
		tablero.numMinasAlrededor(fila-1,col);
		assertEquals(tablero.getCasillas(fila-1,col).getminasCercanas(), 1);
		
		tablero.numMinasAlrededor(fila+1,col);
		assertEquals(tablero.getCasillas(fila+1, col).getminasCercanas(), 1);
		
		tablero.numMinasAlrededor(fila,col+1);
		assertEquals(tablero.getCasillas(fila, col+1).getminasCercanas(), 1);
		
		tablero.numMinasAlrededor(fila,col-1);
		assertEquals(tablero.getCasillas(fila,col-1).getminasCercanas(), 1);
		
		tablero.numMinasAlrededor(fila-1,col+1);
		assertEquals(tablero.getCasillas(fila-1,col+1).getminasCercanas(), 1);
		
		tablero.numMinasAlrededor(fila-1,col-1);
		assertEquals(tablero.getCasillas(fila-1,col+1).getminasCercanas(), 1);
		
		tablero.numMinasAlrededor(fila+1,col+1);
		assertEquals(tablero.getCasillas(fila,col).getminasCercanas(), 1);
		
		tablero.numMinasAlrededor(fila+1,col-1);
		assertEquals(tablero.getCasillas(fila,col).getminasCercanas(), 1);
		
		//8 minas
		tablero = new Tablero(0);
		
		tablero.getCasillas(fila-1,col).setEsMina(true);
		tablero.getCasillas(fila+1,col).setEsMina(true);
		tablero.getCasillas(fila,col+1).setEsMina(true);
		tablero.getCasillas(fila,col-1).setEsMina(true);
		tablero.getCasillas(fila-1,col+1).setEsMina(true);
		tablero.getCasillas(fila-1,col-1).setEsMina(true);
		tablero.getCasillas(fila+1,col+1).setEsMina(true);
		tablero.getCasillas(fila+1,col-1).setEsMina(true);
		
		tablero.numMinasAlrededor(fila,col);
		assertEquals(tablero.getCasillas(fila,col).getminasCercanas(),8);
		
		
		
		}
		
	
	@Test
	public void testPonerBandera() {
		
		//Al empezar no puede haber una bandera en ninguna casilla.
		//Tampoco puedes poner banderas al principio del juego
		
		assertFalse(tablero.getCasillas(1, 1).getBandera());
		
		tablero.getCasillas(1, 1).cambiarBandera();
		
		assertTrue(tablero.getCasillas(1, 1).getBandera());
		
		//No se puede poner una bandera en una casilla abierta
		
		tablero.getCasillas(1,2).abrirCasilla();
		tablero.getCasillas(1,2).cambiarBandera();
		assertFalse(tablero.getCasillas(1,2).getBandera());
		
		
	}

}
