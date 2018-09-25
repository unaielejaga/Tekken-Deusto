package usuario;

public class Usuario {
	private String nombre;
	private String contrasenya;
	private int combG;
	private int combP;
	
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
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
	
	public Usuario(String nombre, String contrasenya) {
		this.nombre = nombre;
		this.contrasenya = contrasenya;
	}
	
	public void GanarComb() {
		this.combG = this.combG + 1;
	}
	
	public void PerderComb( ) {
		this.combP = this.combP + 1;
	}

}
