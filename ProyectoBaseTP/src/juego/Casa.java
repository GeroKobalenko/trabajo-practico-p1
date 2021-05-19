package juego;
import java.awt.Color;
import java.awt.Image;
import java.util.Random;

import entorno.Entorno;
import entorno.Herramientas;

public class Casa {
	private int x;
	private int y;
	private Image imagen;
	
	Casa(int x, int y){
		this.x = x;
		this.y = y;
		
		Random rant = new Random();
		this.imagen = Herramientas.cargarImagen("images/casa0"+rant.nextInt(2)+".png");
	}
	
	public void dibujarCasa(Entorno entorno) {
		entorno.dibujarImagen(imagen, this.x, this.y, 0, 0.09);
	}
}
