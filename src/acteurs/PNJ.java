package acteurs;

import java.awt.Color;
import java.awt.Graphics;

import main.Game;
import main.VisualGameObject;
import plateau.Case;

public abstract class PNJ extends Personnage implements VisualGameObject {
	
	/*
	 * Constructeur
	 */

	
	public PNJ(int x, int y, Game game) {
		super(x, y, game);
		this.setEstJoueur(false);
		
		
	}
	
	/*
	 * Getters
	 */
	
	
	public boolean isInFOV(){ //Renvoie True si le PNJ est visible par le joueur
		int x = this.getX();
		int y = this.getY();
		
		Personnage joueur = this.game.getPopulation().getPerso(0);
		//Coordonnées du joueur:
		int xp = joueur.getX();
		int yp = joueur.getY();
		int FOV = Joueur.FOV;		
		
		return(xp-FOV <= x && x <= xp+FOV && yp-FOV <= y && y <= yp+FOV);
		
	}

}
