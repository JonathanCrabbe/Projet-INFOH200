package acteurs;

import main.Game;

public class Guerrier extends Joueur {
	
	/*
	 * 	Cette classe est un simple joueur aux attributs modif�s.
	 * 	Elle n'apporte rien de plus une coh�rence du point des sous classes
	 * 	associ�es � la sp�cialisation du joueur.
	 */

	public Guerrier(int x, int y, int vitesse, Game game) {
		super(x, y, vitesse, game);
		this.setHPMax(250);
		this.setHP(getHPMax());
	}

}
