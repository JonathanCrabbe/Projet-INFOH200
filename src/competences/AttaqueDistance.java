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
	private int x, y;
	private int vx, vy;
	private int dmg;
	private Thread thread;
	
	private final int  range = 7;
	private  int waitTime;

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
			
			//Vérifier que l'attaque n'a pas rencontré de mur:
			
			if(!this.game.getPlateau().estDalle(x, y)){
				try {
					thread.join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			/*
			 * L'ancienne case doit être mise à jour
			 * du fait que l'attaque à distance ne s'y trouve plus
			 */
			this.attach(this.game.getPlateau().getCase(x, y));
			this.notifyObserver();
			
		
			
			if(!this.game.getPopulation().caseIsFree(x,y)){
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
				thread.sleep(waitTime);
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
	
	public void attach(Observer o){
		this.observers.add(o);
	}
	
	public void notifyObserver(){
		for(Observer oTemp:observers){
			oTemp.update();
		}
	}
	
	

}
