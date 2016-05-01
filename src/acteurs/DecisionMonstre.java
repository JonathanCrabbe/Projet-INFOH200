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
	
	/*
	 * Constructeur et exécution
	 */
	

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
				if(! this.game.getPopulation().caseIsFree(xTemp, yTemp)){ //Si la case est occupée
					Personnage persoTemp = this.game.getPopulation().getPerso(xTemp, yTemp);
					if(persoTemp.getEstJoueur()){ //Si la case est occupée par le joueur
						AttaqueSimple attaque = new AttaqueSimple(this.monstre, persoTemp);
						attaque.run();
						tourFini = true;
						}
				} 
			}
				
			if(! tourFini){ //Si le monstre n'a pas attaqué, il se déplace (différement selon son agressivité)
					Case caseChoisie = null;
					if(!agressif) {caseChoisie = choixRand(ls);}
					else	 {caseChoisie = choixAgressif(ls);}	
				
					if(this.game.getPopulation().caseIsFree(caseChoisie.getX(), caseChoisie.getY())){ //On vérifie que la case est libre 
						this.monstre.moveTo(caseChoisie);
						
				      }									
				}
			}
				
			
		
	
	/*
	 * Choix de la case:
	 */
	
	private synchronized Case choixRand(ArrayList<Case> ls){
		
		/*
		 * Cherche les cases accessibles et se déplace 
		 * sur une d'elle choisie aléatoirement.	
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
		 * qui rapproche le plus du joueur (distance Manhattan).	
		 */
		
		Case caseChoisie = monstre.getCase();
		
		Personnage player = this.game.getPopulation().getJoueur();
		Case casePlayer = player.getCase();
			
		for(Case caseTemp:ls){
	
			if(casePlayer.getDist(caseTemp) < casePlayer.getDist(caseChoisie)){
				caseChoisie = caseTemp;
			}
		}
		
		this.agressif = false; //Le monstre n'est plus agressif
		return caseChoisie;
	}
	
	/*
	 * Setters:
	 */
	
	
	public void devientAgressif(){
		this.agressif = true;
	}
	
	}


