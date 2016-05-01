package acteurs;

import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

import GUI.ImageContainer;
import competences.AttaqueSimple;
import inventaire.Inventaire;
import items.Fleche;
import items.Potion;
import main.Game;
import main.VisualGameObject;
import observateur.Observer;
import plateau.Case;

public class Monstre extends PNJ implements VisualGameObject, Observer {
	
	
	
	private transient Thread action;
	private DecisionMonstre decision; //Ce Runnable gère "l'IA" du monstre.
	
	/*
	 * Constructeur et actualisation
	 */

	
	public Monstre(int x, int y, Game game) {
		super(x, y, game);
		this.setForce(5);
		this.decision = new DecisionMonstre(game, this);
	}
	
	protected Inventaire construireInventaire() {
		Inventaire inventaire = new Inventaire(game, this, 5);
		inventaire.add(new Potion(game, 50));
		inventaire.add(new Fleche(game));
		return inventaire;
	}

	public void tick(){
		//Lance le thread lié à l'"IA" à chaque tick:
		this.action = new Thread(decision);
		action.start();
	
	}

	public void render(Graphics g) {
		
		if(this.isInFOV()){
			//Si le PNJ est visible, on le dessine dans le repère du joueur
			
			int x = this.getX();
			int y = this.getY();
			
			
			//Coordonnées du joueur:
			Personnage joueur = this.game.getPopulation().getPerso(0);
			int xp = joueur.getX();
			int yp = joueur.getY();
			
			
			//Coordonnée relatives:
			int FOV = Joueur.FOV;
			int dim = Case.dim;
			int xi = dim*(x+FOV-xp); 
			int yi = dim*(y+FOV-yp);
			
			//Dessiner dans le repère de la fenêtre:							
			g.drawImage(ImageContainer.imageMonstre, xi,yi , dim,dim, null);
			
			}
	}
	
	/*
	 * Design Pattern Observer (observe le joueur)
	 */

	
	public void update() {
		/*
		 * Le monstre sait quel le joueur est attaqué
		 * Il adapte son comportement
		 * Utilité de l'attribut decision: seul lui est modifié
		 */
		this.decision.devientAgressif();
		
	}
	
	
	/*
	 * Getters:
	 */
	
	public Thread getAction(){
		return this.action;
	}

	
	

}
