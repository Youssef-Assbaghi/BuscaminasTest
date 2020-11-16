package Modelo;

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
		if (intentos>=15) {
			return n+22;
		}
		if(intentos >=11) {
			return 0;
		}
		
		if(intentos >=9) {
			return 9;
		}
		
		if(intentos >=6) {
			return 1;
		}
		
	
		
		
			
		
		return n;
	}

}
