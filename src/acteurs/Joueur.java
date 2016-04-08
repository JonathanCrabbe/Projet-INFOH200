package acteurs;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.*;

import inventaire.Inventaire;
import items.Potion;
import main.Game;
import main.VisualGameObject;

import plateau.Case;


public class Joueur extends Personnage implements VisualGameObject {
	
	public static int FOV = 7;

	

	public Joueur(int x, int y, int vitesse, Game game) {
		super(x, y, game);
		this.estJoueur = true;

		
		try {
			this.image = ImageIO.read(new File("Images/Joueur.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		
	}
	

	public void tick() {
			
		//Case sur laquelle le joueur veut se déplacer:
		Case caseVoulue = this.game.getPlateau().getCase(x+vx, y+vy); 
		boolean caseLibre = this.game.getPopulation().caseIsFree(x+vx, y+vy);
		
		
		if(caseVoulue.getCaseType() != 1 && caseLibre){
			x += vx;
			y += vy;
		}
	}
	
	public void getDammage(int d){
		this.HP -= d;
		if(HP <= 0){
			this.game.stop();
		}
	}
	
	public void render(Graphics g){
		int dim = Case.dim;
		int FOV = Joueur.FOV;
		int xp = (FOV)*dim ;
		int yp = (FOV)*dim;	
		
		g.drawImage(image,xp, yp, dim,dim, null);
		
	}


	
	protected Inventaire construireInventaire() {
		Inventaire inventaire = new Inventaire(game, this, 35);
		inventaire.add(new Potion(this.game, 50));
		inventaire.add(new Potion(this.game, 50));
		inventaire.add(new Potion(this.game, 50));
		return inventaire;
	}


	

	

	

}
