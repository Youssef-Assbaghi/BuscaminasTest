package Modelo;

import java.util.Random;

public class ValorRandom implements RandomPos {

	@Override
	public int getValor(int n) {
		Random rand = new Random();
		int valor = (int) (rand.nextDouble() * n - 1);

		return valor;

	}
}
