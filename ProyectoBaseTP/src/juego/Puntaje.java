package juego;

import entorno.Entorno;

public class Puntaje {
    private int x;
    private int y;
    private int pts;
    private String palabra = "Puntaje: ";

    public Puntaje(int x, int y, int pts) {
        this.x = x;
        this.y = y;
        this.pts = pts;
    }

    public int sumarPts(int pts) {
        this.pts = this.pts + pts;
        return this.pts;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void dibujarse(Entorno entorno) {
        entorno.escribirTexto(palabra + " " + pts, x, y);
    }

}