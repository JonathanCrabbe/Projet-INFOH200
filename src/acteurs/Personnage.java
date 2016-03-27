package acteurs;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

public abstract class Personnage {
	
	protected int x;
	protected int y;
	private int vitesse;
	protected BufferedImage image;
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
	
	
	
	public void render(Graphics g){
		g.drawImage(image,x, y, 20,20, null);
	}
	
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
	public BufferedImage getImage(){
		return this.image;
	}

}
