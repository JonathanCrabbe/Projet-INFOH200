package acteurs;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import GUI.ImageContainer;
import competences.AttaqueDistance;
import main.Game;

public class Archer extends Joueur {
	
	private int nombreFleches;
	
	
	/*
	 * Constructeur et affichage:
	 */

	public Archer(int x, int y, int vitesse, Game game) {
		super(x, y, vitesse, game);
		this.nombreFleches = 20;
		this.setForce(100);
	}
	
	public void render(Graphics g){
		super.render(g);
		Font font = new Font("Courier", Font.BOLD, 15); //Police
		g.setFont(font);
		g.setColor(Color.CYAN);
		g.drawString("Fl�ches: " + nombreFleches, 5* Game.WIDTH / 20, 20 );
	}
	
	
	/*
	 * Attaques:
	 * 
	 */

	
	public void attaqueUp(){ //Attaque la case au dessus du personnage
			if(nombreFleches >= 1){ //A-t-on des fl�ches? 
				//On adapte l'image � la direction de la fl�che:
				ImageContainer.setProjectile("Images/Projectiles/FlecheU.png");
				nombreFleches -= 1;			
				AttaqueDistance attaque = new AttaqueDistance(
						game, this, getX(), getY(), 0, -1, 40); //Cr�ation du Thread qui g�re l'attaque
				}
			}
			
	
	public void attaqueDown(){ //Attaque la case en dessous du personnage(m�me principe)
			if(nombreFleches >= 1){
				ImageContainer.setProjectile("Images/Projectiles/FlecheB.png");
				nombreFleches -= 1;
				AttaqueDistance attaque = new AttaqueDistance(
						game, this, getX(), getY(), 0, 1, 40);	
				}		
			}
			
	
	public void attaqueRight(){ //Attaque la case � droite du personnage(m�me principe)
			if(nombreFleches >= 1){
				ImageContainer.setProjectile("Images/Projectiles/FlecheD.png");
				nombreFleches -= 1;
				AttaqueDistance attaque = new AttaqueDistance(
						game, this, getX(), getY(), 1, 0, 40);	
				}
			}
			
	
	public void attaqueLeft(){ //Attaque la case � gauche du personnage(m�me principe)
			if(nombreFleches >= 1){
				ImageContainer.setProjectile("Images/Projectiles/FlecheG.png");
				nombreFleches -= 1;
				AttaqueDistance attaque = new AttaqueDistance(
						game, this, getX(), getY(), -1, 0, 40);	
				}
			}
	
	/*
	 * Setters:
	 */
	
	public void incrementFleche(){
		this.nombreFleches += 1;
	}

}
