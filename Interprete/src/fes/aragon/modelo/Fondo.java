package fes.aragon.modelo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JOptionPane;

import fes.aragon.compilador.Principal;
import fes.aragon.editor.EditFuente;
import fes.aragon.extras.EfectosMusica;
import fes.aragon.extras.MusicaCiclica;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Fondo extends ComponentesJuego {
	private int yy = 0;
	private int xx = 0;
	private int xC=0;
	private int yC=0;
	private Image arribaImg;
	private Image abajoImg;
	private Image derechaImg;
	private Image izquierdaImg;
	private Image imagen;
	private Stage ventana;
	private ArrayList<String> comandos = new ArrayList<>();
	private int ancho = 40;
	private int alto = 40;
	private boolean iniciar = false;
	private GraphicsContext graficos;
	private int indice = 0;
	private int moverCuadros = 0;
	private String comando = "";
	private boolean arriba=false;
	private boolean abajo=false;
	private boolean derecha=false;
	private boolean izquierda=false;
	private boolean ver=false;
	private Image cherry;
	private int cooX= 0;
	private int cooY= 0;
	private int xx2=0;
	private int yy2=0;

	public Fondo(int x, int y, String imagen, int velocidad, Stage ventana) {
		super(x, y, imagen, velocidad);
		this.derechaImg=new Image(imagen);
		this.izquierdaImg=new Image("/fes/aragon/recursos/Cizquierda.png");
		this.arribaImg=new Image("/fes/aragon/recursos/Carriba.png");
		this.abajoImg=new Image("/fes/aragon/recursos/Cabajo.png");
		this.cherry=new Image("/fes/aragon/recursos/cherry.png");
		this.imagen = derechaImg;
		this.ventana = ventana;
		this.cooX = (int) (Math.random()*10);
		this.cooY = (int) (Math.random()*10);
	}

	@Override
	public void pintar(GraphicsContext graficos) {
		// TODO Auto-generated method stub
		this.graficos = graficos;
		int xx = 50;
		int yy = 50;
		for (int j = 1; j <= 10; j++) {
			for (int i = 1; i <= 10; i++) {
				graficos.strokeRect(xx, yy, 50, 50);
				xx += 50;
			}
			xx = 50;
			yy += 50;
		}
		xC=x;
		yC=y;
		graficos.drawImage(imagen, x, y, ancho, alto);
		if (!comandos.isEmpty()) {
			if(comandos.size()>indice) {
				graficos.strokeText(comandos.get(indice), 100, 40);
			}
		}
		xx2=(55+(ancho+10)*cooX);
		yy2=(55+(alto+10)*cooY);
		graficos.drawImage(cherry, xx2, yy2, 40, 40);
	}

	@Override
	public void teclado(KeyEvent evento, boolean presiona) {
		// TODO Auto-generated method stub
		if (presiona) {
			switch (evento.getCode().toString()) {
			case "A":
				EditFuente editor = new EditFuente();
				editor.setVisible(true);
				break;
			case "R":
				try {
					this.abrirArchivo();
					graficos.clearRect(0, 0, 600, 600);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				EfectosMusica arranque = new EfectosMusica("arranque");
				arranque.run();
				iniciar();
				this.ejecutar();
				this.iniciar = true;
				graficos.clearRect(0, 0, 600, 600);
				break;
			}
		}
	}

	@Override
	public void raton(KeyEvent evento) {

	}

	@Override
	public void logicaCalculos() {
		if (iniciar) {
			switch (this.comando) {
			case "arriba":
			case "abajo":
			case "izquierda":
			case "derecha":
				indice++;
				this.ejecutar();
				break;
			case "coloca":			
				if ((x < xx) && (y<554) && (x<554) && (x>9) && (y>9)) {
					x += velocidad;
					if ((x<553)&& (x>10)) {
						this.imagen=this.derechaImg;
						graficos.clearRect(0, 0, 600, 600);
					}else {
						JOptionPane.showMessageDialog(null, "Fuera de rango");
					}
				} else {
					if ((y < yy) && (y<554) && (x<554) && (x>9) && (y>9)) {
						y += velocidad;
						if ((y<=553) && (y>10)) {
							this.imagen=this.abajoImg;
							graficos.clearRect(0, 0, 600, 600);
						}else {
							JOptionPane.showMessageDialog(null, "Fuera de rango");
						}
					}
				}				
				if ((x >= xx) && (y >= yy)) {
					indice++;
					this.ejecutar();
				}
				break;
				
			case "mover":
				if(arriba) {
					if ((y > yy) && (y<554) && (x<554) && (x>9) && (y>9)) {
						y -= velocidad;
						if ((y<=553) && (y>10)) {
							graficos.clearRect(0, 0, 600, 600);
						}else {
							JOptionPane.showMessageDialog(null, "Fuera de rango");
						}
					}else {
						indice++;
						this.ejecutar();
					}
				}
				if(abajo) {
					if ((y < yy) && (y<554) && (x<554) && (x>9) && (y>9)) {
						y += velocidad;
						if ((y<=553) && (y>10)) {
							graficos.clearRect(0, 0, 600, 600);
						}else {
							JOptionPane.showMessageDialog(null, "Fuera de rango");
						}
					}else {
						indice++;
						this.ejecutar();
					}
				}
				if(izquierda) {
					if ((x > xx) && (y<554) && (x<554) && (x>9) && (y>9)) {
						x -= velocidad;
						if ((x<=553) && (x>10)) {
							graficos.clearRect(0, 0, 600, 600);
						}else {
							JOptionPane.showMessageDialog(null, "Fuera de rango");
						}
					}else {
						indice++;
						this.ejecutar();
					}
				}
				if(derecha) {
					if ((x < xx) && (y<554) && (x<554) && (x>9) && (y>9)) {
						x += velocidad;
						if ((x<=553) && (x>10)) {
							graficos.clearRect(0, 0, 600, 600);
						}else {
							JOptionPane.showMessageDialog(null, "Fuera de rango");
						}
					}else {
						indice++;
						this.ejecutar();
					}
				}
				break;
			case "ver":
				if((ver==true)) {					
					this.ejecutar();
					indice++;
					if(comandos.get(indice).equals("{")) {
						while(!comandos.get(indice).equals("}")){
							indice++;
							this.ejecutar();
						};
					}
				}
				break;
			}
		}
	}

	private void ejecutar() {
		if (indice < comandos.size()) {
			String string = comandos.get(indice);
			String[] datos = string.split(" ");
			System.out.println(datos[0]);
			switch (datos[0]) {
			case "arriba":
				this.arriba = true;
				this.abajo = false;
				this.izquierda = false;
				this.derecha = false;
				this.imagen=this.arribaImg;
				this.comando="arriba";
				break;
			case "abajo":
				this.arriba = false;
				this.abajo = true;
				this.izquierda = false;
				this.derecha = false;
				this.imagen=this.abajoImg;
				this.comando="abajo";
				break;
			case "izquierda":
				this.arriba = false;
				this.abajo = false;
				this.izquierda = true;
				this.derecha = false;
				this.imagen=this.izquierdaImg;
				this.comando="izquierda";
				break;
			case "derecha":
				this.arriba = false;
				this.abajo = false;
				this.izquierda = false;
				this.derecha = true;
				this.imagen=this.derechaImg;
				this.comando="derecha";
				break;
			case "coloca":
				x = 55;
				y = 55;
				xx = Integer.parseInt(datos[1]);
				yy = Integer.parseInt(datos[2]);
				xx = (x + (ancho + 10) * (xx - 1));
				yy = (y + (alto + 10) * (yy - 1));
				this.comando = "coloca";
				break;
			case "mover":
				if(indice>0 && !comandos.get(indice-1).equals("arriba") &&
						!comandos.get(indice-1).equals("abajo") &&
						!comandos.get(indice-1).equals("izquierda") &&
						!comandos.get(indice-1).equals("derecha")) {
					this.imagen=this.derechaImg;
					this.derecha = true;
					this.arriba = false;
					this.abajo = false;
					this.izquierda = false;
				}
				moverCuadros = Integer.parseInt(datos[1]);
				if (arriba) {
					yy = (y - (alto + 10) * moverCuadros);
				}
				if(abajo) {
					yy = (y + (alto + 10) * moverCuadros);
				}
				if(izquierda) {
					xx = (x - (ancho + 10) * moverCuadros);
				}
				if(derecha) {
					xx = (x + (ancho + 10) * moverCuadros);
				}
				this.comando = "mover";
				break;
			case "ver":
				if((x==xx2)&&(y==yy2)) {
					EfectosMusica claxon = new EfectosMusica("claxon");
					claxon.run();
					ver=true;
					break;
				}else{
					indice++;
					while(!comandos.get(indice).equals("}")) {
						indice++;
					}
				}
				
			default:
				break;
			}

		} else {
			System.out.println("limite");
			this.iniciar = false;
			this.indice = 1;
		}

	}

	private void abrirArchivo() throws IOException, ClassNotFoundException {
		String ruta = System.getProperty("user.dir")+"/salida.fes";
		FileReader fr = new FileReader(ruta);
		BufferedReader buff = new BufferedReader(fr);
		String cad;
		this.comandos.clear();
		this.iniciar();
		while ((cad = buff.readLine()) != null) {
			comandos.add(cad);
		}		
		buff.close();
		fr.close();
	}
	private void iniciar() {
		x=55;
		y=55;
		xx=0;
		yy=0;
		indice=0;
		this.imagen=this.derechaImg;
		moverCuadros = 0;
		comando = "";
		arriba=false;
		abajo=false;
		derecha=true;
		izquierda=false;
	}
}
