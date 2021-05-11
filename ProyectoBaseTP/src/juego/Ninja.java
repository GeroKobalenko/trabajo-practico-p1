package juego;

import entorno.Entorno;
import entorno.Herramientas;

public class Ninja {
    private int x;
    private int y;
    private int ancho;
    private int alto;
    private int velocidad;
    private boolean esHorizontal;
    private boolean tipoMovimiento;
    

    public Ninja(int x,int y, int alto, int ancho, int velocidad, boolean esHorizontal, boolean tipoMovimiento){
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
        this.velocidad = velocidad;
        this.esHorizontal = esHorizontal;
        this.tipoMovimiento = tipoMovimiento;
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
    
    public boolean getEsHorizontal(){
        return esHorizontal;
    }

    public void moverY(){
    	if (this.tipoMovimiento) {
    		this.y = this.y + this.velocidad;
    	}
    	else {
    		this.y = this.y - this.velocidad;
    	}
    }

    public void moverX(){
    	if (this.tipoMovimiento) {
    		this.x = this.x + this.velocidad;
    	}
    	else {
    		this.x = this.x - this.velocidad;
    	}
    }
    
    public void dibujar(Entorno entorno){
        entorno.dibujarImagen(Herramientas.cargarImagen("images/ninja.png"), this.x, this.y, 0, 0.08);
    }
    
    public void tocaBorde(Entorno entorno) {
    	if (this.esHorizontal) {
    		if (this.tipoMovimiento) {
    			if (this.getX()>entorno.getWidth()) {
            		this.x = 0;
            	}
    		}
    		else {
    			if (this.getX()<0) {
            		this.x = entorno.getWidth();
            	}
    		}
    		
    	}
    	else {
    		if (this.tipoMovimiento) {
    			if (this.getY()>entorno.getHeight()) {
        			this.y = 0;
        		}
    		}
    		else {
    			if (this.getY()<0) {
        			this.y = entorno.getHeight();
        		}
    		}
    	}
    }
}
