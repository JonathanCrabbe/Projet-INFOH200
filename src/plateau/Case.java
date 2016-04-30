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
	
	
	protected int x;
	protected int y;
	protected int caseType;
	private Inventaire butin;
	
	private boolean lieuAOE;


	
	
	public static int dim = 50;

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

	public synchronized void update(){
		//Switch l'état
		this.lieuAOE = !this.lieuAOE;
	}
	

}
