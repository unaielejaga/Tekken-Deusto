package pantalla;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import sonido.Sonido;

public class PantallaSelEscenario extends JFrame {

	private JPanelBackground fondo;
	private Clip loop;
	private String imagenfondo;

	private JPanel panelNorte;
	private JPanel panelCentral;
	private JPanel panelSup;
	private JPanel panelInferior;

	private JPanel panelSelJ1;
	private JPanel panelSelJ2;
	
	private JButton bAceptar;
	private JButton bEscenario1;
	private JButton bEscenario2;
	private JButton bEscenario3;
	private JButton bEscenario4;
	private JButton botonAnteriorEsc;
	
	private JButton b1;
	private JButton b2;
	private JButton b3;
	private JButton b4;
	private JButton b5;
	private JButton b6;
	private JButton b7;
	private JButton b8;
	
	private ImageIcon escenario1;
	private ImageIcon escenario2;
	private ImageIcon donatello;
	private ImageIcon leonardo;
	private ImageIcon raphael;
	private ImageIcon mike;
	
	
	
	public PantallaSelEscenario() {
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(1920, 1080);
		setTitle("Pantalla Seleccion Escenario");
		setResizable(false);
		setUndecorated(true);
		loop = Sonido.music("canciones/seleccion.wav");
		getContentPane().setLayout(new BorderLayout());
		getContentPane().setBackground(Color.BLACK);
		
		escenario1 = new ImageIcon("imagenes/escenario1.jpg");
		escenario2 = new ImageIcon("imagenes/escenario2.jpg");
		donatello = new ImageIcon("imagenes/Donatello.png");
		leonardo = new ImageIcon("imagenes/Leonardo.png");
		raphael = new ImageIcon("imagenes/Raphael.png");
		mike = new ImageIcon("imagenes/Mike.png");
		
		bEscenario1= new JButton();
		bEscenario2= new JButton();	
		bEscenario3= new JButton();
		bEscenario4= new JButton();	
		botonAnteriorEsc = new JButton();
		
		b1 = new JButton();
		b2 = new JButton();
		b3 = new JButton();
		b4 = new JButton();
		b5 = new JButton();
		b6 = new JButton();
		b7 = new JButton();
		b8 = new JButton();
		
		bEscenario1.setIcon(escenario1);
		bEscenario2.setIcon(escenario2);
		bEscenario3.setIcon(escenario1);
		bEscenario4.setIcon(escenario2);
		b1.setIcon(donatello);
		b5.setIcon(donatello);
		b2.setIcon(leonardo);
		b6.setIcon(leonardo);
		b3.setIcon(raphael);
		b7.setIcon(raphael);
		b4.setIcon(mike);
		b8.setIcon(mike);
		
		
		bEscenario1.setOpaque(false);
		bEscenario1.setContentAreaFilled(false);
		bEscenario1.setBorder(new LineBorder(Color.BLACK));
		bEscenario2.setOpaque(false);
		bEscenario2.setContentAreaFilled(false);
		bEscenario2.setBorder(new LineBorder(Color.BLACK));
		bEscenario3.setOpaque(false);
		bEscenario3.setContentAreaFilled(false);
		bEscenario3.setBorder(new LineBorder(Color.BLACK));
		bEscenario4.setOpaque(false);
		bEscenario4.setContentAreaFilled(false);
		bEscenario4.setBorder(new LineBorder(Color.BLACK));
		
		b1.setOpaque(false);
		b1.setContentAreaFilled(false);
		b1.setBorder(new LineBorder(Color.BLACK));
		b2.setOpaque(false);
		b2.setContentAreaFilled(false);
		b2.setBorder(new LineBorder(Color.BLACK));
		b3.setOpaque(false);
		b3.setContentAreaFilled(false);
		b3.setBorder(new LineBorder(Color.BLACK));
		b4.setOpaque(false);
		b4.setContentAreaFilled(false);
		b4.setBorder(new LineBorder(Color.BLACK));
		b5.setOpaque(false);
		b5.setContentAreaFilled(false);
		b5.setBorder(new LineBorder(Color.BLACK));
		b6.setOpaque(false);
		b6.setContentAreaFilled(false);
		b6.setBorder(new LineBorder(Color.BLACK));
		b7.setOpaque(false);
		b7.setContentAreaFilled(false);
		b7.setBorder(new LineBorder(Color.BLACK));
		b8.setOpaque(false);
		b8.setContentAreaFilled(false);
		b8.setBorder(new LineBorder(Color.BLACK));
		
		
		bAceptar = new JButton("Aceptar");
		bAceptar.setBackground(Color.BLACK);
		bAceptar.setForeground(Color.RED);
		bAceptar.setBorder(new LineBorder(Color.RED));
		bAceptar.setPreferredSize(new Dimension(100, 50));
		
		panelSup = new JPanel();
		panelSup.setLayout( new FlowLayout( FlowLayout.LEFT ) );
		panelSup.setOpaque(false);
		panelCentral = new JPanel();
		panelCentral.setLayout(new GridLayout(1, 2));
		panelCentral.setOpaque(false);
		panelInferior = new JPanel();
		panelInferior.setLayout(new BorderLayout());
		
		
		panelSup.setLayout(new FlowLayout());
		panelSup.add(bEscenario1);
		panelSup.add(bEscenario2);
		panelSup.add(bEscenario3);
		panelSup.add(bEscenario4);

		panelInferior.add(bAceptar);
		
		panelSelJ1 = new JPanel();
		panelSelJ1.setLayout(new GridLayout(2, 2));
		panelSelJ1.setOpaque(false);
		panelSelJ2 = new JPanel();
		panelSelJ2.setLayout(new GridLayout(2, 2));
		panelSelJ2.setOpaque(false);
		
		panelSelJ1.add(b1);
		panelSelJ1.add(b2);
		panelSelJ1.add(b3);
		panelSelJ1.add(b4);
		
		panelSelJ2.add(b5);
		panelSelJ2.add(b6);
		panelSelJ2.add(b7);
		panelSelJ2.add(b8);
		
		panelCentral.add(panelSelJ1);
		panelCentral.add(panelSelJ2);
		
		this.setLayout( new BorderLayout() );
		this.add(panelInferior, BorderLayout.SOUTH);
		this.add(panelCentral, BorderLayout.CENTER);
		this.add(panelSup, BorderLayout.NORTH );


		bEscenario1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				bEscenario1.setBorder(new LineBorder(Color.RED));
				bEscenario1.setBorderPainted(true);
				botonAnteriorEsc.setBorderPainted(false);
				botonAnteriorEsc = bEscenario1;
				
			}
		});
		
	bEscenario2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				bEscenario2.setBorder(new LineBorder(Color.RED));
				bEscenario2.setBorderPainted(true);
				botonAnteriorEsc.setBorderPainted(false);
				botonAnteriorEsc = bEscenario2;
				
			}
		});
		
	
	bEscenario3.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			bEscenario3.setBorder(new LineBorder(Color.RED));
			bEscenario3.setBorderPainted(true);
			botonAnteriorEsc.setBorderPainted(false);
			botonAnteriorEsc = bEscenario3;
			
		}
	});
	
	bEscenario4.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			bEscenario4.setBorder(new LineBorder(Color.RED));
			bEscenario4.setBorderPainted(true);
			botonAnteriorEsc.setBorderPainted(false);
			botonAnteriorEsc = bEscenario4;
			
		}
	});
	
	bAceptar.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
			
		}
	});
	
	addWindowListener(new WindowAdapter() {
		@Override
		public void windowClosed(WindowEvent e) {
			Sonido.stop(loop);
		}
	});
		
	}
	public static void main(String[] args) {
		PantallaSelEscenario p = new PantallaSelEscenario();
		p.setVisible(true);

	}
}


