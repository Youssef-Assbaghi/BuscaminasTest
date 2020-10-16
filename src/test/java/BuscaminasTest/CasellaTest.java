package BuscaminasTest;

import static org.junit.Assert.*;

import org.junit.Test;

public class CasellaTest {

	@Test
	public void testCambiarBandera() {
		Casella bandera =new Casella();
		bandera.cambiarBandera();
		
		assertTrue("Como se inicializa a zero debe devolver un True conforme hay Bandera", bandera.getBandera());
		
		bandera.cambiarBandera();
		assertFalse("Una vez cambia de bandera al volver a darle se debe quitar y devolver False", bandera.getBandera());
		
		bandera.setEstado(true);
		assertFalse("Si la mina es abierta no se puede abrir la casilla", bandera.getBandera());
		
	}

}
