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
	public void constrcutorCasellaTest() {
		assertEquals("Al empezar debe tener 0 minas cercanas",0, casilla.getminasCercanas());
		assertFalse("Al crear la variable la casilla debe estar cerrada osea False", casilla.getEstado());
		assertFalse("Al principio de la ejecucion no es  bandera por lo tanto debe dar False", casilla.getBandera());
		assertFalse("Al principio de la ejecucion no es mina por lo tanto debe dar False", casilla.esMina());
	}
	
	@Test
	//(int nMinas,boolean mina,boolean state,boolean flag)
	public void constructorParametroCAsellaTest() {
		casilla = new Casella(18,true,true,true);
		assertEquals("Al setear no puede tener mas de 8 minas cercanas",8, casilla.getminasCercanas());
		assertFalse("Si la casilla esta abierta la bandera debe ser igual a FALSE", casilla.getBandera());
		casilla = new Casella(0,false,true,true);
		assertEquals("Al setear  puede tener 0 minas cercanas",0, casilla.getminasCercanas());
		assertFalse("Si la casilla esta abierta la bandera debe ser igual a FALSE", casilla.getBandera());
		casilla = new Casella(-1,true,true,false);
		assertEquals("Al setear no puede tener minas cercanas negativas",0, casilla.getminasCercanas());
		casilla = new Casella(8,false,true,false);
		assertEquals("Al setear no puede tener mas de 8 minas cercanas",8, casilla.getminasCercanas());
		casilla = new Casella(5,false,false,false);
		assertFalse("Al setear no puede tener mas de 8 minas cercanas", casilla.getBandera());
		
		
		
		
		
		
	}
	
	
	@Test
	public void testCambiarBandera() {
		
		
		assertFalse("Al principio de la ejecucion debe dar False", casilla.getBandera());
		
		casilla.cambiarBandera();
		
		assertTrue("Como se inicializa a zero debe devolver un True conforme hay Bandera", casilla.getBandera());
		
		casilla.cambiarBandera();
		assertFalse("Una vez cambia de bandera al volver a darle se debe quitar y devolver False", casilla.getBandera());
		
		casilla.setEstado(true);
		casilla.setBandera(true);
		casilla.cambiarBandera();
		assertFalse("Si la mina es abierta no se puede poner bandera a la casilla", casilla.getBandera());
		
	}
	
	@Test
	public void testAbrirCasella() {
	
		assertFalse("Al crear la variable la casilla debe estar cerrada osea False", casilla.getEstado());
		
		casilla.abrirCasella();
		assertTrue("Como se inicializa a false, al cambiar de estado debe ser true", casilla.getEstado());
		
		casilla.abrirCasella();
		assertTrue("Como  ya se ha abierto no se puede volver a abrir", casilla.getEstado());
		
		casilla.setBandera(true);
		casilla.abrirCasella();
		assertTrue("Si abierto y bandera se queda abierto y se elimina bandera", casilla.getEstado());
		assertFalse("Si abierto y bandera se queda abierto y se elimina bandera", casilla.getBandera());
		
		casilla.setEstado(false); // Seteamos para poder volver al estrado inicial y seguir haciedo test
		casilla.cambiarBandera();
		
		assertFalse("Si hay bandera primero se debe quitar y luego se puede hacer", casilla.getEstado());
		
		casilla.cambiarBandera();
		casilla.abrirCasella();
		assertTrue("Como una vez se quita la bandera se puede cambiar el estado de la casilla", casilla.getEstado());
		
		casilla.setEstado(true); 
		casilla.setBandera(true);
		casilla.abrirCasella();
		assertFalse("Si esta abierta y tenemos bandera debemos cambiarla a false", casilla.getBandera());
		assertTrue("El estado seria TRUE", casilla.getEstado());
		
		casilla.setEstado(false); 
		casilla.setBandera(true);
		casilla.abrirCasella();
		assertFalse("El estado seria false", casilla.getEstado());
		assertFalse("El estado seria TRUE", casilla.getBandera());
		
	}
	



}












