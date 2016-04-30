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
	private DecisionMonstre decision; 

	
	public Monstre(int x, int y, Game game) {
		super(x, y, game);
		this.force = 5;
		this.decision = new DecisionMonstre(game, this);
	}
	
	public void render(Graphics g) {
		
		if(this.isInFOV()){
			//Si le PNJ est visible, on le dessine dans le repère du joueur:
			Personnage joueur = this.game.getPopulation().getPerso(0);
			
			//Coordonnées du joueur:
			int xp = joueur.getX();
			int yp = joueur.getY();
			int FOV = Joueur.FOV;			
			int dim = Case.dim;
			
			int xi = dim*(x+FOV-xp);
			int yi = dim*(y+FOV-yp);
			
			//Dessiner dans le repère de la fenêtre
			g.drawImage(ImageContainer.imageMonstre, xi,yi , dim,dim, null);
			
			}
	}

	
	public void tick(){
		
		this.action = new Thread(decision);
		action.start();
	
	}


	
	protected Inventaire construireInventaire() {
		Inventaire inventaire = new Inventaire(game, this, 5);
		inventaire.add(new Potion(game, 50));
		inventaire.add(new Fleche(game));
		return inventaire;
	}


	
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
