package inventaire;

import acteurs.Joueur;
import main.Game;
import plateau.Dalle;

public class SlotJoueur extends Slot { //Ce sont les slots de l'inventaire du joueur

	public SlotJoueur(int x, int y, Game game, Inventaire inventaire) {
		super(x,y,game, inventaire);
		int size = Dalle.dim;
		setXi( (x- Joueur.FOV)*size + Math.round(Game.WIDTH / 2)+size );
		setYi ( (y- Joueur.FOV)*size + Math.round(Game.HEIGHT / 2)+size );
		setBounds(getXi(), getYi(), size, size);
	}

	public void leftClick() { //Utilisation de l'objet sur le joueur
		Inventaire inventaire = getInventaire();
		inventaire.useItem(getXGrid(), getYGrid());
		this.setItem(null);
		
	}

	
	public void rightClick() { //Suppression de l'objet
		setItem(null);
		
	}

}
