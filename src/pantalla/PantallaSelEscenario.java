package pantalla;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Panel;

import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


import sonido.Sonido;

public class PantallaSelEscenario extends JFrame {

	private JPanelBackground fondo;
	private Clip loop;
	private String imagenfondo;

	JPanel panelNorte;
	JPanel panelCentral;
	JPanel panelSur;

	JPanel panelIzquierdo;
	JPanel panelDerecho;
	
	private JButton bAleatorio;
	private JButton bEscenario1;
	private JButton bEscenario2;
	
	private ImageIcon escenario1;
	private ImageIcon escenario2;
	
	
	
	public PantallaSelEscenario() {
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(1920, 1080);
		setTitle("Pantalla Seleccion Escenario");
		setResizable(false);
		loop = Sonido.music("canciones/juego.wav");
		getContentPane().setLayout(new BorderLayout());	
		
		
		
		
		escenario1=new ImageIcon("imagenes/escenario1.jpg");
		escenario2=new ImageIcon("imagenes/escenario2.jpg");
		
		bEscenario1= new JButton();
		bEscenario2= new JButton();	
		
		bEscenario1.setIcon(escenario1);
		bEscenario2.setIcon(escenario2);
		
		
		
		bAleatorio= new JButton("Aleatorio");
		
		
		JPanel panelSup = new JPanel();
		panelSup.setLayout( new FlowLayout( FlowLayout.LEFT ) );
		JPanel panelCentral = new JPanel();
		JPanel panelInferior = new JPanel();
		JPanel panelDerecho = new JPanel();
		JPanel panelIzquierdo = new JPanel();
		
		panelIzquierdo.setLayout(new GridLayout(1,2));
		panelDerecho.setLayout(new GridLayout(2,1));

		panelInferior.add(bEscenario1);
		panelDerecho.add(bEscenario2);
		
		this.setLayout( new BorderLayout() );
		this.add(panelSup, BorderLayout.NORTH );
		this.add(panelCentral, BorderLayout.CENTER );
		panelCentral.add(panelIzquierdo);
		panelCentral.add(panelDerecho);
		
		this.add(panelInferior, BorderLayout.SOUTH );

		
		

		
	}
	public static void main(String[] args) {
		PantallaSelEscenario p = new PantallaSelEscenario();
		p.setVisible(true);

	}
}


