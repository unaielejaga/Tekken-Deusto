package usuario;

public class Usuario {
	private String nick;
	private String contrasenya;
	private int combG;
	private int combP;
	
	
	public String getNick() {
		return nick;
	}
	
	public void setNick(String nombre) {
		this.nick = nombre;
	}
	
	public String getContrasenya() {
		return contrasenya;
	}
	
	public void setContrasenya(String contrasenya) {
		this.contrasenya = contrasenya;
	}
	
	public int getCombG() {
		return combG;
	}
	
	public void setCombG(int combG) {
		this.combG = combG;
	}
	
	public int getCombP() {
		return combP;
	}
	
	public void setCombP(int combP) {
		this.combP = combP;
	}
	
	
	// Constructor que pide nombre y contraseña
	public Usuario(String nombre, String contrasenya) {
		this.nick = nombre;
		this.contrasenya = contrasenya;
	}
	
	// Metodo en el que se le suma uno a la variable combG del Usuario si ha ganado el combate
	public void GanarComb() {
		this.combG = this.combG + 1;
	}
	
	// Metodo en el que se le suma uno a la variable combP del Usuario si ha perdido el combate
	public void PerderComb( ) {
		this.combP = this.combP + 1;
	}

}
