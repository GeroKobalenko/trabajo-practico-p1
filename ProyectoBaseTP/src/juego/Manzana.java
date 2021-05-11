package juego;
import java.awt.Color;
//import java.util.Random;

import entorno.Entorno;
//import entorno.Herramientas;

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
	public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }

	public int getAncho(){
        return ancho;
    }
    public int getAlto(){
        return alto;
    }
	
	public void dibujarManzana(Entorno entorno) {
		//Random rant = new Random();
		entorno.dibujarRectangulo(this.x, this.y, this.ancho, this.alto, 0, new Color(125,213,123));
    }
}
