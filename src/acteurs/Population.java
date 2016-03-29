package acteurs;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

import main.Game;
import plateau.Case;
import plateau.Dalle;

public class Population {
	
	private ArrayList<Personnage> personnages = new ArrayList<Personnage>();
	private Game game;
	private int nombreMonstres;
	
	public Population(int nombreMonstres,Game game){
		this.game = game;
		this.nombreMonstres = nombreMonstres;
		
		Random rd = new Random(); //Pour avoir des attributs de personnages aléatoires
		
		
		//Création du joueur:
		ArrayList<Case> freeDalles = game.getPlateau().getDalles();
		Case spawn = freeDalles.get(rd.nextInt(freeDalles.size())); //Case sur laquelle le joueur apparaîtra
		int spawnX = spawn.getX();
		int spawnY = spawn.getY();
		freeDalles.remove(spawn); //le joueur occupe maintenant cette dalle ==> elle n'est plus libre
		
		addPersonnage(new Joueur(spawnX,spawnY, 1, game)); //Le joueur est toujours l'élément 0 de la liste
		
		try{
			//Création des monstres:
			for(int i = 0; i < nombreMonstres; i++){
				Case spawnTemp = freeDalles.get(rd.nextInt(freeDalles.size())); //Case sur laquelle le monstre apparaîtra
				int spawnXTemp = spawnTemp.getX();
				int spawnYTemp = spawnTemp.getY();
				Monstre monstreTemp = new Monstre(spawnXTemp, spawnYTemp, Case.dim, game);
				personnages.add(monstreTemp);
				freeDalles.remove(spawnTemp); //La case est miantenant occupée
				}
			}
			catch(ArrayIndexOutOfBoundsException e){
				System.out.println("Erreur: pas assez de place pour placer les ennemis!");		
			}
		}
	
	
	
	public void tick(){
		for(int i = 0; i < personnages.size(); i++){
			Personnage persoTemp = personnages.get(i);
			persoTemp.tick();
			
		}
		
	}
	
	public void render(Graphics g){
		
		for(int i = 0; i < personnages.size(); i++){
			Personnage persoTemp = personnages.get(i);
			persoTemp.render(g);
			
		}
		
	}
	
	public void addPersonnage(Personnage perso){
		this.personnages.add(perso);
	}
	public void removePersonnage(Personnage perso){
		this.personnages.remove(perso);
	}
	
	
	//Renvoie True si la case en (x,y) n'est occupée par aucun personnage
	public boolean caseIsFree(int x, int y){
		boolean ans = true;
		for(Personnage persoTemp:personnages){
			if(persoTemp.getX() == x && persoTemp.getY() == y){
				ans = false;
			}
		}
		return ans;
		
	}
	
	// Getters:
	
	public int getSize(){
		return personnages.size();
	}
	
	public Personnage getPerso(int i){
		return personnages.get(i);
	}

}
