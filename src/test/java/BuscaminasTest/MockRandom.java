package BuscaminasTest;

public class MockRandom implements Random { 
	
	private int fila;
	public MockRandom(int fake_fila){
		fila=fake_fila;
	}


	public double nextDouble(){
		return fila;
	}

}
