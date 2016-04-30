package acteurs;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import GUI.ImageContainer;
import competences.AttaqueDistance;
import main.Game;

public class Archer extends Joueur {
	
	private int nombreFleches = 20;

	public Archer(int x, int y, int vitesse, Game game) {
		super(x, y, vitesse, game);
		this.setForce(100);
	}
	
	//Attaque la case au dessus du personnage
	public void attaqueUp(){
			if(nombreFleches >= 1){
				ImageContainer.setProjectile("Images/Projectiles/FlecheU.png");
				nombreFleches -= 1;	
				AttaqueDistance attaque = new AttaqueDistance(
						game, this, x, y, 0, -1, 40);	
				}
			}
			
	//Attaque la case en dessous du personnage
	public void attaqueDown(){
			if(nombreFleches >= 1){
				ImageContainer.setProjectile("Images/Projectiles/FlecheB.png");
				nombreFleches -= 1;
				AttaqueDistance attaque = new AttaqueDistance(
						game, this, x, y, 0, 1, 40);	
				}		
			}
			
	//Attaque la case à droite du personnage
	public void attaqueRight(){
			if(nombreFleches >= 1){
				ImageContainer.setProjectile("Images/Projectiles/FlecheD.png");
				nombreFleches -= 1;
				AttaqueDistance attaque = new AttaqueDistance(
						game, this, x, y, 1, 0, 40);	
				}
			}
			
	//Attaque la case à gauche du personnage
	public void attaqueLeft(){
			if(nombreFleches >= 1){
				ImageContainer.setProjectile("Images/Projectiles/FlecheG.png");
				nombreFleches -= 1;
				AttaqueDistance attaque = new AttaqueDistance(
						game, this, x, y, -1, 0, 40);	
				}
			}
	
	public void incrementFleche(){
		this.nombreFleches += 1;
	}
	
	public void render(Graphics g){
		super.render(g);
		Font font = new Font("Courier", Font.BOLD, 15); //Police
		g.setFont(font);
		g.setColor(Color.CYAN);
		g.drawString("Flèches: " + nombreFleches, 5* Game.WIDTH / 20, 20 );
	}

}
