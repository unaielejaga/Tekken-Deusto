import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import bd.BD;
import personajes.Personajes;
import usuario.Usuario;

public class BDTest {
	
	private Usuario u1, u2;
	private Personajes p1, p2;
	
	@Before
	
	public void setUp() throws Exception {
		u1 = new Usuario("Unai", "hola");
		u2 = new Usuario("Lola", "wapos");
		p1 = new Personajes("Rafael", 100, 50, 40, 50, 1, 0, 10);
		p2 = new Personajes("Koldo", 200, 1000, 10, 20, 50, 70, 100);
	}
	
	@Test
	public void initCrearCerrarTest() {
		Connection con = BD.initBD( "bd-test" );
		assertNotNull( con );
		Statement stat =  BD.usarCrearTablasBD( con );
		assertNotNull( stat );
		BD.cerrarBD( con, stat );
	}
	
	@Test
	public void usuarioInsertTest() {
		Connection con = BD.initBD( "bd-test" );
		Statement stat =  BD.reiniciarBD(con);
		assertTrue( BD.usuarioInsert( stat, u1 ) );
		assertTrue( BD.usuarioInsert( stat, u2 ) );
		assertTrue(BD.personajesInsert(stat, p1));
		assertTrue(BD.personajesInsert(stat, p2));
		BD.cerrarBD( con, stat );
	}
	
}
