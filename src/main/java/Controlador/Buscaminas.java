package Controlador;

import Modelo.Tablero;
import Modelo.ValorRandom;
import Vista.Vista;
import Vista.VistaInterfaz;

/**
 * 
 * Clase que forma parte de la estructura controlador
 * se encarga de instanciar todos los objetos y que se pueda jugar
 *
 */
public class Buscaminas implements InterfazBuscaminas{

	
	private static Tablero tablero;
	private static boolean salir = false;

	private static boolean victoria = false;
	private static VistaInterfaz vista;

	/**
	 * Devuelve el tipo de vista que se utiliza
	 */
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
	/**
	 * Inicializa la vista:
	 * Si se le pasa por parametro adopta esa 
	 * sino mantiene una por defecto
	 */
	public  void initVista() {
		if (vista == null) {
			vista = new Vista();
		}
	}
	/**
	 * Implementa todas las funciones de la vista 
	 * y ejecuta los metodos del tablero para poder jugar
	 */
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
	/**
	 * Comprueba si hemos ganado en funcion de si el numero de 
	 * casillas cerradas es igual al numero de minas
	 */
	@Override
	public void detectarVictoria() {
		if(!salir)
			if (tablero.getNumCasillasCerradas() == tablero.getNumMinas()) {
				vista.printHasGanado();
				victoria = true;
	
			}

	}
	/**
	 * 
	 * @param accion vista a la que vamos a llamar (0 tipo jugada, 1 dificultad)
	 * @return devuelve la variable correcta que concierne a la accion
	 */
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
	/**
	 * 
	 * @param valor valor limite de la fila o columna NFILAS O NCOLUMNAS
	 * @param filaOColumna VARIABLE QUE CONDICIONA LA VISTA, 0 PEDIMOS FILA, 1 PEDIMOS COLUMNA
	 * @return
	 */
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
	/**
	 * Swutch case que define la jugada que se va a realizar
	 * @param tipoJugada cariable del swirch case
	 * @param fila fila sobre la que se ejecutaran acciones
	 * @param columna columna sobre la que se ejecutaran acciones
	 */
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
	/**
	 * metodo que comprueba si hemos perdido
	 * comprueba si la casilla que hemos pulsado no tenga bandera
	 * y si no tiene miramos si es mina
	 */
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
