package BuscaminasTest;

import static org.junit.Assert.*;

import org.junit.Test;

import Controlador.Buscaminas;
import Modelo.Tablero;

public class BuscaminasTest {

	@Test
	public void testMain() {
		MockView prueba=new MockView();
		Buscaminas juego=new Buscaminas(prueba);
		String[] hey=null;
		Buscaminas.main(hey);
		Tablero testeable=juego.getTablero();
		
		assertEquals("Debe dar diez",9,testeable.getNFilas());
		assertEquals("Debe dar diez",9,testeable.getNColumnas());
		assertEquals("Debe ser zero",0,testeable.getDificultad());
		assertTrue("Esta casilla se abre",testeable.getCasillas(0, 0).getAbierta());

	}

}
