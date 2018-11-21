package bd;

import java.sql.*;

import personajes.Personajes;
import usuario.Usuario;

public class PruebaBD {

	public static void main(String[] args) {
		Connection con = BD.initBD("BD");
		BD.usarBD(con);
		BD.usarCrearTablasBD(con);
		Usuario u = new Usuario("Guilemo", "juan");
		Usuario u1 = new Usuario("Aie", "ramon");
		Personajes p = new Personajes("Jvie", 100, 20, 10, 15, 0, 0, 4);
		Statement st1;
		try {
			st1 = con.createStatement();
			BD.usuarioInsert(st1, u);
			BD.personajesInsert(st1, p);
			BD.partidaInsert(st1, u, p, 10, 3);
			BD.partidaUpdate(st1, 11, 2, u, p);
			String contra = BD.usuarioSelect(st1, u1.getNick());
			System.out.println(contra);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
