package items;

import acteurs.Personnage;
import main.Game;

public abstract class Consommable extends Item {

	public Consommable(Game game) {
		super(game);
		this.setConsommable(true);
	}


	public abstract void useOn(Personnage perso);
	
	

}
