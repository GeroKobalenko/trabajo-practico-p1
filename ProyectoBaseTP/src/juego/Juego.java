/**
 *
 * @autores Gutierrez, Kobalenko.
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
	private Ninja[] ninjas = new Ninja[4];
	private Calle[] calles = new Calle[7];
	private boolean movimiento=false;
	private boolean esHori=true;
	private int xNinja;
	private int yNinja;
	

	// Instancio los ninjas
	public void IniciarNinjas(int x, boolean esHori, boolean movimiento, int xNinja, int yNinja) {
		Random rant = new Random();
		for (int i = 0; i < this.ninjas.length; i++) {
			if (x == i) {
				if (esHori) { this.ninjas[i] = new Ninja(rant.nextInt(800),yNinja, 10, 15, 1, esHori,movimiento); } 
				else {this.ninjas[i] = new Ninja(xNinja, rant.nextInt(600), 10, 15, 1, esHori, movimiento); }
			}
		}
	}
	// Variables y métodos propios de cada grupo

	Juego() {
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "Sakura Ikebana Delivery", 800, 600);
		
		// Inicializar lo que haga falta para el juego
		this.sakura = new Sakura(this.entorno.ancho() / 2, 400, 10, 15);

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
		//Inicializo los ninjas
		int xNinja = this.entorno.ancho() / 5;
		int yNinja = (this.entorno.alto() / 6) - 10 ;
		for(int i=0; i < ninjas.length;i++) {
			if(i%2!=0){
				if(i==3) {
					xNinja = xNinja + this.entorno.ancho() / 4 + this.entorno.ancho() / 20;
					IniciarNinjas(i,esHori,movimiento,xNinja,yNinja); 
					movimiento= !movimiento;
					esHori= !esHori;
				}
					else{
						IniciarNinjas(i,esHori,movimiento,xNinja,yNinja); 
						esHori= !esHori;
						movimiento= !movimiento;
					}	
			}
			else{
				if(i==2){
					yNinja= ((this.entorno.alto() / 6) *4) - 20 ;
					IniciarNinjas(i,esHori,movimiento,xNinja,yNinja); 
					esHori= !esHori;
				}
				else{
					IniciarNinjas(i,esHori,movimiento,xNinja,yNinja); 
					esHori= !esHori;
				}
			}
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
		sakura.seMueveHori(entorno, this.calles);
		sakura.seMueveVerti(entorno, this.calles);
		
		
		if(this.entorno.sePresiono(entorno.TECLA_ESPACIO)){
			if (sakura.getRasengan() == null) sakura.crearRasengan(entorno);
		}
		
		if (sakura.getRasengan() != null) {
			this.sakura.efectuarRasengan(entorno);
		}
		

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

				// Verifica la colision del Rasengan respecto a los ninjas
				
				if (ninjas[i].choqueRasengan(sakura.getRasengan())) {
					sakura.setRasengan(null);
					ninjas[i] = null;
				}
				if(ninjas[i]==null) {
						if(i%2!=0) {
							if(i==1){
								this.movimiento= false;
								this.esHori= false;
								xNinja =(this.entorno.ancho() / 4)-40;
								IniciarNinjas(i,esHori,movimiento,xNinja,yNinja);
							}
							else{
								this.esHori= false;
								this.movimiento= true;
								xNinja = this.entorno.ancho() / 2;
								IniciarNinjas(i,esHori,movimiento,xNinja,yNinja);
							}
						}	
						else{
							if(i==0){
								this.esHori=true;
								yNinja= (this.entorno.alto()/6)-10;
								IniciarNinjas(i,esHori,movimiento,xNinja,yNinja);
							}
							else{
								this.esHori=true;
								this.movimiento=true;
								yNinja= ((this.entorno.alto()/6) * 4) - 20;
								IniciarNinjas(i,esHori,movimiento,xNinja,yNinja);
							}
					}
					ninjaMuerto=false;
				
			}
				// Para implementar cuando el ninja toca al pj
//				if (ninjas[i].tocaSakura(sakura)) {
//					this.entorno.escribirTexto("you lose", 400, 300);
////					System.out.println("TOUCHING SAKURA");
//					//this.entorno.removeAll();
//				}
			}
		}
	}

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Juego juego = new Juego();
	}
}
