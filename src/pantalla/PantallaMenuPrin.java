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
	private JLabel lIzqu;
	private JLabel lDere;
	private Icon iTi;
	private Icon iJ1;
	private Icon iJ2;
	private Icon iMarc;
	private Icon iIzqu;
	private Icon iDere;
	private JButton bJ1;
	private JButton bJ2;
	private JButton bMarc;
	private JButton bSalir;
	private Clip loop;
	
	
	public PantallaMenuPrin() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(1920, 1080);
		setTitle("Pantalla Men� Principal");
		setResizable(false);
		loop = Sonido.music("canciones/menu.wav");
		fondo = new JPanelBackground();
		fondo.setBackground("imagenes/fondo1.jpg");
		setUndecorated(true);
		getContentPane().setLayout(new BorderLayout());
		fondo.setLayout(null);	
		getContentPane().add(fondo, BorderLayout.CENTER);
		
		iTi = new ImageIcon("imagenes/titulo.gif");
		lTi = new JLabel(iTi);
		iJ1 = new ImageIcon("imagenes/J1.gif");
		lJ1 = new JLabel(iJ1);
		iJ2 = new ImageIcon("imagenes/J2.gif");
		lJ2 = new JLabel(iJ2);
		iMarc = new  ImageIcon("imagenes/Marc.gif");
		lMarc = new JLabel(iMarc);
		iIzqu = new ImageIcon("imagenes/Izqu.gif");
		lIzqu = new JLabel(iIzqu);
		iDere = new ImageIcon("imagenes/Dere.gif");
		lDere = new JLabel(iDere);
		
		
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
		
		lTi.setBounds(450, 20, 900, 200);
		bJ1.setBounds(350, 500, 500, 100);
		bJ2.setBounds(1050, 500, 500, 100);
		bMarc.setBounds(700, 800, 500, 100);
		bSalir.setBounds(1750, 1000, 100, 50);
		lIzqu.setBounds(150, -125, 450, 550);
		lDere.setBounds(1210, -125, 450, 550);
		
		fondo.add(lTi);	
		fondo.add(bJ1);
		fondo.add(bJ2);
		fondo.add(bMarc);
		fondo.add(bSalir);
		fondo.add(lIzqu);
		fondo.add(lDere);
		
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				Sonido.stop(loop);
			}
		});
		
		bSalir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		bJ1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Pantalla1Jugador p1 = new Pantalla1Jugador(PantallaMenuPrin.this);
				p1.setVisible(true);
				setEnabled(false);
				
			}
		});
		
		bJ2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Pantalla2Jugadores p1 = new Pantalla2Jugadores(PantallaMenuPrin.this);
				p1.setVisible(true);
				setEnabled(false);
				
			}
		});
		
		bMarc.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				PantallaMarcadores p = new PantallaMarcadores();
				p.setVisible(true);
				
				
			}
		});
	
	}

}
