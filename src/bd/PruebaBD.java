package bd;

import java.sql.*;
import java.util.ArrayList;

import personajes.Personajes;
import usuario.Usuario;

public class PruebaBD {

	public static void main(String[] args) {
		Connection con = BD.initBD("BD");
		Statement st = BD.usarBD(con);
		BD.usarCrearTablasBD(con);
		Personajes p1 = new Personajes("Donatello", 100, 50, 10, 15, 0, 0, 10);
		Personajes p2 = new Personajes("Leonardo", 70, 30, 20, 25, 0, 0, 25);
		Personajes p3 = new Personajes("Raphael", 100, 50, 15, 20, 0, 0, 10);
		Personajes p4 = new Personajes("Mike", 60, 80, 25, 30, 0, 0, 30);
		
		BD.personajesInsert(st, p1);
		BD.personajesInsert(st, p2);
		BD.personajesInsert(st, p3);
		BD.personajesInsert(st, p4);

	}

}
