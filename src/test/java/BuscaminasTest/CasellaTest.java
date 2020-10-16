package BuscaminasTest;

import static org.junit.Assert.*;

import org.junit.Test;

public class CasellaTest {

	@Test
	public void testCambiarBandera() {
		Casella bandera =new Casella();
		bandera.cambiarBandera();
		
		assertFalse("Como se inicializa a zero debe devolver un True conforme hay Bandera", bandera.getBandera());
		
		bandera.cambiarBandera();
		assertTrue("Una vez cambia de bandera al volver a darle se debe quitar y devolver False", bandera.getBandera());
	}

}
