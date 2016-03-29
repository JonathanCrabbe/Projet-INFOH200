package acteurs;

import java.awt.Color;
import java.awt.Graphics;

import main.Game;
import plateau.Case;

public abstract class PNJ extends Personnage {
	
	private Game game;
	
	public PNJ(int x, int y, int vitesse, Game game) {
		super(x, y, vitesse, false, game);
		this.game = game;
		
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
			
			g.setColor(Color.red);
			g.fillRect(dim*(x+FOV-xp), dim*(y+FOV-yp), dim, dim);
			}
			

	}
	
	//Renvoie True si le PNJ apparaît dans le champ de vision du joueur
	public boolean isInFOV(){
		Personnage joueur = this.game.getPopulation().getPerso(0);
		//Coordonnées du joueur:
		int xp = joueur.getX();
		int yp = joueur.getY();
		int FOV = Joueur.FOV;		
		
		return(xp-FOV <= x && x <= xp+FOV && yp-FOV <= y && y <= yp+FOV);
		
	}

}
