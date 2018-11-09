package bd;

import java.sql.*;

import personajes.Personajes;
import usuario.Usuario;

public class PruebaBD {

	public static void main(String[] args) {
		Connection con = BD.initBD("BD");
		BD.usarBD(con);
		BD.usarCrearTablasBD(con);
		Usuario u = new Usuario("Pepo", "juan");
		Personajes p = new Personajes("Donatell", 100, 20, 10, 15, 0, 0, 4);
		Statement st1;
		try {
			st1 = con.createStatement();
			BD.usuarioInsert(st1, u);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Statement st12;
		try {
			st12 = con.createStatement();
			BD.personajesInsert(st12, p);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
