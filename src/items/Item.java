package items;

import acteurs.Personnage;
import main.Game;

public abstract class Item {
	
	private Game game;
	protected boolean isConsommable;
	
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
}

