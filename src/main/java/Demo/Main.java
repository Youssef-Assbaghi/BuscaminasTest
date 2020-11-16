package Demo;

import java.util.Scanner;

import Controlador.Buscaminas;
import Controlador.InterfazBuscaminas;

public class Main {
	private static  InterfazBuscaminas buscaminas;
	public void init(InterfazBuscaminas buscaminas) {
		this.buscaminas = buscaminas;
		
	}
	public static void main(String args[]) {
		Scanner input = new Scanner(System.in);
		if(buscaminas==null) {
			buscaminas = new Buscaminas(null);
		}

		boolean salir = false;
		while(!salir) {
			buscaminas.jugar();
			System.out.println("Si quieres salir del juego pulsa 0, si quieres volver a jugar pulsa 1");
			
			int exit =0;
			String valor = input.nextLine();
			try {
				exit = Integer.parseInt(valor);
			} catch (NumberFormatException e) {
				System.err.println("No es pot convertir el input string :" + valor + " a int");
				exit=-1;
			}
			
			if(exit==0) {
				salir=true;
			}
		}		

		System.out.println("Hasta otra amigo!");
	}

}
