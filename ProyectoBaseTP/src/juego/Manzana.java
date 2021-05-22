package juego;

import java.awt.Color;
import java.util.Random;

import entorno.Entorno;

public class Manzana {

	private int x;
	private int y;
	private int ancho;
	private int alto;
	private Casa[] casas = new Casa[4];
	private boolean esEntrega = false;

	Manzana(int x, int y, int ancho, int alto) {
		this.x = x;
		this.y = y;
		this.ancho = ancho;
		this.alto = alto;
		
		crearCasas();
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getAncho() {
		return ancho;
	}

	public int getAlto() {
		return alto;
	}
	
	public Casa[] getCasas() {
		return casas;
	}
	
	public void crearCasas() {
		/** Variables auxiliares */
		int xEsquinaDer = 775;
		int xyEsquinaIzq = 25;
		int yEsquinaInf = 585;
		
		int yCasa = y - alto/2 + 25;
		int xCasa = x - ancho/2 + 25;
		
		for (int i = 0 ; i < 4 ; i++) {
			boolean add = true;
			//Diferentes if para no crear casas en lugares que Sakura no pueda acceder.
			if ((xCasa == xyEsquinaIzq && yCasa == xyEsquinaIzq) || 
				(xCasa == xEsquinaDer && yCasa == xyEsquinaIzq) ||
				(xCasa == xyEsquinaIzq && yCasa == yEsquinaInf) ||
				(xCasa == xEsquinaDer && yCasa == yEsquinaInf)) add = false;
			
			if (add) this.casas[i] = new Casa(xCasa, yCasa);
			
			xCasa = x + ancho/2 - 25;

			if (i == 1) {//reseteo X y bajo en la manzana en Y
				yCasa = y + alto/2 - 25;
				xCasa = x - ancho/2 + 25;
			}
		}
	}
	
	public void setElegida(boolean bool) {
		this.esEntrega = bool;
		
		Random rant = new Random();
		int aux = rant.nextInt(casas.length);
		
		if (casas[aux] != null) {
			casas[aux].setElegida(true);
		}
		else {
			setElegida(bool);
		}
	}
	
	public boolean getElegida() {
		return this.esEntrega;
	}

	public void dibujarManzana(Entorno entorno) {
		// Random rant = new Random();
		entorno.dibujarRectangulo(this.x, this.y, this.ancho, this.alto, 0, new Color(125,213,123));
	}
}
