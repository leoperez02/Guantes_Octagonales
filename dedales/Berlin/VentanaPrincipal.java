import java.awt.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.*;

public class VentanaPrincipal extends JFrame implements Runnable, KeyListener {
	JLabel personaje, fondo, bandera, diamantes, cantidadDiamantes;
	JLabel[][] carretera;
	JLabel[] vidas;
	JLabel[] vidasHuecos;
	ArrayList<JLabel> checada;
	ImageIcon imageIcon, imaFondo, imaBandera, imaDiamantes;
	ImageIcon[] imaCarr;
	ImageIcon imaVidas;
	ImageIcon imaVidasHuecos;
	Thread hilo;
	MenuInicial mi;
	int numPersonaje;
	int banderasRecogidas;
	int numVidas;
	int numDiamantes;
	int posicion;
	int avances;
	int tiempo;
	public VentanaPrincipal(int xP, int yP, int numPersonaje, MenuInicial mi) {
		super("Berlin");
		setSize(600, 500);
		setResizable(true);
		addKeyListener(this);
		setLayout(null);
		this.numPersonaje = numPersonaje;
		this.mi = mi;
		banderasRecogidas = 0;
		numVidas = 5;
		numDiamantes = 0;
		posicion = 0;
		tiempo = 10;
		avances = 5;
		
		setLocation(xP, yP);
		checada = new ArrayList<JLabel>();
		personaje = new JLabel();
		carretera = new JLabel[8][5];
		vidas = new JLabel[5];
		vidasHuecos = new JLabel[5];
		imaCarr = new ImageIcon[6];
		imaVidas = new ImageIcon("vida1.png");
		imaVidasHuecos = new ImageIcon("vida.png");
		imaVidas = new ImageIcon(imaVidas.getImage().getScaledInstance(36, 40, Image.SCALE_DEFAULT));
		imaVidasHuecos = new ImageIcon(imaVidasHuecos.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
			
		fondo = new JLabel();
		imaFondo = new ImageIcon("fondo1.png");
		imaFondo = new ImageIcon(imaFondo.getImage().getScaledInstance(getWidth(), 60, Image.SCALE_DEFAULT));
		fondo.setIcon(imaFondo);
		fondo.setBounds(-5,0,getWidth(),60);
		
		bandera = new JLabel();
		imaBandera = new ImageIcon("deutschland.gif");
		imaBandera = new ImageIcon(imaBandera.getImage().getScaledInstance(200, 40, Image.SCALE_DEFAULT));
		bandera.setIcon(imaBandera);
		bandera.setBounds(10,10,0,40);
		
		diamantes = new JLabel();
		imaDiamantes = new ImageIcon("berlin.png");
		imaDiamantes = new ImageIcon(imaDiamantes.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
		diamantes.setIcon(imaDiamantes);
		diamantes.setBounds(220,10,40,40);
		
		cantidadDiamantes = new JLabel("X "+numDiamantes);
		cantidadDiamantes.setBounds(260, 15, 40, 40);
		
		add(personaje);
		for(int j = 0; j < vidas.length; j++) {
			vidas[j] = new JLabel();
			vidasHuecos[j] = new JLabel();
			vidas[j].setIcon(imaVidas);
			vidasHuecos[j].setIcon(imaVidasHuecos);
			vidas[j].setBounds(getWidth()-58-(j*50), 10, 40, 40);
			vidasHuecos[j].setBounds(getWidth()-60-(j*50), 10, 40, 40);
			add(vidasHuecos[j]);
			add(vidas[j]);
		}
		add(cantidadDiamantes);
		add(diamantes);
		add(bandera);
		add(fondo);
		escalar();
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		setVisible(true);
		hilo = new Thread(this);
		hilo.start();
	}
	public void escalar() {
		int eX = getWidth()/6;
		int eY = (getHeight()-90)/5;
		imageIcon = new ImageIcon("personaje"+(numPersonaje+1)+".gif");
		imageIcon = new ImageIcon(imageIcon.getImage().getScaledInstance(eX, eY, Image.SCALE_DEFAULT));
		personaje.setIcon(imageIcon);
		personaje.setBounds(10,25,eX,eY);
		for(int j=0; j<6; j++) {
			imaCarr[j] = new ImageIcon("elemento"+j+".png");
			imaCarr[j] = new ImageIcon(imaCarr[j].getImage().getScaledInstance(eX, eY, Image.SCALE_DEFAULT));	
		}
		for(int j=0; j<5; j++) {
			for(int i=0; i<8; i++) {
				carretera[i][j] = new JLabel();
				carretera[i][j].setIcon(imaCarr[0]);
				carretera[i][j].setBounds((10+i*eX)+(4-j)*(40)-170,(60+j*eY),eX,eY);
				add(carretera[i][j]);
			}
		}
	}
	public void run() {
		while(numVidas>=0) {
			int random = 0;
			for(int j=0; j<5; j++) {
				for(int i=0; i<8; i++) {
					int x = carretera[i][j].getLocation().x;
					int y = carretera[i][j].getLocation().y;
					int a = carretera[i][j].getWidth();
					int xPersonaje = personaje.getLocation().x;
					int w = personaje.getWidth();
					x-=avances;
					if(!checada.contains(carretera[i][j])) {
						if(posicion==j) {
							if(x<(xPersonaje+w) && (x+a)>xPersonaje) {
								ImageIcon ii = (ImageIcon)carretera[i][j].getIcon();
								if(!ii.equals(imaCarr[0])) {
									checada.add(carretera[i][j]);
									if(ii.equals(imaCarr[5])) { //Bandera
										banderasRecogidas++;
										if(banderasRecogidas==5){
											banderasRecogidas=0;
											numVidas=5;
										}
										bandera.setBounds(10,10,banderasRecogidas*40,40);
									} else if(ii.equals(imaCarr[4])) { //Diamante
										numDiamantes++;
										cantidadDiamantes.setText("X "+numDiamantes);
										if(numDiamantes%5==0) {
											tiempo = (int)(Math.random()*8)+5;
											avances = (int)(Math.random()*7)+4;
										}
									} else  { //Obstaculo
										numVidas--;
										if(numVidas<0) {
											JOptionPane.showMessageDialog(this, "Escudos: "+numDiamantes, "Shade!", 0);
											mi.aumentarDiamantes(numDiamantes);
											mi.setVisible(true);
											this.setVisible(false);
											this.dispose();
										}
									}
									carretera[i][j].setIcon(imaCarr[0]);
									for(int m = 0; m < vidas.length; m++) {
										if(m<numVidas) vidas[m].setVisible(true);
										else vidas[m].setVisible(false);
									}
								}
							}
						}
					}
					if(x+a<=0) {
						int r1 = (int)(Math.random() * 3) + 1;
						int r2 = (int)(Math.random() * 6) + 3;
						int r3 = (int)(Math.random() * 9) + 6;
						int r4 = (int)(Math.random() * 12) + 9;
						int r5 = (int)(Math.random() * 15) + 12;
						x = carretera[(i+7)%8][j].getLocation().x + a - 5;
						random = (int)(Math.random() * 100);
						int numX = 0;
						if(random<r1) { numX = 5; }
						else if(random<r2) { numX = 4; }
						else if(random<r3) { numX = 3; }
						else if(random<r4) { numX = 2; }
						else if(random<r5) { numX = 1; }
						carretera[i][j].setIcon(imaCarr[numX]);
						checada.remove(carretera[i][j]);
					}
					carretera[i][j].setLocation(x,y);
				}
			}
			try{
				Thread.sleep(tiempo);
			} catch (Exception e) { System.err.println(e); }
		}
	}
	public void keyPressed(KeyEvent e) {
		int n = e.getKeyCode();
		int x = personaje.getLocation().x;
		int y = personaje.getLocation().y;
		switch(n) {
			case 87: case 65: case 37: case 38: if(y>25) { y-=((getHeight()-90)/5); posicion--; } break;
			case 83: case 68: case 39: case 40: if(y+personaje.getHeight()+90<getHeight()) {y+=((getHeight()-90)/5); posicion++; } break;
		}
		personaje.setLocation(x,y);
	}
	public void keyReleased(KeyEvent e) {}
	public void keyTyped(KeyEvent e) {}
}
