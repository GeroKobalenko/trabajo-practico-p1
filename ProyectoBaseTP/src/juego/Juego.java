package juego;


import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego
{
	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;
	private Herramientas herramientas;
	
	// Variables y métodos propios de cada grupo
	// ...
	// ACA HICE UN COMENTARIO DE asfasg
	
	
	// Instancio un objeto tipo imagen y con el metodo cargarImagen de la class herramientas lo cargo/asigno.
	Image imagen = this.herramientas.cargarImagen("images/sakura.png");
	
	Juego()
	{
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "Boss Rabbit Rabber - Grupo ... - v1", 800, 600);
		
		// Inicializar lo que haga falta para el juego
		// ...

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
		
		//Tengo que ponerlo en el entorno a la imagen previamente creada
		this.entorno.dibujarImagen(imagen, 400, 300, 0);

	}
	

	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		Juego juego = new Juego();
	}
}
