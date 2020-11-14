package Controlador;

import Modelo.Tablero;
import Modelo.ValorRandom;
import Vista.Vista;
import Vista.VistaInterfaz;

public class Buscaminas {

	private static Tablero tablero;

	// private static Vista vista;
	private static boolean salir = false;
	private static VistaInterfaz vista;

	public Tablero getTablero() {
		return tablero;
	}

	public Buscaminas(VistaInterfaz vistaa) {
		vista = vistaa;
		tablero = new Tablero(0);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if (vista == null) {
			vista = new Vista();
		}
		vista.printBienvenido();
		int dificultad = pedirAccion(1);
		ValorRandom r = new ValorRandom();
		tablero = new Tablero(dificultad);

		vista.printTablero(tablero.getTablero());

		System.out.println("Donde sera tu primer movimiento?");
		int fila = pedirPosicion(tablero.getNFilas(), 0);
		int columna = pedirPosicion(tablero.getNColumnas(), 1);

		tablero.ponerMinas(r);
		tablero.marcarCasilla(fila, columna);
		vista.printTablero(tablero.getTablero());

		do {

			System.out.println("Sobre que posicion deseas interactuar");
			fila = pedirPosicion(tablero.getNFilas(), 0);
			columna = pedirPosicion(tablero.getNColumnas(), 1);

			int tipoJugada = pedirAccion(0);

			switch (tipoJugada) {
			case 0:
				salir = true;
				break;

			case 1:
				if (!tablero.getCasillas(fila, columna).getBandera()) {
					if (tablero.esMina(fila, columna)) {
						salir = true;
						System.out.println("Eres mal�simo. BG.");
					}
				}
				/*
				 * if (tablero.esMina(fila, columna)) { if (!tablero.getCasillas(fila,
				 * columna).getBandera()) { salir = true;
				 * System.out.println("Eres mal�simo. BG."); } }
				 */
				tablero.marcarCasilla(fila, columna);
				break;
			case 2:
				tablero.ponerBandera(fila, columna);
				break;

			default:
				break;
			}
			vista.printTablero(tablero.getTablero());
			if (tablero.getNumCasillasCerradas() == tablero.getNumMinas()) {
				salir = true;
				System.out.println("Felicidades, eres el �nico que ha ganado en este juego.");
			}
		} while (!salir);

	}

	private static int pedirAccion(int accion) {
		boolean acierto = false;
		int queHacer = 0;
		while (!acierto) {
			if (accion == 0) {
				queHacer = vista.pedirTipoJugada();
			} else {
				queHacer = vista.pedirDificultad();
			}
			if (queHacer >= 0 && queHacer <= 2)
				acierto = true;
		}
		return queHacer;
	}

	private static int pedirPosicion(int valor, int filaOColumna) {
		boolean acierto = false;
		int value = 0;
		while (!acierto) {
			if (filaOColumna == 0) {
				value = vista.pedirFila();
			} else {
				value = vista.pedirColumna();
			}

			if (value <= valor && value >= 0)
				acierto = true;
			else {
				if (filaOColumna == 0)
					System.out.println("La fila debe ser una valor entre 0 y " + valor);
				else
					System.out.println("La columna debe ser una valor entre 0 y " + valor);
			}

		}
		return value;
	}

}
