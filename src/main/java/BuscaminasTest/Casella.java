package BuscaminasTest;

public class Casella {
	private int minasCercanas; //N NUMEROS DE MINAS CERCANAS
	private boolean esMina; //FALSE NO ES MINA TRUE ES MINA
	private boolean abierta; //FALSE CERRADO TRUE ABIERTO
	private boolean bandera; // FALSE SIN BANDERA TRUE CON BANDERA
	
	public Casella() {
		minasCercanas=0;
		esMina=false;
		abierta=false;
		bandera=false;
	}
	public Casella(int nMinas,boolean mina,boolean state,boolean flag) {
		
		if(nMinas>8) {
			minasCercanas=8;
		}else if(nMinas<0) {
			minasCercanas=0;
		}else {
			minasCercanas=nMinas;
		}
		
		esMina=mina;
		
		if(state && flag) {
			bandera=false;
		}else {
			abierta=state;
			bandera=flag;
		}

	}
	
	public void setAbierta(boolean state) {abierta=state;}
	//public void setMinasCercanas(int nMinas) {minasCercanas=nMinas;}
	public void setBandera(boolean flag) {bandera=flag;}
	public void setEsMina(boolean mina) {esMina=mina;}
	public boolean getBandera() {return this.bandera;}
	public boolean getAbierta() {return this.abierta;}
	public boolean esMina() {return this.esMina;}
	public int getminasCercanas() {return this.minasCercanas;}
	public void cambiarBandera() {
		if (!abierta && !bandera)  bandera=true;
		else bandera=false;

	}

	public void abrirCasella() {
		if (!abierta && !bandera) {
			abierta=true;
		}else if(bandera) {
			bandera=false;
		}
	}
}
