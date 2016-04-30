package acteurs;

import main.Game;

public class Guerrier extends Joueur {

	public Guerrier(int x, int y, int vitesse, Game game) {
		super(x, y, vitesse, game);
		this.setHPMax(250);
		this.setHP(getHPMax());
	}

}
