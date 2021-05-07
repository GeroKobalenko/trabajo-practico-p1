package juego;

import entorno.Entorno;
import entorno.Herramientas;

public class Sakura {

    private int x;
    private int y;
    private int ancho;
    private int alto;

    public Sakura(int x,int y, int alto, int ancho){
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
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
    public void moverIzquierda(){
        this.x = this.x-5;
    }
    public void moverDerecha(){
        this.x = this.x+5;
    }
    public void moverArriba(){
        this.x = this.y+5;
    }
    public void moverAbajo(){
        this.x = this.y-5;
    }

    public void dibujarse(Entorno entorno){
        entorno.dibujarImagen(Herramientas.cargarImagen("images/sakura.png"), this.x, this.y, 0,0.2);
    }

}
