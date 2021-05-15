package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Rasengan {
    private int x;
    private int y;
    private String direccion;
    private Image imagen;

    public Rasengan(int x, int y, String direccion) {
        this.x = x;
        this.y = y;
        this.direccion = direccion;
        this.imagen = Herramientas.cargarImagen("images/rasengan.png");
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    public String getDireccion() {
        return direccion;
    }

    void moverIzquierda() {
        this.x = this.x - 2;
    }

    void moverDerecha() {
        this.x = this.x + 2;
    }

    void moverArriba() {
        this.y = this.y - 2;
    }

    void moverAbajo() {
        this.y = this.y + 2;
    }

    public void dibujarse(Entorno entorno) {
        entorno.dibujarImagen(imagen, this.x, this.y, 0, 0.07);
    }
}
