package BuscaminasTest;

public class MockRandom implements Random { 
	
	private double fila;
	public MockRandom(double fake_fila){
		fila=fake_fila;
	}


	public double nextDouble(){
		return fila;
	}

}
