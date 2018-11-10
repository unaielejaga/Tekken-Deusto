package pantalla;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.nio.file.attribute.UserDefinedFileAttributeView;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import bd.BD;
import usuario.Usuario;

public class Pantalla1Jugador extends JFrame{
	
	JPanelBackground fondo;
	JButton bSalir;
	JButton bAceptar;
	Icon usuarioi;
	Icon contrasenyai;
	JLabel usuariol;
	JLabel contrasenyal;
	JTextField usuariot;
	JPasswordField contrasenyap;
	JFrame ventanaAnterior;
	
	
	public Pantalla1Jugador(JFrame v) {
		ventanaAnterior = v;
		JFrame frame = new JFrame();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(800, 1000);
		setTitle("Pantalla 1 Jugador");
		setResizable(false);
		setLocationRelativeTo(null);
		setUndecorated(true);
		fondo = new JPanelBackground();
		fondo.setBackground("imagenes/fondoRegistro1.jpg");
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(fondo, BorderLayout.CENTER);
		fondo.setLayout(null);
		
		
		
		Font fuente = new Font("Dialog", Font.BOLD, 40);
		usuarioi = new ImageIcon("imagenes/nick.gif");
		usuariol = new JLabel(usuarioi);
		usuariot = new JTextField(20);
		usuariot.setFont(fuente);
		contrasenyai = new ImageIcon("imagenes/contraseña.gif");
		contrasenyal = new JLabel(contrasenyai);
		contrasenyap = new JPasswordField(20);
		contrasenyap.setFont(fuente);
	
		bAceptar = new JButton("Aceptar");
		bAceptar.setBackground(Color.BLACK);
		bAceptar.setForeground(Color.RED);
		bAceptar.setBorder(new LineBorder(Color.RED));
		bSalir = new JButton("Salir");
		bSalir.setBackground(Color.BLACK);
		bSalir.setForeground(Color.RED);
		bSalir.setBorder(new LineBorder(Color.RED));
		
		usuariol.setBounds(150, 250, 300, 200);
		usuariot.setBounds(400, 335, 300, 50);
		contrasenyal.setBounds(0, 500, 400, 200);
		contrasenyap.setBounds(400, 585, 300, 50);
		bAceptar.setBounds(350, 900, 100, 50);
		bSalir.setBounds(650, 900, 100, 50);
	
		fondo.add(usuariol);
		fondo.add(usuariot);
		fondo.add(contrasenyal);
		fondo.add(contrasenyap);
		fondo.add(bAceptar);
		fondo.add(bSalir);
		
		bSalir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ventanaAnterior.setEnabled(true);	
				dispose();
			}
		});
		
		bAceptar.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Usuario u = new Usuario(usuariot.getText(), contrasenyap.getText());
				Connection con = BD.initBD("BD");
				BD.usarBD(con);
				BD.usarCrearTablasBD(con);
				Statement st;
				try {
					st = con.createStatement();
					BD.usuarioInsert(st, u);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		
	}
	

}
