package acteurs;

import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

import competences.AttaqueSimple;
import inventaire.Inventaire;
import items.Potion;
import main.Game;
import main.VisualGameObject;
import plateau.Case;

public class Monstre extends PNJ implements VisualGameObject {
	
	private Random rd = new Random(); //Pour les décisions aléatoires
	public static final int waitTime = 2000; //Temps entre chaque action (milliseconde)
	private Thread action;

	public Monstre(int x, int y, Game game) {
		super(x, y, game);
		this.force = 5;
		
		try {
			this.image = ImageIO.read(new File("Images/Monstre.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	
	public void tick(){
		
		
		//Cherche si le joueur est accessible. Si oui, l'attaquer.
		boolean tourFini = false;
		ArrayList<Case> ls = this.getReachableCases();
		for(Case caseTemp:ls){
			int xTemp = caseTemp.getX();
			int yTemp = caseTemp.getY();		
			
			if(!this.game.getPopulation().caseIsFree(xTemp, yTemp)){
				Personnage persoTemp = this.game.getPopulation().getPerso(xTemp, yTemp);
			
				if(persoTemp.getEstJoueur()){
					AttaqueSimple attaque = new AttaqueSimple(this, persoTemp);
					attaque.run();
					tourFini = true;
				}
			} 
		}
		
		if(! tourFini){
			//Cherche les cases accessibles et se déplace sur une d'elle choisie aléatoirement.
			ls = this.getFreeReachableCases();
			if(ls.size() != 0){		
				int c = rd.nextInt(ls.size()); //Indice de la case
				Case caseChoisie =  ls.get(c);
				if(this.game.getPopulation().caseIsFree(caseChoisie.getX(), caseChoisie.getY())){
				this.moveTo(caseChoisie);
				}									
			}
		}
			
	}


	
	protected Inventaire construireInventaire() {
		Inventaire inventaire = new Inventaire(game, this, 5);
		inventaire.add(new Potion(game, 50));
		return inventaire;
	}

	
	

}
