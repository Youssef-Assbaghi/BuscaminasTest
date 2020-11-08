package Modelo;

import java.util.Random;

public final class Tablero {
	private final int arr[] = { 10, 16, 24 };
	private final int nMinas[] = { 10, 40, 99 };

	private int numMinas;
	private int dificultad;
	private int nFilas;
	private int nColumnas;
	private Casella[][] casillas; // Debe ser privada y acceder mediante getters y setters
	private boolean primerMovimiento;

	public Tablero(int dificultad) {
		// creamos random aqui
		// interfaz rndom en una clase
		// y cuando la llamemmos la vamos llamando poco a poco
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

	public Casella getCasillas(int fila, int columna) {
		return casillas[fila][columna];
	}

	public void generarTablero() {
		casillas = new Casella[nFilas][nColumnas];
		for (int j = 0; j <= nFilas - 1; j++)
			for (int i = 0; i <= nColumnas - 1; i++)
				casillas[j][i] = new Casella();

	}

	public void pintarTablero() {
	}

	public void ponerMinas() {
		Random rand = new Random();
		int mineCount = 0;
		while (mineCount < numMinas) {
			int filaRandom = (int) (rand.nextDouble() * nFilas - 1);
			int colRandom = (int) (rand.nextDouble() * nColumnas - 1);
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

	public boolean alrededorPrimerMovimiento(int fila, int columna) {
		return true;
	}

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
				restarMinasCercanas();

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

	public void restarMinasCercanas(int fila, int columna) {

	}

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

	public void setDificultad(int dificultad) {
		this.dificultad = dificultad;
	}

	public void sumarMinasAlrededor(int fila, int col) { // A ESTA FILA Y A ESTA COLUMNA LE PONEMOS UNA MINA
// SUMAMOS 1 A TODAS LAS DE ALREDEDOR
		if (fila - 1 >= 0 && col + 1 >= 0)
			casillas[fila - 1][col + 1].sumarMinaCercana();

		if (fila - 1 >= 0 && col - 1 >= 0)
			casillas[fila - 1][col - 1].sumarMinaCercana();

		if (fila + 1 < nFilas - 1 && col + 1 < nColumnas - 1)
			casillas[fila + 1][col + 1].sumarMinaCercana();

		if (fila + 1 < nFilas - 1 && col - 1 >= 0)
			casillas[fila + 1][col - 1].sumarMinaCercana();

		if (fila - 1 >= 0)
			casillas[fila - 1][col].sumarMinaCercana();

		if (fila + 1 <= nFilas - 1)
			casillas[fila + 1][col].sumarMinaCercana();

		if (col + 1 < nColumnas - 1)
			casillas[fila][col + 1].sumarMinaCercana();

		if (col - 1 >= 0)
			casillas[fila][col - 1].sumarMinaCercana();
	}

}

/**
 * Al haberse creado las minas el jugador selecciona una casilla para abrir, la
 * casilla seleccionada y las casillas cercanas se igualan a zero forma que si
 * habia minas estas se borran. Despues de haber borrado las minas cercanas a la
 * casilla seleccionada se checa cada casilla para verificar si tiene una bomba
 * cerca, si tiene una bomba cerca se le suma 1 a menos de que sea bomba o este
 * fuera del rango de la matriz. La casilla que selecciono el jugador se abre
 * reemplazando la casilla de la matriz que el jugador ve con la casilla que
 * esta en el mismo lugar de la matriz que contiene toda la informacion de las
 * minas junto con las 8 casillas vecinas, si alguna de las casillas que se
 * abrio es cero todas las casillas cercanas a estas tambien se abren hasta que
 * todas las casillas cercanas a un ya abierto 0 esten tambien abiertas.
 */
