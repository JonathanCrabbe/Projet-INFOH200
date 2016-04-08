package inventaire;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import acteurs.Joueur;
import items.Item;
import main.Game;
import main.VisualGameObject;
import plateau.Dalle;

public class Slot extends Rectangle implements VisualGameObject { 
	/*
	 *  Cette classe gère les slots d'inventaire et contiennent un objet.
	 *  Hérite de Rectangle pour l'interraction souris.
	 */
	private Game game;
	private Item item;
	private int x,y; //Position sur la grille 
	private int  size = Dalle.dim;
	private int xi, yi; //Position sur le Canvas Game

	
	private boolean isClicked;
	
	public Slot(int x, int y, Game game){
		this.x = x;
		this.y = y;
		this.game = game;
		this.xi = (x- Joueur.FOV)*size + Math.round(Game.WIDTH / 2)+size;
		this.yi = (y- Joueur.FOV)*size + Math.round(Game.HEIGHT / 2)+size;
		setBounds(xi, yi, size, size);
	}
	
	public void tick(){
		
		
	}

	public void render(Graphics g) {
		if(item != null){
			
			g.drawImage(item.getImage(), xi,yi, size, size, null);
		}
		
		g.setColor(Color.BLUE);
		g.drawRect(xi, yi, size, size);	
	}
	
	/*
	 * Getters:
	 */
	
	public Item getItem(){
		return this.item;
	}
	
	public boolean isEmpty(){
		return(item == null);
	}
	
	
	
	/*
	 * Setters:
	 */
	
	public void setItem(Item item){
		this.item = item;
	}
	
	

}
