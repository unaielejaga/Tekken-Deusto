package pantalla;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.Statement;

import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;

import bd.BD;
import personajes.Personajes;
import sonido.Sonido;

public class PantallaJuego extends JFrame{
	
	private JPanelBackgroundGif fondo;
	private Clip loop;
	private String imagenfondo;
	private JPanel PanelSup;
	private JPanel PanelCentral;
	private JPanel PanelJ1;
	private JPanel PanelJ1Int;
	private JPanel PanelJ2;
	private JPanel PanelJ2Int;
	private JPanel vacio1;
	private JPanel vacio2;
	private JPanel vacio3;
	private JProgressBar JPB11;
	private JProgressBar JPB21;
	private JProgressBar JPB12;
	private JProgressBar JPB22;
	private JLabel lTiempo;
	private int contador = 60;
	private Personajes J1;
	private Personajes J2;
	private int J1VidaIni;
	private int J1EnergiaIni;
	private JLabelGraficoAjustado imagen1;
	private JLabelGraficoAjustado imagen1Ant;
	private JLabelGraficoAjustado imagen2;
	private boolean botonPulsado = true;
	private boolean anteriorIzq = false;
	
	public PantallaJuego(String fondoImagen, boolean J2B, String nombreJ1, String nombreJ2) {
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(1920, 1080);
		setTitle("Pantalla Menú Principal");
		setResizable(false);
		fondo = new JPanelBackgroundGif("imagenes/" + fondoImagen + ".gif");
		//setUndecorated(true);
		getContentPane().setLayout(new BorderLayout());	
		fondo.setLayout(new BorderLayout());
		getContentPane().add(fondo);

		Connection con = BD.initBD("BD");
		Statement st = BD.usarBD(con);
		if(J2B) {
			J1 = BD.personajeSelect(st, nombreJ1);
			J2 = BD.personajeSelect(st, nombreJ2);
		}else {
			J1 = BD.personajeSelect(st, nombreJ1);
			J2 = BD.personajeSelect(st, "Donatello");
		}
	
		BD.cerrarBD(con, st);	
		
		
		J1VidaIni = J1.getVida();
		J1EnergiaIni = J1.getEnergia();
		
		PanelSup = new JPanel();
		PanelSup.setOpaque(false);
		PanelCentral = new JPanel();
		PanelCentral.setOpaque(false);
		vacio1 = new JPanel();
		vacio1.setOpaque(false);
		vacio2 = new JPanel();
		vacio2.setOpaque(false);
		vacio3 = new JPanel();
		vacio3.setOpaque(false);
		PanelSup.setLayout(new GridLayout(1, 3, 100, 100));
		PanelJ1 = new JPanel();
		PanelJ1.setOpaque(false);
		PanelJ1.setLayout(new GridLayout(2, 1));
		PanelJ2 = new JPanel();
		PanelJ2.setOpaque(false);
		PanelJ2.setLayout(new GridLayout(2, 1));
		
		JPB11 = new JProgressBar(0, 100);
		JPB11.setPreferredSize(new Dimension(500, 50));
		JPB21 = new JProgressBar(0, 100);
		JPB12 = new JProgressBar(0, 100);
		JPB12.setPreferredSize(new Dimension(500, 50));
		JPB22 = new JProgressBar(0, 100);
		
		JPB12.setForeground(Color.RED);
		JPB22.setForeground(Color.BLUE);
		JPB11.setBackground(Color.RED);
		JPB11.setForeground(Color.WHITE);
		JPB21.setBackground(Color.BLUE);
		JPB21.setForeground(Color.WHITE);
		
		lTiempo = new JLabel();
		lTiempo.setFont(new Font("Apple Casual", Font.BOLD, 70));
		lTiempo.setForeground(Color.GREEN);
		lTiempo.setHorizontalAlignment(JLabel.CENTER);
		
		JPB11.setValue(100-J1.getVida());
		JPB21.setValue(100-J1.getEnergia());
		JPB12.setValue(J2.getVida());
		JPB22.setValue(J2.getEnergia());
		
		PanelJ1.add(JPB11);
		PanelJ1.add(JPB21);
		PanelJ2.add(JPB12);
		PanelJ2.add(JPB22);
		
		vacio1.add(PanelJ1);
		vacio2.add(lTiempo);
		vacio3.add(PanelJ2);
		
		PanelJ1.setAlignmentX(CENTER_ALIGNMENT);
		vacio2.setAlignmentX(CENTER_ALIGNMENT);
		PanelJ2.setAlignmentX(CENTER_ALIGNMENT);
		
		imagen1 = new JLabelGraficoAjustado("imagenes/donatello/DonatelloQuieto1.png", 250, 400);
		imagen1.setBounds((int) J1.getPosX(), 500, 350, 450);
//		imagen2 = new JLabelGraficoAjustado("imagenes/donatello/DonatelloPuño2.png", 250, 400);
//		imagen2.setBounds(700, 500, 300, 450);
//		imagen2.setHorFlip(true);
		
		
		PanelSup.add(vacio1);
		PanelSup.add(vacio2);
		PanelSup.add(vacio3);

		PanelCentral.setLayout(null);
		PanelCentral.add(imagen1);
		//PanelCentral.add(imagen2);
		
		fondo.add(PanelCentral, BorderLayout.CENTER);
		fondo.add(PanelSup, BorderLayout.NORTH);
		
		
		

		
		loop = Sonido.music("canciones/juego.wav");

		
		Thread cuentaAtras = new Thread() {
			public void run() {
				while(contador>=0) {
					try {
						if(contador == 0) {
							lTiempo.setText("¡Muerte Súbita!");
							JPB12.setValue(JPB12.getValue() - 5);
							JPB11.setValue(JPB11.getValue() + 5);
							repaint();
							Thread.sleep(1000);
						}else {
							contador--;
							lTiempo.setText(String.valueOf(contador));
							repaint();
							Thread.sleep(1000);
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		cuentaAtras.start();
		
		
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent ke) {
				if(ke.getKeyCode() == ke.VK_D) {
					Thread movimientoJ1 = new Thread() {
						public void run() {
							if(!botonPulsado) {
								try {
									for(int i=1; i<5; i++) {
										if(J1.getPosX()>=0 && J1.getPosX()<=1580) {
											imagen1.setImagen("imagenes/"+nombreJ1.toLowerCase()+"/"+nombreJ1+"Quieto"+i+".png");
											J1.MoverseX(20);
											imagen1.setBounds((int)J1.getPosX(), 500, 350, 450);
											imagen1.setHorFlip(false);
											repaint();
											Thread.sleep(50);
										}if(J1.getPosX()<0) {
											imagen1.setImagen("imagenes/"+nombreJ1.toLowerCase()+"/"+nombreJ1+"Quieto"+i+".png");
											int moverse = (int) (0-J1.getPosX());
											J1.MoverseX(moverse);
											imagen1.setBounds((int)J1.getPosX(), 500, 350, 450);
											imagen1.setHorFlip(false);
											repaint();
										}
									}
									botonPulsado=true;
									this.stop();	
								}catch (Exception e) {
									
								}
							}
						}
					}; movimientoJ1.start();
					botonPulsado = false;
					anteriorIzq = false;
				}if(ke.getKeyCode() == ke.VK_A) {
					Thread movimientoJ1I = new Thread() {
						public void run() {
							if(!botonPulsado) {
								try {
									for(int i=1; i<5; i++) {
										if(J1.getPosX()>=0 && J1.getPosX()<=1580) {
											imagen1.setImagen("imagenes/"+nombreJ1.toLowerCase()+"/"+nombreJ1+"Quieto"+i+".png");
											imagen1.setHorFlip(true);
											J1.MoverseX(-20);
											imagen1.setBounds((int)J1.getPosX(), 500, 350, 450);
											repaint();
											Thread.sleep(50);
										}if(J1.getPosX()>1580) {
											imagen1.setImagen("imagenes/"+nombreJ1.toLowerCase()+"/"+nombreJ1+"Quieto"+i+".png");
											int moverse = (int) (1580-J1.getPosX());
											J1.MoverseX(moverse);
											imagen1.setBounds((int)J1.getPosX(), 500, 350, 450);
											imagen1.setHorFlip(false);
											repaint();
										}
									}
									botonPulsado=true;
									this.stop();	
								}catch (Exception e) {
									
								}
							}
						}
					}; movimientoJ1I.start();
					anteriorIzq = true;
					botonPulsado = false;
				}if(ke.getKeyCode() == ke.VK_W) {
					Thread saltoJ1 = new Thread() {
						public void run() {
							if(!botonPulsado) {
								try {
									for(int i=1; i<4; i++) {
										if(J1.getPosX()>=0 && J1.getPosX()<=1580) {
											imagen1.setImagen("imagenes/"+nombreJ1.toLowerCase()+"/"+nombreJ1+"Salto"+i+".png");
											imagen1.setSize(350, 450);
											J1.MoverseY(-40);
											imagen1.setBounds((int)J1.getPosX(),(int) J1.getPosY() + 500 , 350, 450);
											imagen1.setHorFlip(anteriorIzq);
											repaint();
											Thread.sleep(100);
										}
									}for(int i=3; i<5; i++) {
										if(J1.getPosX()>=0 && J1.getPosX()<=1580) {
											imagen1.setImagen("imagenes/"+nombreJ1.toLowerCase()+"/"+nombreJ1+"Salto"+i+".png");
											J1.MoverseY(60);
											imagen1.setBounds((int)J1.getPosX(),(int) J1.getPosY() + 500 , 350, 450);
											imagen1.setHorFlip(anteriorIzq);
											repaint();
											Thread.sleep(100);
											if(i==4) {
												imagen1.setSize(250, 400);
												imagen1.setImagen("imagenes/"+nombreJ1.toLowerCase()+"/"+nombreJ1+"Salto"+i+".png");
												imagen1.setBounds((int)J1.getPosX(),(int) J1.getPosY() + 500 , 350, 450);
												imagen1.setHorFlip(anteriorIzq);
												repaint();
											}
										}
									}
								
									botonPulsado=true;
									this.stop();
								} catch (Exception e) {
									// TODO: handle exception
								}
							}
						}
					};saltoJ1.start();
					botonPulsado = false;
				}if(ke.getKeyCode() == ke.VK_Q) {
					System.out.println("Patada");
				}if(ke.getKeyCode() == ke.VK_E) {
					System.out.println("Puño");
				}if(J2B) {
					if(ke.getKeyCode() == ke.VK_LEFT) {
						
					}if(ke.getKeyCode() == ke.VK_RIGHT) {
						
					}if(ke.getKeyCode() == ke.VK_UP) {
						
					}
				}
				
			}
		});
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				Sonido.stop(loop);
				cuentaAtras.stop();
				
			}
		});
	}

}
