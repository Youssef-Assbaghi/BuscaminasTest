package Controlador;

import Modelo.Tablero;
import Vista.VistaInterfaz;

public interface InterfazBuscaminas {

	VistaInterfaz getVista();

	Tablero getTablero();

	void initVista();


	boolean getVictoria();

	boolean getSalir();

	void detectarVictoria();

	void detectarDerrota(int fila, int columna);

	void jugar();

}
