package acteurs;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.*;

import GUI.ImageContainer;
import inventaire.Inventaire;
import items.Potion;
import main.Game;
import main.VisualGameObject;
import observateur.Observer;
import observateur.Subject;
import plateau.Case;


public class Joueur extends Personnage implements VisualGameObject , Subject {
	
	public static int FOV = 7; //Champ de vision du joueur
	private ArrayList<Observer> observateurs = new ArrayList<Observer>();

	/*
	 * Constructeur et actualisation
	 */

	public Joueur(int x, int y, int vitesse, Game game) {
		super(x, y, game);
		this.setEstJoueur(true);
		this.setInventaire(construireInventaire());
	
	}
	

	protected Inventaire construireInventaire() {
		Inventaire inventaire = new Inventaire(game, this, 35);
		inventaire.add(new Potion(this.game, 50));
		inventaire.add(new Potion(this.game, 50));
		inventaire.add(new Potion(this.game, 50));
		return inventaire;
	}


	public void tick() {
			
		//Case sur laquelle le joueur veut se déplacer:
		int x = this.getX();
		int y = this.getY();
		Case caseVoulue = this.game.getPlateau().getCase(x+getVx(), y+getVy()); 
		boolean caseLibre = this.game.getPopulation().caseIsFree(x+getVx(), y+getVy());
		
		
		if(caseVoulue.getCaseType() != 1 && caseLibre){ //Si la case est une dalle et est libre
			this.setX(x + getVx());
			this.setY(y + getVy());
		}
	}
	
	public void render(Graphics g){
		int dim = Case.dim;
		int FOV = Joueur.FOV;
		int xp = (FOV)*dim ;
		int yp = (FOV)*dim;	
		//Joueur représenté au centre de l'écran
		g.drawImage(ImageContainer.imageJoueur,xp, yp, dim,dim, null); 
		
	}
	
	
	/*
	 *	 Setters:
	 */


	public synchronized void getDammage(int d){
		this.setHP( getHP() - d);
		if(getHP() <= 0){
			this.game.stop(); //Si le joueur est mort, le jeu est arrêté
		}
		this.notifyObserver(); 
		//Avertir les observateurs que le joueur est attaqué pour les rendre agressifs
	}
	
	/*
	 * Design Pattern (observé par les monstres)
	 */
	
	public void attach(Observer o) {
		this.observateurs.add(o);
		
	}

	public void notifyObserver() {
		for(Observer o:observateurs){
			o.update();
		}
		
	}


	

	

	

}
