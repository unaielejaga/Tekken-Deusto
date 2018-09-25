package pantalla;

import javax.swing.JFrame;

public class PantallaMenuPrin extends JFrame{
	
	private JPanelBackground fondo;
	
	public PantallaMenuPrin() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(1920, 1080);
		setTitle("Pantalla Menú Principal");
		setResizable(false);
		fondo = new JPanelBackground();
		fondo.setBackground("fondo.jpg");
		getContentPane().add(fondo);
		
		
	}

	public static void main(String[] args) {
		PantallaMenuPrin p = new PantallaMenuPrin();
		p.setVisible(true);
				

	}

}
