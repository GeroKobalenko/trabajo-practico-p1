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
	private boolean esEntrega = false;
	
	Casa(int x, int y){
		this.x = x;
		this.y = y;
		Random rant = new Random();
		this.imagen = Herramientas.cargarImagen("images/casa0"+rant.nextInt(2)+".png");
	}
	
	public void setElegida(boolean bool) {
		this.esEntrega = bool;
	}
	
	public boolean verificarEntrega(Sakura sakura) {
		if (this.esEntrega && sakura != null) {
			if((Math.abs(this.x-sakura.getX())<=50 && Math.abs(this.y-sakura.getY()-10)<=70)){
		        return true;
		    }
		}
	    return false;
    }
	
	public void dibujarCasa(Entorno entorno) {
		entorno.dibujarImagen(imagen, this.x, this.y, 0, 0.09);
		if (this.esEntrega) {
			Image imagen = Herramientas.cargarImagen("images/flechaAbajo.png");
			entorno.dibujarImagen(imagen, this.x, this.y-20, 0, 0.06);
			
		}
	}
}
