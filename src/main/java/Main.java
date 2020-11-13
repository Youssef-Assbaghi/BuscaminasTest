import Controlador.Buscaminas;
import Vista.Vista;

public class Main {

	public static void main(String[] args) {
		Vista prints_juego=new Vista();
		Buscaminas juego=new Buscaminas(prints_juego);
	}
}
