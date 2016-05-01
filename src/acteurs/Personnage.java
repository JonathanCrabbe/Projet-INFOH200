package acteurs;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.ArrayList;

import competences.AttaqueSimple;
import inventaire.Inventaire;
import main.Game;
import main.VisualGameObject;
import plateau.Case;

public abstract class Personnage implements VisualGameObject, Serializable {
	
	protected Game game;
	private int x,y; //Position
	private int vitesse; //Vitesse en norme
	private int vx, vy; //Vitesses instantanées
	private boolean estJoueur; //Indique si le personnage est joueur
	private int HPMax; //Points de vie max. du personnage
	private int HP; //Points de via instantannés du personnage
	private int force; //Dégats infligés par une attaque
	private Inventaire inventaire;
	
	/*
	 * Constructeur et actualisation:
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
	
	
	protected abstract Inventaire construireInventaire(); //A redéfinir dans les classes filles
	public abstract void tick();
	public abstract void render(Graphics g);
		
	
	/*
	 * Setters:
	 */
	
	public void setX(int x){
		if(x > 0 && x < game.getTaillePlateau()) this.x = x;
	}
	public void setY(int y){
		if(y > 0 && y < game.getTaillePlateau()) this.y = y;
	}
	
	public void setVx(int vx) {
		this.vx = vx;
	}

	public void setVy(int vy) {
		this.vy = vy;
	}

	public void setHP(int HP){
		if(HP <= HPMax){
			this.HP = HP;
		}
	}

	public void setHPMax(int HPMax){
		if(HPMax > 0) this.HPMax = HPMax;
	}
	
	public void setForce(int force){
		if(force > 0) this.force = force;
	}
	
	public void setEstJoueur(boolean ans){
		this.estJoueur = ans;
	}
	
	public void setInventaire(Inventaire inventaire){
		this.inventaire = inventaire;
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
	public int getVx() {
		return vx;
	}

	public int getVy() {
		return vy;
	}

	public boolean getEstJoueur(){
		return estJoueur;
	}
	
	public int getHPMax(){
		return HPMax;
	}
	
	
	public Case getCase(){ //Renvoie la case sur laquelle se trouve le personnage:
		
		return this.game.getPlateau().getCase(x, y);
	}
	
	
	public ArrayList<Case> getReachableCases(){ //Renvoie les cases accessibles au personnage
		ArrayList<Case> ls = new ArrayList<Case>();	
		int taille = this.game.getPlateau().getTaille();
		
		//Bornes du champ de vision:
		
		int imin = Math.max(0, x-vitesse);
		int imax = Math.min(taille-1, x+vitesse);
		int jmin = Math.max(0, y-vitesse);
		int jmax = Math.min(taille-1, y+vitesse);
		
		//For séparés car pas de déplacement diagonaux:
		
		for(int i = imin ; i <= imax ; i++ ){
			//Cases accessibles à gauche et à droite
			Case caseTemp = this.game.getPlateau().getCase(i, y);
			if(caseTemp.getCaseType() == 0){ //On vérifie que la case est une Dalle
				ls.add(caseTemp);  
			}
			
		}
		
		for(int j = jmin ; j<= jmax ; j++){
			//Cases accessibles en haut et en bas
			Case caseTemp = this.game.getPlateau().getCase(x, j);
			if(caseTemp.getCaseType() == 0){ //On vérifie que la case est une Dalle
				ls.add(caseTemp); 
			}
		}
		return ls;
	}
	
	
	 
	public ArrayList<Case> getFreeReachableCases(){ //Renvoie les cases libres accessibles au personnage
			
			ArrayList<Case> ls = getReachableCases();		
			
			for(Case caseTemp:ls){
				int xTemp = caseTemp.getX();
				int yTemp = caseTemp.getY();
				if(! this.game.getPopulation().caseIsFree(xTemp, yTemp)){
					ls.remove(caseTemp); //On retire toutes les cases non libres
				}
			}
			return ls;
	}
		
	
		
	public int getHP(){
			return HP;
	}
	
	public int getForce(){
		return this.force;
	}
	
	public Inventaire getInventaire(){
		return this.inventaire;
	}

	/*
	 * Mouvement:
	 */
	
	public void moveUp(){
		this.setVy(-vitesse);
		this.setVx(0);
	}
	
	public void moveDown(){
		this.setVy(vitesse);
		this.setVx(0);
	}
	
	public void moveRight(){
		this.setVx(vitesse);
		this.setVy(0);
	}
	
	public void moveLeft(){
		this.setVx(-vitesse);
		this.setVy(0);
	}
	
	public void immobilize(){
		this.setVx(0);
		this.setVy(0);
	}
	
	public synchronized void moveTo(Case cs){
		this.x = cs.getX();
		this.y = cs.getY();
	}
	
	
	/*
	 * Attaque:
	 */
	
	
	public synchronized void attaqueCoord(int x, int y, int dmg){ //Fait subir à l'ennemi ayant les corrdonnées (x,y) le dégat dmg
		if(!this.game.getPopulation().caseIsFree(x, y)){ //Si la case est occupée:
			Personnage target = this.game.getPopulation().getPerso(x, y); 
			target.getDammage(dmg); //On fait subir à la cible les dégats
		}	
	}
	
	public void attaqueUp(){ //Attaque la case au dessus du personnage
		int xTarget = x;
		int yTarget = y-1;
		if(! this.game.getPopulation().caseIsFree(xTarget, yTarget)){ //Si la caase visée est occupée:
			Personnage target = this.game.getPopulation().getPerso(xTarget, yTarget);
			AttaqueSimple attaque = new AttaqueSimple(this, target);  //On y créer un thread qui gère l'attaque
			attaque.run();
			}	
	}
	
	
	public void attaqueDown(){ //Attaque la case en dessous du personnage
		int xTarget = x;
		int yTarget = y+1;
		if(! this.game.getPopulation().caseIsFree(xTarget, yTarget)){
			Personnage target = this.game.getPopulation().getPerso(xTarget, yTarget);
			AttaqueSimple attaque = new AttaqueSimple(this, target);
			attaque.run();
			}
	}
	
	
	public void attaqueRight(){ //Attaque la case à droite du personnage
		int xTarget = x+1;
		int yTarget = y;
		if(! this.game.getPopulation().caseIsFree(xTarget, yTarget)){
			Personnage target = this.game.getPopulation().getPerso(xTarget, yTarget);
			AttaqueSimple attaque = new AttaqueSimple(this, target);
			attaque.run();
			}
	}
	
	
	public void attaqueLeft(){ //Attaque la case à gauche du personnage
		int xTarget = x-1;
		int yTarget = y;
		if(! this.game.getPopulation().caseIsFree(xTarget, yTarget)){
			Personnage target = this.game.getPopulation().getPerso(xTarget, yTarget);
			AttaqueSimple attaque = new AttaqueSimple(this, target);
			attaque.run();
			}	
	}
	
	
	public synchronized void getDammage(int d){ //Fait subir au personnage un dégat d
		this.HP -= d;
	}


	/*
	 *  Inventaire
	 */
	
	protected void dropInventory(){
		
		this.inventaire.setPorteur(null);
		Case caseDrop = this.getCase();
		caseDrop.setButin(this.inventaire);
	}
}
