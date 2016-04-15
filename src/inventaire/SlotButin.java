package inventaire;

import acteurs.Joueur;
import acteurs.Personnage;
import main.Game;
import plateau.Dalle;

public class SlotButin extends Slot{

	public SlotButin(int x, int y, Game game, Inventaire inventaire) {
		super(x,y,game,inventaire);
		int size = Dalle.dim;
		setXi( (x- Joueur.FOV)*size + Math.round(Game.WIDTH / 2)+size );
		setYi ( (y- Joueur.FOV)*size + Math.round(Game.HEIGHT )+size );
		setBounds(getXi(), getYi(), size, size);
	}

	public void leftClick() {
		
		Inventaire inventaireJoueur = this.getGame().getPopulation().getJoueur().getInventaire();
		inventaireJoueur.add(this.getItem());
		this.setItem(null);
		
	}

	
	public void rightClick() {
		setItem(null);
		
	}

}
