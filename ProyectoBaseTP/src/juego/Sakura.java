package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Sakura {

    private int x;
    private int y;
    private int ancho;
    private int alto;
    private Image imagen;

    public Sakura(int x,int y, int alto, int ancho){
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
        this.imagen = Herramientas.cargarImagen("images/sakurav2.png");
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
    private void moverIzquierda(){
        this.x = this.x-3;
    }
    private void moverDerecha(){
        this.x = this.x+3;
    }
    private void moverArriba(){
        this.y = this.y-3;
    }
    private void moverAbajo(){
        this.y = this.y+3;
    }

    public void dibujarse(Entorno entorno){
        entorno.dibujarImagen(imagen, this.x, this.y, 0,0.1);
    }

    public boolean noEstaPresionando(Entorno entorno,char tecla) {
    	if (entorno.estaPresionada(tecla)) {
    		return false;
    	}
    	return true;
    }
    
    public void seMueve(Entorno entorno) {
    	if(
    			entorno.estaPresionada(entorno.TECLA_ARRIBA)
				&& this.getY()>40
				&& this.noEstaPresionando(entorno,entorno.TECLA_ABAJO)
				&& this.noEstaPresionando(entorno,entorno.TECLA_DERECHA)
				&& this.noEstaPresionando(entorno,entorno.TECLA_IZQUIERDA)
				) 
    	{
			this.moverArriba();
		}
		if(
				entorno.estaPresionada(entorno.TECLA_ABAJO)
				&& this.getY()<entorno.getHeight()-80
				&& this.noEstaPresionando(entorno,entorno.TECLA_ARRIBA)
				&& this.noEstaPresionando(entorno,entorno.TECLA_DERECHA)
				&& this.noEstaPresionando(entorno,entorno.TECLA_IZQUIERDA)
				)
		{
			this.moverAbajo();
		}
		if(
				entorno.estaPresionada(entorno.TECLA_DERECHA)
				&& this.getX()<entorno.getWidth()-40
				&& this.noEstaPresionando(entorno,entorno.TECLA_ABAJO)
				&& this.noEstaPresionando(entorno,entorno.TECLA_ARRIBA)
				&& this.noEstaPresionando(entorno,entorno.TECLA_IZQUIERDA)
				)
		{
			this.moverDerecha();
		}
		if(
				entorno.estaPresionada(entorno.TECLA_IZQUIERDA)
				&& this.getX()>25
				&& this.noEstaPresionando(entorno,entorno.TECLA_ABAJO)
				&& this.noEstaPresionando(entorno,entorno.TECLA_DERECHA)
				&& this.noEstaPresionando(entorno,entorno.TECLA_ARRIBA)
				)
		{
			this.moverIzquierda();
		}
    }
}
