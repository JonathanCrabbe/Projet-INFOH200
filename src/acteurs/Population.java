package acteurs;

import java.awt.Graphics;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import main.Game;
import main.VisualGameObject;
import observateur.Observer;
import plateau.Case;
import plateau.Dalle;

public class Population implements VisualGameObject, Serializable{
	
	private ArrayList<Personnage> personnages = new ArrayList<Personnage>(); //Liste des personnages
	private Game game;
	private int nombreMonstres; //Nombre de monstres dans la liste
	private Random rd = new Random(); //Pour avoir des attributs de personnages aléatoires
	
	
	/*
	 * Constructeur et actualisaation:
	 */
	
	public Population(int nombreMonstres,Game game){
		this.game = game;
		this.nombreMonstres = nombreMonstres;
		
		//Création du joueur:
		ArrayList<Case> freeDalles = game.getPlateau().getDalles();
		Case spawn = freeDalles.get(rd.nextInt(freeDalles.size())); //Case sur laquelle le joueur apparaîtra
		int spawnX = spawn.getX();
		int spawnY = spawn.getY();
		freeDalles.remove(spawn); //le joueur occupe maintenant cette dalle ==> elle n'est plus libre
		
		String classeJoueur = this.game.getClasseJoueur(); //String contenant le nom de la classe	
		Joueur player = null;
		
		switch(classeJoueur){
			case "Guerrier" : player = new Guerrier(spawnX,spawnY, 1, game);
							  break;
			case "Mage" 	: player = new Mage(spawnX,spawnY, 1, game);
							  break;
			case "Archer"   : player = new Archer(spawnX,spawnY, 1, game);
			  				  break;				  
		}
			
		addPersonnage(player); //Le joueur est toujours l'élément 0 de la liste
		
		try{
			//Création des monstres:
			for(int i = 0; i < nombreMonstres; i++){
				Case spawnTemp = freeDalles.get(rd.nextInt(freeDalles.size())); //Case sur laquelle le monstre apparaîtra
				int spawnXTemp = spawnTemp.getX();
				int spawnYTemp = spawnTemp.getY();
				Monstre monstreTemp = new Monstre(spawnXTemp, spawnYTemp, game);
				personnages.add(monstreTemp);
				player.attach(monstreTemp); //Pour le design pattern Observer (le joueur est observé par les monstres)
				freeDalles.remove(spawnTemp); //La case est maintenant occupée
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
		for(int i = 0; i < personnages.size(); i++){
			Personnage persoTemp = personnages.get(i);
			if(persoTemp.getHP() <= 0) {
				persoTemp.dropInventory(); //Fait tomber le butin par terre
				personnages.remove(persoTemp);
				this.game.setScore(this.game.getScore() + 5); //Augmente le score du joueur
			}
			
		}
		if(this.personnages.size() == 1) this.game.relaunch(); //Si seul le joueur est en vie, on relance une map
	}
	
	public void render(Graphics g){
		
		for(int i = 0; i < personnages.size(); i++){
			Personnage persoTemp = personnages.get(i);
			persoTemp.render(g);
			
		}
		
	}
	
	//Renvoie True si la case en (x,y) n'est occupée par aucun personnage
	public synchronized boolean caseIsFree(int x, int y){
		boolean ans = true;
		for(Personnage persoTemp:this.personnages){
			if(persoTemp.getX() == x && persoTemp.getY() == y){
				ans = false;
			}
		}
		return ans;
		
	}
	
	public void relaunch(){
		
		/*
		 * Récréer une population (sans changer le joueur) 
		 */
		
		ArrayList<Case> freeDalles = game.getPlateau().getDalles();
		Case spawn = freeDalles.get(rd.nextInt(freeDalles.size())); //Case sur laquelle le joueur apparaîtra
		int spawnX = spawn.getX();
		int spawnY = spawn.getY();
		freeDalles.remove(spawn); //le joueur occupe maintenant cette dalle ==> elle n'est plus libre
		Joueur player = (Joueur)this.getJoueur();
		player.setX(spawnX);
		player.setY(spawnY);
		
		try{
			//Création des monstres:
			for(int i = 0; i < nombreMonstres; i++){
				Case spawnTemp = freeDalles.get(rd.nextInt(freeDalles.size())); //Case sur laquelle le monstre apparaîtra
				int spawnXTemp = spawnTemp.getX();
				int spawnYTemp = spawnTemp.getY();
				Monstre monstreTemp = new Monstre(spawnXTemp, spawnYTemp, game);
				personnages.add(monstreTemp);
				player.attach(monstreTemp);
				freeDalles.remove(spawnTemp); //La case est miantenant occupée
				}
			}
			catch(ArrayIndexOutOfBoundsException e){
				System.out.println("Erreur: pas assez de place pour placer les ennemis!");		
			}
	}
	
	// Getters:
	
	public int getSize(){
		return personnages.size();
	}
	
	public ArrayList<Personnage> getPersonnages(){
		return this.personnages;
	}
	
	public Personnage getPerso(int i){
		return personnages.get(i);
	}
	
	
	public synchronized Personnage getPerso(int x, int y){ //Renvoie le personzznage ayant les coordonnées (x,y)
		Personnage perso = null;
		for(Personnage persoTemp:personnages){
			if(persoTemp.getX() == x && persoTemp.getY() == y){
				perso = persoTemp;
			}
		}
		return perso;	
	}
	
	public Personnage getJoueur(){
		return getPerso(0);
	}
	
	
	public boolean playerIsAlive(){ //Renvoie true si le joueur est en vie
		boolean ans = false;
		for(Personnage persoTemp:personnages){ 
			if(persoTemp.getEstJoueur()) ans = true;
		}
		return ans;
	}
	
	/*
	 * 	Manipulation de la liste:
	 */


	private void addPersonnage(Personnage perso){
		this.personnages.add(perso);
	}



	private void removePersonnage(Personnage perso){
		this.personnages.remove(perso);
	}

	
}
