package Modelo;

public final class Tablero {
	private int filasColumnas[] = { 10, 16, 24 };
	private int nMinas[] = { 15, 40, 99 };

	private int numMinas;
	private int dificultad;
	private int nFilas;
	private int nColumnas;
	private Casella[][] casillas;
	private boolean primerMovimiento;
	private int numCasillasCerradas;

	public void setnFilas(int nFilas) {
		this.nFilas = nFilas;
	}

	public void setnColumnas(int nColumnas) {
		this.nColumnas = nColumnas;
	}

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
		nFilas = filasColumnas[dificultad];
		nColumnas = filasColumnas[dificultad];
		setNumMinas(nMinas[dificultad]);
		primerMovimiento = true;
		generarTablero();
		setNumCasillasCerradas(filasColumnas[dificultad] * filasColumnas[dificultad]);

	}

	/**
	 * Retorna una casilla.
	 * 
	 * @param fila    es el �ndice de la fila.
	 * @param columna es el �ndice de la columna.
	 * @return
	 */
	public Casella getCasillas(int fila, int columna) {
		if (posicionValida(fila, columna))
			return casillas[fila][columna];
		System.out.println("Intenats acceder a una posicion que no existe, te devolvemos la 0,0");
		return casillas[0][0];
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

	/**
	 * Pone bombas de forma aleatoria seg�n el resultado de un valor random.
	 * 
	 * @param r
	 */
	public int ponerMinas(RandomPos r) {
		int mineCount = 0;
		int filaRandom = 0;
		int colRandom = 0;
		while (mineCount < numMinas) {
			filaRandom = r.getValor(nFilas);
			colRandom = r.getValor(nColumnas);
			if (posicionValida(filaRandom, colRandom)) {
				if (casillas[filaRandom][colRandom].esMina())
					continue;
				else {
					casillas[filaRandom][colRandom].setEsMina(true);
					sumarMinasAlrededor(filaRandom, colRandom);
					mineCount++;
				}
			}
		}

		return mineCount;
	}

	/**
	 * Devuelve si una casilla contiene una mina
	 * 
	 * @param fila    �ndice de la fila de la casilla
	 * @param columna �ndice de la columna de la casilla
	 * @return true si es Mina, false si no lo es.
	 */
	public boolean esMina(int fila, int columna) {
		if (posicionValida(fila, columna))
			if (casillas[fila][columna].esMina())
				return true;
		return false;
	}

	/**
	 * Devuelve si una casilla esta cerrada
	 * 
	 * @param fila    �ndice de la fila de la casilla
	 * @param columna �ndice de la columna de la casilla
	 * @return true si esta cerrada, false si no lo esta.
	 */
	public boolean casillaCerrada(int fila, int columna) {
		if (posicionValida(fila, columna))
			return (!casillas[fila][columna].getAbierta());
		return false;
	}

	/**
	 * Devuelve si la posicion de fila columna dada es valida para el tablero.
	 * 
	 * @param fila    �ndice de la fila de la casilla
	 * @param columna �ndice de la columna de la casilla
	 * @return true si es v�lida, false si no lo es
	 */
	public boolean posicionValida(int fila, int columna) {
		return (fila >= 0 && fila < nFilas && columna >= 0 && columna < nColumnas);

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
		if (!posicionValida(fila, columna))
			return -1;
		if (casillas[fila][columna].getAbierta()) {
			return 0;

		} else {

			if (primerMovimiento) {
				primerMovimiento = false;
				if (casillas[fila][columna].esMina()) {
					casillas[fila][columna].setEsMina(false);
					restarMinasAlrededor(fila, columna);
					numMinas--;
				}
			}
			casillas[fila][columna].abrirCasilla();
			setNumCasillasCerradas(getNumCasillasCerradas() - 1);
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

		if (posicionValida(fila, col)) {

			casillas[fila][col].restarMinaCercana();

			if (posicionValida(fila - 1, col)) {
				casillas[fila - 1][col].restarMinaCercana();
			}

			if (posicionValida(fila + 1, col)) {
				casillas[fila + 1][col].restarMinaCercana();
			}

			if (posicionValida(fila, col + 1)) {
				casillas[fila][col + 1].restarMinaCercana();
			}

			if (posicionValida(fila, col - 1)) {
				casillas[fila][col - 1].restarMinaCercana();
			}

			// Diagonales
			if (posicionValida(fila - 1, col + 1)) {
				casillas[fila - 1][col + 1].restarMinaCercana();
			}

			if (posicionValida(fila - 1, col - 1)) {
				casillas[fila - 1][col - 1].restarMinaCercana();
			}

			if (posicionValida(fila + 1, col + 1)) {
				casillas[fila + 1][col + 1].restarMinaCercana();
			}

			if (posicionValida(fila + 1, col - 1)) {
				casillas[fila + 1][col - 1].restarMinaCercana();
			}
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
		if (!posicionValida(fila, columna))
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

	public int getNMinasPorDificultad(int dificultad) {
		return nMinas[dificultad];
	}

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
		if (posicionValida(fila, col)) {

			casillas[fila][col].sumarMinaCercana(); // Ella misma

			// Vertical y horizontal

			if (posicionValida(fila - 1, col)) {
				casillas[fila - 1][col].sumarMinaCercana();
			}

			if (posicionValida(fila + 1, col)) {
				casillas[fila + 1][col].sumarMinaCercana();
			}

			if (posicionValida(fila, col + 1)) {
				casillas[fila][col + 1].sumarMinaCercana();
			}

			if (posicionValida(fila, col - 1)) {
				casillas[fila][col - 1].sumarMinaCercana();
			}

			// Diagonales
			if (posicionValida(fila - 1, col + 1)) {
				casillas[fila - 1][col + 1].sumarMinaCercana();
			}

			if (posicionValida(fila - 1, col - 1)) {
				casillas[fila - 1][col - 1].sumarMinaCercana();
			}

			if (posicionValida(fila + 1, col + 1)) {
				casillas[fila + 1][col + 1].sumarMinaCercana();
			}

			if (posicionValida(fila + 1, col - 1)) {
				casillas[fila + 1][col - 1].sumarMinaCercana();
			}
		}
	}

	public int getNumCasillasCerradas() {
		return numCasillasCerradas;
	}

	public void setNumCasillasCerradas(int numCasillasCerradas) {
		this.numCasillasCerradas = numCasillasCerradas;
	}

}
