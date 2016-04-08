package items;

import java.awt.image.BufferedImage;

import acteurs.Personnage;
import main.Game;

public abstract class Item {
	
	private Game game;
	protected boolean isConsommable;
	protected BufferedImage itemImage;
	protected String itemName;
	
	public Item(Game game){
		this.game = game;
	}
	
	public abstract void useOn(Personnage perso); //Utilise l'onjet sur le personnage

	
	/*
	 * Getters:
	 */
	
	public boolean getIsConsommable(){
		return this.isConsommable;
	}
	
	public BufferedImage getImage(){
		return this.itemImage;
	}
}

