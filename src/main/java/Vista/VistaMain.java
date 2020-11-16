package Vista;

import java.util.Scanner;

public class VistaMain implements InterfazVistaMain {

	private Scanner input = new Scanner(System.in);
	@Override
	public void printDespedida() {
		System.out.println("Hasta otra amigo!");

	}

	@Override
	public int pedirFinal() {
		// TODO Auto-generated method stub
		String valor = input.nextLine();
		int exit=0;
		try {
			exit = Integer.parseInt(valor);
		} catch (NumberFormatException e) {
			System.err.println("No es pot convertir el input string :" + valor + " a int");
			exit=-1;
		}
		return exit;
	}

}
