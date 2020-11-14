package Vista;

import java.util.Scanner;

import Modelo.Casella;

public class Vista implements VistaInterfaz {

	private Scanner input = new Scanner(System.in);

	private int getTeclado() {
		String valor = input.nextLine();
		int dificultad = 0;
		try {
			dificultad = Integer.parseInt(valor);
		} catch (NumberFormatException e) {
			System.err.println("No es pot convertir el input string :" + valor + " a int");
			return -1;
		}
		return dificultad;
	}

	@Override
	public int pedirColumna() {
		// TODO Auto-generated method stub

		System.out.print("Columna: ");
		// int col = input.nextInt();

		return getTeclado();
	}

	@Override
	public int pedirFila() {
		// TODO Auto-generated method stub

		System.out.print("Fila: ");
		return getTeclado();
	}

	@Override
	public void printTablero(Casella[][] casilla) {
		// TODO Auto-generated method stub
		System.out.println("");
		for (int i = 0; i < casilla.length; i++) {
			for (int j = 0; j < casilla[i].length; j++) {

				if (casilla[i][j].getAbierta()) {
					int casillasCercanas = casilla[i][j].getminasCercanas();
					if (casillasCercanas != 0) {
						System.out.print(casillasCercanas);
					} else
						System.out.print(" ");
				} else if (casilla[i][j].getBandera()) {
					System.out.print("B");
				} else
					System.out.print("C");
			}
			System.out.println("");
		}

		/*for (int i = 0; i < casilla.length; i++) {
			for (int j = 0; j < casilla[i].length; j++) {
				boolean min = casilla[i][j].esMina();
				if (min) {
					System.out.print("X");
				} else {
					System.out.print("0");
				}
			}
			System.out.println("");
		}

		System.out.println("ARBOL MINAS CERCANAS");
		for (int i = 0; i < casilla.length; i++) {
			for (int j = 0; j < casilla[i].length; j++) {

				System.out.print(casilla[i][j].getminasCercanas());
			}
			System.out.println("");
		}*/

	}

	@Override
	public int pedirDificultad() {
		// TODO Auto-generated method stub
		System.out.println("Introduce la dificultad (0=Facil, 1 = Intermedio, 2=Dificil): ");

		return getTeclado();

	}

	@Override
	public void printBienvenido() {
		// TODO Auto-generated method stub
		System.out.println("Bienvenido al Buscaminas");

	}

	@Override
	public int pedirTipoJugada() {
		// TODO Auto-generated method stub
		System.out.println("Que quieres hacer? (1 = Abrir Casilla, 2 = Poner Bandera, 0 = Salir)");

		return getTeclado();

	}

}
