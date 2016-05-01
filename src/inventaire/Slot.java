package inventaire;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.Serializable;

import acteurs.Joueur;
import items.Item;
import main.Game;
import main.VisualGameObject;
import plateau.Dalle;

public abstract class Slot extends Rectangle implements  Serializable { 
	/*
	 *  Cette classe gère les slots d'inventaire et contiennent un objet.
	 *  Hérite de Rectangle pour l'interraction avec la souris.
	 */
	private  Game game;
	private Inventaire inventaire;
	private Item item;
	private int x,y; //Position sur la grille 
	private int  size = Dalle.dim;
	private int xi, yi; //Position sur le Canvas Game
	private boolean isClicked;
	
	/*
	 * Constructeur et actualisation:
	 */
	
	public Slot(int x, int y, Game game, Inventaire inventaire){
		this.x = x;
		this.y = y;
		this.game = game;
		this.inventaire = inventaire;
		
		
	}
	
	public void render(Graphics g) {
		if(item != null){
			
			g.drawImage(item.getImage(), xi,yi, size, size, null);
		}
		g.setColor(Color.BLUE);
		g.drawRect(xi, yi, size, size);	
	}
	
	/*
	 * Gestion des clics:
	 */

	public abstract void leftClick();
	
	public abstract void rightClick();
	

	
	
	/*
	 * Getters:
	 */
	
	public Item getItem(){
		return this.item;
	}
	
	public boolean isEmpty(){
		return(item == null);
	}
	
	public int getXi(){
		return xi;
	}
	
	public int getYi(){
		return yi;
	}
	
	public int getXGrid(){
		return x;
	}
	
	public int getYGrid(){
		return y;
	}
	
	public Inventaire getInventaire(){
		return inventaire;
	}
	
	public Game getGame(){
		return game;
	}
	
	
	/*
	 * Setters:
	 */
	
	public void setItem(Item item){
		this.item = item;
	}
	
	public void setXi(int xi){
		this.xi = xi;
		
	}
	
	public void setYi(int yi){
		this.yi = yi;
		
	}
	

}
