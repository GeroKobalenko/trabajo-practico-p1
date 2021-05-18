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
	private String direccion;
	private Rasengan poder;

	public Sakura(int x, int y, int alto, int ancho) {
		this.x = x;
		this.y = y;
		this.ancho = ancho;
		this.alto = alto;
		this.poder = null;
		this.imagen = Herramientas.cargarImagen("images/sakurav2.png");
	}
	
	public String direcc(){
		return this.direccion;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public Rasengan getRasengan() {
		return poder;
	}

	void setRasengan(Rasengan rasengan) {
		this.poder = rasengan;
	}

	private void moverIzquierda() {
		this.x = this.x - 2;
		
		this.direccion = "izquierda";
	}

	private void moverDerecha() {
		this.x = this.x + 2;
		
		this.direccion = "derecha";
	}

	private void moverArriba() {
		this.y = this.y - 2;
		
		this.direccion = "arriba";
	}

	private void moverAbajo() {
		this.y = this.y + 2;
		
		this.direccion = "abajo";
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
				if (this.y + 40 <= calles[i].getY() + (calles[i].getAlto() / 2)
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
	
	public void crearRasengan(Entorno entorno) {
		
		if (this.direccion == null) return;
		
		this.poder = new Rasengan(this.x, this.y+20,this.direccion);
	}
	
	public void efectuarRasengan(Entorno entorno) {
		
		this.poder.dibujarse(entorno);
		
		switch (this.poder.getDireccion()) {
		case "arriba":
			this.poder.moverArriba();
			break;
		case "abajo":
			this.poder.moverAbajo();
			break;
		case "derecha":
			this.poder.moverDerecha();
			break;
		case "izquierda":
			this.poder.moverIzquierda();
			break;
		}
		
		// Cuando el rasengan toque algun borde, lo nulleo.
		if (this.poder.getX() == entorno.ancho() || this.poder.getX() == 0|| this.poder.getY() == entorno.alto() || this.poder.getY() == 0) this.poder = null;
	}
}
