package Vista;

import java.util.Scanner;

public class Vista {

	private Scanner input = new Scanner(System.in);

	public int pedirColumna() {
		// TODO Auto-generated method stub

		System.out.print("Columna: ");
		int col = input.nextInt();

		return col;
	}

	public int pedirFila() {
		// TODO Auto-generated method stub

		System.out.print("Fila: ");
		return 0;
	}

	public void printTablero() {
		// TODO Auto-generated method stub

	}

	public int pedirDificultad() {
		// TODO Auto-generated method stub
		System.out.print("Introduce la dificultad (0=Fàcil, 1 = Intermedio, 2=Díficil): ");
		return 0;
	}

	public void printBienvenido() {
		// TODO Auto-generated method stub
		System.out.print("Bienvenido al Buscaminas");

	}

	public int pedirTipoJugada() {
		// TODO Auto-generated method stub
		System.out.print("Que quieres hacer? (1 = Abrir Casilla, 2 = Poner Bandera, 0 = Salir)");
		return 0;
	}

}
