package juego;


import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego
{
	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;
	private Sakura Sakura;
	private Ninjas Ninjas;
	
	// Variables y métodos propios de cada grupo
	// ...
	// ACA HICE UN COMENTARIO DE asfasg
	
	
	// Instancio un objeto tipo imagen y con el metodo cargarImagen de la class herramientas lo cargo/asigno.
	//Image imagen = this.herramientas.cargarImagen("images/sakura.png");
	
	Juego()
	{
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "Gero, Martin Y Matias", 800, 600);
		
		// Inicializar lo que haga falta para el juego
		// ...
		this.Sakura = new Sakura(300, 300, 10, 5);
		this.Ninjas = new Ninjas(200, 100, 100, 5, 10);
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
		Sakura.dibujarse(entorno);
		if(this.entorno.estaPresionada(this.entorno.TECLA_ARRIBA)
				&& this.Sakura.getY()>40){
			Sakura.moverArriba();
		}
		if(this.entorno.estaPresionada(this.entorno.TECLA_ABAJO)
				&& this.Sakura.getY()<this.entorno.getHeight()-80){
			Sakura.moverAbajo();
		}
		if(this.entorno.estaPresionada(this.entorno.TECLA_DERECHA)
				&& this.Sakura.getX()<this.entorno.getWidth()-50){
			Sakura.moverDerecha();
		}
		if(this.entorno.estaPresionada(this.entorno.TECLA_IZQUIERDA)
				&& this.Sakura.getX()>30){
			Sakura.moverIzquierda();
		}
		Ninjas.dibujar(entorno);

	}
	

	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		Juego juego = new Juego();
	}
}
