package acteurs;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import competences.AttaqueSimple;
import items.Inventaire;
import main.Game;
import main.VisualGameObject;
import plateau.Case;

public abstract class Personnage implements VisualGameObject {
	
	protected Game game;
	protected int x;
	protected int y;
	private int vitesse;
	protected BufferedImage image;
	protected int vx;
	protected int vy;
	protected boolean estJoueur;
	private int HPMax;
	protected int HP;
	protected int force;
	protected Inventaire inventaire;
	
	/*
	 * Implémentation:
	 */
	
	public Personnage(int x, int y, Game game){
		this.x = x;
		this.y = y;
		this.game = game;
		this.vitesse = 1;
		this.HPMax = 100;
		this.HP = HPMax;
		this.force = 50;
		this.inventaire = construireInventaire();
	}
	
	protected abstract Inventaire construireInventaire();
	
	/* 
	 * Actualisation:
	 */
	
	public abstract void tick();
	
	
	
	public abstract void render(Graphics g);
		
	
	/*
	 * Setters:
	 */
	
	public void setX(int x){
		this.x = x;
	}
	public void setY(int y){
		this.y = y;
	}
	
	//Fait subir au personnage un dégat d
	public void getDammage(int d){
		this.HP -= d;
	}
	
	
	/*
	 * Getters:
	 */
	
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public boolean getEstJoueur(){
		return estJoueur;
	}
	
	public int getHPMax(){
		return HPMax;
	}
	
	//Renvoie la case sur laquelle se trouve le personnage:
	public Case getCase(){
		
		return this.game.getPlateau().getCase(x, y);
	}
	
	//Renvoie les cases accessibles au personnage
	protected ArrayList<Case> getReachableCases(){
		ArrayList<Case> ls = new ArrayList<Case>();
		
		int taille = this.game.getPlateau().getTaille();
		
		int imin = Math.max(0, x-vitesse);
		int imax = Math.min(taille-1, x+vitesse);
		int jmin = Math.max(0, y-vitesse);
		int jmax = Math.min(taille-1, y+vitesse);
		
		for(int i = imin ; i <= imax ; i++ ){
			//Cases accessibles à gauche et à droite
			Case caseTemp = this.game.getPlateau().getCase(i, y);
			if(caseTemp.getCaseType() == 0){
				ls.add(caseTemp);  //On vérifie que la case est une Dalle
			}
			
		}
		
		for(int j = jmin ; j<= jmax ; j++){
			//Cases accessibles en haut et en bas
			Case caseTemp = this.game.getPlateau().getCase(x, j);
			if(caseTemp.getCaseType() == 0){
				ls.add(caseTemp); //On vérifie que la case est une Dalle
			}
		}
		return ls;
	}
	
	
	 //Renvoie les cases libres accessibles au personnage
	protected ArrayList<Case> getFreeReachableCases(){
			
			ArrayList<Case> ls = getReachableCases();		
			
			for(int i = 0; i < ls.size(); i++){
				//On retire toutes les cases non libres:
				Case caseTemp = ls.get(i);
				int xTemp = caseTemp.getX();
				int yTemp = caseTemp.getY();
				if(! this.game.getPopulation().caseIsFree(xTemp, yTemp)){
					ls.remove(caseTemp);
				}
			}
			return ls;
	}
		
	public BufferedImage getImage(){
			return this.image;
	}
		
	//Renvoie les points de vie du personnage
	public int getHP(){
			return HP;
	}
	
	//Renvoie la force
	public int getForce(){
		return this.force;
	}
	
	public Inventaire getInventaire(){
		return this.inventaire;
	}

	/*
	 * Setters:
	 */
	
	public void setHP(int HP){
		if(HP <= HPMax){
			this.HP = HP;
		}
	}
	
	
	
	/*
	 * Mouvement:
	 */
	
	public void moveUp(){
		this.vy = -vitesse;
		this.vx = 0;
	}
	
	public void moveDown(){
		this.vy = vitesse;
		this.vx = 0;
	}
	
	public void moveRight(){
		this.vx = vitesse;
		this.vy = 0;
	}
	
	public void moveLeft(){
		this.vx = -vitesse;
		this.vy = 0;
	}
	
	public void immobilize(){
		this.vx = 0;
		this.vy = 0;
	}
	
	public void moveTo(Case cs){
		this.x = cs.getX();
		this.y = cs.getY();
	}
	
	
	/*
	 * Attaque:
	 */
	
	
	//Attaque l'ennemi ayant les corrdonnées (x,y)
	public synchronized void attaqueCoord(int x, int y, int dmg){
		if(!this.game.getPopulation().caseIsFree(x, y)){
			Personnage target = this.game.getPopulation().getPerso(x, y);
			target.getDammage(dmg);
		}	
	}
	
	//Attaque la case au dessus du personnage
	public void attaqueUp(){
		int xTarget = x;
		int yTarget = y-1;
		if(! this.game.getPopulation().caseIsFree(xTarget, yTarget)){
			Personnage target = this.game.getPopulation().getPerso(xTarget, yTarget);
			AttaqueSimple attaque = new AttaqueSimple(this, target);
			attaque.run();
			}
		
		//attaqueCoord(xTarget, yTarget, force);		
	}
	
	//Attaque la case en dessous du personnage
	public void attaqueDown(){
		int xTarget = x;
		int yTarget = y+1;
		if(! this.game.getPopulation().caseIsFree(xTarget, yTarget)){
			Personnage target = this.game.getPopulation().getPerso(xTarget, yTarget);
			AttaqueSimple attaque = new AttaqueSimple(this, target);
			attaque.run();
			}
		
		//attaqueCoord(xTarget, yTarget, force);	
		
	}
	
	//Attaque la case à droite du personnage
	public void attaqueRight(){
		int xTarget = x+1;
		int yTarget = y;
		if(! this.game.getPopulation().caseIsFree(xTarget, yTarget)){
			Personnage target = this.game.getPopulation().getPerso(xTarget, yTarget);
			AttaqueSimple attaque = new AttaqueSimple(this, target);
			attaque.run();
			}
		
		//attaqueCoord(xTarget, yTarget, force);	
		
	}
	
	//Attaque la case à gauche du personnage
	public void attaqueLeft(){
		int xTarget = x-1;
		int yTarget = y;
		if(! this.game.getPopulation().caseIsFree(xTarget, yTarget)){
			Personnage target = this.game.getPopulation().getPerso(xTarget, yTarget);
			AttaqueSimple attaque = new AttaqueSimple(this, target);
			attaque.run();
			}
		
		//attaqueCoord(xTarget, yTarget, force);	
		
	}
	
}
