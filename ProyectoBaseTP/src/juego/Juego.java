package juego;

import entorno.Entorno;
import entorno.InterfaceJuego;
import java.util.Random;

public class Juego extends InterfaceJuego {
	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;
	private Sakura sakura;
	private Puntaje puntaje;
	private Ninja[] ninjas = new Ninja[6];
	private Calle[] calles = new Calle[6];
	private Manzana[] manzanas = new Manzana[16];
	private int[] contI = { 0, 0, 0, 0, 0, 0 };
	private int[] ninjaI = { -1, -1, -1, -1, -1, -1 };

	Juego() {
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "Sakura Ikebana Delivery", 800, 600);
		this.puntaje = new Puntaje(700, 30, 0);

		// Inicializar lo que haga falta para el juego
		this.sakura = new Sakura(this.entorno.ancho() / 2, 400, 10, 15);
		this.inicializarManzanas();
		this.inicializarCalles();
		this.inicializarNinjas();
		// Inicia el juego!
		this.entorno.iniciar();
	}

	void inicializarCalles() {
		// Posiciones de clles iniciales
		int calleX = 190;
		int calleY = 145;
		// Ciclo y añado un objeto Calle a cada elemento del array.
		for (int i = 0; i < calles.length; i++) {
			if (i < 3) {
				this.calles[i] = new Calle(this.entorno.ancho() / 2, calleY, 30, this.entorno.ancho(), true);
				calleY = calleY + this.entorno.alto() / 4 + 10;
			} else {
				this.calles[i] = new Calle(calleX, this.entorno.alto() / 2, this.entorno.alto(), 40, false);
				calleX = calleX + this.entorno.ancho() / 4 + 10;
			}
		}
	}

	void inicializarManzanas() {
		int anchoManzana = 170;
		int altoManzana = 130;
		int xManzana = 85;
		int yManzana = 65;

		for (int i = 0; i < manzanas.length; i++) {
			manzanas[i] = new Manzana(xManzana, yManzana, anchoManzana, altoManzana);
			xManzana = xManzana + anchoManzana + this.entorno.ancho() / 20;
			if (xManzana >= this.entorno.ancho()) {
				xManzana = 85;
				yManzana = yManzana + altoManzana + this.entorno.alto() / 20;
			}
		}
	}

	void inicializarNinjas() {
		boolean esHorizontal = true;
		boolean movimiento = false;
		Random rant = new Random();
		int xNinja = this.entorno.ancho() / 4 - 10;
		for (int i = 0; i < this.ninjas.length; i++) {
			if (esHorizontal) {
				this.ninjas[i] = new Ninja(rant.nextInt(800), this.calles[i].getY() - 5, 10, 15, 2, esHorizontal,
						movimiento);
			} else {
				this.ninjas[i] = new Ninja(xNinja, rant.nextInt(600), 10, 15, 2, esHorizontal, movimiento);
				movimiento = !movimiento;
				xNinja = xNinja + this.entorno.ancho() / 4 + 10;
			}
			esHorizontal = !esHorizontal;
		}
	}

	void dibujarManzanas() {
		for (Manzana manzana : manzanas) {
			manzana.dibujarManzana(entorno);
			for (Casa casa : manzana.getCasas()) {
				if (casa != null)
					casa.dibujarCasa(entorno);
			}
		}
	}

	void dibujarCalles() {
		for (Calle calle : calles) {
			calle.dibujarCalle(entorno);
		}
	}

	void dibujarNinjas() {
		boolean esHorizontal = true;
		for (int i = 0; i < ninjas.length; i++) {
			if (ninjas[i] != null) {
				ninjas[i].dibujar(entorno);
				ninjas[i].tocaBorde(entorno);
				if (ninjas[i].getEsHorizontal()) {
					ninjas[i].moverX();
				} else {
					ninjas[i].moverY();
				}
				// Verifica la colision del Rasengan respecto a los ninjas
				if (ninjas[i].choqueRasengan(sakura.getRasengan())) {
					sakura.setRasengan(null);
					ninjas[i] = null;
					ninjaI[i] = i;
					puntaje.sumarPts(1);
				}
			} else {
				contI[i] = contI[i] + 1;
				// Ver segun conveniencia de tiempo.
				if (contI[i] > 500) {
					Random rant = new Random();
					if (!esHorizontal) {
						int xNinja = this.calles[i].getX();
						ninjas[i] = new Ninja(xNinja, rant.nextInt(600), 10, 15, 2, esHorizontal, !esHorizontal);
					} else {
						ninjas[i] = new Ninja(rant.nextInt(800), this.calles[i].getY() - 5, 10, 15, 2, esHorizontal,
								!esHorizontal);
					}
					contI[i] = 0;
				}
				ninjaI[i] = -1;
			}
			esHorizontal = !esHorizontal;

			// Para implementar cuando el ninja toca al pj
			// if (ninjas[i].tocaSakura(sakura)) {
			// this.entorno.escribirTexto("you lose", 400, 300);
			//// System.out.println("TOUCHING SAKURA");
			// //this.entorno.removeAll();
			// }
		}
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

		// Llamo los metodos para dibujar los objetos.
		this.dibujarManzanas();
		this.dibujarCalles();
		this.dibujarNinjas();

		sakura.dibujarse(entorno);
		sakura.seMueveHori(entorno, this.calles);
		sakura.seMueveVerti(entorno, this.calles);

		if (this.entorno.sePresiono(entorno.TECLA_ESPACIO)) {
			if (sakura.getRasengan() == null)
				sakura.crearRasengan(entorno);
		}
		if (sakura.getRasengan() != null)
			this.sakura.efectuarRasengan(entorno);

		puntaje.dibujarse(entorno);
	}

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Juego juego = new Juego();
	}
}
