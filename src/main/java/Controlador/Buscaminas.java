package Controlador;

import Modelo.Tablero;
import Modelo.ValorRandom;
import Vista.Vista;
import Vista.VistaInterfaz;

public class Buscaminas implements InterfazBuscaminas{

	
	private static Tablero tablero;
	private static boolean salir = false;

	private static boolean victoria = false;
	private static VistaInterfaz vista;

	public VistaInterfaz getVista() {
		return vista;
	}
	@Override
	public Tablero getTablero() {
		return tablero;
	}

	public Buscaminas(VistaInterfaz vistaa) {
		vista = vistaa;
		tablero = new Tablero(0);
		salir = false;
		victoria = false;
	}
	public  void initVista() {
		if (vista == null) {
			vista = new Vista();
		}
	}
	@Override
	public void jugar() {

		initVista();

		vista.printBienvenido();
		int dificultad = pedirAccion(1);
		ValorRandom r = new ValorRandom();
		tablero = new Tablero(dificultad);
		int tipoJugada = 0;
		tablero.ponerMinas(r);
		vista.printTablero(tablero.getTablero(), tablero.getDificultad());

		System.out.println("Donde sera tu primer movimiento?");
		int fila = pedirPosicion(tablero.getNFilas(), 0);
		int columna = pedirPosicion(tablero.getNColumnas(), 1);

		tablero.marcarCasilla(fila, columna);
		vista.printTablero(tablero.getTablero(), tablero.getDificultad());

		do {

			System.out.println("Sobre que posicion deseas interactuar");
			fila = pedirPosicion(tablero.getNFilas(), 0);
			columna = pedirPosicion(tablero.getNColumnas(), 1);

			tipoJugada = pedirAccion(0);

			jugada(tipoJugada, fila, columna);

			vista.printTablero(tablero.getTablero(), dificultad);

			detectarVictoria();

		} while (!salir && !victoria);

	}
	@Override
	public boolean getVictoria() {
		return victoria;
	}
	@Override
	public boolean getSalir() {
		return salir;
	}
	@Override
	public void detectarVictoria() {
		if(!salir)
			if (tablero.getNumCasillasCerradas() == tablero.getNumMinas()) {
				vista.printHasGanado();
				victoria = true;
	
			}

	}
	private  int pedirAccion(int accion) {
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
	private int pedirPosicion(int valor, int filaOColumna) {
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
	private void jugada(int tipoJugada, int fila, int columna) {
		switch (tipoJugada) {
		case 0:
			salir = true;
			break;

		case 1:
			detectarDerrota(fila, columna);
			tablero.marcarCasilla(fila, columna);
			break;
		case 2:
			tablero.ponerBandera(fila, columna);
			break;

		}
	}
	@Override
	public void detectarDerrota(int fila, int columna) {

		if (!tablero.getCasillas(fila, columna).getBandera()) {
			if (tablero.esMina(fila, columna)) {
				salir = true;
				vista.printHasPerdido();
			}
		}
	}

}
