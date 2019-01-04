package bd;

import java.sql.*;
import java.util.*; 
import java.util.logging.*;

import javax.swing.JOptionPane;

import usuario.Usuario;
import personajes.Personajes;

public class BD {
	
	private static Exception lastError = null;

	public static Connection initBD( String nombreBD ) {
		try {
		    Class.forName("org.sqlite.JDBC");
		    Connection con = DriverManager.getConnection("jdbc:sqlite:" + nombreBD );
		    Statement st = con.createStatement();      
		    st.execute( "PRAGMA foreign_keys = ON" );  
		    st.close();                                
			log( Level.INFO, "Conectada base de datos " + nombreBD, null );
		    return con;
		} catch (ClassNotFoundException | SQLException e) {
			lastError = e;
			log( Level.SEVERE, "Error en conexi�n de base de datos " + nombreBD, e );
			e.printStackTrace();
			return null;
		}
	}
	
	public static Statement usarBD( Connection con ) {
		try {
			Statement statement = con.createStatement();
			statement.setQueryTimeout(30);  // poner timeout 30 msg
			return statement;
		} catch (SQLException e) {
			lastError = e;
			log( Level.SEVERE, "Error en uso de base de datos", e );
			e.printStackTrace();
			return null;
		}
	}
	
	
	public static Statement usarCrearTablasBD( Connection con ) {
		try {
			Statement statement = con.createStatement();
			statement.setQueryTimeout(30);  
			try {
				statement.executeUpdate("create table usuario " +
					"(nick string PRIMARY KEY"
					+ ", contrasenya string)");
			} catch (SQLException e) {} // Tabla ya existe. Nada que hacer
			try {
				statement.executeUpdate("create table personajes " +
					"(nombre string PRIMARY KEY" + 
						", vida integer, energia integer, damageB integer, damageP inetger, posX integer, posY integer, vel integer)"); // (1) Solo para foreign keys
			} catch (SQLException e) {} // Tabla ya existe. Nada que hacer
			try {
				statement.executeUpdate("create table partida" +
			"( combG integer, combP integer" +
			", usuario string REFERENCES usuario(nick) ON DELETE CASCADE" +
			", personaje string REFERENCES personajes(nombre) ON DELETE CASCADE)");
			} catch (SQLException e) {} //Tabla ya existe. Nada que hacer
			log( Level.INFO, "Creada base de datos", null );
			return statement;
		} catch (SQLException e) {
			lastError = e;
			log( Level.SEVERE, "Error en creaci�n de base de datos", e );
			e.printStackTrace();
			return null;
		}
	}
	
	public static Statement reiniciarBD( Connection con ) {
		try {
			Statement statement = con.createStatement();
			statement.setQueryTimeout(30);  // poner timeout 30 msg
			statement.executeUpdate("drop table if exists usuario");
			statement.executeUpdate("drop table if exists partida");
			log( Level.INFO, "Reiniciada base de datos", null );
			return usarCrearTablasBD( con );
		} catch (SQLException e) {
			log( Level.SEVERE, "Error en reinicio de base de datos", e );
			lastError = e;
			e.printStackTrace();
			return null;
		}
	}
	
	public static boolean usuarioInsert( Statement st, Usuario u ) {
		String sentSQL = "";
		try {
	
			sentSQL = "insert into usuario values(" +
					"'" + secu(u.getNick()) + "', " +
					"'" + secu(u.getContrasenya()) + "')";
			int val = st.executeUpdate( sentSQL );
			log( Level.INFO, "BD a�adida " + val + " fila\t" + sentSQL, null );
			if (val!=1) {  // Se tiene que a�adir 1 - error si no
				log( Level.SEVERE, "Error en insert de BD\t" + sentSQL, null );
				return false;  
			}
			return true;
		} catch (SQLException e) {
			log( Level.SEVERE, "Error en BD\t" + sentSQL, e );
			lastError = e;
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean usuarioExiste( Statement st, String nombre ) {
		String sentSQL = "";
		try {
			sentSQL = "select * from usuario where nick='" + nombre + "'";
			ResultSet rs = st.executeQuery( sentSQL );
			boolean existe = rs.next();
			rs.close();
			return existe;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static String usuarioSelect( Statement st, String nombre ) {
		String sentSQL = "";
		String contra = "";
		try {
			sentSQL = "select contrasenya from usuario where nick='" + nombre + "'";
			ResultSet rs = st.executeQuery( sentSQL );
			while(rs.next()) {
				contra = rs.getString("contrasenya");
			}
			rs.close();
			log(Level.INFO, "BD seleccionada" + sentSQL + "valor: " + contra, null);
			return contra;
			

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static Usuario usuarioSelectUsuario( Statement st, String nombre ) {
		String sentSQL = "";
		Usuario u = null;
		try {
			sentSQL = "select contrasenya from usuario where nick='" + nombre + "'";
			ResultSet rs = st.executeQuery( sentSQL );
			while(rs.next()) {
				String contra = rs.getString("contrasenya");
				u = new Usuario(nombre, contra);
			}
			rs.close();
			log(Level.INFO, "BD seleccionada" + sentSQL + "valor: " + u.toString(), null);
			return u;
			

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static int usuarioContador(Statement st) {
		int contador = 0;
		String sentSQL = "";
		try {
			sentSQL = "select count(nick) from usuario";
			ResultSet rs = st.executeQuery(sentSQL);
			while(rs.next()) {
				contador = rs.getInt("count(nick)");
			}
			rs.close();
			log(Level.INFO, "Numero de usuarios: " + contador, null);
			return contador;
		}catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public static ArrayList<String> usuarioUsuarios(Statement st) {
		ArrayList<String> usuarios = new ArrayList<>();
		String sentSQL = "";
		try {
			sentSQL = "select nick from usuario";
			ResultSet rs = st.executeQuery(sentSQL);
			while(rs.next()) {
				usuarios.add(rs.getString("nick"));
			}
			rs.close();
			log(Level.INFO, "Usuarios: " + usuarios, null);
			return usuarios;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	
	public static boolean personajesInsert( Statement st, Personajes p ) {
		String sentSQL = "";
		try {
			
			sentSQL = "insert into personajes values(" +
					"'" + secu(p.getNombre()) + "', " +
					"" + p.getVida() + ", " +
					"" + p.getEnergia() + ", " +
					"" + p.getDamageB() + "," + 
					"" + p.getDamageP() + "," + 
					"" + p.getPosX() + "," + 
					"" + p.getPosY() + "," + 
					"" + p.getVel() + ")";
			int val = st.executeUpdate( sentSQL );
			log( Level.INFO, "BD a�adida " + val + " fila\t" + sentSQL, null );
			if (val!=1) {  // Se tiene que a�adir 1 - error si no
				log( Level.SEVERE, "Error en insert de BD\t" + sentSQL, null );
				return false;  
			}
			return true;
		} catch (SQLException e) {
			log( Level.SEVERE, "Error en BD\t" + sentSQL, e );
			lastError = e;
			e.printStackTrace();
			return false;
		}
	}
	
	public static Personajes personajeSelect( Statement st, String nombre ) {
		String sentSQL = "";
		Personajes p = null;
		try {
			sentSQL = "select * from personajes where nombre='" + nombre + "'";
			ResultSet rs = st.executeQuery( sentSQL );
			while(rs.next()) {
				int vida = rs.getInt("vida");
				int energia = rs.getInt("energia");
				int damageB = rs.getInt("damageB");
				int damageP = rs.getInt("damageP");
				int posX = rs.getInt("posX");
				int posY = rs.getInt("posY");
				int vel = rs.getInt("vel");
				
				p = new Personajes(nombre, vida, energia, damageB, damageP, posX, posY, vel);
			}
			rs.close();
			log(Level.INFO, "BD seleccionada" + sentSQL + "valor: " + p, null);
			return p;
			

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static boolean partidaInsert( Statement st, Usuario u, Personajes p, int combG, int combP ) {
		String sentSQL = "";
		try {
	
			sentSQL = "insert into partida values(" + " " +
					 combG + ", " + combP + ", " +
					"'" + secu(u.getNick()) + "', " +
					"'" + secu(p.getNombre()) + "')";
			int val = st.executeUpdate( sentSQL );
			log( Level.INFO, "BD a�adida " + val + " fila\t" + sentSQL, null );
			if (val!=1) {  // Se tiene que a�adir 1 - error si no
				log( Level.SEVERE, "Error en insert de BD\t" + sentSQL, null );
				return false;  
			}
			return true;
		} catch (SQLException e) {
			log( Level.SEVERE, "Error en BD\t" + sentSQL, e );
			lastError = e;
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean partidaExiste( Statement st, Usuario u, Personajes p ) {
		String sentSQL = "";
		try {
			sentSQL = "select * from partida where (usuario='" + secu(u.getNick()) + "' and" +
		" personaje='" + secu(p.getNombre())+ "' )";
			ResultSet rs = st.executeQuery( sentSQL );
			boolean existe = rs.next();
			rs.close();
			return existe;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean partidaExisteString( Statement st, String usuario, String personajes ) {
		String sentSQL = "";
		try {
			sentSQL = "select * from partida where (usuario='" + usuario + "' and" +
		" personaje='" + personajes + "' )";
			ResultSet rs = st.executeQuery( sentSQL );
			boolean existe = rs.next();
			rs.close();
			return existe;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean partidaUpdate( Statement st, int combG, int combP, Usuario u, Personajes p) {
		String sentSQL = "";
		try {
			
			sentSQL = "update partida set" +
					" combG=" + combG + ", " +
					" combP=" + combP + "" + 
					" where (usuario='" + secu(u.getNick()) + "' and" +
					" personaje='" + secu(p.getNombre()) + "' )";
			int val = st.executeUpdate( sentSQL );
			log( Level.INFO, "BD modificada " + val + " fila\t" + sentSQL, null );
			if (val!=1) {  // Se tiene que modificar 1 - error si no
				log( Level.SEVERE, "Error en update de BD\t" + sentSQL, null );
				return false;  
			}
			return true;
		} catch (SQLException e) {
			log( Level.SEVERE, "Error en BD\t" + sentSQL, e );
			lastError = e;
			e.printStackTrace();
			return false;
		}
	}
	
	public static ArrayList<Integer> partidaSelect( Statement st, Usuario u, Personajes p ) {
		String sentSQL = "";
		int combG = 0;
		int combP = 0;
		ArrayList<Integer> combates = new ArrayList<>();
		try {
			sentSQL = "select combG, combP from partida where (usuario='" + secu(u.getNick()) + "' and" +
					" personaje='" + secu(p.getNombre()) + "' )";
			ResultSet rs = st.executeQuery( sentSQL );
			while(rs.next()) {
				combG = rs.getInt("combG");
				combP = rs.getInt("combP");
			}
			rs.close();
			combates.add(combG); 
			combates.add(combP);
			log(Level.INFO, "BD seleccionada" + sentSQL + "valor: " + combates, null);
			return combates;
			

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static ArrayList<Integer> partidaSelectString( Statement st, String usuario, String personaje ) {
		String sentSQL = "";
		int combG = 0;
		int combP = 0;
		ArrayList<Integer> combates = new ArrayList<>();
		try {
			sentSQL = "select combG, combP from partida where (usuario='" + usuario + "' and" +
					" personaje='" + personaje + "' )";
			ResultSet rs = st.executeQuery( sentSQL );
			while(rs.next()) {
				combG = rs.getInt("combG");
				combP = rs.getInt("combP");
			}
			rs.close();
			combates.add(combG); 
			combates.add(combP);
			log(Level.INFO, "BD seleccionada" + sentSQL + "valor: " + combates, null);
			return combates;
			

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static void cerrarBD( Connection con, Statement st ) {
		try {
			if (st!=null) st.close();
			if (con!=null) con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	private static String secu( String string ) {
		StringBuffer ret = new StringBuffer();
		for (char c : string.toCharArray()) {
			if ("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ��������������.,:;-_(){}[]-+*=<>'\"�?�!&%$@#/\\0123456789 ".indexOf(c)>=0) ret.append(c);
		}
		return ret.toString();
	}
	
	// Logging
	
	private static Logger logger = null;
	// M�todo p�blico para asignar un logger externo
	/** Asigna un logger ya creado para que se haga log de las operaciones de base de datos
	 * @param logger	Logger ya creado
	 */
	public static void setLogger( Logger logger ) {
		BD.logger = logger;
	}
	// M�todo local para loggear (si no se asigna un logger externo, se asigna uno local)
	private static void log( Level level, String msg, Throwable excepcion ) {
		if (logger==null) {  // Logger por defecto local:
			logger = Logger.getLogger( "BD-server-ejemplocs" );  // Nombre del logger
			logger.setLevel( Level.ALL );  // Loguea todos los niveles
			try {
				// logger.addHandler( new FileHandler( "bd-" + System.currentTimeMillis() + ".log.xml" ) );  // Y saca el log a fichero xml
				logger.addHandler( new FileHandler( "bd.log.xml", true ) );  // Y saca el log a fichero xml
			} catch (Exception e) {
				logger.log( Level.SEVERE, "No se pudo crear fichero de log", e );
			}
		}
		if (excepcion==null)
			logger.log( level, msg );
		else
			logger.log( level, msg, excepcion );
		
		//prueba
	}
}
