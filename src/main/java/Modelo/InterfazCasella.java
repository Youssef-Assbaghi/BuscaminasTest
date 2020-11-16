package Modelo;

public interface InterfazCasella {


	void setEsMina(boolean mina);

	void abrirCasilla();

	void restarMinaCercana();

	void sumarMinaCercana();

	void cambiarBandera();

	int getminasCercanas();

	boolean esMina();

	boolean getAbierta();

	boolean getBandera();

	void setBandera(boolean flag);

	void setAbierta(boolean state);

}
