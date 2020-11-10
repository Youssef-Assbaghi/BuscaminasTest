package BuscaminasTest;

import Vista.VistaInterfaz;

public class MockView implements VistaInterfaz{

	int cuento =0;
	@Override
	public int pedirColumna() {
		// TODO Auto-generated method stub
		cuento++;
		if(cuento ==30) {
			cuento=0;
			return 0;
		}
		return -154;
	}

	@Override
	public int pedirFila() {
		// TODO Auto-generated method stub
		cuento++;
		if(cuento ==30) {
			cuento=0;
			return 0;
		}
		return 99;
	}

	@Override
	public void printTablero() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int pedirDificultad() {
		// TODO Auto-generated method stub
		cuento++;
		if(cuento ==30) {
			cuento=0;
			return 0;
		}
		return -1;
	}

	@Override
	public void printBienvenido() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int pedirTipoJugada() {
		// TODO Auto-generated method stub
		cuento ++;
		if(cuento ==30) {
			cuento=0;
			return 0;
		}
		return -8;
	}

}
