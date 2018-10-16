package pantalla;

import java.awt.BorderLayout;

import javax.sound.sampled.Clip;
import javax.swing.JFrame;
import javax.swing.JPanel;

import sonido.Sonido;

public class PantallaJuego extends JFrame{
	
	private JPanelBackground fondo;
	private Clip loop;
	private String imagenfondo;
	private JPanel PanelSup;
	
	public PantallaJuego() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(1920, 1080);
		setTitle("Pantalla Menú Principal");
		setResizable(false);
		loop = Sonido.music("juego/menu.wav");
		fondo = new JPanelBackground();
		fondo.setBackground(imagenfondo);
		setUndecorated(true);
		getContentPane().setLayout(new BorderLayout());
		fondo.setLayout(null);	
		getContentPane().add(fondo, BorderLayout.CENTER);
		getContentPane().add(PanelSup, BorderLayout.NORTH);
		
		
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
