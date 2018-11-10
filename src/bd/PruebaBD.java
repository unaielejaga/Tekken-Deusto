package bd;

import java.sql.*;

import personajes.Personajes;
import usuario.Usuario;

public class PruebaBD {

	public static void main(String[] args) {
		Connection con = BD.initBD("BD");
		BD.usarBD(con);
		BD.usarCrearTablasBD(con);
		Usuario u = new Usuario("Pepe", "juan");
		Usuario u1 = new Usuario("Juan", "ramon");
		Personajes p = new Personajes("Donatllo", 100, 20, 10, 15, 0, 0, 4);
		Statement st1;
		try {
			st1 = con.createStatement();
		//	BD.usuarioInsert(st1, u);
//			BD.personajesInsert(st1, p);
//			BD.partidaInsert(st1, u, p, 10, 3, 1);
//			BD.partidaUpdate(st1, 11, 2, 1);
			BD.usuarioSelec(st1, u1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
