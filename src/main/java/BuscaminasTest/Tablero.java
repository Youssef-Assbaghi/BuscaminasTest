package BuscaminasTest;

public final class Tablero {
	
	private int nFila;
	private int nColumna;
	Casella casillas [][];
	
	public Tablero(int fila,int columna) {
		// TODO Auto-generated constructor stub
		nFila=fila;
		nColumna=columna;
		casillas=new Casella[nFila][nColumna];
	}
	

}
