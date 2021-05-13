/**
 *
 * @autores Gutierrez, Kobalenko, Ortigoza.
 *
 */

package juego;

//import java.awt.Color;
//import java.awt.Image;
import entorno.Entorno;
//import entorno.Herramientas;
import entorno.InterfaceJuego;

import java.awt.Color;
import java.util.Random;

//import javax.naming.ldap.ManageReferralControl;

public class Juego extends InterfaceJuego {
	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;
	private Sakura sakura;
	private Manzana[] manzanas = new Manzana[20];
	private Ninja[] ninjas = new Ninja[4];
	private Calle[] calles = new Calle[7];

	// Variables y métodos propios de cada grupo

	Juego() {
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "Sakura Ikebana Delivery", 800, 600);

		// Inicializar lo que haga falta para el juego
		this.sakura = new Sakura(this.entorno.ancho() / 2, 400, 10, 15);

		// Instancio las manzanas
		// Variables auxiliares.
		int anchoManzana = this.entorno.ancho() / 4;
		int altoManzana = this.entorno.alto() / 5;
		int xManzana = this.entorno.ancho() / 20;
		int yManzana = this.entorno.ancho() / 30;
		for (int i = 0; i < this.manzanas.length; i++) {
			if (i == 4 || i == 8 || i == 12 || i == 16) {
				xManzana = this.entorno.ancho() / 20;
				yManzana = yManzana + altoManzana + this.entorno.ancho() / 30;
			}
			this.manzanas[i] = new Manzana(xManzana, yManzana, anchoManzana, altoManzana, null);
			xManzana = xManzana + anchoManzana + this.entorno.ancho() / 20;
		}

		// Variables auxiliares.
		int calleX = this.entorno.ancho() / 5;
		int calleY = this.entorno.alto() / 6;

		// Ciclo y añado un objeto Calle a cada elemento del array.
		for (int i = 0; i < calles.length; i++) {
			if (i < 4) {
				this.calles[i] = new Calle(this.entorno.ancho() / 2, calleY, 30, this.entorno.ancho(), true);
				calleY = calleY + this.entorno.alto() / 5 + this.entorno.alto() / 24;
			} else {
				this.calles[i] = new Calle(calleX, this.entorno.alto() / 2, this.entorno.alto(), 40, false);
				calleX = calleX + this.entorno.ancho() / 4 + this.entorno.ancho() / 20;
			}
		}

		// Instancio los ninjas
		boolean esHorizontal = true;
		boolean movimiento = false;
		Random rant = new Random();
		int xNinja = this.entorno.ancho() / 5;
		for (int i = 0; i < this.ninjas.length; i++) {
			if (esHorizontal) {
				this.ninjas[i] = new Ninja(rant.nextInt(800), this.calles[i].getY() /*- 5*/, 10, 15, 1, esHorizontal,
						movimiento); // null;
			} else {
				this.ninjas[i] = new Ninja(xNinja, rant.nextInt(600), 10, 15, 1, esHorizontal, movimiento);
				movimiento = !movimiento;
				xNinja = xNinja + this.entorno.ancho() / 4 + this.entorno.ancho() / 20;
			}
			esHorizontal = !esHorizontal;
		}

		// Inicia el juego!
		this.entorno.iniciar();
	}

	/**
	 * Durante el juego, el método tick() será ejecutado en cada instante y por lo
	 * tanto es el método más importante de esta clase. Aquí se debe actualizar el
	 * estado interno del juego para simular el paso del tiempo (ver el enunciado
	 * del TP para mayor detalle).
	 */
	public void tick() {
		// Procesamiento de un instante de tiempo
		// ...

		// Dibujo los diferentes objetos.
		/*for (int i = 0; i < this.manzanas.length; i++) {
			this.manzanas[i].dibujarManzana(entorno);
		}*/
		this.entorno.dibujarRectangulo(400, 300, 800, 600, 0, new Color(125,213,123));

		for (int i = 0; i < calles.length; i++) {
			this.calles[i].dibujarCalles(entorno);
		}

		sakura.dibujarse(entorno);

		// Ninjas
		for (int i = 0; i < ninjas.length; i++) {
			if (ninjas[i] != null) {
				ninjas[i].dibujar(entorno);
				ninjas[i].tocaBorde(entorno);
				if (ninjas[i].getEsHorizontal()) {
					ninjas[i].moverX();
				} else {
					ninjas[i].moverY();
				}

				// Para implementar cuando el ninja toca al pj
				if (ninjas[i].tocaSakura(sakura)) {
					this.entorno.escribirTexto("you lose", 400, 300);
					System.out.println("TOUCHING SAKURA");
					this.entorno.removeAll();
				}
			}
		}
		sakura.seMueveHori(entorno, this.calles);
		sakura.seMueveVerti(entorno, this.calles);
		System.out.println("sakura X: "+sakura.getX() + " sakura Y: " + sakura.getY());
		System.out.println("Ninja 0 X: " + ninjas[0].getX() + " Ninja 0 Y: " + ninjas[0].getY());
		System.out.println("Ninja 1 X: " + ninjas[1].getX() + " Ninja 1 Y: " + ninjas[1].getY());
		System.out.println("Ninja 2 X: " + ninjas[2].getX() + " Ninja 2 Y: " + ninjas[2].getY());
		System.out.println("Ninja 3 X: " + ninjas[3].getX() + " Ninja 3 Y: " + ninjas[3].getY());
	}

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Juego juego = new Juego();
	}
}
