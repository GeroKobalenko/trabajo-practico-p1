package juego;
import java.awt.Color;
import entorno.Entorno;

public class Calles {
    private int x;
    private int y;
    private int ancho;
    private int alto;

    public Calles(int x,int y, int alto, int ancho){
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

    /*public void dibujarse(Entorno entorno){
        entorno.dibujarImagen(imagen, this.x, this.y, angulo, escala);
    }*/
    public void dibujarCalles(Entorno entorno) {
		entorno.dibujarRectangulo(this.x, this.y, this.ancho, this.alto, 0, Color.BLUE);//(rant.nextInt(200),rant.nextInt(200),rant.nextInt(200)));
    }
}
