package juego;

import entorno.Entorno;
import entorno.Herramientas;

public class Ninjas {
    private int x;
    private int y;
    private int ancho;
    private int alto;
    private int velocidad;
    private boolean esHorizontal;
    

    public Ninjas(int x,int y, int alto, int ancho, int velocidad, boolean esHorizontal){
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
        this.velocidad = velocidad;
        this.esHorizontal = esHorizontal;
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
    public int getVelocidad(){
        return velocidad;
    }

    public void moverY(){
        this.y = this.y + this.velocidad;
    }

    public void moverX(){
       this.x = this.x + this.velocidad;
    }
    
    public void dibujar(Entorno entorno){
        entorno.dibujarImagen(Herramientas.cargarImagen("images/ninja_amarillo.png"), this.x, this.y, 0, 0.08);
    }
    
    public void tocaBorde(Entorno entorno) {
    	if (this.esHorizontal) {
    		if (this.getX()>entorno.getWidth()-40) {
        		this.x = 0;
        	}
    	}
    	else {
    		if (this.getY()>entorno.getHeight()-70) {
    			this.y = 0;
    		}
    	}
    }
}
