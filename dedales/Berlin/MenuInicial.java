import java.io.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class MenuInicial extends JFrame implements MouseListener {
	JLabel personaje, dialogo, texto, cofre, jugar, fondo, ayuda, nubes, hojas, diamantes, cantidadDiamantes;
	ImageIcon imageIcon, imaDialogo, imaTexto, imaCofre, imaJugar, imaFondo, imaAyuda, imaNubes, imaHojas, imaDiamantes;
	int numPersonaje, numDiamantes;
	public MenuInicial(int numDiamantes) {
		super("Berlin");
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setSize(600, 500);
		this.numDiamantes = numDiamantes;
		
		numPersonaje = 0;
		personaje = new JLabel();
		imageIcon = new ImageIcon("personaje1.gif");
		imageIcon = new ImageIcon(imageIcon.getImage().getScaledInstance(getWidth(), getHeight()-70, Image.SCALE_DEFAULT));
		personaje.setIcon(imageIcon);
		imageIcon.setImageObserver(personaje);
		personaje.setBounds(0,50,getWidth(),getHeight()-70);
		personaje.addMouseListener(this);
		
		dialogo = new JLabel();
		imaDialogo = new ImageIcon("dialogo.png");
		imaDialogo = new ImageIcon(imaDialogo.getImage().getScaledInstance(150, 120, Image.SCALE_DEFAULT));
		dialogo.setIcon(imaDialogo);
		dialogo.setBounds(420,5,150,120);
		
		texto = new JLabel();
		imaTexto = new ImageIcon("Willkommen.png");
		imaTexto = new ImageIcon(imaTexto.getImage().getScaledInstance(120, 70, Image.SCALE_DEFAULT));
		texto.setIcon(imaTexto);
		texto.setBounds(440,-5,120,70);
		
		jugar = new JLabel();
		imaJugar = new ImageIcon("spielt.png");
		imaJugar = new ImageIcon(imaJugar.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
		jugar.setIcon(imaJugar);
		jugar.setBounds(70,10,50,50);
		jugar.addMouseListener(this);
		
		ayuda = new JLabel();
		imaAyuda = new ImageIcon("ayuda.png");
		imaAyuda = new ImageIcon(imaAyuda.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
		ayuda.setIcon(imaAyuda);
		ayuda.setBounds(130,10,50,50);
		ayuda.addMouseListener(this);
		
		cofre = new JLabel();
		imaCofre = new ImageIcon("cofre.png");
		imaCofre = new ImageIcon(imaCofre.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
		cofre.setIcon(imaCofre);
		cofre.setBounds(10,10,50,50);
		cofre.addMouseListener(this);
		
		fondo = new JLabel();
		imaFondo = new ImageIcon("fondo.png");
		imaFondo = new ImageIcon(imaFondo.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_DEFAULT));
		fondo.setIcon(imaFondo);
		fondo.setBounds(10,10,getWidth(),getHeight());
		
		nubes = new JLabel();
		imaNubes = new ImageIcon("nubes.gif");
		imaNubes = new ImageIcon(imaNubes.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_DEFAULT));
		nubes.setIcon(imaNubes);
		nubes.setBounds(0,0,getWidth(),120);
		
		hojas = new JLabel();
		imaHojas = new ImageIcon("deutschland.gif");
		imaHojas = new ImageIcon(imaHojas.getImage().getScaledInstance(getWidth()+100, 20, Image.SCALE_DEFAULT));
		hojas.setIcon(imaHojas);
		hojas.setBounds(-50,340,getWidth()+100,20);
		
		diamantes = new JLabel();
		imaDiamantes = new ImageIcon("berlin.png");
		imaDiamantes = new ImageIcon(imaDiamantes.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
		diamantes.setIcon(imaDiamantes);
		diamantes.setBounds(190,10,50,50);
		cantidadDiamantes = new JLabel("X "+numDiamantes);
		cantidadDiamantes.setBounds(250, 10, 200, 50);
		
		add(jugar);
		add(cofre);
		add(ayuda);
		add(diamantes);
		add(cantidadDiamantes);
		add(texto);
		add(dialogo);
		add(personaje);
		add(hojas);
		add(nubes);
		add(fondo);
		
		setVisible(true);
	}
	public void aumentarDiamantes(int nuevos) {
		numDiamantes+=nuevos;
		cantidadDiamantes.setText("X "+numDiamantes);
		try {
			FileWriter f = new FileWriter("TotalDiamantes.txt");
			BufferedWriter b = new BufferedWriter(f);
			b.write(numDiamantes+"");
			b.close();
		} catch(Exception e) {e.printStackTrace();}
	}
	public void mouseClicked(MouseEvent e) {
		JLabel jl = (JLabel)e.getSource();
		if(jl.equals(jugar)) {
			new VentanaPrincipal(this.getLocation().x, this.getLocation().y, numPersonaje, this);
			this.setVisible(false);
		} else if(jl.equals(ayuda)) {
			new Instructivo(this.getLocation().x, this.getLocation().y, this);
			this.setVisible(false);
		} else if(jl.equals(cofre)) {
			new Baul(this.getLocation().x, this.getLocation().y, this);
			this.setVisible(false);
		} else if(jl.equals(personaje)) {
			numPersonaje = (numPersonaje+1)%5;
			imageIcon = new ImageIcon("personaje"+(numPersonaje+1)+".gif");
			imageIcon = new ImageIcon(imageIcon.getImage().getScaledInstance(getWidth(), getHeight()-70, Image.SCALE_DEFAULT));
			personaje.setIcon(imageIcon);			
		}
	}
	public void mouseEntered(MouseEvent e) {
		JLabel jl = (JLabel)e.getSource();
		if(jl.equals(jugar)) {
			imaJugar = new ImageIcon(imaJugar.getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT));
			jugar.setIcon(imaJugar);
			jugar.setBounds(65,5,60,60);
		} else if(jl.equals(ayuda)) {
			imaAyuda = new ImageIcon(imaAyuda.getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT));
			ayuda.setIcon(imaAyuda);
			ayuda.setBounds(125,5,60,60);
		} else if(jl.equals(cofre)) {
			imaCofre = new ImageIcon(imaCofre.getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT));
			cofre.setIcon(imaCofre);
			cofre.setBounds(5,5,60,60);
		}
	}
	public void mouseExited(MouseEvent e) {
		JLabel jl = (JLabel)e.getSource();
		if(jl.equals(jugar)) {
			imaJugar = new ImageIcon(imaJugar.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
			jugar.setIcon(imaJugar);
			jugar.setBounds(70,10,50,50);
		} else if(jl.equals(ayuda)) {
			imaAyuda = new ImageIcon(imaAyuda.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
			ayuda.setIcon(imaAyuda);
			ayuda.setBounds(130,10,50,50);
		} else if(jl.equals(cofre)) {
			imaCofre = new ImageIcon(imaCofre.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
			cofre.setIcon(imaCofre);
			cofre.setBounds(10,10,50,50);
		}
	}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
}
