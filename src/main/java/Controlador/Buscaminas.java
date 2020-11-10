package Controlador;

import Modelo.Tablero;
import Modelo.ValorRandom;
import Vista.Vista;
import Vista.VistaInterfaz;

public class Buscaminas {

	private static Tablero tablero;

	//private static Vista vista;
	private static boolean salir = false;
	private static VistaInterfaz vista;
	public Tablero getTablero() {return tablero;}
	public Buscaminas(VistaInterfaz vistaa) {vista=vistaa;
	tablero=new Tablero(0);}


	public static void main() {
		// TODO Auto-generated method stub

		vista.printBienvenido();
		int dificultad = pedirDificultad();

		tablero = new Tablero(dificultad);

		vista.printTablero();

		int fila = pedirFilaCol(tablero.getNFilas());
		int columna = pedirFilaCol(tablero.getNColumnas());
		
		ValorRandom r=new ValorRandom();
		tablero.ponerMinas(r);
		tablero.marcarCasilla(fila, columna);

		do {

			fila = pedirFilaCol(tablero.getNFilas());
			columna = pedirFilaCol(tablero.getNColumnas());

			int tipoJugada = pedirJugada();

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

	private static int pedirJugada() {
		
		boolean acierto=false;
		int tipoJugada=0;
		while(!acierto) {
			tipoJugada = vista.pedirTipoJugada();
			if(tipoJugada>=0&&tipoJugada<=2)
				acierto=true;
		}
		return tipoJugada;
	}
	
	private static int pedirFilaCol(int valor) {
		
		boolean acierto=false;
		int value=0;
		while(!acierto) {
			value=vista.pedirFila();
			if(value<=valor&& value>=0)
				acierto=true;
		}
		return value;
	}
	
	private static int pedirDificultad() {
		int valor=0;
		boolean acierto=false;
		while(!acierto) {
			valor=vista.pedirDificultad();
			if(valor>=0&& valor<=2)
				acierto=true;
		}
		return valor;
	}
	

}
