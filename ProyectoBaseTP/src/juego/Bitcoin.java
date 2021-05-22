package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Bitcoin {

	private int x;
	private int y;
	private Image imagen;
	
	Bitcoin(int x, int y){
		this.x = x;
		this.y = y;
		this.imagen = Herramientas.cargarImagen("images/bitcoin.png");
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public void dibujarBtc(Entorno entorno) {
		entorno.dibujarImagen(imagen, this.x, this.y, 0, 0.03);
	}
}
