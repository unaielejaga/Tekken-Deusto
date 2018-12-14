package personajes;

public class Personajes {
	private String nombre;
	private int vida;
	private int energia;
	private int damageB;
	private int damageP;
	private int posX;
	private int posY;
	private int vel;


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

	public void setPosX(int d) {
		this.posX = d;
	}

	public double getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public double getVel() {
		return vel;
	}

	public void setVel(int vel) {
		this.vel = vel;
	}

	// Constructor de la clase personajes
	public Personajes(String nombre, int vida, int energia, int damageB, int damageP, int posX, int posY, int vel) {
		this.nombre = nombre;
		this.vida = vida;
		this.energia = energia;
		this.damageB = damageB;
		this.damageP = damageP;
		this.posX = posX;
		this.posY = posY;
		this.vel = vel;

	}
	
	public String toString() {
		String personaje = "Nombre: " + nombre + ", Vida: " + vida + ", Energia: " + energia +
		", DamageB: " + damageB + ", DamageP: " + damageP + ", PosX: " + posX + ", Posy: " + posY +
		", Vel: " + vel;
		return personaje;
	}
	
	//Metodo en el que se le resta vida al Personaje utilizado cuando recibe daño
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
