package juego;

import java.awt.Color;

import entorno.Entorno;
import entorno.Herramientas;

public class Manzana {

	private int x;
	private int y;
	private int ancho;
	private int alto;
	private Casa[] casas;
	
	Manzana(int x, int y,int ancho, int alto, Casa[] casas){
		this.x = x;
		this.y = y;
		this.ancho = ancho;
		this.alto = alto;
		this.casas = casas;
	}
	
	public void dibujarManzana(Entorno entorno) {
		entorno.dibujarRectangulo(this.x, this.y, this.ancho, this.alto, 0, new Color(69,176,0));
    }
}
