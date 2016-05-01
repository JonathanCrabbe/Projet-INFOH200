package plateau;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import javax.imageio.ImageIO;

import acteurs.Personnage;
import inventaire.Inventaire;
import main.VisualGameObject;
import observateur.Observer;

public abstract class Case implements  Observer, Serializable{
	
	
	private int x, y;
	protected int caseType; //Type de case (mur, dalle)
	private Inventaire butin; //Loot contenu sur la case
	private boolean lieuAOE; //True si la case subit une AOE

	public static int dim = 50; //Dimension des cases
	
	/*
	 * Constructeur et représentation:
	 */

	public Case(int x, int y, int caseType){
		this.x = x;
		this.y = y;
		this.caseType = caseType;
	}
	
	public abstract void render(Graphics g, int xi, int yi);
	
	
	
	/*
	 * Getters:
	 */
	
	public int getCaseType(){
		return this.caseType;
	}
	public int getX(){
		return this.x;
	}
	
	public int getY(){
		return this.y;
	}
	
	public Inventaire getButin(){
		return this.butin;
	}
	
	public boolean getLieuAOE(){
		return this.lieuAOE;
	}
	
	public double getDist(Case autreCase){ 
		/*
		 * Renvoie la distance entre cette
		 * case et l'autre case (distance Manhattan)
		 */
		
		int x2 = autreCase.getX();
		int y2 = autreCase.getY();
		return (Math.abs(x-x2) + Math.abs(y-y2));
	}
	
	/*
	 * Setters:
	 */
	
	public void setButin(Inventaire butin){
		this.butin = butin;
	}
	
	/*
	 * Inventaire
	 */
	
	public void afficherButin(){
		if(this.butin != null){
			butin.changeVisible();
		}
	}
	
	/*
	 * Design Pattern Observer (la Case observe les AOE)
	 */

	public synchronized void update(){ //Switch l'état
		
		this.lieuAOE = !this.lieuAOE;
	}
	

}
