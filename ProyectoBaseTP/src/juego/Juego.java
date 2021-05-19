package juego;

import entorno.Entorno;
import entorno.InterfaceJuego;
import java.awt.Color;
import java.util.LinkedList;
import java.util.Random;


public class Juego extends InterfaceJuego {
	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;
	private Sakura sakura;
	private Ninja[] ninjas = new Ninja[6];
	private Calle[] calles = new Calle[6];
	private Manzana[] manzanas = new Manzana[16];
	private boolean movimiento=false;
	private boolean esHori=true;
	private int xNinja;
	private int yNinja;
	private int[] contI = { 0, 0, 0, 0 };
	private int[] ninjaI = { -1, -1, -1, -1 };
	
//	private Timer tiempo = new Timer();
//	private TimerTask task;
//	private int segundos = 0;

	Juego() {
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "Sakura Ikebana Delivery", 800, 600);
		
		// Inicializar lo que haga falta para el juego
		this.sakura = new Sakura(this.entorno.ancho() / 2, 400, 10, 15);
		this.inicializarManzanas();
		this.inicializarCalles();
		this.inicializarNinjas();

		// Inicia el juego!
		this.entorno.iniciar();
	}
	
	void inicializarCalles() {
		//Posiciones de clles iniciales
		int calleX = 190;
		int calleY = 145;
		// Ciclo y añado un objeto Calle a cada elemento del array.
		for (int i = 0; i < calles.length; i++) {
			if (i < 3) {
				this.calles[i] = new Calle(this.entorno.ancho() / 2, calleY, 30, this.entorno.ancho(), true);
				calleY = calleY + this.entorno.alto()/4 + 10;
			} else {
				this.calles[i] = new Calle(calleX, this.entorno.alto() / 2, this.entorno.alto(), 40, false);
				calleX = calleX + this.entorno.ancho()/4 + 10;
			}
		}
	}
	
	void inicializarManzanas() {
		int anchoManzana = 170;
		int altoManzana = 130;
		int xManzana = 85;
		int yManzana = 65;
		
		for (int i = 0; i < manzanas.length; i++) {
			manzanas[i] = new Manzana(xManzana,yManzana,anchoManzana,altoManzana);
			xManzana = xManzana + anchoManzana + this.entorno.ancho()/20;
			if (xManzana >= this.entorno.ancho()) {
				xManzana = 85;
				yManzana = yManzana + altoManzana + this.entorno.alto()/20;
			}
		}
	}
	
	void inicializarNinjas() {
		boolean esHorizontal = true;
		boolean movimiento = false;
		Random rant = new Random();
		int xNinja = this.entorno.ancho()/4 - 10;
		for (int i = 0; i < this.ninjas.length; i++) {
			if (esHorizontal) {
				this.ninjas[i] = new Ninja(rant.nextInt(800), this.calles[i].getY() - 5, 10, 15, 2, esHorizontal,movimiento);
			} else {
				this.ninjas[i] = new Ninja(xNinja, rant.nextInt(600), 10, 15, 2, esHorizontal, movimiento);
				movimiento = !movimiento;
				xNinja = xNinja + this.entorno.ancho()/4 + 10;
			}
			esHorizontal = !esHorizontal;
		}
	}
	
	public void iniciarNinja(int x, boolean esHori, boolean movimiento, int xNinja, int yNinja) {
		Random rant = new Random();
		for (int i = 0; i < this.ninjas.length; i++) {
			if (x == i) {
				if (esHori) { this.ninjas[i] = new Ninja(rant.nextInt(800),yNinja, 10, 15, 1, esHori,movimiento); } 
				else {this.ninjas[i] = new Ninja(xNinja, rant.nextInt(600), 10, 15, 1, esHori, movimiento); }
			}
		}
	}
	
	void dibujarManzanas() {
		for (Manzana manzana : manzanas) {
			manzana.dibujarManzana(entorno);
			for (Casa casa : manzana.getCasas()) {
				if (casa != null) casa.dibujarCasa(entorno);
			}
		}
	}
	
	void dibujarCalles() {
		for (Calle calle : calles) {
			calle.dibujarCalle(entorno);
		}
	}
	
	void dibujarNinjas() {

		for (int i = 0; i < ninjas.length; i++) {
			Ninja ninjaAux = ninjas[i];
			
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
					// ninjai = i;
					ninjaI[i] = i;
				}
			}
//			else {
//				if (ninjaI[i] == 0) {
//					contI[i] = contI[i]+1;
//					if (contI[0] > 300) {
//						this.esHori = true;
//						yNinja = (this.entorno.alto() / 6) - 10;
//						iniciarNinja(ninjaI[0], esHori, movimiento, xNinja, yNinja);
//						ninjaI[i] = -1;
//						contI[i] = 0;
//					}
//				}
////				ninjas[i] = new Ninja();
//			}
			// System.out.println(cont);
			// Para implementar cuando el ninja toca al pj
			// if (ninjas[i].tocaSakura(sakura)) {
			// this.entorno.escribirTexto("you lose", 400, 300);
			//// System.out.println("TOUCHING SAKURA");
			// //this.entorno.removeAll();
			// }
		}
		if (ninjaI[0] == 0 && ninjas[0]==null) {
			//System.out.println("0");
			contI[0] = contI[0]+1;
			System.out.println(contI[0]);
			if (contI[0] > 300) {
				this.esHori = true;
				yNinja = (this.entorno.alto() / 6) - 10;
				iniciarNinja(ninjaI[0], esHori, movimiento, xNinja, yNinja);
				ninjaI[0] = -1;
				contI[0] = 0;
			}
		}
		if (ninjaI[1] == 1 && ninjas[1]==null) {
			contI[1] = contI[1]+1;
			if (contI[1] > 300) {
				this.movimiento=false;
				this.esHori = false;
				xNinja = (this.entorno.ancho() / 4) - 40;
				iniciarNinja(ninjaI[1], esHori, movimiento, xNinja, yNinja);
				ninjaI[1] = -1;
				contI[1] = 0;
			}
		}
		if (ninjaI[2] == 2 && ninjas[2]==null) {
			contI[2] = contI[2]+1;
			if (contI[2] > 300) {
				this.movimiento=true;
				this.esHori = true;
				yNinja = ((this.entorno.alto() / 6) * 4) - 10;
				iniciarNinja(ninjaI[2], esHori, movimiento, xNinja, yNinja);
				ninjaI[2] = -1;
				contI[2] = 0;
			}
		}
		if (ninjaI[3] == 3 && ninjas[3]==null) {
			contI[3] = contI[3]+1;
			if (contI[3] > 300) {
				this.movimiento=true;
				this.esHori = false;
				xNinja = this.entorno.ancho() / 2;
				iniciarNinja(ninjaI[3], esHori, movimiento, xNinja, yNinja);
				ninjaI[3] = -1;
				contI[3] = 0;
			}
		}
	}
	
	void reaparecerNinja() {
		
	}
	
	
//	public void tiempo() {
//		this.tiempo = new Timer();
//		this.tiempo.s
//	}
	
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
		
		if(this.entorno.sePresiono(entorno.TECLA_ESPACIO)){
			if (sakura.getRasengan() == null) sakura.crearRasengan(entorno);
		}
		if (sakura.getRasengan() != null) this.sakura.efectuarRasengan(entorno);
		
		
	}
		
		
		

		// Ninjas
//		for (int i = 0; i < ninjas.length; i++) {
//			if (ninjas[i] != null) {
//				ninjas[i].dibujar(entorno);
//				ninjas[i].tocaBorde(entorno);
//				if (ninjas[i].getEsHorizontal()) {
//					ninjas[i].moverX();
//				} else {
//					ninjas[i].moverY();
//				}
//				
//				// Verifica la colision del Rasengan respecto a los ninjas
//				
//				if (ninjas[i].choqueRasengan(sakura.getRasengan())) {
//					sakura.setRasengan(null);
//					ninjas[i] = null;
//				}
//				// Para implementar cuando el ninja toca al pj
////				if (ninjas[i].tocaSakura(sakura)) {
////					this.entorno.escribirTexto("you lose", 400, 300);
//////					System.out.println("TOUCHING SAKURA");
////					//this.entorno.removeAll();
////				}
//			}
//		}
		
	// Para implementar cuando el ninja toca al pj
//	if (ninjas[i].tocaSakura(sakura)) {
//	this.entorno.escribirTexto("you lose", 400, 300);
//	System.out.println("TOUCHING SAKURA");
//	this.entorno.removeAll();
//	}

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Juego juego = new Juego();
	}
}
