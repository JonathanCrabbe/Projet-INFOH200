package competences;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import acteurs.Personnage;
import main.Game;
import observateur.Observer;
import observateur.Subject;

public class AttaqueDistance implements Runnable, Subject  {
	
	private Game game;
	private Personnage attaquant;
	private ArrayList<Observer> observers = new ArrayList<Observer>(); 
	private int x, y; //Position de l'attaque
	private int vx, vy; //Vitesse instantan�e de l'attaque
	private int dmg;  //D�gats caus� par l'attaque
	private Thread thread;  //Thread associ�
	
	private final int  range = 7; //Port�e de l'attaque
	private  int waitTime; //Temps d'attente pour le thread
	
	/*
	 * Constructeur et ex�cution:
	 */

	public AttaqueDistance(Game game, Personnage attaquant, int x,
			int y, int vx, int vy, int vitesse) {
		this.game = game;
		this.attaquant = attaquant;
		
		
		this.dmg = attaquant.getForce();
		this.x = x;
		this.y = y;
		this.vx = vx;
		this.vy = vy;
		this.waitTime = 1000/vitesse;
		this.thread = new Thread(this);
		thread.start();
	}
	
	public void run(){
		for(int i = 0; i < range ; i++){
			
			this.x += vx;
			this.y += vy;
			
			//V�rifier que l'attaque n'a pas rencontr� de mur:
			
			if(!this.game.getPlateau().estDalle(x, y)){
				try {
					thread.join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			/*
			 * L'ancienne case doit �tre mise � jour
			 * du fait que l'attaque � distance ne s'y trouve plus
			 */
			this.attach(this.game.getPlateau().getCase(x, y));
			this.notifyObserver();
			
		
			
			if(!this.game.getPopulation().caseIsFree(x,y)){ //Si un joueut est touch�, l'attaque est d�truite
				Personnage persoTemp = this.game.getPopulation().getPerso(x, y);
				persoTemp.getDammage(dmg);
				try {
					this.notifyObserver();
					thread.join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			try {
				thread.sleep(waitTime);  //Pour rendre le d�placement de l'attaque non instantann�
			} catch (InterruptedException e) { 
				e.printStackTrace();
			}
			this.notifyObserver();
			this.observers.remove(0);
		}
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	/*
	 * Design Pattern Observer (l'attaque est observ�e par des dalles pour son affichage)
	 */
	
	public void attach(Observer o){
		this.observers.add(o);
	}
	
	public void notifyObserver(){
		for(Observer oTemp:observers){
			oTemp.update();
		}
	}
	
	

}
