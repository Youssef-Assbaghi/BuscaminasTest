package Demo;

import org.junit.Test;

import Controlador.MockBuscaminas;

public class MainTest {

	@Test
	public void testMain() {
		MockBuscaminas mockBuscaminas = new MockBuscaminas();
		Main principal = new Main();
		principal.init(mockBuscaminas);
		String args[]=null;
		Main.main(args);
		
	}
}
