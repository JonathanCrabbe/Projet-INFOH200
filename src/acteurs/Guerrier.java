package acteurs;

import main.Game;

public class Guerrier extends Joueur {
	
	/*
	 * 	Cette classe est un simple joueur aux attributs modifés.
	 * 	Elle n'apporte rien de plus une cohérence du point des sous classes
	 * 	associées à la spécialisation du joueur.
	 */

	public Guerrier(int x, int y, int vitesse, Game game) {
		super(x, y, vitesse, game);
		this.setHPMax(250);
		this.setHP(getHPMax());
	}

}
