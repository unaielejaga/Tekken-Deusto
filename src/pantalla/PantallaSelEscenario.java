package pantalla;

import java.awt.BorderLayout;
import java.awt.Color;
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

	private JPanel panelIzquierdo;
	private JPanel panelDerecho;
	
	private JButton bAleatorio;
	private JButton bEscenario1;
	private JButton bEscenario2;
	private JButton bEscenario3;
	private JButton bEscenario4;
	private JButton botonAnterior;
	

	
	private ImageIcon escenario1;
	private ImageIcon escenario2;
	
	
	
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
		
		bEscenario1= new JButton();
		bEscenario2= new JButton();	
		bEscenario3= new JButton();
		bEscenario4= new JButton();	
		botonAnterior = new JButton();
		
		bEscenario1.setIcon(escenario1);
		bEscenario2.setIcon(escenario2);
		bEscenario3.setIcon(escenario1);
		bEscenario4.setIcon(escenario2);
		
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
		
		
		bAleatorio= new JButton("Aleatorio");
		
		panelSup = new JPanel();
		panelSup.setLayout( new FlowLayout( FlowLayout.LEFT ) );
		panelCentral = new JPanel();
		panelSup.setOpaque(false);

		
		
		panelSup.setLayout(new FlowLayout());
		panelSup.add(bEscenario1);
		panelSup.add(bEscenario2);
		panelSup.add(bEscenario3);
		panelSup.add(bEscenario4);

		
		this.setLayout( new BorderLayout() );
		this.add(panelSup, BorderLayout.NORTH );


		bEscenario1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				bEscenario1.setBorder(new LineBorder(Color.RED));
				bEscenario1.setBorderPainted(true);
				botonAnterior.setBorderPainted(false);
				botonAnterior = bEscenario1;
				
			}
		});
		
	bEscenario2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				bEscenario2.setBorder(new LineBorder(Color.RED));
				bEscenario2.setBorderPainted(true);
				botonAnterior.setBorderPainted(false);
				botonAnterior = bEscenario2;
				
			}
		});
		
	
	bEscenario3.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			bEscenario3.setBorder(new LineBorder(Color.RED));
			bEscenario3.setBorderPainted(true);
			botonAnterior.setBorderPainted(false);
			botonAnterior = bEscenario3;
			
		}
	});
	
	bEscenario4.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			bEscenario4.setBorder(new LineBorder(Color.RED));
			bEscenario4.setBorderPainted(true);
			botonAnterior.setBorderPainted(false);
			botonAnterior = bEscenario4;
			
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


