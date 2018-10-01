package pantalla;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class Pantalla2Jugadores extends JFrame{
	
	JPanelBackground fondo;
	JButton bSalir;
	JButton bAceptar;
	Icon usuarioi1;
	Icon contrasenyai1;
	JLabel usuariol1;
	JLabel contrasenyal1;
	JTextField usuariot1;
	JPasswordField contrasenyap1;
	Icon usuarioi2;
	Icon contrasenyai2;
	JLabel usuariol2;
	JLabel contrasenyal2;
	JTextField usuariot2;
	JPasswordField contrasenyap2;
	JFrame ventanaAnterior;
	
	public Pantalla2Jugadores(JFrame v) {
		ventanaAnterior = v;
		JFrame frame = new JFrame();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(800, 1000);
		setTitle("Pantalla 1 Jugador");
		setResizable(false);
		setLocationRelativeTo(null);
		setUndecorated(true);
		fondo = new JPanelBackground();
		fondo.setBackground("imagenes/fondoRegistro2.jpg");
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(fondo, BorderLayout.CENTER);
		fondo.setLayout(null);
		
		Font fuente = new Font("Dialog", Font.BOLD, 40);
		usuarioi1 = new ImageIcon("imagenes/nick.gif");
		usuariol1 = new JLabel(usuarioi1);
		usuariot1 = new JTextField(20);
		usuariot1.setFont(fuente);
		contrasenyai1 = new ImageIcon("imagenes/contraseña.gif");
		contrasenyal1 = new JLabel(contrasenyai1);
		contrasenyap1 = new JPasswordField(20);
		contrasenyap1.setFont(fuente);
		usuarioi2 = new ImageIcon("imagenes/nick.gif");
		usuariol2 = new JLabel(usuarioi2);
		usuariot2 = new JTextField(20);
		usuariot2.setFont(fuente);
		contrasenyai2 = new ImageIcon("imagenes/contraseña.gif");
		contrasenyal2 = new JLabel(contrasenyai2);
		contrasenyap2 = new JPasswordField(20);
		contrasenyap2.setFont(fuente);
		
		bAceptar = new JButton("Aceptar");
		bAceptar.setBackground(Color.BLACK);
		bAceptar.setForeground(Color.RED);
		bAceptar.setBorder(new LineBorder(Color.RED));
		bSalir = new JButton("Salir");
		bSalir.setBackground(Color.BLACK);
		bSalir.setForeground(Color.RED);
		bSalir.setBorder(new LineBorder(Color.RED));
		
		usuariol1.setBounds(150, 10, 300, 200);
		usuariot1.setBounds(400, 95, 300, 50);
		contrasenyal1.setBounds(0, 150, 400, 200);
		contrasenyap1.setBounds(400, 235, 300, 50);
		usuariol2.setBounds(150, 450, 300, 200);
		usuariot2.setBounds(400, 535, 300, 50);
		contrasenyal2.setBounds(0, 590, 400, 200);
		contrasenyap2.setBounds(400, 675, 300, 50);
		bAceptar.setBounds(350, 900, 100, 50);
		bSalir.setBounds(650, 900, 100, 50);
		
		fondo.add(usuariol1);
		fondo.add(usuariot1);
		fondo.add(contrasenyal1);
		fondo.add(contrasenyap1);
		fondo.add(usuariol2);
		fondo.add(usuariot2);
		fondo.add(contrasenyal2);
		fondo.add(contrasenyap2);
		fondo.add(bAceptar);
		fondo.add(bSalir);
		
		bSalir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ventanaAnterior.setEnabled(true);	
				dispose();
			}
		});
		
	}

}
