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
import java.util.ArrayList;

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
import usuario.Usuario;

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
	private boolean botonPulsado2 = true;
	private boolean anteriorIzq2 = false;
	private ArrayList<Integer> combates1;
	private ArrayList<Integer> combates2;
	Thread IA;
	
	public PantallaJuego(String fondoImagen, boolean J2B, String nombreJ1, String nombreJ2, Usuario U1, Usuario U2) {
		
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
		
		imagen1 = new JLabelGraficoAjustado("imagenes/" + nombreJ1.toLowerCase()+ "/"+ nombreJ1 +"Quieto1.png", 250, 400);
		imagen1.setBounds((int) J1.getPosX(), 500, 350, 450);
		
		if(J2B) {
			imagen2 = new JLabelGraficoAjustado("imagenes/"+nombreJ2.toLowerCase()+"/"+nombreJ2+"Quieto1.png", 250, 400);
			imagen2.setBounds(1500, 500, 350, 450);
			J2.setPosX(1500);
			imagen2.setHorFlip(true);
		}else {
			imagen2 = new JLabelGraficoAjustado("imagenes/donatello/DonatelloQuieto1.png", 250, 400);
			imagen2.setBounds(1500, 500, 350, 450);
			J2.setPosX(1500);
			imagen2.setHorFlip(true);
		}
		
		
		
		PanelSup.add(vacio1);
		PanelSup.add(vacio2);
		PanelSup.add(vacio3);

		PanelCentral.setLayout(null);
		PanelCentral.add(imagen1);
		PanelCentral.add(imagen2);
		
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
							muerte(U1, U2, st);
						}else {
							contador--;
							lTiempo.setText(String.valueOf(contador));
							repaint();
							Thread.sleep(1000);
							muerte(U1, U2, st);
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		cuentaAtras.start();
		
		if(!J2B) {
			IA = new Thread() {
				public void run() {
					while(true) {
						try {
							int distancia = (int) (imagen2.getX()-imagen1.getX());
							System.out.println(distancia);
							if(Math.abs(distancia)<300) {
								Thread punyoIA = new Thread() {
									public void run() {
											try {
												for(int i=1; i<6; i++) {
													if(distancia > 0) {
														imagen2.setImagen("imagenes/donatello/DonatelloPuño"+i+".png");
														imagen2.setHorFlip(true);
													}else {
														imagen2.setImagen("imagenes/donatello/DonatelloPuño"+i+".png");
													}
													if(i!=4) {
														imagen2.setSize(200, 400);
													}else {
														imagen2.setSize(350, 400);
															if(JPB22.getValue() == 100) {
																JPB11.setValue(JPB11.getValue() + J2.getDamageB()*3);
																JPB22.setValue(0);
																JPB21.setValue(JPB21.getValue() - 10);
															}else {
																JPB11.setValue(JPB11.getValue() + J2.getDamageB());
																JPB22.setValue(JPB22.getValue() + 5 );
																JPB21.setValue(JPB21.getValue() - 10);
															}	
													}
													imagen2.setBounds((int)J2.getPosX(), 500, 350, 450);
													imagen2.setHorFlip(anteriorIzq2);
													repaint();
													Thread.sleep(1000);
												}
												this.stop();	
											}catch (Exception e) {
												
											}
									}
								}; punyoIA.start();
							}Thread.sleep(1000);
							if(Math.abs(distancia)>300) {
								Thread movimientoIA = new Thread() {
									public void run() {
										while(Math.abs(distancia)>300) {
											try {
												if(distancia > 0) {
												for(int i=1; i<5; i++) {
													if(J2.getPosX()>=0 && J2.getPosX()<=1580) {
														imagen2.setImagen("imagenes/donatello/DonatelloQuieto"+i+".png");
														imagen2.setHorFlip(true);
														J2.MoverseX(-20);
														imagen2.setBounds((int)J2.getPosX(), 500, 350, 450);
														repaint();
														Thread.sleep(1000);
													}if(J2.getPosX()>1580) {
														imagen2.setImagen("imagenes/donatello/DonatelloQuieto"+i+".png");
														int moverse = (int) (1580-J2.getPosX());
														J2.MoverseX(moverse);
														imagen2.setBounds((int)J2.getPosX(), 500, 350, 450);
														imagen2.setHorFlip(false);
														repaint();
													}
												}
												}if(distancia < 0) {
													for(int i=1; i<5; i++) {
														if(J2.getPosX()>=0 && J2.getPosX()<=1580) {
															imagen2.setImagen("imagenes/donatello/DonatelloQuieto"+i+".png");
															imagen2.setHorFlip(true);
															J2.MoverseX(-20);
															imagen2.setBounds((int)J2.getPosX(), 500, 350, 450);
															repaint();
															Thread.sleep(50);
														}if(J2.getPosX()>1580) {
															imagen2.setImagen("imagenes/donatello/DonatelloQuieto"+i+".png");
															int moverse = (int) (1580-J2.getPosX());
															J2.MoverseX(moverse);
															imagen2.setBounds((int)J2.getPosX(), 500, 350, 450);
															imagen2.setHorFlip(false);
															repaint();
														}
													}
												}
											}catch (Exception e) {
												// TODO: handle exception
											}
										}
									}
								};movimientoIA.start();
								
							}Thread.sleep(1000);
							System.out.println(distancia);
						}catch (Exception e) {
							// TODO: handle exception
						}
					}
				}
			};IA.start();
		}
		
		
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent ke) {
				if(ke.getKeyCode() == ke.VK_D) {
					Thread movimientoJ1 = new Thread() {
						public void run() {
							if(!botonPulsado) {
								botonPulsado=true;
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
								botonPulsado=true;
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
								botonPulsado=true;
								try {
									for(int i=1; i<4; i++) {
										if(J1.getPosY()>=-120) {
											imagen1.setImagen("imagenes/"+nombreJ1.toLowerCase()+"/"+nombreJ1+"Salto"+i+".png");
											J1.MoverseY(-40);
											imagen1.setBounds((int)J1.getPosX(),(int) J1.getPosY() + 500 , 350, 450);
											imagen1.setHorFlip(anteriorIzq);
											repaint();
											Thread.sleep(100);
										}
										if(J1.getPosY()<-120) {
											imagen1.setImagen("imagenes/"+nombreJ1.toLowerCase()+"/"+nombreJ1+"Salto"+i+".png");
											int moverse = (int) (-120-J1.getPosY());
											J1.MoverseY(moverse);
											imagen1.setBounds((int)J1.getPosX(), (int) J1.getPosY() + 500, 350, 450);
											imagen1.setHorFlip(anteriorIzq);
											repaint();
										}
									}for(int i=3; i<5; i++) {
										if(J1.getPosY()>=-120) {
											imagen1.setImagen("imagenes/"+nombreJ1.toLowerCase()+"/"+nombreJ1+"Salto"+i+".png");
											J1.MoverseY(60);
											imagen1.setBounds((int)J1.getPosX(),(int) J1.getPosY() + 500 , 350, 450);
											imagen1.setHorFlip(anteriorIzq);
											repaint();
											Thread.sleep(100);
										}
									}
									this.stop();
								} catch (Exception e) {
									// TODO: handle exception
								}
							}
						}
					};saltoJ1.start();
					if(J1.getPosY()==0) {
						botonPulsado = false;
					}
			
				
				}if(ke.getKeyCode() == ke.VK_Q) {
					Thread patadaJ1 = new Thread() {
						public void run() {
							if(!botonPulsado) {
								botonPulsado=true;
								try {
									for(int i=1; i<4; i++) {
										imagen1.setImagen("imagenes/"+nombreJ1.toLowerCase()+"/"+nombreJ1+"Patada"+i+".png");
										if(i!=2) {
											imagen1.setSize(200, 400);
										}else {
											imagen1.setSize(350, 400);
											int distancia = (int) (J1.getPosX()-imagen2.getX());
											if(Math.abs(distancia) < 150) {
												if(JPB21.getValue() == 0) {
													JPB12.setValue(JPB12.getValue() - J1.getDamageP()*3);
													JPB21.setValue(100);
													JPB22.setValue(JPB22.getValue() + 10);
												}else {
													JPB12.setValue(JPB12.getValue() - J1.getDamageP());
													JPB21.setValue(JPB21.getValue() - 5 );
													JPB22.setValue(JPB22.getValue() + 10);
												}
											
											}
										
										}
										imagen1.setBounds((int)J1.getPosX(), 500, 350, 450);
										imagen1.setHorFlip(anteriorIzq);
										repaint();
										Thread.sleep(150);
									}
									this.stop();	
								}catch (Exception e) {
									
								}
							}
						}
					}; patadaJ1.start();
					botonPulsado = false;
					
				}if(ke.getKeyCode() == ke.VK_E) {
					Thread punyoJ1 = new Thread() {
						public void run() {
							if(!botonPulsado) {
								botonPulsado=true;
								try {
									for(int i=1; i<6; i++) {
										imagen1.setImagen("imagenes/"+nombreJ1.toLowerCase()+"/"+nombreJ1+"Puño"+i+".png");
										if(i!=4) {
											imagen1.setSize(200, 400);
										}else {
											imagen1.setSize(350, 400);
											int distancia = (int) (J1.getPosX()-imagen2.getX());
											if(Math.abs(distancia) < 150) {
												if(JPB21.getValue() == 0) {
													JPB12.setValue(JPB12.getValue() - J1.getDamageB()*3);
													JPB21.setValue(100);
													JPB22.setValue(JPB22.getValue() + 10);
												}else {
													JPB12.setValue(JPB12.getValue() - J1.getDamageB());
													JPB21.setValue(JPB21.getValue() - 5 );
													JPB22.setValue(JPB22.getValue() + 10);
												}
											
											}
										}
										imagen1.setBounds((int)J1.getPosX(), 500, 350, 450);
										imagen1.setHorFlip(anteriorIzq);
										repaint();
										Thread.sleep(150);
									}
									this.stop();	
								}catch (Exception e) {
									
								}
							}
						}
					}; punyoJ1.start();
					botonPulsado = false;
				}if(J2B) {
					if(ke.getKeyCode() == ke.VK_L) {
						Thread movimientoJ2 = new Thread() {
							public void run() {
								if(!botonPulsado2) {
									botonPulsado2=true;
									try {
										for(int i=1; i<5; i++) {
											if(J2.getPosX()>=0 && J2.getPosX()<=1580) {
												imagen2.setImagen("imagenes/"+nombreJ2.toLowerCase()+"/"+nombreJ2+"Quieto"+i+".png");
												J2.MoverseX(20);
												imagen2.setBounds((int)J2.getPosX(), 500, 350, 450);
												imagen2.setHorFlip(false);
												repaint();
												Thread.sleep(50);
											}if(J2.getPosX()<0) {
												imagen2.setImagen("imagenes/"+nombreJ1.toLowerCase()+"/"+nombreJ1+"Quieto"+i+".png");
												int moverse = (int) (0-J2.getPosX());
												J2.MoverseX(moverse);
												imagen2.setBounds((int)J2.getPosX(), 500, 350, 450);
												imagen2.setHorFlip(false);
												repaint();
											}
										}
										this.stop();	
									}catch (Exception e) {
										
									}
								}
							}
						}; movimientoJ2.start();
						botonPulsado2 = false;
						anteriorIzq2 = false;
					}if(ke.getKeyCode() == ke.VK_J) {
						Thread movimientoJ2I = new Thread() {
							public void run() {
								if(!botonPulsado2) {
									botonPulsado2=true;
									try {
										for(int i=1; i<5; i++) {
											if(J2.getPosX()>=0 && J2.getPosX()<=1580) {
												imagen2.setImagen("imagenes/"+nombreJ2.toLowerCase()+"/"+nombreJ2+"Quieto"+i+".png");
												imagen2.setHorFlip(true);
												J2.MoverseX(-20);
												imagen2.setBounds((int)J2.getPosX(), 500, 350, 450);
												repaint();
												Thread.sleep(50);
											}if(J2.getPosX()>1580) {
												imagen2.setImagen("imagenes/"+nombreJ2.toLowerCase()+"/"+nombreJ2+"Quieto"+i+".png");
												int moverse = (int) (1580-J2.getPosX());
												J2.MoverseX(moverse);
												imagen2.setBounds((int)J2.getPosX(), 500, 350, 450);
												imagen2.setHorFlip(false);
												repaint();
											}
										}
										this.stop();	
									}catch (Exception e) {
										
									}
								}
							}
						}; movimientoJ2I.start();
						anteriorIzq2 = true;
						botonPulsado2 = false;
						
					}if(ke.getKeyCode() == ke.VK_I) {
						Thread saltoJ2 = new Thread() {
							public void run() {
								if(!botonPulsado2) {
									botonPulsado2=true;
									try {
										for(int i=1; i<4; i++) {
											if(J2.getPosY()>=-120) {
												imagen2.setImagen("imagenes/"+nombreJ2.toLowerCase()+"/"+nombreJ2+"Salto"+i+".png");
												J2.MoverseY(-40);
												imagen2.setBounds((int)J2.getPosX(),(int) J2.getPosY() + 500 , 350, 450);
												imagen2.setHorFlip(anteriorIzq2);
												repaint();
												Thread.sleep(100);
											}
											if(J2.getPosY()<-120) {
												imagen2.setImagen("imagenes/"+nombreJ2.toLowerCase()+"/"+nombreJ2+"Salto"+i+".png");
												int moverse = (int) (-120-J2.getPosY());
												J2.MoverseY(moverse);
												imagen2.setBounds((int)J2.getPosX(), (int) J2.getPosY() + 500, 350, 450);
												imagen2.setHorFlip(anteriorIzq2);
												repaint();
											}
										}for(int i=3; i<5; i++) {
											if(J2.getPosY()>=-120) {
												imagen2.setImagen("imagenes/"+nombreJ2.toLowerCase()+"/"+nombreJ2+"Salto"+i+".png");
												J2.MoverseY(60);
												imagen2.setBounds((int)J2.getPosX(),(int) J2.getPosY() + 500 , 350, 450);
												imagen2.setHorFlip(anteriorIzq2);
												repaint();
												Thread.sleep(100);
											}
										}
										this.stop();
									} catch (Exception e) {
										// TODO: handle exception
									}
								}
							}
						};saltoJ2.start();
						if(J2.getPosY()==0) {
							botonPulsado2 = false;
						}
						
					}if(ke.getKeyCode() == ke.VK_U) {
						Thread patadaJ2 = new Thread() {
							public void run() {
								if(!botonPulsado2) {
									botonPulsado2=true;
									try {
										for(int i=1; i<4; i++) {
											imagen2.setImagen("imagenes/"+nombreJ2.toLowerCase()+"/"+nombreJ2+"Patada"+i+".png");
											if(i!=2) {
												imagen2.setSize(200, 400);
											}else {
												imagen2.setSize(350, 400);
												int distancia = (int) (J2.getPosX()-imagen1.getX());
												if(Math.abs(distancia) < 150) {
													if(JPB22.getValue() == 100) {
														JPB11.setValue(JPB11.getValue() + J2.getDamageP()*3);
														JPB22.setValue(0);
														JPB21.setValue(JPB21.getValue() - 10);
													}else {
														JPB11.setValue(JPB11.getValue() + J2.getDamageP());
														JPB22.setValue(JPB22.getValue() + 5 );
														JPB21.setValue(JPB21.getValue() - 10);
													}
													
												}
											
											}
											imagen2.setBounds((int)J2.getPosX(), 500, 350, 450);
											imagen2.setHorFlip(anteriorIzq2);
											repaint();
											Thread.sleep(150);
										}
										this.stop();	
									}catch (Exception e) {
										
									}
								}
							}
						}; patadaJ2.start();
						botonPulsado2 = false;
						
					}if(ke.getKeyCode() == ke.VK_O) {
						Thread punyoJ2 = new Thread() {
							public void run() {
								if(!botonPulsado2) {
									botonPulsado2=true;
									try {
										for(int i=1; i<6; i++) {
											imagen2.setImagen("imagenes/"+nombreJ2.toLowerCase()+"/"+nombreJ2+"Puño"+i+".png");
											if(i!=4) {
												imagen2.setSize(200, 400);
											}else {
												imagen2.setSize(350, 400);
												int distancia = (int) (J2.getPosX()-imagen1.getX());
												if(Math.abs(distancia) < 150) {
													if(JPB22.getValue() == 100) {
														JPB11.setValue(JPB11.getValue() + J2.getDamageB()*3);
														JPB22.setValue(0);
														JPB21.setValue(JPB21.getValue() - 10);
													}else {
														JPB11.setValue(JPB11.getValue() + J2.getDamageB());
														JPB22.setValue(JPB22.getValue() + 5 );
														JPB21.setValue(JPB21.getValue() - 10);
													}	
												}
											}
											imagen2.setBounds((int)J2.getPosX(), 500, 350, 450);
											imagen2.setHorFlip(anteriorIzq2);
											repaint();
											Thread.sleep(150);
										}
										this.stop();	
									}catch (Exception e) {
										
									}
								}
							}
						}; punyoJ2.start();
						botonPulsado2 = false;
						
					}
				}
				
			}
		});
		
	
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				Sonido.stop(loop);
				cuentaAtras.stop();
				if(!J2B) {
					IA.stop();	
				}
				BD.cerrarBD(con, st);
				
			}
		});
		
	
	}	
	
	public void muerte(Usuario U1, Usuario U2, Statement st) {
		if(JPB12.getValue() == 0) {
			if(BD.partidaExiste(st, U1, J1) && BD.partidaExiste(st, U2, J2)) {
				combates1 = BD.partidaSelect(st, U1, J1);
				combates2 = BD.partidaSelect(st, U2, J2);
				int combG1 = combates1.get(0);
				int combP1 = combates1.get(1);
				int combG2 = combates2.get(0);
				int combP2 = combates2.get(1);
				combG1 = combG1 + 1;
				combP2 = combP2 + 1;
				BD.partidaUpdate(st, combG1, combP1, U1, J1);
				BD.partidaUpdate(st, combG2, combP2, U2, J2);
				dispose();
			}if(BD.partidaExiste(st, U1, J1) && !BD.partidaExiste(st, U2, J2)) {
				combates1 = BD.partidaSelect(st, U1, J1);
				int combG1 = combates1.get(0);
				int combP1 = combates1.get(1);
				combG1 = combG1 + 1;
				BD.partidaUpdate(st, combG1, combP1, U1, J1);
				BD.partidaInsert(st, U2, J2, 0, 1);
				dispose();
			}if(BD.partidaExiste(st, U2, J2) && !BD.partidaExiste(st, U1, J1)) {
				combates2 = BD.partidaSelect(st, U2, J2);
				int combG2 = combates2.get(0);
				int combP2 = combates2.get(1);
				combP2 = combP2 + 1;
				BD.partidaUpdate(st, combG2, combP2, U2, J2);
				BD.partidaInsert(st, U1, J1, 1, 0);
				dispose();
			}if(!BD.partidaExiste(st, U2, J2) && !BD.partidaExiste(st, U1, J1)) {
				System.out.println(!BD.partidaExiste(st, U2, J2));
				BD.partidaInsert(st, U1, J1, 1, 0);
				BD.partidaInsert(st, U2, J2, 0, 1);
				dispose();
			}
		}if(JPB11.getValue() == 100) {
			if(BD.partidaExiste(st, U1, J1) && BD.partidaExiste(st, U2, J2)) {
				combates1 = BD.partidaSelect(st, U1, J1);
				combates2 = BD.partidaSelect(st, U2, J2);
				int combG1 = combates1.get(0);
				int combP1 = combates1.get(1);
				int combG2 = combates2.get(0);
				int combP2 = combates2.get(1);
				combG2 = combG2 + 1;
				combP1 = combP1 + 1;
				BD.partidaUpdate(st, combG1, combP1, U1, J1);
				BD.partidaUpdate(st, combG2, combP2, U2, J2);
				dispose();
			}if(BD.partidaExiste(st, U1, J1) && !BD.partidaExiste(st, U2, J2)) {
				combates1 = BD.partidaSelect(st, U1, J1);
				int combG1 = combates1.get(0);
				int combP1 = combates1.get(1);
				combP1 = combP1 + 1;
				BD.partidaUpdate(st, combG1, combP1, U1, J1);
				BD.partidaInsert(st, U2, J2, 1, 0);
				dispose();
			}if(BD.partidaExiste(st, U2, J2) && !BD.partidaExiste(st, U1, J1)) {
				combates2 = BD.partidaSelect(st, U2, J2);
				int combG2 = combates2.get(0);
				int combP2 = combates2.get(1);
				combG2 = combG2 + 1;
				BD.partidaUpdate(st, combG2, combP2, U2, J2);
				BD.partidaInsert(st, U1, J1, 0, 1);
				dispose();
			}else {
				BD.partidaInsert(st, U1, J1, 0, 1);
				BD.partidaInsert(st, U2, J2, 1, 0);
				dispose();
			}
		}
	}
}
