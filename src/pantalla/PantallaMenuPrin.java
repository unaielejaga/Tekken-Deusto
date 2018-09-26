package pantalla;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;

import javax.sound.sampled.Clip;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;

import sonido.Sonido;

public class PantallaMenuPrin extends JFrame{
	
	private JPanelBackground fondo;
	private JLabel lTi;
	private JLabel lJ1;
	private JLabel lJ2;
	private JLabel lMarc;
	private JLabel lImagen;
	private Icon iTi;
	private Icon iJ1;
	private Icon iJ2;
	private Icon iMarc;
	private Icon iImagen;
	private JButton bJ1;
	private JButton bJ2;
	private JButton bMarc;
	private JButton bSalir;
	private Clip loop;
	private static PantallaMenuPrin p;
	
	
	public PantallaMenuPrin() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(1920, 1080);
		setTitle("Pantalla Menú Principal");
		setResizable(false);
		loop = Sonido.music("Dracukeo.wav");
		fondo = new JPanelBackground();
		fondo.setBackground("fondo.jpg");
		setUndecorated(true);
		getContentPane().setLayout(new BorderLayout());
		fondo.setLayout(null);	
		getContentPane().add(fondo, BorderLayout.CENTER);
		
		iTi = new ImageIcon("titulo.gif");
		lTi = new JLabel(iTi);
		iJ1 = new ImageIcon("J1.gif");
		lJ1 = new JLabel(iJ1);
		iJ2 = new ImageIcon("J2.gif");
		lJ2 = new JLabel(iJ2);
		iMarc = new  ImageIcon("Marc.gif");
		lMarc = new JLabel(iMarc);
		iImagen = new ImageIcon("alber-einstein.gif");
		lImagen = new JLabel(iImagen);
		
		bJ1 = new JButton();
		bJ1.setLayout(new BorderLayout());
		bJ1.add(lJ1, BorderLayout.CENTER);
		bJ1.setBackground(Color.BLACK);
		bJ1.setBorder(new LineBorder(Color.RED));
		bJ2 = new JButton();
		bJ2.setLayout(new BorderLayout());
		bJ2.add(lJ2, BorderLayout.CENTER);
		bJ2.setBackground(Color.BLACK);
		bJ2.setBorder(new LineBorder(Color.RED));
		bMarc = new JButton();
		bMarc.setLayout(new BorderLayout());
		bMarc.add(lMarc, BorderLayout.CENTER);
		bMarc.setBackground(Color.BLACK);
		bMarc.setBorder(new LineBorder(Color.RED));
		bSalir = new JButton("Salir");
		bSalir.setBackground(Color.BLACK);
		bSalir.setForeground(Color.RED);
		bSalir.setBorder(new LineBorder(Color.RED));
		
		lTi.setBounds(450, 50, 900, 200);
		bJ1.setBounds(400, 400, 500, 100);
		bJ2.setBounds(550, 600, 500, 100);
		bMarc.setBounds(700, 800, 500, 100);
		bSalir.setBounds(1700, 1000, 100, 50);
		lImagen.setBounds(80, 450, 450, 550);
		
		fondo.add(lTi);	
		fondo.add(bJ1);
		fondo.add(bJ2);
		fondo.add(bMarc);
		fondo.add(bSalir);
		fondo.add(lImagen);
		
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				Sonido.stop(loop);
			}
		});
		
		bSalir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				p.dispatchEvent(new WindowEvent(p, WindowEvent.WINDOW_CLOSING));
			}
		});
	}

	public static void main(String[] args) {
		p = new PantallaMenuPrin();
		p.setVisible(true);
				

	}

}
