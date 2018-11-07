package pantalla;

import java.awt.BorderLayout;
import java.awt.GridLayout;

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
	private JPanel PanelSup;
	private JPanel PanelJ1;
	private JPanel PanelJ2;

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
		
		bEscenario1.setSize(50, 50);
		bEscenario2.setSize(50, 50);
		
		bAleatorio= new JButton("Aleatorio");
		
		
		PanelSup = new JPanel();
		PanelSup.setOpaque(false);
		PanelSup.setLayout(new GridLayout(1, 3));
		
		
		this.add(PanelSup, BorderLayout.NORTH);

		
		PanelSup.add(bEscenario1);
		PanelSup.add(bEscenario2);
		
		
	}
	public static void main(String[] args) {
		PantallaSelEscenario p = new PantallaSelEscenario();
		p.setVisible(true);

	}
}


