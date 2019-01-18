package pantalla;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;

import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

import com.orsoncharts.plot.PiePlot3D;

import bd.BD;
import personajes.Personajes;
import sonido.Sonido;
import usuario.Usuario;

public class PantallaMarcadores extends JFrame {
	private JPanelBackground fondo;
	private JPanel PanelSup;
	private JTable tMarcadores;
	private ImageIcon MarcadoresI;
	private JLabel MarcadoresL;
	private int numUsuarios;
	private int numFilas;
	private JScrollPane scrollPane;
	private JButton bSalir;
	private ArrayList<String> usuarios;
	private ArrayList<Integer> combates;
	private int ganadas=0;
	private int perdidas=0;

	public PantallaMarcadores() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(1920, 1080);
		setTitle("Pantalla Menú Principal");
		setResizable(false);
		fondo = new JPanelBackground();
		fondo.setBackground("imagenes/fondo1.jpg");
		setUndecorated(true);
		getContentPane().setLayout(new BorderLayout());
		fondo.setLayout(new BorderLayout());
		getContentPane().add(fondo);

		bSalir = new JButton("Salir");
		bSalir.setBackground(Color.BLACK);
		bSalir.setForeground(Color.RED);
		bSalir.setBorder(new LineBorder(Color.RED));
		bSalir.setPreferredSize(new Dimension(100, 50));
		
		Connection con = BD.initBD("BD");
		Statement st = BD.usarBD(con);

		PanelSup = new JPanel();
		PanelSup.setOpaque(false);

		MarcadoresI = new ImageIcon("imagenes/Marc.gif");
		MarcadoresL = new JLabel(MarcadoresI);

		numUsuarios = BD.usuarioContador(st);
		numFilas = numUsuarios*4;
		tMarcadores = new JTable(numFilas, 4);
		
		usuarios = BD.usuarioUsuarios(st);
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( SwingConstants.CENTER );
		for(int i = 0; i<4; i++) {
			tMarcadores.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		}
		
		
		for(int i = 0; i < numUsuarios; i++) {
			for(int j = 0; j < 4; j++) {
				int posicion = i*4 + j;
				tMarcadores.setValueAt(usuarios.get(i), posicion, 0);
				switch (j) {
				case 0:
					tMarcadores.setValueAt("Donatello", posicion, 1);
					if(BD.partidaExisteString(st, usuarios.get(i), "Donatello")) {
						combates = BD.partidaSelectString(st, usuarios.get(i), "Donatello");
						tMarcadores.setValueAt(combates.get(0), posicion, 2);
						tMarcadores.setValueAt(combates.get(1), posicion, 3);
					}else {
						tMarcadores.setValueAt(0, posicion, 2);
						tMarcadores.setValueAt(0, posicion, 3);
					}
					
					break;
				case 1:
					tMarcadores.setValueAt("Raphael", posicion, 1);
					if(BD.partidaExisteString(st, usuarios.get(i), "Raphael")) {
						combates = BD.partidaSelectString(st, usuarios.get(i), "Raphael");
						tMarcadores.setValueAt(combates.get(0), posicion, 2);
						tMarcadores.setValueAt(combates.get(1), posicion, 3);
					}else {
						tMarcadores.setValueAt(0, posicion, 2);
						tMarcadores.setValueAt(0, posicion, 3);
					}
					break;
				case 2:
					tMarcadores.setValueAt("Leonardo", posicion, 1);
					if(BD.partidaExisteString(st, usuarios.get(i), "Leonardo")) {
						combates = BD.partidaSelectString(st, usuarios.get(i), "Leonardo");
						tMarcadores.setValueAt(combates.get(0), posicion, 2);
						tMarcadores.setValueAt(combates.get(1), posicion, 3);
					}else {
						tMarcadores.setValueAt(0, posicion, 2);
						tMarcadores.setValueAt(0, posicion, 3);
					}
					break;
				case 3:
					tMarcadores.setValueAt("Mike", posicion, 1);
					if(BD.partidaExisteString(st, usuarios.get(i), "Mike")) {
						combates = BD.partidaSelectString(st, usuarios.get(i), "Mike");
						tMarcadores.setValueAt(combates.get(0), posicion, 2);
						tMarcadores.setValueAt(combates.get(1), posicion, 3);
					}else {
						tMarcadores.setValueAt(0, posicion, 2);
						tMarcadores.setValueAt(0, posicion, 3);
					}
					break;
				default:
					break;
				}
				
				
			}
		}
		
		tMarcadores.setFont(new Font("Apple Casual",Font.CENTER_BASELINE, 40));
		tMarcadores.setRowHeight(45);
		
		scrollPane = new JScrollPane(tMarcadores, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		
		tMarcadores.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
					JTable table = (JTable) e.getSource();
	                int row = table.rowAtPoint(e.getPoint());
	                TableModel model = table.getModel();
	                botonGrafico((int) model.getValueAt(row, 2), (int) model.getValueAt(row, 3));
			}
		});
		
		
		PanelSup.add(MarcadoresL);

		fondo.add(PanelSup, BorderLayout.NORTH);
		fondo.add(scrollPane, BorderLayout.CENTER);
		fondo.add(bSalir, BorderLayout.SOUTH);
		
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				BD.cerrarBD(con, st);
				
			}

		});
		
		bSalir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				
			}
		});
	}
	
	public void botonGrafico(int ganadas, int perdidas) {
		DefaultPieDataset pieDataset = new DefaultPieDataset();
		pieDataset.setValue("Ganadas", ganadas);
		pieDataset.setValue("Perdidas", perdidas);
		JFreeChart chart = ChartFactory.createPieChart("Grafico", pieDataset, true, true, true);
		PiePlot p = (PiePlot) chart.getPlot();
		ChartFrame frame = new ChartFrame("Frame Grafico", chart);
		frame.setVisible(true);
		frame.setSize(950, 950);
		frame.setLocationRelativeTo(null);
		
	}
}
