package Demo;

import org.junit.Test;

import Controlador.MockBuscaminas;
import Controlador.MockVistaMain;

public class MainTest {

	@Test
	public void testMain() {
		MockBuscaminas mockBuscaminas = new MockBuscaminas();
		MockVistaMain mockVista= new MockVistaMain();
		Main principal = new Main();
		principal.init(mockBuscaminas,mockVista);
		String args[]=null;
		Main.main(args);

		
	}
}
