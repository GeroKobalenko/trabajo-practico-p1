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
	private Ninja ninjas;

	public Sakura(int x, int y, int alto, int ancho) {
		this.x = x;
		this.y = y;
		this.ancho = ancho;
		this.alto = alto;
		this.imagen = Herramientas.cargarImagen("images/sakurav2.png");
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
		entorno.dibujarImagen(imagen, this.x, this.y, 0, 0.1);
	}

	public boolean noEstaPresionando(Entorno entorno, char tecla) {
		if (entorno.estaPresionada(tecla)) {
			return false;
		}
		return true;
	}

	public void seMueveVerti(Entorno entorno, Calle[] calles) {
		for (int i = 0; i < calles.length; i++) {
			if (!calles[i].esHorizontal()) {
				if (this.x <= calles[i].getX() + (calles[i].getAncho() / 3)
						&& this.x >= calles[i].getX() - (calles[i].getAncho() / 3)) {
					
					this.imagen = Herramientas.cargarImagen("images/sakurav2.png");
					
					if ((entorno.estaPresionada('w') || entorno.estaPresionada(entorno.TECLA_ARRIBA))
							&& this.getY() > 20) {		
						this.moverArriba();
					}
					if ((entorno.estaPresionada('s') || entorno.estaPresionada(entorno.TECLA_ABAJO))
							&& this.getY() < entorno.getHeight() - 80) {
						this.moverAbajo();
					}
				}
			}
		}
	}

	public void seMueveHori(Entorno entorno, Calle[] calles) {
		for (int i = 0; i < calles.length; i++) {
			if (calles[i].esHorizontal()) {
				if (this.y /*+ 40*/ <= calles[i].getY() + (calles[i].getAlto() / 2)
						&& this.y + 40 >= calles[i].getY() - (calles[i].getAlto() / 2)) {

					if ((entorno.estaPresionada('d') || entorno.estaPresionada(entorno.TECLA_DERECHA))
							&& this.getX() < entorno.getWidth() - 40) {
						this.imagen = Herramientas.cargarImagen("images/sakurav2ramo.png");
						this.moverDerecha();
					}
					if ((entorno.estaPresionada('a') || entorno.estaPresionada(entorno.TECLA_IZQUIERDA))
							&& this.getX() > 25) {
						this.imagen = Herramientas.cargarImagen("images/sakurav2ramo1.png");
						this.moverIzquierda();
					}
				}
			}
		}
	}
}
