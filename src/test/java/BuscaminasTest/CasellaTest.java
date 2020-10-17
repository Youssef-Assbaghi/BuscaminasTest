package BuscaminasTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CasellaTest {

	private Casella casilla;
	
	/**
	 * PRUEBA TEST DE LOS GETTERS	
	 * @Test
	 *	public void testCreate() {
     *		assertEquals(23, new MyClass(23).getX()); 
	 *	}
	 */
	
	@Before 
	public void setUp() {
        casilla = new Casella();
    }
	@Test
	public void testCambiarBandera() {
		
		
		assertFalse("Al principio de la ejecucion debe dar False", casilla.getBandera());
		
		casilla.cambiarBandera();
		
		assertTrue("Como se inicializa a zero debe devolver un True conforme hay Bandera", casilla.getBandera());
		
		casilla.cambiarBandera();
		assertFalse("Una vez cambia de bandera al volver a darle se debe quitar y devolver False", casilla.getBandera());
		
		casilla.setEstado(true);
		assertFalse("Si la mina es abierta no se puede abrir la casilla", casilla.getBandera());
		
	}
	
	@Test
	public void testAbrirCasella() {
	
		assertFalse("Al crear la variable la casilla debe estar cerrada osea False", casilla.getEstado());
		
		casilla.abrirCasella();
		assertTrue("Como se inicializa a false, al cambiar de estado debe ser true", casilla.getEstado());
		
		casilla.abrirCasella();
		assertTrue("Como  ya se ha abierto no se puede volver a abrir", casilla.getEstado());
		
		casilla.setEstado(false); // Seteamos para poder volver al estrado inicial y seguir haciedo test
		casilla.cambiarBandera();
		
		assertFalse("Si hay bandera primero se debe quitar y luego se puede hace", casilla.getEstado());
		
		casilla.cambiarBandera();
		casilla.abrirCasella();
		assertTrue("Como una vez se quita la bandera se puede cambiar el estado de la casilla", casilla.getEstado());
		
	}
	


}












