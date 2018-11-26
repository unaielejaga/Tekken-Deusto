package pantalla;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.sound.sampled.Clip;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import sonido.Sonido;

public class PantallaJuego extends JFrame{
	
	private JPanelBackground fondo;
	private Clip loop;
	private String imagenfondo;
	private JPanel PanelSup;
	private JPanel PanelJ1;
	private JPanel PanelJ1Int;
	private JPanel PanelJ2;
	private JPanel PanelJ2Int;
	private JPanel vacio;
	private JProgressBar JPB11;
	private JProgressBar JPB21;
	private JProgressBar JPB12;
	private JProgressBar JPB22;
	private JLabel lTiempo;
	private int vida1;
	
	public PantallaJuego() {
		
		vida1 = 100;
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(1920, 1080);
		setTitle("Pantalla Menú Principal");
		setResizable(false);
		loop = Sonido.music("canciones/juego.wav");
		fondo = new JPanelBackground();
		fondo.setBackground("imagenes/fondo1.jpg");
		//setUndecorated(true);
		getContentPane().setLayout(new BorderLayout());	
		getContentPane().add(fondo);
		
		PanelSup = new JPanel();
		PanelSup.setOpaque(false);
		vacio = new JPanel();
		vacio.setOpaque(false);
		PanelSup.setLayout(new FlowLayout());
		PanelJ1 = new JPanel();
		PanelJ1.setOpaque(false);
		PanelJ1.setLayout(new GridLayout(2, 1));
		PanelJ2 = new JPanel();
		PanelJ2.setOpaque(false);
		PanelJ2.setLayout(new GridLayout(2, 1));
		PanelJ1Int = new JPanel();
		PanelJ1Int.setOpaque(false);
		PanelJ1Int.setLayout(new GridLayout(1, 2));
		PanelJ2Int = new JPanel();
		PanelJ2Int.setOpaque(false);
		PanelJ2Int.setLayout(new GridLayout(1, 2));
		
		JPB11 = new JProgressBar(0, 100);
		JPB11.setSize(100, 50);
		JPB21 = new JProgressBar(0, 100);
		JPB12 = new JProgressBar(0, 100);
		JPB22 = new JProgressBar(0, 100);
		
		JPB11.setForeground(Color.RED);
		JPB21.setForeground(Color.BLUE);
		JPB12.setForeground(Color.RED);
		JPB22.setForeground(Color.BLUE);
		
		lTiempo = new JLabel("5");
		lTiempo.setFont(new Font("Apple Casual", Font.BOLD, 60));
		lTiempo.setForeground(Color.RED);
		lTiempo.setHorizontalAlignment(JLabel.CENTER);
		
		JPB11.setValue(vida1);
		JPB21.setValue(30);
		JPB12.setValue(6);
		JPB22.setValue(40);
		
		PanelJ1Int.add(vacio);
		PanelJ1Int.add(JPB12);
		PanelJ2Int.add(vacio);
		PanelJ2Int.add(JPB22);
		
		PanelJ1.add(JPB11);
		PanelJ1.add(JPB21);
		PanelJ2.add(JPB12);
		PanelJ2.add(PanelJ1Int);
		
		PanelSup.add(PanelJ1);
		PanelSup.add(lTiempo);
		PanelSup.add(PanelJ2);

		fondo.add(PanelSup, BorderLayout.NORTH);		
	}

	public static void main(String[] args) {
		PantallaJuego p = new PantallaJuego();
		p.setVisible(true);

	}

}
