package acteurs;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import competences.AttaqueDistance;
import competences.AttaqueSimple;
import competences.RegenMana;
import main.Game;

public class Mage extends Joueur {
	
	private int mana;
	private int manaMax;
	private RegenMana regenThread;
	
	
	/*
	 * Constructeur et actualisation:
	 */

	public Mage(int x, int y, int vitesse, Game game) {
		super(x, y, vitesse, game);
		this.setForce(25);
		this.manaMax = 100;
		this.mana = manaMax;
		
		//Lance le thread qui permets au mage de récupérer périodiquement sa mana:
		this.regenThread = new RegenMana(this); 
		
		
	}
	
	public void tick(){
		super.tick();
		if(!regenThread.getIsRunning()) regenThread.start(); 
		//Utile pour les parties chargées car le Thread n'est pas sérializé 
	}
	
	public void render(Graphics g){
		super.render(g);
		Font font = new Font("Courier", Font.BOLD, 15); //Police
		g.setFont(font);
		g.setColor(Color.CYAN);
		g.drawString("Mana: " + mana + " / " + manaMax, 5* Game.WIDTH / 20, 20 );
	}

	/*
	 * Attaque:
	 */
	
	

	
	public void attaqueUp(){ //Attaque la case au dessus du personnage
		if(mana >= 5){	//Le mage a-t-il assez de mana?
			mana -= 5;	
			AttaqueDistance attaque = new AttaqueDistance(
					game, this, getX(), getY(), 0, -1, 20);	
			}
		}
		
	
	public void attaqueDown(){ //Attaque la case en dessous du personnage
		if(mana >= 5){
			mana -= 5;	
			AttaqueDistance attaque = new AttaqueDistance(
					game, this, getX(), getY(), 0, 1, 20);	
			}		
		}
		
	
	public void attaqueRight(){ //Attaque la case à droite du personnage
		if(mana >= 5){
			mana -= 5;	
			AttaqueDistance attaque = new AttaqueDistance(
					game, this, getX(), getY(), 1, 0, 20);	
			}
		}
		
	
	public void attaqueLeft(){ //Attaque la case à gauche du personnage
		if(mana >= 5){
			mana -= 5;	
			AttaqueDistance attaque = new AttaqueDistance(
					game, this, getX(), getY(), -1, 0, 20);	
			}
		}
	
	
	
	/*
	 * Getters:
	 */
	
	public int getMana(){
		return this.mana;
	}
	
	/*
	 * Setters:
	 */
	
	public void setMana(int mana){
		if(mana > 0 && mana <= manaMax){
			this.mana = mana;
		}
	}

}
