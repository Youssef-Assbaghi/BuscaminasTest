package BuscaminasTest;

public class Casella {
	private int minasCercanas; //N NUMEROS DE MINAS
	private boolean esMina; //FALSE NO ES MINA TRUE ES MINA
	private boolean estado; //FALSE CERRADO TRUE ABIERTO
	private boolean bandera; // FALSE SIN BANDERA TRUE CON BANDERA
	
	public Casella() {
		minasCercanas=0;
		esMina=false;
		estado=false;
		bandera=false;
	}
	
	public void setEstado(boolean state) {estado=state;}
	
	public boolean getBandera() {return this.bandera;}
	public boolean getEstado() {return this.estado;}
	public void cambiarBandera() {
		if (!estado && !bandera)  bandera=true;
		else bandera=false;

	}

	public void abrirCasella() {
		if (!estado && !bandera) {
			estado=true;
		}
	}
}
