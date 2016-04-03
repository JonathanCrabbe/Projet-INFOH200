package plateau;

import java.awt.Graphics;
import java.util.ArrayList;
import java.awt.Image;

import javax.swing.JPanel;

import acteurs.Joueur;
import acteurs.Personnage;
import generateurDonjon.Topologie;
import main.Game;
import main.VisualGameObject;

public class Plateau extends JPanel implements VisualGameObject{
	
	private int taille;
	private ArrayList<ArrayList<Case>> grille = new ArrayList<ArrayList<Case>>();
	private Game game;
	
	public Plateau(int taille, Game game){
		this.taille = taille;
		this.setFocusable(true);
		this.requestFocusInWindow();
		Topologie topologie = new Topologie(taille);
		for(int i = 0; i < taille ; i++){
			grille.add(new ArrayList<Case>());
			for(int j = 0; j < taille; j++){
				if(topologie.estSalle(i, j) && i != 0 && j != 0 && i != taille-1 && j != taille-1){
					//On ne place des dalles selon la topologie générée et pas sur les bords du donjon
					addCase(new Dalle(i,j), i, j);
				}
				else{
					addCase(new Mur(i,j),i,j);
				}
			}
		}		
	}
	
	/*
	 * Actualisation:
	 */
	
	//Ce qu'il se passe à chaque ittération de la Game Loop
	public void tick(){
		for(int i = 0; i < taille ; i++){
			for(int j = 0; j < taille; j++){
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
			for(int i = 0; i < taille; i++){
				for(int j = 0; j < taille; j++){
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
			int xsup = Math.min(x+FOV+1, taille);
			int yinf = Math.max(y-FOV, 0);
			int ysup = Math.min(y+FOV+1, taille);
					
			for(int i = xinf; i < xsup; i++){
				for(int j = yinf; j < ysup; j++){
					Case caseTemp = grille.get(i).get(j);
					int dim = Case.dim;
					g.drawImage(caseTemp.image, dim*(i+FOV-x), dim*(j+FOV-y), dim,dim, null);
				}
				
			}
		}		
     }
	
	
	/*
	 *  Implémentation:
	 */
	
	//Ajoute une case à la ArrayList
	private void addCase(Case c, int x, int y){
		this.grille.get(x).add(y, c);
	}
	
	
	/*
	 * Getters:
	 */
	
	
	//Donne une liste des Dalles
	public ArrayList<Case> getDalles(){		
		ArrayList<Case> ls = new ArrayList<Case>();
		for(int i = 0; i < taille; i++ ){
			ArrayList<Case> listeTemp = grille.get(i);
			for(int j = 0; j < taille; j++){
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
			int k = caseTemp.getX();
			int l = caseTemp.getY();
			if(!this.game.getPopulation().caseIsFree(k, l)){
				//Si la case n'est pas libre, la retirer de la liste				
				ls.remove(caseTemp);
			}
		}
		return ls;
		
	}
	
	
	public Case getCase(int k, int l){
		Case res = this.grille.get(k).get(l);
		return res;
	}
	
	public int getTaille(){
		return this.taille;
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	

}
	
