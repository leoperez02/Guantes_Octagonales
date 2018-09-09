import java.io.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Baul extends JFrame implements MouseListener {
	int pagActual;
	int totPages;
	MenuInicial mi;
	JLabel paginaInicio;
	JLabel visualizador;
	JLabel fondoBoolean;
	JLabel fondoPergamino;
	JLabel sigui, antes;
	JLabel costoDiamante;
	JLabel diamanteContain;
	JLabel diamantes;
	JLabel cantidadDiamantes;
	JLabel kaufen;
	ImageIcon[] contenido;
	ImageIcon isigu, iante;
	ImageIcon iPaginaInicio;
	ImageIcon imaBooleanS;
	ImageIcon imaBooleanN;
	ImageIcon imaPergamino;
	ImageIcon diamanteChil;
	ImageIcon imaDiamantes;
	ImageIcon imaKaufen;
	boolean[] bloqueada;
	int[] costos;

	public Baul(int xP, int yP, MenuInicial mi) {
		super("Berlin");
		this.mi = mi;
		pagActual = 0;
		totPages = 6;
		setSize(600,500);
		setLocation(xP, yP);
		setResizable(false);
		sigui = new JLabel(); 
		antes = new JLabel();
		visualizador = new JLabel();
		paginaInicio = new JLabel();
		fondoBoolean = new JLabel();
		fondoPergamino = new JLabel();
		costoDiamante = new JLabel();
		diamanteContain = new JLabel();
		kaufen = new JLabel();
		
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		contenido = new ImageIcon[totPages+1];
		bloqueada = new boolean[totPages];
		
		isigu = new ImageIcon("adelante.png");
		iante = new ImageIcon("atras.png");
		iPaginaInicio = new ImageIcon("home.png");
		imaPergamino = new ImageIcon("pergamino.png");
		imaBooleanS = new ImageIcon("fondoVerde.png");
		imaBooleanN = new ImageIcon("fondoRojo.png");
		diamanteChil = new ImageIcon("berlin.png");
		imaKaufen = new ImageIcon("jetzt-kaufen-rot.png");
		
		isigu = new ImageIcon(isigu.getImage().getScaledInstance(50,50,Image.SCALE_DEFAULT));
		iante = new ImageIcon(iante.getImage().getScaledInstance(50,50,Image.SCALE_DEFAULT));
		iPaginaInicio = new ImageIcon(iPaginaInicio.getImage().getScaledInstance(50,50,Image.SCALE_DEFAULT));
		imaPergamino = new ImageIcon(imaPergamino.getImage().getScaledInstance(getWidth()-50, getHeight()-50, Image.SCALE_DEFAULT));
		imaBooleanS = new ImageIcon(imaBooleanS.getImage().getScaledInstance(getWidth(),getHeight(),Image.SCALE_DEFAULT));
		imaBooleanN = new ImageIcon(imaBooleanN.getImage().getScaledInstance(getWidth(),getHeight(),Image.SCALE_DEFAULT));
		diamanteChil = new ImageIcon(diamanteChil.getImage().getScaledInstance(60,60, Image.SCALE_DEFAULT));
		imaKaufen = new ImageIcon(imaKaufen.getImage().getScaledInstance(100, 50, Image.SCALE_DEFAULT));
		
		int y = (getHeight()-90);
		antes.setBounds(50, y, 50, 50);
		sigui.setBounds(getWidth()-100, y, 50, 50);
		paginaInicio.setBounds((getWidth()-70)/2, y, 50, 50);
		fondoPergamino.setBounds(25,25,getWidth()-50, getHeight()-90);
		diamanteContain.setBounds((getWidth()-80)/2, (getHeight()-60)/2 + 30, 60, 60);
		costoDiamante.setBounds((getWidth()-55)/2, (getHeight()-60)/2 + 15, 60, 20);
		kaufen.setBounds(410, 80, 100, 50);
		
		sigui.setIcon(isigu);
		antes.setIcon(iante);
		paginaInicio.setIcon(iPaginaInicio);
		fondoPergamino.setIcon(imaPergamino);
		diamanteContain.setIcon(diamanteChil);
		kaufen.setIcon(imaKaufen);
		
		costos = new int[totPages];
		try {
			String cadena;
			char prim;
			FileReader f = new FileReader("Candados.txt");
			BufferedReader b = new BufferedReader(f);
			for(int i=0; i<totPages+1; i++) {
				if((cadena = b.readLine())!=null) {
					costos[i] = Integer.parseInt(cadena.substring(1));
					prim = cadena.charAt(0);
					if(prim == '0') { //Desbloqueada
						bloqueada[i] = false;
					} else if(prim == '1') { //Bloqueada
						bloqueada[i] = true;
					}
				}
				contenido[i] = new ImageIcon("TarjetasHistoricas/Tarjeta"+i+".png");
				contenido[i] = new ImageIcon(contenido[i].getImage().getScaledInstance(getWidth()-140,getHeight()-160,Image.SCALE_DEFAULT));
			}
			b.close();
		} catch(Exception e) {e.printStackTrace();}
		
		visualizador.setBounds(70,-20,getWidth(),getHeight());
		if(bloqueada[pagActual]==false) {
			fondoBoolean.setIcon(imaBooleanS);
			kaufen.setVisible(false);
			costoDiamante.setVisible(false);
			diamanteContain.setVisible(false);
			visualizador.setIcon(contenido[pagActual+1]);
		} else {
			kaufen.setVisible(true);
			costoDiamante.setVisible(true);
			diamanteContain.setVisible(true);
			costoDiamante.setText(" "+costos[pagActual]);
			fondoBoolean.setIcon(imaBooleanN);
			visualizador.setIcon(contenido[0]);
		}
		
		diamantes = new JLabel();
		imaDiamantes = new ImageIcon("berlin.png");
		imaDiamantes = new ImageIcon(imaDiamantes.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
		diamantes.setIcon(imaDiamantes);
		diamantes.setBounds(110,5,50,50);
		cantidadDiamantes = new JLabel("X "+mi.numDiamantes);
		cantidadDiamantes.setBounds(170, 15, 200, 50);
		
		antes.setVisible(false);
		antes.addMouseListener(this);
		sigui.addMouseListener(this);
		paginaInicio.addMouseListener(this);
		kaufen.addMouseListener(this);
		
		add(antes);
		add(sigui);
		add(paginaInicio);
		add(kaufen);
		add(diamantes);
		add(cantidadDiamantes);
		add(costoDiamante);
		add(diamanteContain);
		add(visualizador);
		add(fondoPergamino);
		add(fondoBoolean);
		setVisible(true);
	}
	
	public void mouseClicked(MouseEvent e) {
		JLabel jl = (JLabel)e.getSource();
		if(jl.equals(antes)) {
			if(pagActual>0) { pagActual--; sigui.setVisible(true); }
			if(pagActual==0) { antes.setVisible(false); }
			if(bloqueada[pagActual]==false) {
				fondoBoolean.setIcon(imaBooleanS);
				kaufen.setVisible(false);
				costoDiamante.setVisible(false);
				diamanteContain.setVisible(false);
				visualizador.setIcon(contenido[pagActual+1]);
			} else {
				kaufen.setVisible(true);
				costoDiamante.setVisible(true);
				diamanteContain.setVisible(true);
				costoDiamante.setText(" "+costos[pagActual]);
				fondoBoolean.setIcon(imaBooleanN);
				visualizador.setIcon(contenido[0]);
			}
		} else if(jl.equals(sigui)) {
			if(pagActual<(totPages-1)) { pagActual++; antes.setVisible(true); }
			if(pagActual==totPages-1) { sigui.setVisible(false); }
			if(bloqueada[pagActual]==false) {
				fondoBoolean.setIcon(imaBooleanS);
				kaufen.setVisible(false);
				costoDiamante.setVisible(false);
				diamanteContain.setVisible(false);
				visualizador.setIcon(contenido[pagActual+1]);
			} else {
				kaufen.setVisible(true);
				costoDiamante.setVisible(true);
				diamanteContain.setVisible(true);
				costoDiamante.setText(" "+costos[pagActual]);
				fondoBoolean.setIcon(imaBooleanN);
				visualizador.setIcon(contenido[0]);
			}
		} else if(jl.equals(paginaInicio)) {
			mi.setVisible(true);
			this.setVisible(false);
		} else if(jl.equals(kaufen)) {
			if(mi.numDiamantes>=costos[pagActual]){
				bloqueada[pagActual] = false;
				fondoBoolean.setIcon(imaBooleanS);
				kaufen.setVisible(false);
				costoDiamante.setVisible(false);
				diamanteContain.setVisible(false);
				visualizador.setIcon(contenido[pagActual+1]);
				mi.aumentarDiamantes(-1*costos[pagActual]);
				cantidadDiamantes.setText("X "+mi.numDiamantes);
			} else {
				JOptionPane.showMessageDialog(this, "Te faltan: "+(costos[pagActual]-mi.numDiamantes)+" escudos", "Tut mir Leid!", 0);
			}
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
			paginaInicio.setBounds((getWidth()-75)/2, y, 60, 60);
		} else if(jl.equals(kaufen)) {
			imaKaufen = new ImageIcon(imaKaufen.getImage().getScaledInstance(120, 60, Image.SCALE_DEFAULT));
			kaufen.setIcon(imaKaufen);
			kaufen.setBounds(400,75,120,60);
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
			paginaInicio.setBounds((getWidth()-70)/2, y, 50, 50);
		} else if(jl.equals(kaufen)) {
			imaKaufen = new ImageIcon(imaKaufen.getImage().getScaledInstance(100, 50, Image.SCALE_DEFAULT));
			kaufen.setIcon(imaKaufen);
			kaufen.setBounds(410, 80, 100, 50);
		}
	}
	public void mouseReleased(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
}