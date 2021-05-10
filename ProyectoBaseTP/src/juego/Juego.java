package juego;


//import java.awt.Color;
//import java.awt.Image;
import entorno.Entorno;
//import entorno.Herramientas;
import entorno.InterfaceJuego;
import java.util.Random;

//import javax.naming.ldap.ManageReferralControl;

public class Juego extends InterfaceJuego
{
	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;
	private Sakura sakura;
	private Manzana[] manzana = new Manzana[20];
	private Ninjas[] ninjas = new Ninjas[4];
	private Calles[] callesHori = new Calles[4];
	private Calles[] callesVerti = new Calles[3];
	
	// Variables y métodos propios de cada grupo
	
	
	Juego()
	{
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "Gero, Martin Y Matias", 800, 600);
		
		// Inicializar lo que haga falta para el juego
		this.sakura = new Sakura(160, 300,40,30); //Le resto 25 para las calles horizontales
		//Instancio las manzanas
		int anchoManzana = this.entorno.ancho()/4;
		int altoManzana = this.entorno.alto()/5;
		int manzanaLength = this.manzana.length;
		int posx = 40;
		int posy = 0;
		for (int i = 0; i < manzanaLength; i++) {
			if (i == 4 || i == 8 || i == 12 || i == 16) {
				posx = 40;
				posy = posy + altoManzana + 30;
			}
			this.manzana[i] = new Manzana(posx,posy,anchoManzana,altoManzana,null);
			posx = posx + anchoManzana + 40;
		}
		//Instancio las calles
		this.callesHori[0] = new Calles(400, 75, 30, 800);
		this.callesHori[1] = new Calles(400, 225, 30, 800);
		this.callesHori[2] = new Calles(400, 375, 30, 800);
		this.callesHori[3] = new Calles(400, 525, 30, 800);
		this.callesVerti[0] = new Calles(160, 300, 600, 40);
		this.callesVerti[1] = new Calles(400, 300, 600, 40);
		this.callesVerti[2] = new Calles(640, 300, 600, 40);

		//Instancio los ninjas
		int ninjasLenght = this.ninjas.length;
		boolean esHorizontal=true;
		Random rant = new Random();
		for (int i = 0; i < ninjasLenght; i++) {
			if(i==0){
				this.ninjas[i] = new Ninjas(rant.nextInt(800), 60, 10, 10, 3,esHorizontal);
			}
			if(i==1){
				this.ninjas[i] = new Ninjas(160, rant.nextInt(600), 10, 10, 3,esHorizontal);
			}
			if(i==2){
				this.ninjas[i] = new Ninjas(rant.nextInt(800), 210, 10, 10, 3,esHorizontal);
			}
			if(i==3){
				this.ninjas[i] = new Ninjas(640, rant.nextInt(600), 10, 10, 3,esHorizontal);
			}
			esHorizontal = !esHorizontal;
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
		
        //manzana.dibujarManzana(entorno, 50, 0);
		for (int i = 0; i < this.manzana.length; i++) {
			this.manzana[i].dibujarManzana(entorno);
		}
		//calles
		this.callesHori[0].dibujarCalles(entorno);
		this.callesHori[1].dibujarCalles(entorno);
		this.callesHori[2].dibujarCalles(entorno);
		this.callesHori[3].dibujarCalles(entorno);
		this.callesVerti[0].dibujarCalles(entorno);
		this.callesVerti[1].dibujarCalles(entorno);
		this.callesVerti[2].dibujarCalles(entorno);
		

		sakura.dibujarse(entorno);
		

		//Ninjas
		ninjas[0].dibujar(entorno);
		ninjas[0].moverX();
		ninjas[0].tocaBorde(entorno);
		ninjas[1].dibujar(entorno);
		ninjas[1].moverY();
		ninjas[1].tocaBorde(entorno);
		ninjas[2].dibujar(entorno);
		ninjas[2].moverX();
		ninjas[2].tocaBorde(entorno);
		ninjas[3].dibujar(entorno);
		ninjas[3].moverY();
		ninjas[3].tocaBorde(entorno);
		
		//PRUEBA
		for (int i = 0; i < callesHori.length; i++) {
			if(sakura.getY()+40<=callesHori[i].getY()+(callesHori[i].getAlto()/2) &&
			 sakura.getY()+40>=callesHori[i].getY()-(callesHori[i].getAlto()/2)){
				sakura.seMueveHori(entorno);
			}
		}
		for (int i = 0; i < callesVerti.length; i++) {
			if(sakura.getX()<=callesVerti[i].getX()+(callesVerti[i].getAncho()/3) &&
			 sakura.getX()>=callesVerti[i].getX()-(callesVerti[i].getAncho()/3)){
				sakura.seMueveVerti(entorno);
			}
		}
				
	}	
	

	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		Juego juego = new Juego();
	}
}
