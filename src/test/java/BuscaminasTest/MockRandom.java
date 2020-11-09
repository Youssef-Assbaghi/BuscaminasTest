package BuscaminasTest;

import Modelo.RandomPos;

public class MockRandom implements RandomPos {

	@Override
	public int getValor(int n) {
		if (n < 2) {
			return n - 1000;
		} 
		if(n>7) {
			return n+3;
		}
		return n;
	}

}
