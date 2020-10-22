package BuscaminasTest;

public final class Tablero {
	
	private int nFilas;
	private int nColumnas;
	Casella casillas [][];
	
	public Tablero(int filas,int columnas) {
		// TODO Auto-generated constructor stub
		nFilas=filas;
		nColumnas=columnas;
		casillas=new Casella[nFilas][nColumnas];
	}
	
	public void generarTablero();
		
	public void pintarTablero();
	
	public void marcarCasilla(int fila, int columna);
	
	public void ponerBandera(int fila, int columna);
	
	public int getNFiles() {return nFilas; }
	
	public int getNColumnes() {return nColumnas; }

}
