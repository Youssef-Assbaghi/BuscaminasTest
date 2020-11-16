package Demo;

import java.util.Scanner;

import Controlador.Buscaminas;
import Controlador.InterfazBuscaminas;
import Vista.InterfazVistaMain;
import Vista.VistaMain;

public class Main {
	private static  InterfazBuscaminas buscaminas;
	private static InterfazVistaMain vista;
	public void init(InterfazBuscaminas buscaminas, InterfazVistaMain vista) {
		this.buscaminas = buscaminas;
		this.vista=vista;
		
	}
	public static void main(String args[]) {
		
		if(buscaminas==null) {
			buscaminas = new Buscaminas(null);
		}
		if (vista==null) {
			vista=new VistaMain();
		}

		boolean salir = false;
		while(!salir) {
			buscaminas.jugar();
			System.out.println("Si quieres salir del juego pulsa 0, si quieres volver a jugar pulsa 1");
			
			int exit =0;
			exit=vista.pedirFinal();
			
			if(exit==0) {
				salir=true;
			}
		}		

		vista.printDespedida();

	}

}
