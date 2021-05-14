package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Rasengan {
    private int x;
    private int y;
    private int ancho;
    private int alto;
    private int velocidad;
    private Image imagen;
    


    public Rasengan(int x,int y, int alto, int ancho, int velocidad){
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
        this.velocidad=velocidad;
    	this.imagen = Herramientas.cargarImagen("images/rasengan.png");
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
    
    public void moverRasenganY () { // El rasengan se desplaza sobre el eje y
    	this.y +=  this.velocidad;
    }
    
    public void moverRasenganX () {//El rasengan se desplaza sobre el eje x
    	this.x +=  this.velocidad ;
    }
    
    public void iniciarRasenganArriba () {
    	this.velocidad=-8;
    }
    
    public void iniciarRasenganAbajo () {
    	this.velocidad=8;
    }
    
    public void dibujarse(Entorno entorno) {
		entorno.dibujarImagen(imagen, this.x, this.y, 0, 0.07);
	}
    
    public boolean noEstaPresionando(Entorno entorno, char tecla) {
		if (entorno.estaPresionada(tecla)) {
			return false;
		}
		return true;
	}
    
    public void DisparaRasengan(Entorno entorno,Rasengan rasengan) {  
    	
    	if (entorno.estaPresionada(entorno.TECLA_ESPACIO)){	
    		
    		if(entorno.estaPresionada(entorno.TECLA_ARRIBA)){ 
    			
    			if (rasengan.getVelocidad() == 0) {
	    		
    				rasengan.iniciarRasenganArriba(); 
	    			}
    		}
    		
    		if(entorno.estaPresionada(entorno.TECLA_ABAJO)){ 	    		
			    if (rasengan.getVelocidad() == 0) {	    			 
			    	
			    	rasengan.iniciarRasenganAbajo(); 
					}
    		}
			if(entorno.estaPresionada(entorno.TECLA_IZQUIERDA)){ 	    		
				    if (rasengan.getVelocidad() == 0) {	    			 
				    	rasengan.iniciarRasenganArriba();
				    
						}
					}
				
				
				if(entorno.estaPresionada(entorno.TECLA_DERECHA)){ 
				    if (rasengan.getVelocidad() == 0) {
					    			 
				    	rasengan.iniciarRasenganAbajo();
					}
				}
    		
    	}
    }
    
   
}
