package pantalla;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
	private JButton botonAntriorJ1;
	private JButton botonAntriorJ2;
	
	private JButton b1;
	private JButton b2;
	private JButton b3;
	private JButton b4;
	private JButton b5;
	private JButton b6;
	private JButton b7;
	private JButton b8;
	
	private JLabel J1l;
	private JLabel J2l;
	
	private ImageIcon escenario1;
	private ImageIcon escenario2;
	private ImageIcon escenario3;
	private ImageIcon escenario4;
	private ImageIcon donatello;
	private ImageIcon leonardo;
	private ImageIcon raphael;
	private ImageIcon mike;
	private ImageIcon J1;
	private ImageIcon J2;
	private ImageIcon donatelloG;
	private ImageIcon leonardoG;
	private ImageIcon mikeG;
	private ImageIcon raphaelG;
	
	private String J1t;
	private String J2t;
	private String fondoImagen;
	
	
	public PantallaSelEscenario(boolean J2B) {
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(1920, 1080);
		setTitle("Pantalla Seleccion Escenario");
		setResizable(false);
		setUndecorated(true);
		loop = Sonido.music("canciones/seleccion.wav");
		getContentPane().setLayout(new BorderLayout());
		getContentPane().setBackground(Color.BLACK);
		
		escenario1 = new ImageIcon("imagenes/Escenario1Icono.gif");
		escenario2 = new ImageIcon("imagenes/escenario2Icono.gif");
		escenario3 = new ImageIcon("imagenes/Escenario3Icono.gif");
		escenario4 = new ImageIcon("imagenes/escenario4Icono.gif");
		donatello = new ImageIcon("imagenes/Donatello.png");
		leonardo = new ImageIcon("imagenes/Leonardo.png");
		raphael = new ImageIcon("imagenes/Raphael.png");
		mike = new ImageIcon("imagenes/Mike.png");
		donatelloG = new ImageIcon("imagenes/DonatelloG.png");
		leonardoG = new ImageIcon("imagenes/LeonardoG.png");
		mikeG = new ImageIcon("imagenes/MikeG.png");
		raphaelG = new ImageIcon("imagenes/RaphaelG.png");
		
		J1 = new ImageIcon("imagenes/J1l.gif");
		J1l = new JLabel(J1);
		J2 = new ImageIcon("imagenes/J2l.gif");
		J2l = new JLabel(J2);
		
		
		bEscenario1= new JButton();
		bEscenario2= new JButton();	
		bEscenario3= new JButton();
		bEscenario4= new JButton();	
		botonAnteriorEsc = new JButton();
		botonAntriorJ1 = new JButton();
		botonAntriorJ2 = new JButton();
		
		b1 = new JButton();
		b2 = new JButton();
		b3 = new JButton();
		b4 = new JButton();
		b5 = new JButton();
		b6 = new JButton();
		b7 = new JButton();
		b8 = new JButton();
		
		b5.setEnabled(J2B);
		b6.setEnabled(J2B);
		b7.setEnabled(J2B);
		b8.setEnabled(J2B);
		
		
		bEscenario1.setIcon(escenario1);
		bEscenario2.setIcon(escenario2);
		bEscenario3.setIcon(escenario3);
		bEscenario4.setIcon(escenario4);
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
		panelSup.add(J1l);
		panelSup.add(bEscenario1);
		panelSup.add(bEscenario2);
		panelSup.add(bEscenario3);
		panelSup.add(bEscenario4);
		panelSup.add(J2l);

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
				fondoImagen = "Escenario1";
				
			}
		});
		
	bEscenario2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				bEscenario2.setBorder(new LineBorder(Color.RED));
				bEscenario2.setBorderPainted(true);
				botonAnteriorEsc.setBorderPainted(false);
				botonAnteriorEsc = bEscenario2;
				fondoImagen = "Escenario2";
				
			}
		});
		
	
	bEscenario3.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			bEscenario3.setBorder(new LineBorder(Color.RED));
			bEscenario3.setBorderPainted(true);
			botonAnteriorEsc.setBorderPainted(false);
			botonAnteriorEsc = bEscenario3;
			fondoImagen = "Escenario3";
			
		}
	});
	
	bEscenario4.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			bEscenario4.setBorder(new LineBorder(Color.RED));
			bEscenario4.setBorderPainted(true);
			botonAnteriorEsc.setBorderPainted(false);
			botonAnteriorEsc = bEscenario4;
			fondoImagen = "Escenario4";
			
		}
	});
	
	b1.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			b1.setBorder(new LineBorder(Color.BLUE));
			b1.setBorderPainted(true);
			botonAntriorJ1.setBorderPainted(false);
			botonAntriorJ1 = b1;
			J1t = "Donatello";
			
		}
	});
	
	b2.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			b2.setBorder(new LineBorder(Color.BLUE));
			b2.setBorderPainted(true);
			botonAntriorJ1.setBorderPainted(false);
			botonAntriorJ1 = b2;
			J1t = "Leonardo";
			
		}
	});
	
	b3.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			b3.setBorder(new LineBorder(Color.BLUE));
			b3.setBorderPainted(true);
			botonAntriorJ1.setBorderPainted(false);
			botonAntriorJ1 = b3;
			J1t = "Raphael";
			
		}
	});
	
	b4.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			b4.setBorder(new LineBorder(Color.BLUE));
			b4.setBorderPainted(true);
			botonAntriorJ1.setBorderPainted(false);
			botonAntriorJ1 = b4;
			J1t = "Mike";
			
		}
	});
	
	b5.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			b5.setBorder(new LineBorder(Color.ORANGE));
			b5.setBorderPainted(true);
			botonAntriorJ2.setBorderPainted(false);
			botonAntriorJ2 = b5;
			J2t = "Donatello";
			
		}
	});
	
	b6.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			b6.setBorder(new LineBorder(Color.ORANGE));
			b6.setBorderPainted(true);
			botonAntriorJ2.setBorderPainted(false);
			botonAntriorJ2 = b6;
			J2t = "Leonardo";
			
		}
	});
	
	b7.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			b7.setBorder(new LineBorder(Color.ORANGE));
			b7.setBorderPainted(true);
			botonAntriorJ2.setBorderPainted(false);
			botonAntriorJ2 = b7;
			J2t = "Raphael";
			
		}
	});
	
	b8.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			b8.setBorder(new LineBorder(Color.ORANGE));
			b8.setBorderPainted(true);
			botonAntriorJ2.setBorderPainted(false);
			botonAntriorJ2 = b8;
			J2t = "Mike";
			
		}
	});
	
	bAceptar.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(J2B) {
				if(fondoImagen != null && J1t != null && J2t != null) {
					PantallaJuego p = new PantallaJuego(fondoImagen);
					p.setVisible(true);
					dispose();
				}else {
					JOptionPane.showMessageDialog(PantallaSelEscenario.this, "Faltan campos por rellenar", "Warning!", JOptionPane.WARNING_MESSAGE);
				}
			}else {
				if(fondoImagen != null && J1t != null) {
					PantallaJuego p = new PantallaJuego(fondoImagen);
					p.setVisible(true);
					dispose();
				}else {
					JOptionPane.showMessageDialog(PantallaSelEscenario.this, "Faltan campos por rellenar", "Warning!", JOptionPane.WARNING_MESSAGE);
				}
			}
			
			
		}
	});
	
	b1.addMouseListener(new MouseAdapter() {
		
		@Override
		public void mouseExited(MouseEvent e) {
			b1.setIcon(donatello);
			
		}
		
		@Override
		public void mouseEntered(MouseEvent e) {
			b1.setIcon(donatelloG);
		}

		
	});
	
	b2.addMouseListener(new MouseAdapter() {
		
		@Override
		public void mouseExited(MouseEvent e) {
			b2.setIcon(leonardo);
			
		}
		
		@Override
		public void mouseEntered(MouseEvent e) {
			b2.setIcon(leonardoG);
		}
			
	});
	
	b3.addMouseListener(new MouseAdapter() {
		
		@Override
		public void mouseExited(MouseEvent e) {
			b3.setIcon(raphael);
			
		}
		
		@Override
		public void mouseEntered(MouseEvent e) {
			b3.setIcon(raphaelG);
		}
	});
	
	b4.addMouseListener(new MouseAdapter() {
		
		@Override
		public void mouseExited(MouseEvent e) {
			b4.setIcon(mike);
			
		}
		
		@Override
		public void mouseEntered(MouseEvent e) {
			b4.setIcon(mikeG);
		}
	});
	
	b5.addMouseListener(new MouseAdapter() {
		
		@Override
		public void mouseExited(MouseEvent e) {
			b5.setIcon(donatello);
			
		}
		
		@Override
		public void mouseEntered(MouseEvent e) {
			b5.setIcon(donatelloG);
		}

		
	});
	
	b6.addMouseListener(new MouseAdapter() {
		
		@Override
		public void mouseExited(MouseEvent e) {
			b6.setIcon(leonardo);
			
		}
		
		@Override
		public void mouseEntered(MouseEvent e) {
			b6.setIcon(leonardoG);
		}
			
	});
	
	b7.addMouseListener(new MouseAdapter() {
		
		@Override
		public void mouseExited(MouseEvent e) {
			b7.setIcon(raphael);
			
		}
		
		@Override
		public void mouseEntered(MouseEvent e) {
			b7.setIcon(raphaelG);
		}
	});
	
	b8.addMouseListener(new MouseAdapter() {
		
		@Override
		public void mouseExited(MouseEvent e) {
			b8.setIcon(mike);
			
		}
		
		@Override
		public void mouseEntered(MouseEvent e) {
			b8.setIcon(mikeG);
		}
	});
	
	addWindowListener(new WindowAdapter() {
		@Override
		public void windowClosed(WindowEvent e) {
			Sonido.stop(loop);
			System.out.println(J1t);
			System.out.println(J2t);
		}
	});
		
	}
	public static void main(String[] args) {
		PantallaSelEscenario p = new PantallaSelEscenario(true);
		p.setVisible(true);

	}
}


