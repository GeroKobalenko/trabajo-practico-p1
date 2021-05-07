package juego;

public class rasengan {
    private int x;
    private int y;
    private int ancho;
    private int alto;

    public rasengan(int x,int y, int alto, int ancho){
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
}
