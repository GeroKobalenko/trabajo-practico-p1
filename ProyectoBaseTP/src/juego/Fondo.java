package juego;

import entorno.Entorno;
import entorno.Herramientas;

public class Fondo {

    public void dibujar(Entorno entorno){
        entorno.dibujarImagen(Herramientas.cargarImagen("images/fondo.png"), 0, 0, 0, 1);
    }
}
