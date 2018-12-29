package pantalla;

import java.awt.BorderLayout;

import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import sonido.Sonido;

public class PantallaMarcadores extends JFrame{
	private JPanelBackground fondo;
	JPanel PanelSup;
	ImageIcon MarcadoresI;
	JLabel MarcadoresL;
	

	public PantallaMarcadores() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(1920, 1080);
		setTitle("Pantalla Menú Principal");
		setResizable(false);
		fondo = new JPanelBackground();
		fondo.setBackground("imagenes/fondo1.jpg");
		//setUndecorated(true);
		getContentPane().setLayout(new BorderLayout());
		fondo.setLayout(new BorderLayout());	
		getContentPane().add(fondo);
		
		PanelSup = new JPanel();
		PanelSup.setOpaque(false);
		
		MarcadoresI = new ImageIcon("imagenes/Marc.gif");
		MarcadoresL = new JLabel(MarcadoresI);
		
		PanelSup.add(MarcadoresL);
		
		fondo.add(PanelSup, BorderLayout.NORTH);
		
		
		
	}
}
