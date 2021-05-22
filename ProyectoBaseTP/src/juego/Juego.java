package juego;

import entorno.Entorno;
import entorno.Herramientas;
import entorno.InterfaceJuego;

import java.awt.Color;
import java.awt.Image;
import java.util.Random;

import javax.sound.sampled.Clip;

public class Juego extends InterfaceJuego {
	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;
	private Sakura sakura;
	private Bitcoin bitcoin;
	private Puntaje puntaje;
	private Puntaje cantNinjasMuertos;
	private Ninja[] ninjas = new Ninja[4];
	private Calle[] calles = new Calle[6];
	private Manzana[] manzanas = new Manzana[16];
	private int[] contI = { 0, 0, 0, 0, 0, 0 };
	private int[] ninjaI = { -1, -1, -1, -1, -1, -1 };
	private int contObjetoExtra = 0;
	private boolean verMenu = true;
	private boolean tocoAbajo = false;
	private boolean tocoArriba = true;
	private Image flechaMenu = Herramientas.cargarImagen("images/flechaRoja.png");

	Juego() {
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "Sakura Ikebana Delivery", 800, 600);
		
		// Inicializar lo que haga falta para el juego
		this.puntaje = new Puntaje(700, 30, 0, "Puntaje: ");
		this.cantNinjasMuertos = new Puntaje(0, 30, 0, "Ninjas muertos: ");
		this.sakura = new Sakura(this.entorno.ancho()/2 + 210, 350);
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
		
		this.setearCasaElegida();
	}

	void inicializarNinjas() {
		boolean esHorizontal = true;
		boolean movimiento = false;
		Random rant = new Random();
		int xNinja = this.entorno.ancho() / 4 - 10;
		for (int i = 0; i < this.ninjas.length; i++) {
			if (esHorizontal) {
				this.ninjas[i] = new Ninja(rant.nextInt(800), this.calles[i].getY() - 5, 2, esHorizontal,
						movimiento);
			} else {
				this.ninjas[i] = new Ninja(xNinja, rant.nextInt(600), 2, esHorizontal, movimiento);
				movimiento = !movimiento;
				xNinja = xNinja + this.entorno.ancho() / 4 + 10;
			}
			esHorizontal = !esHorizontal;
		}
	}
	
	void inicializarBitcoin() {
		Random random = new Random();
		int rand1 = random.nextInt(calles.length);
		
		if (calles[rand1].esHorizontal()) {
			int ranAncho = random.nextInt(this.entorno.ancho());
			this.bitcoin = new Bitcoin(ranAncho + 210, calles[rand1].getY());
		}
		else {
			int ranAlto = random.nextInt(this.entorno.alto());
			this.bitcoin = new Bitcoin(calles[rand1].getX(), ranAlto);
		}
	}
	
	void setearCasaElegida() {
		// Seteo todo en false.
		this.setearCasasNoElegidas();
		
		// Elijo un num al azar y seteo true.
		Random rant = new Random();
		int aux = rant.nextInt(manzanas.length);
		manzanas[aux].setElegida(true);
	}
	
	void setearCasasNoElegidas() {
		for (int i = 0; i < manzanas.length; i++) {
			manzanas[i].setElegida(false);
			for (Casa casa : manzanas[i].getCasas()) {
				if (casa != null) {
					casa.setElegida(false);
				}
			}
		}
	}

	void dibujarManzanas() {
		for (Manzana manzana : manzanas) {
			manzana.dibujarManzana(entorno);
			for (Casa casa : manzana.getCasas()) {
				if (casa != null) {
					casa.dibujarCasa(entorno);
					
					if (casa.verificarEntrega(sakura)) {
						this.setearCasaElegida();
						puntaje.sumarPts(5);
					}
				}
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
				// Para implementar cuando el ninja toca al pj
			    if (ninjas[i].tocaSakura(sakura)) {
			    	this.terminarJuego("GAME OVER");
			    	this.entorno.removeAll();
			    }
				// Verifica la colision del Rasengan respecto a los ninjas
				if (sakura != null && ninjas[i].choqueRasengan(sakura.getRasengan())) {
					sakura.setRasengan(null);
					ninjas[i] = null;
					ninjaI[i] = i;
					cantNinjasMuertos.sumarPts(1);
					puntaje.sumarPts(3);
				}
			} else {
				contI[i] = contI[i] + 1;
				// Ver segun conveniencia de tiempo.
				if (contI[i] > 500) {
					Random rant = new Random();
					if (!esHorizontal) {
						int xNinja = this.calles[i].getX();
						ninjas[i] = new Ninja(xNinja, rant.nextInt(600),2, esHorizontal, !esHorizontal);
					} else {
						ninjas[i] = new Ninja(rant.nextInt(800), this.calles[i].getY() - 5, 2, esHorizontal,
								!esHorizontal);
					}
					contI[i] = 0;
				}
				ninjaI[i] = -1;
			}
			esHorizontal = !esHorizontal;
		}
	}
	
	void dibujarFlechaMenu(int posY){
		this.entorno.dibujarImagen(flechaMenu,100,posY,0,0.1);
	}
	
	void menuJuego() {
		this.entorno.dibujarImagen(Herramientas.cargarImagen("images/black.jpg"),400,300,0);
		this.entorno.cambiarFont("arial", 100, Color.RED);
		this.entorno.escribirTexto("JUGAR", 200, 200);
		this.entorno.escribirTexto("SALIR", 200, 400);
	}
	
	void terminarJuego(String palabra) {
		this.entorno.dibujarImagen(Herramientas.cargarImagen("images/black.jpg"),400,300,0);
		this.entorno.cambiarFont("arial", 100, Color.RED);
		this.entorno.escribirTexto(palabra, 100, 300);
		this.entorno.cambiarFont("arial", 20, Color.CYAN);
		puntaje.dibujarse(entorno);
		cantNinjasMuertos.dibujarse(entorno);
		this.sakura=null;
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
		if (verMenu) {
			menuJuego();
			if (this.entorno.sePresiono(entorno.TECLA_ABAJO) || tocoAbajo) {
				tocoAbajo = true;
				tocoArriba = false;
				dibujarFlechaMenu(350);
				
			}
			if (this.entorno.sePresiono(entorno.TECLA_ARRIBA) || tocoArriba) {
				tocoArriba = true;
				tocoAbajo = false;
				dibujarFlechaMenu(150);
			}
			
			if (this.entorno.sePresiono(entorno.TECLA_ENTER)&& tocoArriba) {
				verMenu = false;
			}
			
			if (this.entorno.sePresiono(entorno.TECLA_ENTER)&& tocoAbajo) {
				System.exit(0);
			}
		}
		else {
			this.dibujarManzanas();
			this.dibujarCalles();
			this.dibujarNinjas();
			
			// Cuando el contador llega al numero especificado se crea la moneda.
			if(contObjetoExtra == 700) {
				inicializarBitcoin();
				contObjetoExtra = 0;
			}
			
			if (bitcoin != null) {
				bitcoin.dibujarBtc(entorno);
			}
			else {
				this.contObjetoExtra++;
			}

			if (sakura != null) {
				sakura.dibujarse(entorno);
				sakura.seMueveHori(entorno, this.calles);
				sakura.seMueveVerti(entorno, this.calles);
				
				if (this.entorno.sePresiono(entorno.TECLA_ESPACIO)) {
					if (sakura.getRasengan() == null)
						sakura.crearRasengan(entorno);
				}
				if (sakura.getRasengan() != null)
					this.sakura.efectuarRasengan(entorno);
				
				if (sakura.agarraBitcoin(bitcoin)) {
					puntaje.sumarPts(10);
					bitcoin = null;
				}
			}
			
			if (puntaje.getPuntos() >= 100) {
				this.terminarJuego("GANASTE!");
			}
			
			this.entorno.cambiarFont("arial", 20, Color.BLACK);
			puntaje.dibujarse(entorno);
			cantNinjasMuertos.dibujarse(entorno);
		}
	}

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Juego juego = new Juego();
	}
}
