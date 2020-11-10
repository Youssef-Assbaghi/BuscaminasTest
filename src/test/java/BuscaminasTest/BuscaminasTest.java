package BuscaminasTest;

import static org.junit.Assert.*;

import org.junit.Test;

import Controlador.Buscaminas;
import Modelo.Tablero;

public class BuscaminasTest {

	@Test
	public void testMain() {
		MockView prueba=new MockView();
		Buscaminas juego=new Buscaminas();
		juego.main(prueba);
		Tablero testeable=juego.getTablero();
		
		assertEquals("Debe dar diez",10,testeable.getNFilas());
		assertEquals("Debe dar diez",10,testeable.getNColumnas());
		assertEquals("Debe ser zero",0,testeable.getDificultad());
		
		
		
	}

}
