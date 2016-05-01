package items;

import java.awt.image.BufferedImage;
import java.io.Serializable;

import acteurs.Personnage;
import main.Game;

public abstract class Item implements Serializable{
	
	private Game game;
	private boolean isConsommable;
	private String itemName;
	
	public Item(Game game){
		this.game = game;
	}
	
	public abstract void useOn(Personnage perso); //Utilise l'objet sur le personnage

	
	public boolean getIsConsommable(){
		return this.isConsommable;
	}
	
	public void setConsommable(boolean ans){
		this.isConsommable = ans;
	}
	
	public abstract BufferedImage getImage();
		
}

