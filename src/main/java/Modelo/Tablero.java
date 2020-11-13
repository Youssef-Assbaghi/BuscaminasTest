package Modelo;

public final class Tablero {
	private final int arr[] = { 10, 16, 24 };
	private final int nMinas[] = { 15, 40, 99 };

	private int numMinas;
	private int dificultad;
	private int nFilas;
	private int nColumnas;
	private Casella[][] casillas;
	private boolean primerMovimiento;

	/**
	 * Constructor, en caso de que no se indique una dificultad se inicializa a
	 * dificultad 0.
	 * 
	 * @param dificultad es el valor (0,1,2) que indica la dificultad del juego.
	 */
	public Tablero(int dificultad) {
		if (dificultad <= 0)
			dificultad = 0;
		else if (dificultad > 2)
			dificultad = 0;
		this.setDificultad(dificultad);
		nFilas = arr[dificultad];
		nColumnas = arr[dificultad];
		setNumMinas(nMinas[dificultad]);
		primerMovimiento = true;
		generarTablero();

	}

	/**
	 * Retorna una casilla.
	 * 
	 * @param fila    es el �ndice de la fila.
	 * @param columna es el �ndice de la columna.
	 * @return
	 */
	public Casella getCasillas(int fila, int columna) {
		return casillas[fila][columna];
	}

	/**
	 * Inicializa el tablero con casillas vacias, cerradas y sin bombas.
	 */
	public void generarTablero() {
		casillas = new Casella[nFilas][nColumnas];
		for (int j = 0; j <= nFilas - 1; j++)
			for (int i = 0; i <= nColumnas - 1; i++)
				casillas[j][i] = new Casella();

	}

	public void pintarTablero() {
	}

	/**
	 * Pone bombas de forma aleatoria seg�n el resultado de un valor random.
	 * 
	 * @param r
	 */
	public void ponerMinas(RandomPos r) {
		int mineCount = 0;
		while (mineCount < numMinas) {
			int filaRandom = r.getValor(nFilas);
			int colRandom = r.getValor(nColumnas);
			if (filaRandom >= 0 && filaRandom < nFilas && colRandom >= 0 && colRandom < nColumnas) {
				if (casillas[filaRandom][colRandom].esMina())
					continue;
				else if (!casillas[filaRandom][colRandom].getAbierta()) // Ver hacer un mock para testearlo
				{
					casillas[filaRandom][colRandom].setEsMina(true);
					sumarMinasAlrededor(filaRandom, colRandom);
					mineCount++;
				}
			}
		}

	}

	/**
	 * 
	 * @return Devuelve la matriz de casillas
	 */

	public Casella[][] getTablero() {
		return casillas;
	}

	/**
	 * Est� funci�n marca i abre una casilla en el tablero.
	 * 
	 * @param fila    es el �ndice de la fila.
	 * @param columna es el �ndice de la columna.
	 * @return
	 */
	public int marcarCasilla(int fila, int columna) {
		if (fila < 0 || fila >= nFilas)
			return -1;
		if (columna < 0 || columna >= nColumnas)
			return -1;
		if (casillas[fila][columna].getAbierta()) {
			return 0;

		} else {

			if (primerMovimiento && casillas[fila][columna].esMina()) {
				casillas[fila][columna].setEsMina(false);
				restarMinasAlrededor(fila, columna);
				primerMovimiento = false;
			}

			casillas[fila][columna].abrirCasilla();
			if (casillas[fila][columna].getminasCercanas() == 0) {
				for (int i = fila - 1; i <= fila + 1; i++)
					for (int j = columna - 1; j <= columna + 1; j++)
						marcarCasilla(i, j);

			}
			return 0;
		}
	}

	/**
	 * Esta funci�n resta 1 al contador de minasCercanas que tienen las casillas
	 * alrededor de la casilla que se pasa. Esto se utiliza si borramos una mina en
	 * el tablero (primer click del jugador) y hay que actualizar el n�mero de
	 * minas alrededor de las casillas alrededor.
	 * 
	 * @param fila es el �ndice de la fila.
	 * @param col  es el �ndice de la columna
	 */
	public void restarMinasAlrededor(int fila, int col) {

		if (fila - 1 >= 0) {
			casillas[fila - 1][col].restarMinaCercana();
		}

		if (fila + 1 < nFilas) {
			casillas[fila + 1][col].restarMinaCercana();
		}

		if (col + 1 < nColumnas) {
			casillas[fila][col + 1].restarMinaCercana();
		}

		if (col - 1 >= 0) {
			casillas[fila][col - 1].restarMinaCercana();
		}

		// Diagonales
		if (fila - 1 >= 0 && col + 1 < nColumnas) {
			casillas[fila - 1][col + 1].restarMinaCercana();
		}

		if (fila - 1 >= 0 && col - 1 >= 0) {
			casillas[fila - 1][col - 1].restarMinaCercana();
		}

		if (fila + 1 < nFilas && col + 1 < nColumnas) {
			casillas[fila + 1][col + 1].restarMinaCercana();
		}

		if (fila + 1 < nFilas && col - 1 >= 0) {
			casillas[fila + 1][col - 1].restarMinaCercana();
		}

	}

	/**
	 * Pone una bandera en la casilla. En el caso de que ya haya una bandera en esa
	 * casilla, se quita.
	 * 
	 * @param fila    �ndice de la fila.
	 * @param columna �ndice de la columna.
	 * @return
	 */
	public int ponerBandera(int fila, int columna) {
		if (fila < 0 || fila >= nFilas)
			return -1;

		if (columna < 0 || columna >= nColumnas)
			return -1;

		casillas[fila][columna].cambiarBandera();
		return 0;

	}

	public int getNFilas() {
		return nFilas - 1;
	}

	public int getNColumnas() {
		return nColumnas - 1;
	}

	public int getNumMinas() {
		return numMinas;
	}

	public void setNumMinas(int numMinas) {
		this.numMinas = numMinas;
	}

	public int getDificultad() {
		return dificultad;
	}

//	public int getDificultad() {
//		return dificultad;
//	}

	public void setDificultad(int dificultad) {
		this.dificultad = dificultad;
	}

	/**
	 * Esta funcion suma 1 al contador de minasCercanas que tienen las casillas
	 * alrededor de la casilla que se pasa. Esto se utiliza al a�adir una mina,
	 * entonces hay que actualizar el numero de minas alrededor de las casillas
	 * alrededor.
	 * 
	 * @param fila es el indice de la fila.
	 * @param col  es el indice de la columna.
	 */
	public void sumarMinasAlrededor(int fila, int col) {
		// Vertical y horizontal
		if (fila - 1 >= 0) {
			casillas[fila - 1][col].sumarMinaCercana();
		}

		if (fila + 1 < nFilas) {
			casillas[fila + 1][col].sumarMinaCercana();
		}

		if (col + 1 < nColumnas) {
			casillas[fila][col + 1].sumarMinaCercana();
		}

		if (col - 1 >= 0) {
			casillas[fila][col - 1].sumarMinaCercana();
		}

		// Diagonales
		if (fila - 1 >= 0 && col + 1 < nColumnas) {
			casillas[fila - 1][col + 1].sumarMinaCercana();
		}

		if (fila - 1 >= 0 && col - 1 >= 0) {
			casillas[fila - 1][col - 1].sumarMinaCercana();
		}

		if (fila + 1 < nFilas && col + 1 < nColumnas) {
			casillas[fila + 1][col + 1].sumarMinaCercana();
		}

		if (fila + 1 < nFilas && col - 1 >= 0) {
			casillas[fila + 1][col - 1].sumarMinaCercana();
		}
	}

}
