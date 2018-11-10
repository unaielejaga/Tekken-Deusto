package bd;

import java.sql.*;
import java.util.*; 
import java.util.logging.*;

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
			"(cod_partida integer PRIMARY KEY"
			+ ", combG integer, combP integer" +
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
	
	public static boolean partidaInsert( Statement st, Usuario u, Personajes p, int combG, int combP, int cod_partida ) {
		String sentSQL = "";
		try {
	
			sentSQL = "insert into partida values(" + "" + cod_partida + ", " +
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
	
	public static boolean partidaUpdate( Statement st, int combG, int combP, int cod_partida ) {
		String sentSQL = "";
		try {
			
			sentSQL = "update partida set" +
					" combG=" + combG + ", " +
					" combP=" + combP + "" + 
					" where cod_partida=" + cod_partida;
			// System.out.println( sentSQL );  // para ver lo que se hace en consola
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
