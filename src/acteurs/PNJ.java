package acteurs;

import java.awt.Color;
import java.awt.Graphics;

import main.Game;
import main.VisualGameObject;
import plateau.Case;

public abstract class PNJ extends Personnage implements VisualGameObject {
	

	
	public PNJ(int x, int y, Game game) {
		super(x, y, game);
		this.estJoueur = false;
		
		
	}
	
	public void render(Graphics g) {
		
		if(isInFOV()){
			//Si le PNJ est visible, on le dessine dans le repère du joueur:
			Personnage joueur = this.game.getPopulation().getPerso(0);
			
			//Coordonnées du joueur:
			int xp = joueur.getX();
			int yp = joueur.getY();
			int FOV = Joueur.FOV;			
			int dim = Case.dim;
			
			//Dessiner dans le repère de la fenêtre
			g.setColor(Color.red);
			g.fillRect(dim*(x+FOV-xp), dim*(y+FOV-yp), dim, dim);
			}
			

	}
	
	//Renvoie True si le PNJ apparaît dans le champ de vision du joueur
	private boolean isInFOV(){
		Personnage joueur = this.game.getPopulation().getPerso(0);
		//Coordonnées du joueur:
		int xp = joueur.getX();
		int yp = joueur.getY();
		int FOV = Joueur.FOV;		
		
		return(xp-FOV <= x && x <= xp+FOV && yp-FOV <= y && y <= yp+FOV);
		
	}

}
