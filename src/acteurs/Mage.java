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

	public Mage(int x, int y, int vitesse, Game game) {
		super(x, y, vitesse, game);
		this.setForce(25);
		this.manaMax = 100;
		this.mana = manaMax;
		this.regenThread = new RegenMana(this);
		
		
	}
	
	//Attaque la case au dessus du personnage
	public void attaqueUp(){
		if(mana >= 5){
			mana -= 5;	
			AttaqueDistance attaque = new AttaqueDistance(
					game, this, x, y, 0, -1, 20);	
			}
		}
		
	//Attaque la case en dessous du personnage
	public void attaqueDown(){
		if(mana >= 5){
			mana -= 5;	
			AttaqueDistance attaque = new AttaqueDistance(
					game, this, x, y, 0, 1, 20);	
			}		
		}
		
	//Attaque la case à droite du personnage
	public void attaqueRight(){
		if(mana >= 5){
			mana -= 5;	
			AttaqueDistance attaque = new AttaqueDistance(
					game, this, x, y, 1, 0, 20);	
			}
		}
		
	//Attaque la case à gauche du personnage
	public void attaqueLeft(){
		if(mana >= 5){
			mana -= 5;	
			AttaqueDistance attaque = new AttaqueDistance(
					game, this, x, y, -1, 0, 20);	
			}
		}
	
	public void render(Graphics g){
		super.render(g);
		Font font = new Font("Courier", Font.BOLD, 15); //Police
		g.setFont(font);
		g.setColor(Color.CYAN);
		g.drawString("Mana: " + mana + " / " + manaMax, 5* Game.WIDTH / 20, 20 );
	}
	
	public void tick(){
		super.tick();
		if(!regenThread.getIsRunning()) regenThread.start();
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
