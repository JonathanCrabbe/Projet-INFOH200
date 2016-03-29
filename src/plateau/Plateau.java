package plateau;

import java.awt.Graphics;
import java.util.ArrayList;
import java.awt.Image;

import javax.swing.JPanel;

import acteurs.Joueur;
import acteurs.Personnage;
import generateurDonjon.Topologie;
import main.Game;

public class Plateau extends JPanel{
	
	private int size;
	private ArrayList<ArrayList<Case>> grille = new ArrayList<ArrayList<Case>>();
	private Game game;
	
	public Plateau(int size, Game game){
		this.size = size;
		this.setFocusable(true);
		this.requestFocusInWindow();
		Topologie topologie = new Topologie(size);
		for(int i = 0; i < size ; i++){
			grille.add(new ArrayList<Case>());
			for(int j = 0; j < size; j++){
				if(topologie.estSalle(i, j)){			
					addCase(new Dalle(i,j), i, j);
				}
				else{
					addCase(new Mur(i,j),i,j);
				}
			}
		}		
	}
	
	//Ce qu'il se passe à chaque ittération de la Game Loop
	public void tick(){
		for(int i = 0; i < size ; i++){
			for(int j = 0; j < size; j++){
				Case caseTemp = grille.get(i).get(j);
				caseTemp.tick();
				//On actualise chaque case
			}
		}			
	}
		
	
	//Permets d'avoir un rendu global de la carte
	public void renderGlobal(Graphics g) { 
		if(grille == null){
		}else{
			for(int i = 0; i < size; i++){
				for(int j = 0; j < size; j++){
					Case caseTemp = grille.get(i).get(j);
					caseTemp.render(g);
				}
				
			}
		}		
     }
	
	// N'affiche que les dalles
	public void renderDalles(Graphics g) { 
		ArrayList<Case> dalles	= getDalles();
		for(Case caseTemp:dalles){
			caseTemp.render(g);
		}
     }
	
	//Cherche chacune des cases visibles par le joueur et la dessine
	public void renderLocal(Graphics g, int x, int y) { 
		if(grille == null){
		}else{
			int FOV = Joueur.FOV; //Champ de vision du joueur
			
			//Bornes des boucles (ne pas sortir de l'indexation des listes:
			int xinf = Math.max(x-FOV, 0);
			int xsup = Math.min(x+FOV+1, size);
			int yinf = Math.max(y-FOV, 0);
			int ysup = Math.min(y+FOV+1, size);
					
			for(int i = xinf; i < xsup; i++){
				for(int j = yinf; j < ysup; j++){
					Case caseTemp = grille.get(i).get(j);
					int dim = Case.dim;
					g.drawImage(caseTemp.image, dim*(i+FOV-x), dim*(j+FOV-y), dim,dim, null);
				}
				
			}
		}		
     }
	
	//Ajoute une case à la ArrayList
	public void addCase(Case c, int x, int y){
		this.grille.get(x).add(y, c);
	}
	
	
	/*
	 * Getters:
	 */
	
	
	//Donne une liste des Dalles
	public ArrayList<Case> getDalles(){		
		ArrayList<Case> ls = new ArrayList<Case>();
		for(int i = 0; i < size; i++ ){
			ArrayList<Case> listeTemp = grille.get(i);
			for(int j = 0; j < size; j++){
				Case caseTemp = listeTemp.get(j);
				if(caseTemp.getCaseType() == 0){
					ls.add(caseTemp);
				}
			}
		}
		return ls;
	}
	//Renvoie une liste des Dalles non occupées par des personnages
	public ArrayList<Case> getFreeDalles(){
		ArrayList<Case> ls = getDalles();
		
		for(Case caseTemp:ls){
			int x = caseTemp.getX();
			int y = caseTemp.getY();
			if(!this.game.getPopulation().caseIsFree(x, y)){
				//Si la case n'est pas libre, la retirer de la liste				
				ls.remove(caseTemp);
			}
		}
		return ls;
		
	}
	
	
	public Case getCase(int x, int y){
		Case res = this.grille.get(x).get(y);
		return res;
	}
	
	

}
	
