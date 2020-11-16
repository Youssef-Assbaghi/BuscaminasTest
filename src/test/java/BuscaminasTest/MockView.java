package BuscaminasTest;

import Modelo.Casella;
import Vista.VistaInterfaz;

public class MockView implements VistaInterfaz {

	int cuento = -1;
	private int counter = 0;

	@Override
	public int pedirColumna() {

		cuento++;
		if (cuento == 30) {
			cuento = -1;
			return 0;
		}

		return -154;
	}

	@Override
	public int pedirFila() {

		cuento++;
		if (cuento == 30) {
			cuento = -1;
			return 0;
		}
		if (cuento > 15) {
			
			return 100;
		}

		return -1;
	}

	@Override
	public int pedirDificultad() {

		cuento++;
		if (cuento == 30) {
			cuento = -1;
			return 0;
		}
		return -1;
	}



	@Override
	public int pedirTipoJugada() {
		counter++;
		if (counter == 30) {
			counter = -1;
			return 0;
		}
		if (counter == 5) {
			return 2;
		}
		
		if (counter ==8) {
			return 1;
		}
		return counter;
	}

	@Override
	public void printTablero(Casella[][] casilla, int dificultad) {

		
	}

	@Override
	public void printBienvenido() {

	}

	@Override
	public void printHasPerdido() {
		
	}
	
	@Override
	public void printHasGanado() {
	
		
	}



}
