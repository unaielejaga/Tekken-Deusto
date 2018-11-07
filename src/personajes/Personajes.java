package personajes;

public class Personajes {
	private String nombre;
	private int vida;
	private int energia;
	private int damageB;
	private int damageP;
	private double posX;
	private double posY;
	private double vel;


	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getVida() {
		return vida;
	}

	public void setVida(int vida) {
		this.vida = vida;
	}

	public int getEnergia() {
		return energia;
	}

	public void setEnergia(int energia) {
		this.energia = energia;
	}

	public int getDamageB() {
		return damageB;
	}

	public void setDamageB(int damageB) {
		this.damageB = damageB;
	}

	public int getDamageP() {
		return damageP;
	}

	public void setDamageP(int damageP) {
		this.damageP = damageP;
	}

	public double getPosX() {
		return posX;
	}

	public void setPosX(double posX) {
		this.posX = posX;
	}

	public double getPosY() {
		return posY;
	}

	public void setPosY(double posY) {
		this.posY = posY;
	}

	public double getVel() {
		return vel;
	}

	public void setVel(double vel) {
		this.vel = vel;
	}

	// Constructor de la clase personajes
	public Personajes(String nombre, int vida, int energia, int damageB, int damageP, double posX, double posY, double vel) {
		this.nombre = nombre;
		this.vida = vida;
		this.energia = energia;
		this.damageB = damageB;
		this.damageP = damageP;
		this.posX = posX;
		this.posY = posY;
		this.vel = vel;

	}
	
	
	//Metodo en el que se le resta vida al Personaje utilizado cuando recibe da�o
	public void RecebirDamage(int damageRecibido) {
		this.vida = this.vida - damageRecibido;
	}
	
	//Metodo en el que se le cambia la posicion al personaje en el eje X
	public void MoverseX (int movidoX) {
		this.posX = this.posX + movidoX;
	}
	
	//Metodo en el que se le cambia la posicion al personaje en el eje Y
	public void MoverseY (int movidoY) {
		this.posY = this.posY + movidoY;
	}
}
