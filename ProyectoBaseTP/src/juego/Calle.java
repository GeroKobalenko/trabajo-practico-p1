package juego;
import java.awt.Color;
import entorno.Entorno;

public class Calle {
    private int x;
    private int y;
    private int ancho;
    private int alto;
    private boolean esHorizontal;

    public Calle(int x,int y, int alto, int ancho, boolean esHorizontal){
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
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
    public boolean esHorizontal(){
        return esHorizontal;
    }

    /*public void dibujarse(Entorno entorno){
        entorno.dibujarImagen(imagen, this.x, this.y, angulo, escala);
    }*/
    public void dibujarCalles(Entorno entorno) {
		entorno.dibujarRectangulo(this.x, this.y, this.ancho, this.alto, 0, new Color(204,204,204));
    }
}
