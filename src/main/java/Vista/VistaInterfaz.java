package Vista;

import Modelo.Casella;

public interface VistaInterfaz {

	public int pedirColumna() ;

	public int pedirFila();

	public void printTablero(Casella[][] casilla);

	public int pedirDificultad();

	public void printBienvenido();

	public int pedirTipoJugada() ;

}
