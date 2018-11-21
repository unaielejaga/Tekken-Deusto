package usuario;

public class Usuario {
	private String nick;
	private String contrasenya;

	
	
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
		
	// Constructor que pide nombre y contraseña
	public Usuario(String nombre, String contrasenya) {
		this.nick = nombre;
		this.contrasenya = contrasenya;
	}


}
