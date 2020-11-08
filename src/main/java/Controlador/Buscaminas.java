package Controlador;

import Modelo.Tablero;
import Vista.Vista;

public class Buscaminas {

	private static Tablero tablero;

	private static Vista vista;
	private static boolean salir = false;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		vista.printBienvenido();
		int dificultad = vista.pedirDificultad();

		tablero = new Tablero(dificultad);

		vista.printTablero();

		int fila = vista.pedirFila();
		int columna = vista.pedirColumna();

		tablero.marcarCasilla(fila, columna);

		tablero.ponerMinas();

		do {

			fila = vista.pedirFila();
			columna = vista.pedirColumna();

			int tipoJugada = vista.pedirTipoJugada();

			switch (tipoJugada) {
			case 0:
				salir = true;
				break;

			case 1:
				tablero.marcarCasilla(fila, columna);
				break;
			case 2:
				tablero.ponerBandera(fila, columna);
				break;

			default:
				break;
			}

		} while (!salir);

	}

}
