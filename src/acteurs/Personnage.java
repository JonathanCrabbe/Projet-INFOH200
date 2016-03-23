package acteurs;

import java.awt.Graphics;

public abstract class Personnage {
	
	protected int x;
	protected int y;
	private int vitesse;
	protected int vx;
	protected int vy;
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

}
