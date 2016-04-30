package items;

import java.awt.image.BufferedImage;
import java.io.Serializable;

import acteurs.Personnage;
import main.Game;

public abstract class Item implements Serializable{
	
	private Game game;
	protected boolean isConsommable;
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
	
	public abstract BufferedImage getImage();
		
}

