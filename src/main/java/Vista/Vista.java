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
	public void printTablero(Casella[][] casilla, int dificultad) {

//		for (int i = 0; i < casilla.length; i++) {
//			for (int j = 0; j < casilla[i].length; j++) {
//				boolean min = casilla[i][j].esMina();
//				if (min) {
//					System.out.print("X");
//				} else {
//					System.out.print("0");
//				}
//			}
//			System.out.println("");
//		}

		for (int fila = 0; fila < casilla.length; fila++) {
			System.out.println("");
			if (dificultad == 0) {

				System.out.println("-----------------------------------------");
			} else if (dificultad == 1) {
				System.out.println("-----------------------------------------------------------------");
			} else {
				System.out.println(
						"-------------------------------------------------------------------------------------------------");
			}

			for (int col = 0; col < casilla[fila].length; col++) {
				System.out.print("|");

				if (casilla[fila][col].getAbierta()) {
					if (casilla[fila][col].esMina()) {
						System.out.print(" " + "X" + " ");
					} else if (casilla[fila][col].getminasCercanas() != 0) {
						System.out.print(" " + casilla[fila][col].getminasCercanas() + " ");
					} else {
						System.out.print("   ");
					}

				} else if (casilla[fila][col].getBandera()) {
					System.out.print(" " + "B" + " ");
				} else {
					System.out.print(" " + "C" + " ");
				}
			}
			System.out.print("|");
		}
		System.out.println("");
		if (dificultad == 0) {
			System.out.println("-----------------------------------------");
		} else if (dificultad == 1) {
			System.out.println("-----------------------------------------------------------------");
		} else {
			System.out.println(
					"-------------------------------------------------------------------------------------------------");
		}

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
	public void printHasPerdido() {
		// TODO Auto-generated method stub
		System.out.println("-----------");
		System.out.println("HAS PERDIDO");
		System.out.println("-----------");

	}

	@Override
	public void printHasGanado() {
		// TODO Auto-generated method stub
		System.out.println("-----------");
		System.out.println("HAS GANADO");
		System.out.println("-----------");

	}

	@Override
	public int pedirTipoJugada() {
		// TODO Auto-generated method stub
		System.out.println("Que quieres hacer? (1 = Abrir Casilla, 2 = Poner Bandera, 0 = Salir)");

		return getTeclado();

	}

}
