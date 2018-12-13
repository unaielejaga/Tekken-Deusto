package pantalla;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;

/** Clase mejorada de JLabel para gestionar im�genes ajustadas al JLabel
 */
public class JLabelGraficoAjustado extends JLabel {
	// la posici�n X,Y se hereda de JLabel
	protected int anchuraObjeto;   // Anchura definida del objeto en pixels
	protected int alturaObjeto;    // Altura definida del objeto en pixels
	protected double zoom;         // Zoom del objeto en %  (1.0 = 100%)
	protected double radsRotacion; // Rotaci�n del objeto en radianes
	protected float opacidad;      // Opacidad del objeto (0.0f a 0.1f)
	protected BufferedImage imagenObjeto;  // imagen para el escalado
	private static final long serialVersionUID = 1L;  // para serializar
	private boolean horFlip = false;   // Flip horizontal
	private boolean vertFlip = false;  // Flip vertical
	
	public boolean isHorFlip() {
		return horFlip;
	}

	public void setHorFlip(boolean horFlip) {
		this.horFlip = horFlip;
	}

	public boolean isVertFlip() {
		return vertFlip;
	}

	public void setVertFlip(boolean vertFlip) {
		this.vertFlip = vertFlip;
	}

	/** Crea un nuevo JLabel gr�fico.<br>
	 * Si no existe el fichero de imagen, se crea un rect�ngulo blanco con borde rojo
	 * @param nombreImagenObjeto	Nombre fichero donde est� la imagen del objeto. Puede ser tambi�n un nombre de recurso desde el paquete de esta clase.
	 * @param anchura	Anchura del gr�fico en p�xels (si es <= 0 ocupa todo el ancho)
	 * @param altura	Altura del gr�fico en p�xels (si es <= 0 ocupa todo el alto)
	 */
	public JLabelGraficoAjustado( String nombreImagenObjeto, int anchura, int altura ) {
		zoom = 1.0;
		setName( nombreImagenObjeto );
		opacidad = 1.0f;
		setImagen( nombreImagenObjeto ); // Cargamos el icono
		setSize( anchura, altura );
	}
	
	@Override
	public void setSize(int anchura, int altura) {
        if (anchura <= 0 && imagenObjeto!=null) anchura = imagenObjeto.getWidth();
        if (altura <= 0 && imagenObjeto!=null) altura = imagenObjeto.getHeight();
		anchuraObjeto = anchura;
		alturaObjeto = altura;
    	super.setSize( anchura, altura );
    	setPreferredSize( new Dimension( anchura, altura ));
	}
	
		Border bordeError = BorderFactory.createLineBorder( Color.red );
	/** Cambia la imagen del objeto
	 * @param nomImagenObjeto	Nombre fichero donde est� la imagen del objeto. Puede ser tambi�n un nombre de recurso desde el paquete de esta clase.
	 */
	public void setImagen( String nomImagenObjeto ) {
		File f = new File(nomImagenObjeto);
        URL imgURL = null;
        try {
        	imgURL = f.toURI().toURL();
    		if (!f.exists()) {
    			imgURL = JLabelGraficoAjustado.class.getResource( nomImagenObjeto ).toURI().toURL();
    		}
        } catch (Exception e) {}  // Cualquier error de carga, la imagen se queda nula
        if (imgURL == null) {
        	imagenObjeto = null;
        } else {
        	try {  // guarda la imagen para dibujarla de forma escalada despu�s
    			imagenObjeto = ImageIO.read(imgURL);
    		} catch (IOException e) {}  // Error al leer la imagen
        }
        if (imagenObjeto==null) {
			setOpaque( true );
			setBackground( Color.orange );
			setForeground( Color.blue );
	    	setBorder( bordeError );
	    	setText( nomImagenObjeto );
        } else {
        	if (getBorder()==bordeError) setBorder( null );
        	setOpaque( false );
        	setText( "" );
        }
        repaint();
	}
	
	/** Cambia la imagen del objeto
	 * @param urlImagenObjeto	URL de recurso o fichero donde est� la imagen del objeto.
	 */
	public void setImagen( URL urlImagenObjeto ) {
		imagenObjeto = null;
    	try {  // guarda la imagen para dibujarla de forma escalada despu�s
			imagenObjeto = ImageIO.read( urlImagenObjeto );
		} catch (IOException e) {}  // Error al leer la imagen - se queda a null
        if (imagenObjeto==null) {
			setOpaque( true );
			setBackground( Color.red );
			setForeground( Color.blue );
	    	setBorder( bordeError );
	    	setText( "" + urlImagenObjeto.getFile() );
        } else {
        	if (getBorder()==bordeError) setBorder( null );
        	setOpaque( false );
        	setText( "" );
        }
        repaint();
	}
	
	/** Devuelve la anchura del rect�ngulo gr�fico del objeto
	 * @return	Anchura
	 */
	public int getAnchuraObjeto() {
		return anchuraObjeto;
	}
	
	/** Devuelve la altura del rect�ngulo gr�fico del objeto
	 * @return	Altura
	 */
	public int getAlturaObjeto() {
		return alturaObjeto;
	}
	
	/** Devuelve la rotaci�n del objeto
	 * @return	Rotaci�n actual del objeto en radianes
	 */
	public double getRotacion() {
		return radsRotacion;
	}

	/** Modifica la rotaci�n del objeto
	 * @param rotacion	Nueva rotaci�n del objeto (en radianes)
	 */
	public void setRotacion( double rotacion ) {
		radsRotacion = rotacion;
		repaint(); // Si no repintamos aqu� Swing no sabe que ha cambiado el dibujo
	}
	
	/** Devuelve el zoom del objeto
	 * @return	Zoom actual del objeto en % (1.0 = 100%)
	 */
	public double getZoom() {
		return zoom;
	}

	/** Modifica el zoom del objeto
	 * @param rotacion	Nuevo zoom del objeto en % (1.0 = 100%)
	 */
	public void setZoom( double zoom ) {
		this.zoom = zoom;
		repaint(); // Si no repintamos aqu� Swing no sabe que ha cambiado el dibujo
	}
	
	/** Devuelve la opacidad del objeto
	 * @return	Opacidad del objeto (0.0f transparente a 1.0f opaco)
	 */
	public float getOpacidad() {
		return opacidad;
	}

	/** Modifica la opacidad del objeto
	 * @param opacidad	Opacidad del objeto (0.0f transparente a 1.0f opaco)
	 */
	public void setOpacidad(float opacidad) {
		if (opacidad<0.0f || opacidad>1.0f) return; // No se cambia si el valor es inv�lido
		this.opacidad = opacidad;
		repaint(); // Si no repintamos aqu� Swing no sabe que ha cambiado el dibujo
	}

	/** Actualiza la posici�n del objeto
	 * @param x	Coordenada x (doble) - se redondea al p�xel m�s cercano
	 * @param y	Coordenada y (doble) - se redondea al p�xel m�s cercano
	 */
	public void setLocation( double x, double y ) {
		setLocation( (int)Math.round(x), (int)Math.round(y) );
	}
	
	
	/** Provoca que la imagen sea especular
	 * @param horFlip	true para flip horizontal
	 * @param vertFlip	true para flip vertical
	 */
	public void setFlip( boolean horFlip, boolean vertFlip ) {
		this.horFlip = horFlip;
		this.vertFlip = vertFlip;
		repaint();
	}
	
	// Dibuja este componente de una forma no habitual
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (imagenObjeto!=null) {
			Graphics2D g2 = (Graphics2D) g;  // El Graphics realmente es Graphics2D
			g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			g2.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);	
			int anc = anchuraObjeto;
			int alt = alturaObjeto;
			int iniX = 0;
			int iniY = 0;
			if (anc<=0) {
				anc = getWidth();
			} else {
				iniX = (getWidth() - anc) / 2;
			}
			if (alt<=0) {
				alt = getHeight();
			} else {
				iniY = (getHeight() - alt) / 2;
			}
			// Rotaci�n
			g2.rotate( radsRotacion, getWidth()/2, getHeight()/2 );  // Incorporar al gr�fico la rotaci�n definida
			// Transparencia
			g2.setComposite(AlphaComposite.getInstance( AlphaComposite.SRC_OVER, opacidad ) ); // Incorporar la transparencia definida
			// C�lculo de zoom
			int ancZoom = (int) Math.round( anc * zoom );
			int altZoom = (int) Math.round( alt * zoom );
			int iniXZoom = iniX + (anc - ancZoom)/2;
			int iniYZoom = iniY + (alt - altZoom)/2;
			if (horFlip || vertFlip) {
				g2.scale( horFlip?-1:1, vertFlip?-1:1);
			    g2.translate(horFlip?-getWidth():0, vertFlip?-getHeight():0);
			}
		    g2.drawImage(imagenObjeto, iniXZoom, iniYZoom, ancZoom, altZoom, null);
		}
	}

	/** M�todo de prueba de label gr�fico
	 */
	public static void main(String[] args) {
		JFrame f = new JFrame( "Prueba JLabelGraficoAjustado" );
		f.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		JLabelGraficoAjustado label = new JLabelGraficoAjustado( "Melee (4).png", 400, 400 );
		f.setSize( 600, 400 );
		f.add( label, BorderLayout.CENTER );
		f.setVisible( true );
		try { Thread.sleep( 2000 ); } catch (Exception e) {}  // Espera 2 segundos
		label.setFlip( true, false );
		try { Thread.sleep( 2000 ); } catch (Exception e) {}  // Espera 2 segundos
		label.setFlip( false, true );
		try { Thread.sleep( 2000 ); } catch (Exception e) {}  // Espera 2 segundos
		label.setFlip( true, true );
		try { Thread.sleep( 5000 ); } catch (Exception e) {}  // Espera 5 segundos
		for (int rot=0; rot<=200; rot++ ) {
			label.setRotacion( rot*Math.PI/100 );
			try { Thread.sleep( 20 ); } catch (Exception e) {}  // Espera dos d�cimas entre rotaci�n y rotaci�n
		}
		for (int op=-100; op<=100; op++ ) {
			label.setOpacidad( Math.abs(op*0.01f) );
			try { Thread.sleep( 20 ); } catch (Exception e) {}  // Espera dos d�cimas entre cambio y cambio de opacidad
		}
		for (int rot=100; rot>1; rot-- ) {
			label.setZoom( rot/100.0 );
			try { Thread.sleep( 20 ); } catch (Exception e) {}  // Espera dos d�cimas entre zoom y zoom
		}
		for (int rot=1; rot<=100; rot++ ) {
			label.setZoom( rot/100.0 );
			try { Thread.sleep( 20 ); } catch (Exception e) {}  // Espera dos d�cimas entre zoom y zoom
		}
	}
	
}
	
