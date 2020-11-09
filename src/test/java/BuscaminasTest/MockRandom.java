package BuscaminasTest;

import java.util.Random;

import Modelo.RandomPos;

public class MockRandom implements RandomPos {

	private int intentos=0;
	@Override
	public int getValor(int n) {
		intentos++;
		if(intentos<5) {
			return n-15;
		}
		if (intentos>30) {
			Random rand = new Random();
			int valor = (int) (rand.nextDouble() * n - 1);
			return valor;
		}
		
			
		if (intentos>=5) {
			return n+15;
		}
		return n;
	}

}
