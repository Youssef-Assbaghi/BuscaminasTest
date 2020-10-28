package BuscaminasTest;

public final class Tablero {
	private final int arr[] = {10,16,24};
	private final int nMinas[] = {10,40,99};
	
	private int numMinas;
	private int dificultad;
	private int nFilas;
	private int nColumnas;
	private Casella[][] casillas; // Debe ser privada y acceder mediante getters y setters
	
	public Tablero(int dificultad) {
		// TODO Auto-generated constructor stub
		this.setDificultad(dificultad);
		nFilas=arr[dificultad];
		nColumnas=arr[dificultad];
		setNumMinas(nMinas[dificultad]);
		generarTablero();

	}
	
	public Casella getCasillas(int fila, int columna) {
		return casillas[fila][columna];
	}
	
	public void generarTablero() {
		casillas=new Casella[nFilas][nColumnas];
		for (int j=0;j<=nFilas-1;j++)
			for (int i = 0; i <= nColumnas-1; i++) 
				casillas[j][i]=new Casella();
				
	}
		
	public void pintarTablero() {}
	
	public void ponerMinas(){
		
	}
	
	public void marcarCasilla(int fila, int columna) {}
	
	public void ponerBandera(int fila, int columna) {}
	
	public int getNFilas() {return nFilas; }
	
	public int getNColumnas() {return nColumnas; }

	public int getNumMinas() {
		return numMinas;
	}

	public void setNumMinas(int numMinas) {
		this.numMinas = numMinas;
	}

	public int getDificultad() {
		return dificultad;
	}

	public void setDificultad(int dificultad) {
		this.dificultad = dificultad;
	}

}
