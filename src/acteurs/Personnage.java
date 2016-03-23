package acteurs;

import java.awt.Graphics;

public abstract class Personnage {
	
	protected int x;
	protected int y;
	protected int vitesse;
	boolean estJoueur;
	
	public Personnage(int x, int y, int vitesse, boolean estJoueur){
		this.x = x;
		this.y = y;
		this.vitesse = vitesse;
		this.estJoueur = estJoueur;
	}
	public abstract void tick();
	public abstract void render(Graphics g);
	
	//Setters:
	
	public void setX(int x){
		this.x = x;
	}
	public void setY(int y){
		this.y = y;
	}
	
	//Getters:
	
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public boolean getEstJoueur(){
		return estJoueur;
	}
	
	//Mouvement:
	
	public void moveUp(){
		this.y -= vitesse;
	}
	
	public void moveDown(){
		this.y += vitesse;
	}
	
	public void moveRight(){
		this.x += vitesse;
	}
	
	public void moveLeft(){
		this.x -= vitesse;
	}
	

}
