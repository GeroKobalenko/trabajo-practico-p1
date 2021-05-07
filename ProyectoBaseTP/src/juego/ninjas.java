package juego;

import entorno.Entorno;
import entorno.Herramientas;

public class Ninjas {
    private int x;
    private int y;
    private int ancho;
    private int alto;
    private int velocidad;

    public Ninjas(int x,int y, int alto, int ancho, int velocidad){
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
        this.velocidad = velocidad;
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
        entorno.dibujarImagen(Herramientas.cargarImagen("images/ninja.png"), this.x, this.y, 0, 0.1);
    }
}
