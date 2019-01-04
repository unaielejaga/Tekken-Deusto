package pantalla;

import java.awt.BorderLayout;import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;

import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

import bd.BD;
import personajes.Personajes;
import sonido.Sonido;
import usuario.Usuario;

public class PantallaMarcadores extends JFrame {
	private JPanelBackground fondo;
	private JPanel PanelSup;
	private JPanel PanelCentral;
	private JTable tMarcadores;
	private ImageIcon MarcadoresI;
	private JLabel MarcadoresL;
	private int numUsuarios;
	private int numFilas;
	private ArrayList<String> usuarios;
	private ArrayList<Integer> combates;

	public PantallaMarcadores() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(1920, 1080);
		setTitle("Pantalla Menú Principal");
		setResizable(false);
		fondo = new JPanelBackground();
		fondo.setBackground("imagenes/fondo1.jpg");
		// setUndecorated(true);
		getContentPane().setLayout(new BorderLayout());
		fondo.setLayout(new BorderLayout());
		getContentPane().add(fondo);

		Connection con = BD.initBD("BD");
		Statement st = BD.usarBD(con);

		PanelSup = new JPanel();
		PanelSup.setOpaque(false);
		PanelCentral = new JPanel();
		PanelCentral.setOpaque(false);

		MarcadoresI = new ImageIcon("imagenes/Marc.gif");
		MarcadoresL = new JLabel(MarcadoresI);

		numUsuarios = BD.usuarioContador(st);
		numFilas = numUsuarios*4;
		tMarcadores = new JTable(numFilas, 5);
		
		usuarios = BD.usuarioUsuarios(st);
		
		for(int i = 0; i < numUsuarios; i++) {
			for(int j = 0; j < 4; j++) {
				tMarcadores.setValueAt(usuarios.get(j), j, 0);
				switch (j) {
				case 0:
					tMarcadores.setValueAt("Donatello", j, 1);
					if(BD.partidaExisteString(st, usuarios.get(i), "Donatello")) {
						combates = BD.partidaSelectString(st, usuarios.get(i), "Donatello");
						tMarcadores.setValueAt(combates.get(0), j, 2);
						tMarcadores.setValueAt(combates.get(1), j, 3);
					}else {
						tMarcadores.setValueAt(0, j, 2);
						tMarcadores.setValueAt(0, j, 3);
					}
					//Falta la cuarta columna JFreeChart
					break;
				case 1:
					tMarcadores.setValueAt("Raphael", j, 1);
					if(BD.partidaExisteString(st, usuarios.get(i), "Raphael")) {
						combates = BD.partidaSelectString(st, usuarios.get(i), "Raphael");
						tMarcadores.setValueAt(combates.get(0), j, 2);
						tMarcadores.setValueAt(combates.get(1), j, 3);
					}else {
						tMarcadores.setValueAt(0, j, 2);
						tMarcadores.setValueAt(0, j, 3);
					}
					//Falta la cuarta columna JFreeChart
					break;
				case 2:
					tMarcadores.setValueAt("Leonardo", j, 1);
					if(BD.partidaExisteString(st, usuarios.get(i), "Leonardo")) {
						combates = BD.partidaSelectString(st, usuarios.get(i), "Leonardo");
						tMarcadores.setValueAt(combates.get(0), j, 2);
						tMarcadores.setValueAt(combates.get(1), j, 3);
					}else {
						tMarcadores.setValueAt(0, j, 2);
						tMarcadores.setValueAt(0, j, 3);
					}
					//Falta la cuarta columna JFreeChart
					break;
				case 3:
					tMarcadores.setValueAt("Mike", j, 1);
					if(BD.partidaExisteString(st, usuarios.get(i), "Mike")) {
						combates = BD.partidaSelectString(st, usuarios.get(i), "Mike");
						tMarcadores.setValueAt(combates.get(0), j, 2);
						tMarcadores.setValueAt(combates.get(1), j, 3);
					}else {
						tMarcadores.setValueAt(0, j, 2);
						tMarcadores.setValueAt(0, j, 3);
					}
					//Falta la cuarta columna JFreeChart
					break;
				default:
					break;
				}
				
				
			}
		}

		PanelSup.add(MarcadoresL);
		PanelCentral.add(tMarcadores);

		fondo.add(PanelSup, BorderLayout.NORTH);
		fondo.add(PanelCentral, BorderLayout.CENTER);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				BD.cerrarBD(con, st);
				
			}

		});

	}
}
