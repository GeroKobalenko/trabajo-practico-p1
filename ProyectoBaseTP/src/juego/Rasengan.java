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
    /*private Sakura sakura;
    private Calle calle;
    private Entorno entorno;*/
    private Juego juego;

    public Rasengan(int x, int y, int alto, int ancho, int velocidad) {
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
        this.velocidad = velocidad;
        this.imagen = Herramientas.cargarImagen("images/rasengan.png");
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getAncho() {
        return ancho;
    }

    public int getAlto() {
        return alto;
    }

    public int getVelocidad() {
        return velocidad;
    }

    private void moverIzquierda() {
        this.x = this.x - 2;
    }

    private void moverDerecha() {
        this.x = this.x + 2;
    }

    private void moverArriba() {
        this.y = this.y - 2;
    }

    private void moverAbajo() {
        this.y = this.y + 2;
    }

    public void dibujarse(Entorno entorno) {
        entorno.dibujarImagen(imagen, this.x, this.y, 0, 0.07);
    }

    void movRasengan(Sakura sakura, Entorno entorno) {
        {
            juego.crearRasegan();
            switch (sakura.direcc()) {
                case "arriba":
                    moverArriba();
                    break;
                case "derecha":
                    moverDerecha();
                    break;
                case "izquierda":
                    moverIzquierda();
                    break;
                case "abajo":
                    moverAbajo();
                    break;
            }
        }

    }
}

/*
 * public void seDispara(Entorno entorno, int x,int y){ seMueveVerti(entorno,
 * y); seMueveHori(entorno, x); }
 * 
 * public void seMueveVerti(Entorno entorno, int y) { this.y=y;
 * if((entorno.estaPresionada('d') ||
 * entorno.estaPresionada(entorno.TECLA_DERECHA))){ this.y = this.y + 2;
 * dibujarse(entorno); } else{ this.y = this.y - 2; dibujarse(entorno); } }
 * 
 * public void seMueveHori(Entorno entorno,int x) { this.x=x;
 * if((entorno.estaPresionada('w') ||
 * entorno.estaPresionada(entorno.TECLA_ARRIBA))){ this.x = this.x - 2;
 * dibujarse(entorno); } else{ this.x = this.x + 2; dibujarse(entorno); } }
 * 
 * 
 * 
 * public void seMueveHori(Entorno entorno, Calle[] calles) { for (int i = 0; i
 * < calles.length; i++) { if (calles[i].esHorizontal()) { if (this.y + 40 <=
 * calles[i].getY() + (calles[i].getAlto() / 2) && this.y + 40 >=
 * calles[i].getY() - (calles[i].getAlto() / 2)) {
 * 
 * if ((entorno.estaPresionada('d') ||
 * entorno.estaPresionada(entorno.TECLA_DERECHA)) && this.getX() <
 * entorno.getWidth() - 40) { this.imagen =
 * Herramientas.cargarImagen("images/sakurav2ramo.png"); this.moverDerecha(); }
 * if ((entorno.estaPresionada('a') ||
 * entorno.estaPresionada(entorno.TECLA_IZQUIERDA)) && this.getX() > 25) {
 * this.imagen = Herramientas.cargarImagen("images/sakurav2ramo1.png");
 * this.moverIzquierda(); } } } } }
 */
