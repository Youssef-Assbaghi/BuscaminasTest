package BuscaminasTest;

import Modelo.Casella;
import Vista.VistaInterfaz;

public class MockView implements VistaInterfaz {

	int cuento = -1;
	private int counter = 0;

	@Override
	public int pedirColumna() {
		// TODO Auto-generated method stub
		cuento++;
		if (cuento == 30) {
			cuento = -1;
			return 0;
		}

		return -154;
	}

	@Override
	public int pedirFila() {
		// TODO Auto-generated method stub
		cuento++;
		if (cuento == 30) {
			cuento = -1;
			return 0;
		}

		return -1;
	}

	@Override
	public int pedirDificultad() {
		// TODO Auto-generated method stub
		cuento++;
		if (cuento == 30) {
			cuento = -1;
			return 0;
		}
		return -1;
	}

	@Override
	public void printBienvenido() {
		// TODO Auto-generated method stub

	}

	@Override
	public int pedirTipoJugada() {
		// TODO Auto-generated method stub
		counter++;
		if (counter == 30) {
			counter = -1;
			return 0;
		}
		return counter;
	}

	@Override
	public void printTablero(Casella[][] casilla, int dificultad) {
		// TODO Auto-generated method stub

	}

	@Override
	public void printHasPerdido() {
		// TODO Auto-generated method stub

	}

	@Override
	public void printHasGanado() {
		// TODO Auto-generated method stub

	}

}
