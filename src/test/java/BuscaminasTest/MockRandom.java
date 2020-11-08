package BuscaminasTest;

import Modelo.RandomPos;

public class MockRandom implements RandomPos {

	@Override
	public int getValor(int n) {
		if (n < 5) {
			return n - 1000;
		} else {
			return n + 1000;
		}
	}

}
