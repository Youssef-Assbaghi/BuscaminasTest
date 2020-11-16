package Modelo;

public class Casella {
	private int minasCercanas; // N NUMEROS DE MINAS CERCANAS
	private boolean esMina; // FALSE NO ES MINA TRUE ES MINA
	private boolean abierta; // FALSE CERRADO TRUE ABIERTO
	private boolean bandera; // FALSE SIN BANDERA TRUE CON BANDERA

	/**
	 * Constructor. Inicializa una casilla vacia.
	 */
	public Casella() {
		minasCercanas = 0;
		esMina = false;
		abierta = false;
		bandera = false;
	}

	/**
	 * Constructor. Inicializa una casilla con los parámetros que se le pasan.
	 * 
	 * @param nMinas es el valor del contador de minas cercanas.
	 * @param mina   indica si la casilla es una mina.
	 * @param state  indica si la casilla se encuentra abierta.
	 * @param flag   indica si hay una bandera en la casilla.
	 */
	public Casella(int nMinas, boolean mina, boolean state, boolean flag) {

		if (nMinas > 8) {
			minasCercanas = 8;
		} else if (nMinas < 0) {
			minasCercanas = 0;
		} else {
			minasCercanas = nMinas;
		}

		esMina = mina;

		if (state && flag) {
			bandera = false;
		} else {
			abierta = state;
			bandera = flag;
		}

	}

	/**
	 * Pone el estado de la casilla al state que se le pasa.
	 * 
	 * @param state
	 */
	public void setAbierta(boolean state) {
		abierta = state;
	}

	/**
	 * Pone una bandera en la casilla.
	 * 
	 * @param flag
	 */
	public void setBandera(boolean flag) {
		bandera = flag;
	}

	/**
	 * Indica que la casilla es una mina.
	 * 
	 * @param mina
	 */
	public void setEsMina(boolean mina) {
		esMina = mina;
	}

	/**
	 * Devuelve true si hay una bandera en la casilla
	 * 
	 * @return
	 */
	public boolean getBandera() {
		return this.bandera;
	}

	/**
	 * Devuelve true si la casilla esta abierta.
	 * 
	 * @return
	 */
	public boolean getAbierta() {
		return this.abierta;
	}

	/**
	 * Devuelve true si hay una mina en la casilla.
	 * 
	 * @return
	 */
	public boolean esMina() {
		return this.esMina;
	}

	/**
	 * Devuelve el número de minas cercanas.
	 * 
	 * @return
	 */
	public int getminasCercanas() {
		return this.minasCercanas;
	}

	/**
	 * Cambia el estado de bandera. Pone una si no hay bandera en la casilla, si ya
	 * hay, la quita.
	 */
	public void cambiarBandera() {
		if (!abierta && !bandera)
			bandera = true;
		else
			bandera = false;
	}

	/**
	 * Suma 1 al contador de minas cercanas de la casilla.
	 */
	public void sumarMinaCercana() {
		
		if(minasCercanas < 8) {
			minasCercanas++;
		}
	}

	/**
	 * Resta uno al contador de minas cercanas de la casilla.
	 */
	public void restarMinaCercana() {
		if (minasCercanas > 0) {
			minasCercanas--;
		}
	}

	/**
	 * Abre la casilla, si hay una bandera la quita.
	 */
	public void abrirCasilla() {
			if (bandera)
				bandera = false;
			else {
				abierta = true;
			}

	}
}
