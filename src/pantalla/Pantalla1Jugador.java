package pantalla;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

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
	
	
	public Pantalla1Jugador() {
		JFrame frame = new JFrame();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(800, 1000);
		setTitle("Pantalla 1 Jugador");
		setResizable(false);
		setLocationRelativeTo(null);
		setUndecorated(true);
		fondo = new JPanelBackground();
		fondo.setBackground("fondoRegistro.jpg");
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(fondo, BorderLayout.CENTER);
		fondo.setLayout(null);
		
		usuarioi = new ImageIcon("nick.gif");
		usuariol = new JLabel(usuarioi);
		usuariot = new JTextField(20);
		contrasenyai = new ImageIcon("contraseña.gif");
		contrasenyal = new JLabel(contrasenyai);
		contrasenyap = new JPasswordField(20);
	
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
				dispose();
			}
		});
		
	}
	

}
