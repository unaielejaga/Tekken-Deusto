package pantalla;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.attribute.UserDefinedFileAttributeView;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import bd.BD;
import usuario.Usuario;

public class Pantalla1Jugador extends JFrame{
	
	JPanelBackground fondo;
	JButton bSalir;
	JButton bAceptar;
	JButton bRegistrar;
	Icon usuarioi;
	Icon contrasenyai;
	JLabel usuariol;
	JLabel contrasenyal;
	JTextField usuariot;
	JPasswordField contrasenyap;
	JFrame ventanaAnterior;
	Properties properties;
	String USUARIO_ANTERIOR;
	JCheckBox usuarioAnterior;
	
	public Pantalla1Jugador(JFrame v) {
		ventanaAnterior = v;
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
		contrasenyai = new ImageIcon("imagenes/contraseņa.gif");
		contrasenyal = new JLabel(contrasenyai);
		contrasenyap = new JPasswordField(20);
		contrasenyap.setFont(fuente);
	
		bAceptar = new JButton("Aceptar");
		bAceptar.setBackground(Color.BLACK);
		bAceptar.setForeground(Color.RED);
		bAceptar.setBorder(new LineBorder(Color.RED));
		bRegistrar = new JButton("Registrar");
		bRegistrar.setBackground(Color.BLACK);
		bRegistrar.setForeground(Color.RED);
		bRegistrar.setBorder(new LineBorder(Color.RED));
		bSalir = new JButton("Salir");
		bSalir.setBackground(Color.BLACK);
		bSalir.setForeground(Color.RED);
		bSalir.setBorder(new LineBorder(Color.RED));
		usuarioAnterior = new JCheckBox("Recuerda el Usuario");
		usuarioAnterior.setForeground(Color.RED);
		usuarioAnterior.setFont(new Font("Dialog", Font.BOLD, 30));
		usuarioAnterior.setOpaque(false);
		
		usuariol.setBounds(150, 250, 300, 200);
		usuariot.setBounds(400, 335, 300, 50);
		contrasenyal.setBounds(0, 500, 400, 200);
		contrasenyap.setBounds(400, 585, 300, 50);
		bAceptar.setBounds(350, 900, 100, 50);
		bRegistrar.setBounds(50, 900, 100, 50);
		bSalir.setBounds(650, 900, 100, 50);
		usuarioAnterior.setBounds(250, 800, 400,50 );
	
		fondo.add(usuariol);
		fondo.add(usuariot);
		fondo.add(contrasenyal);
		fondo.add(contrasenyap);
		fondo.add(bAceptar);
		fondo.add(bSalir);
		fondo.add(bRegistrar);
		fondo.add(usuarioAnterior);
		
		try {
			properties = new Properties();
			properties.loadFromXML( new FileInputStream( "usuario.ini" ) );
			USUARIO_ANTERIOR = properties.getProperty( "USUARIO_ANTERIOR" );
			usuariot.setText(USUARIO_ANTERIOR);
		}catch (Exception i) {}
		
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
				Connection con = BD.initBD("BD");
				Statement st = BD.usarBD(con);
				if(BD.usuarioExiste(st, usuariot.getText())) {
					String contra = BD.usuarioSelect(st, usuariot.getText());
					if(contra.equals(contrasenyap.getText())) {
					//	JOptionPane.showMessageDialog(Pantalla1Jugador.this, "Enhorabuena, te has loggeado corectamente");
						Usuario u = BD.usuarioSelectUsuario(st, usuariot.getText());
						PantallaSelEscenario p = new PantallaSelEscenario(false, u, null);
						p.setVisible(true);
						dispose();
						v.dispose();
						if(usuarioAnterior.isSelected()) {
							try {
								USUARIO_ANTERIOR = usuariot.getText();
								properties.setProperty( "USUARIO_ANTERIOR", USUARIO_ANTERIOR );
								properties.storeToXML( new FileOutputStream( "usuario.ini" ), "Usuario Anterior" );
							} catch (Exception e1) {
								e1.printStackTrace();
							}
						}else {
							try {
								USUARIO_ANTERIOR = "";
								properties.setProperty( "USUARIO_ANTERIOR", USUARIO_ANTERIOR );
								properties.storeToXML( new FileOutputStream( "usuario.ini" ), "Usuario Anterior" );
							} catch (Exception e1) {
								e1.printStackTrace();
							}
						}
					}else {
						JOptionPane.showMessageDialog(Pantalla1Jugador.this, "Usuario o Contraseņa Incorrectas", "Warning!", JOptionPane.ERROR_MESSAGE);
					}
				}else {
					JOptionPane.showMessageDialog(Pantalla1Jugador.this, "Usuario o Contraseņa Incorrectas", "Warning!", JOptionPane.ERROR_MESSAGE);
				}
				
				BD.cerrarBD(con, st);
			}
	
		});
		
		bRegistrar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Connection con = BD.initBD("BD");
				Statement st = BD.usarBD(con);
				if(!BD.usuarioExiste(st, usuariot.getText())) {
					Usuario u = new Usuario(usuariot.getText(), contrasenyap.getText());
					BD.usuarioInsert(st, u);
					JOptionPane.showMessageDialog(Pantalla1Jugador.this, "Enhorabuena, te has registrado correctamente");
				}else {
					JOptionPane.showMessageDialog(Pantalla1Jugador.this, "Nombre de Usuario ya existe", "Warning!", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		
	}
}
