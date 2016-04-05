package items;

import acteurs.Personnage;
import main.Game;

public class Potion extends Consommable {
	
	private int puissance; 

	public Potion(Game game, int puissance) {
		super(game);
		this.puissance = puissance;
	}

	//Soigne le joueur
	public void useOn(Personnage perso) {
		//Le joueur ne peut avoir plus de vie que 100:
		int newHP = Math.min(perso.getHP() + puissance, 100);
		perso.setHP(newHP);

	}

}
