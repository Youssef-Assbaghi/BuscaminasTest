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


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if(vista==null) {
			vista=new Vista();
		}
		vista.printBienvenido();
		int dificultad = pedirDificultad();
		ValorRandom r=new ValorRandom();
		tablero = new Tablero(dificultad);
		
		vista.printTablero(tablero.getTablero());

		System.out.println("Donde sera tu primer movimiento?");
		int fila = pedirFila(tablero.getNFilas());
		int columna = pedirColumna(tablero.getNColumnas());
		
		
		tablero.ponerMinas(r);
		tablero.marcarCasilla(fila, columna);
		vista.printTablero(tablero.getTablero());

		do {

			System.out.println("Sobre que posicion deseas interactuar");
			fila = pedirFila(tablero.getNFilas());
			columna = pedirColumna(tablero.getNColumnas());

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

			vista.printTablero(tablero.getTablero());
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
	
	private static int pedirFila(int valor) {
		
		boolean acierto=false;
		int value=0;
		while(!acierto) {
			value=vista.pedirFila();
			if(value<=valor&& value>=0)
				acierto=true;
			else
				System.out.println("La fila debe ser una valor entre 0 y "+valor);
		}
		return value;
	}
	
	private static int pedirColumna(int valor) {
		
		boolean acierto=false;
		int value=0;
		while(!acierto) {
			value=vista.pedirColumna();
			if(value<=valor&& value>=0)
				acierto=true;
			else
				System.out.println("La columna debe ser una valor entre 0 y "+valor);
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
