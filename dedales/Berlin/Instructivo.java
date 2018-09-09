import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Instructivo extends JFrame implements MouseListener {
	int pagActual;
	MenuInicial mi;
	JLabel paginaInicio;
	JLabel visualizador;
	JLabel sigui, antes;
	ImageIcon[] contenido;
	ImageIcon isigu, iante;
	ImageIcon iPaginaInicio;
	public final int totPages = 6;

	public Instructivo(int xP, int yP, MenuInicial mi) {
		super("Berlin");
		this.mi = mi;
		pagActual = 0;
		setSize(600,500);
		setLocation(xP, yP);
		setResizable(false);
		sigui = new JLabel(); 
		antes = new JLabel();
		paginaInicio = new JLabel();
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		visualizador = new JLabel();
		contenido = new ImageIcon[totPages];
		isigu = new ImageIcon("adelante.png");
		iante = new ImageIcon("atras.png");
		iPaginaInicio = new ImageIcon("home.png");
		isigu = new ImageIcon(isigu.getImage().getScaledInstance(50,50,Image.SCALE_DEFAULT));
		iante = new ImageIcon(iante.getImage().getScaledInstance(50,50,Image.SCALE_DEFAULT));
		iPaginaInicio = new ImageIcon(iPaginaInicio.getImage().getScaledInstance(50,50,Image.SCALE_DEFAULT));
		int y = (getHeight()-90);
		antes.setBounds(50, y, 50, 50);
		sigui.setBounds(getWidth()-100, y, 50, 50);
		paginaInicio.setBounds((getWidth()-50)/2, y, 50, 50);
		sigui.setIcon(isigu);
		antes.setIcon(iante);
		paginaInicio.setIcon(iPaginaInicio);
		for(int i=0; i<totPages; i++) {
			contenido[i] = new ImageIcon("pagina"+(i+1)+".png");
			contenido[i] = new ImageIcon(contenido[i].getImage().getScaledInstance(getWidth(),getHeight(),Image.SCALE_DEFAULT));
		}
		visualizador.setIcon(contenido[pagActual]);
		visualizador.setBounds(0,0,getWidth(),getHeight());
		antes.setVisible(false);
		antes.addMouseListener(this);
		sigui.addMouseListener(this);
		paginaInicio.addMouseListener(this);
		add(antes);
		add(sigui);
		add(paginaInicio);
		add(visualizador);
		setVisible(true);
	}
	
	public void mouseClicked(MouseEvent e) {
		JLabel jl = (JLabel)e.getSource();
		if(jl.equals(antes)) {
			if(pagActual>0) { pagActual--; sigui.setVisible(true); }
			if(pagActual==0) { antes.setVisible(false); }
			visualizador.setIcon(contenido[pagActual]);
		} else if(jl.equals(sigui)) {
			if(pagActual<(totPages-1)) { pagActual++; antes.setVisible(true); }
			if(pagActual==totPages-1) { sigui.setVisible(false); }
			visualizador.setIcon(contenido[pagActual]);
		} else if(jl.equals(paginaInicio)) {
			mi.setVisible(true);
			this.setVisible(false);
		}
	}
	public void mouseEntered(MouseEvent e) {
		JLabel jl = (JLabel)e.getSource();
		int y = (getHeight()-95);
		if(jl.equals(antes)) {
			iante = new ImageIcon(iante.getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT));
			antes.setIcon(iante);
			antes.setBounds(45, y, 60, 60);
		} else if(jl.equals(sigui)) {
			isigu = new ImageIcon(isigu.getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT));
			sigui.setIcon(isigu);
			sigui.setBounds(getWidth()-105, y, 60, 60);
		} else if(jl.equals(paginaInicio)) {
			iPaginaInicio = new ImageIcon(iPaginaInicio.getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT));
			paginaInicio.setIcon(iPaginaInicio);
			paginaInicio.setBounds((getWidth()-50)/2 - 5, y, 60, 60);
		}
	}
	public void mouseExited(MouseEvent e) {
		JLabel jl = (JLabel)e.getSource();
		int y = (getHeight()-90);
		if(jl.equals(antes)) {
			iante = new ImageIcon(iante.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
			antes.setIcon(iante);
			antes.setBounds(50, y, 50, 50);
		} else if(jl.equals(sigui)) {
			isigu = new ImageIcon(isigu.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
			sigui.setIcon(isigu);
			sigui.setBounds(getWidth()-100, y, 50, 50);
		} else if(jl.equals(paginaInicio)) {
			iPaginaInicio = new ImageIcon(iPaginaInicio.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
			paginaInicio.setIcon(iPaginaInicio);
			paginaInicio.setBounds((getWidth()-50)/2, y, 50, 50);
		}
	}
	public void mouseReleased(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
}