package pantalla;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.sound.sampled.Clip;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;

import sonido.Sonido;
import usuario.Usuario;

public class PantallaGanador extends JFrame{
	JPanelBackground fondo;
	JFrame ventanaAnterior;
	JLabel label;
	JButton bSalir;
	JButton bRevancha;
	Clip loop;
	
	public PantallaGanador(String jugadorGanador, Usuario U1, Usuario U2) {
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(1000, 900);
		setTitle("Pantalla 1 Jugador");
		setResizable(false);
		setLocationRelativeTo(null);
		setUndecorated(true);
		loop = Sonido.music("canciones/ganar.wav");
		fondo = new JPanelBackground();
		fondo.setBackground("imagenes/winner.jpg");
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(fondo, BorderLayout.CENTER);
		fondo.setLayout(null);
		 
		label = new JLabel("El ganador es: " + jugadorGanador);
		label.setFont( new Font("Apple Casual",Font.CENTER_BASELINE, 40));
		label.setBounds(100, 500, 1000, 100);
		
		bSalir = new JButton("Salir");
		bSalir.setBounds(200, 775, 100, 50);
		bSalir.setBackground(Color.BLACK);
		bSalir.setForeground(Color.RED);
		bSalir.setBorder(new LineBorder(Color.RED));
		
		bRevancha = new JButton("Revancha");
		bRevancha.setBounds(700, 775, 100, 50);
		bRevancha.setBackground(Color.BLACK);
		bRevancha.setForeground(Color.RED);
		bRevancha.setBorder(new LineBorder(Color.RED));
		
		fondo.add(label);
		fondo.add(bSalir);
		fondo.add(bRevancha);
		
		bSalir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				PantallaMenuPrin p = new PantallaMenuPrin();
				p.setVisible(true);
				
			}
		});
		
		bRevancha.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				PantallaSelEscenario p = new PantallaSelEscenario(true, U1, U2);
				p.setVisible(true);
			}
		});
		
		addWindowListener(new WindowAdapter() {
						
			@Override
			public void windowClosed(WindowEvent e) {
				Sonido.stop(loop);
				
			}
			
		});
	}
	


}
