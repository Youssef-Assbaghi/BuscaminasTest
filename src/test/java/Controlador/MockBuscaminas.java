package Controlador;

import Modelo.Tablero;
import Vista.VistaInterfaz;

public class MockBuscaminas implements InterfazBuscaminas {

	public MockBuscaminas() {
		
	}
	@Override
	public VistaInterfaz getVista() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Tablero getTablero() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void initVista() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean getVictoria() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getSalir() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void detectarVictoria() {
		// TODO Auto-generated method stub

	}

	@Override
	public void detectarDerrota(int fila, int columna) {
		// TODO Auto-generated method stub

	}

	@Override
	public void jugar() {
		// TODO Auto-generated method stub
		System.out.println("AQUI SE JUEGA PERO SOLO ME VES A MI");
		int dificultad=0;
		for (int fila = 0; fila < 10; fila++) {
			System.out.println("");
			System.out.println("-----------------------------------------");
			
			for (int col = 0; col < 10; col++) {
				System.out.print("|");
					System.out.print(" " + "C" + " ");
			}
			System.out.print("|");
		}
		System.out.println("");

		System.out.println("-----------------------------------------");
		
	}

}
