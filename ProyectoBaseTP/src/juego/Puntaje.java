package juego;

import entorno.Entorno;

public class Puntaje {
    private int x;
    private int y;
    private int pts;
    private String palabra;

    public Puntaje(int x, int y, int pts, String palabra) {
        this.x = x;
        this.y = y;
        this.pts = pts;
        this.palabra = palabra;
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
    
    public int getPuntos() {
        return pts;
    }

    public void dibujarse(Entorno entorno) {
        entorno.escribirTexto(palabra + " " + pts, x, y);
    }

}