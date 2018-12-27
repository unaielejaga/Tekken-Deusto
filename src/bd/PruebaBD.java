package bd;

import java.sql.*;
import java.util.ArrayList;

import personajes.Personajes;
import usuario.Usuario;

public class PruebaBD {

	public static void main(String[] args) {
		Connection con = BD.initBD("BD");
		BD.usarBD(con);
		BD.usarCrearTablasBD(con);
//		Usuario u = new Usuario("Guilemo", "juan");
//		Usuario u1 = new Usuario("Aie", "ramon");
		Personajes p = new Personajes("Mike", 100, 10, 15,10, 0, 0, 10);
		Statement st1;
		try {
			st1 = con.createStatement();
//			BD.usuarioInsert(st1, u);
			BD.personajesInsert(st1, p);
//			BD.partidaInsert(st1, u, p, 10, 3);
//			BD.partidaUpdate(st1, 11, 2, u, p);
//			String contra = BD.usuarioSelect(st1, u.getNick());
			Personajes per = BD.personajeSelect(st1, p.getNombre());
			System.out.println(per.toString());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
