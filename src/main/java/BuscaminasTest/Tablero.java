package BuscaminasTest;

import java.util.Random;

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
		if(dificultad<=0)
			dificultad=0;
		else if(dificultad>2)
			dificultad = 0;
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
		
		Random rand = new Random();
	    int mineCount = 0;
	    while (mineCount < numMinas)
	    {
	        int filaRandom = (int) (rand.nextDouble() * nFilas);
	        int colRandom = (int) (rand.nextDouble() * nColumnas);
	        if (casillas[filaRandom][colRandom].esMina())
	            continue;
	        else //SINO VER  SI LA CASILLA ESTA ABIERTA
	        {
	            casillas[filaRandom][colRandom].setEsMina(true);
	            mineCount++;
	        }
	    }
		
	}
	
	public int marcarCasilla(int fila, int columna) {
		if(fila<0 || fila >= nFilas) 
			return -1;
		
		if(columna < 0 || columna >= nColumnas)
			return -1;
		
		if(casillas[fila][columna].getAbierta()) {
			return 0;
		}else {
			casillas[fila][columna].abrirCasilla();
			return 0;
		}
		
		
		
		
	}
	
	public void ponerBandera(int fila, int columna) {
		casillas[fila][columna].cambiarBandera();
	}
	
	public int getNFilas() {return nFilas-1; }
	
	public int getNColumnas() {return nColumnas-1; }

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
