package acteurs;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import competences.AttaqueSimple;
import main.Game;
import plateau.Case;

public class DecisionMonstre implements Runnable, Serializable {
	
	private Monstre monstre;
	private Game game;
	private Random rd = new Random();
	private boolean agressif;
	

	public DecisionMonstre(Game game, Monstre monstre) {
		this.game = game;
		this.monstre = monstre;
		this.agressif = false;
	}


	public synchronized void run() {
		
		
			//Cherche si le joueur est accessible. Si oui, l'attaquer.
			boolean tourFini = false;
			ArrayList<Case> ls = this.monstre.getReachableCases();
		
			for(Case caseTemp:ls){
				int xTemp = caseTemp.getX();
				int yTemp = caseTemp.getY();				
				if(! this.game.getPopulation().caseIsFree(xTemp, yTemp)){
					Personnage persoTemp = this.game.getPopulation().getPerso(xTemp, yTemp);
					if(persoTemp.getEstJoueur()){
						AttaqueSimple attaque = new AttaqueSimple(this.monstre, persoTemp);
						attaque.run();
						tourFini = true;
						}
				} 
			}
				
			if(! tourFini){
					Case caseChoisie = null;
					if(!agressif) {caseChoisie = choixRand(ls);}
					else	 {caseChoisie = choixAgressif(ls);}	
				
					if(this.game.getPopulation().caseIsFree(caseChoisie.getX(), caseChoisie.getY())){
						this.monstre.moveTo(caseChoisie);
						
				      }									
				}
			}
				
			
		
	
	/*
	 * Choix de la case:
	 */
	
	private synchronized Case choixRand(ArrayList<Case> ls){
		
		/*
		 * Cherche les cases accessibles et se déplace sur une d'elle choisie aléatoirement.	
		 */
		
		Case caseChoisie = monstre.getCase();
		if(ls.size() != 0){					
			int c = rd.nextInt(ls.size()); //Indice de la case
			caseChoisie =  ls.get(c);
		}
		return caseChoisie;
	}
	
	private synchronized  Case choixAgressif(ArrayList<Case> ls){
		
		/*
		 * Cherche les cases accessibles et se déplace sur celle 
		 * qui rapproche le plus du joueur.	
		 */
		
		Case caseChoisie = monstre.getCase();
		//System.out.println("Je suis sur: " + caseChoisie.getX() + " " + caseChoisie.getY() );
		Personnage player = this.game.getPopulation().getJoueur();
		Case casePlayer = player.getCase();
		//System.out.println(ls.size() + " possibilités" );		
		for(Case caseTemp:ls){
	
			if(casePlayer.getDist(caseTemp) < casePlayer.getDist(caseChoisie)){
				caseChoisie = caseTemp;
			}
		}
		//System.out.println("Je vais sur: " + caseChoisie.getX() + " " + caseChoisie.getY() );
		this.agressif = false;
		return caseChoisie;
	}
	
	
	
	public void devientAgressif(){
		this.agressif = true;
	}
	
	}


