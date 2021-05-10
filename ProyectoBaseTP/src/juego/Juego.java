package juego;


import java.awt.Color;
import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego
{
	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;
	private Sakura sakura;
	private Manzana[] manzana = new Manzana[20];
	private Ninjas[] ninjas = new Ninjas[2];
	
	// Variables y métodos propios de cada grupo
	
	
	Juego()
	{
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "Gero, Martin Y Matias", 800, 600);
		
		// Inicializar lo que haga falta para el juego
		this.sakura = new Sakura(400, 275,0,0);
		Ninjas ninja1 = new Ninjas(200, 100, 100, 5, 2, true);
		Ninjas ninja2= new Ninjas(175, 200, 50, 5, 2, false);
		this.ninjas[0] = ninja1;
		this.ninjas[1] = ninja2;
		
		//Instancio las manzanas
		int anchoManzana = this.entorno.ancho()/4;
		int altoManzana = this.entorno.alto()/5;
		int manzanaLength = this.manzana.length;
		int posx = 40;
		int posy = 30;
		for (int i = 0; i < manzanaLength; i++) {
			if (i == 4 || i == 8 || i == 12 || i == 16) {
				posx = 40;
				posy = posy + altoManzana + 30;
			}
			this.manzana[i] = new Manzana(posx,posy,anchoManzana,altoManzana,null);
			posx = posx + anchoManzana + 40;
		}
		
		
		// Inicia el juego!
		this.entorno.iniciar();
	}

	/**
	 * Durante el juego, el método tick() será ejecutado en cada instante y 
	 * por lo tanto es el método más importante de esta clase. Aquí se debe 
	 * actualizar el estado interno del juego para simular el paso del tiempo 
	 * (ver el enunciado del TP para mayor detalle).
	 */
	public void tick()
	{
		// Procesamiento de un instante de tiempo
		// ...
		
		
//		manzana.dibujarManzana(entorno, 50, 0);
		for (int i = 0; i < this.manzana.length; i++) {
			this.manzana[i].dibujarManzana(entorno);
		}
		sakura.dibujarse(entorno);
		sakura.seMueve(entorno);
		

		
		
		ninjas[0].dibujar(entorno);
		ninjas[0].moverX();
		ninjas[0].tocaBorde(entorno);
		
		ninjas[1].dibujar(entorno);
		ninjas[1].moverY();
		ninjas[1].tocaBorde(entorno);
	}
	

	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		Juego juego = new Juego();
	}
}
