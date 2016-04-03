package main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import acteurs.Personnage;
import acteurs.Population;



/*
 * 
 * Cette classe g�re les �v�nement provoqu�s par la pression d'une touche sur le clavier.
 * Ces pressions n'ont d'influence que sur le joueur (partie de la population) et l'interface graphique.
 * Cete classe h�rite de KeyAdapter en adaptant les m�thode de celles-ci au jeu.
 * On mets la population en attribut pour un �ventuel multijoueur
 * 
 */
public class KeyInput extends KeyAdapter { 
	
	private Population population;  									// Pour manipuler le joueur
	
	public KeyInput(Population population){
		this.population = population;
	}
	
	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
		
		// On va ici parcourir la liste de toute la population pour ne manipuler que le joueur:
		
		for(int i = 0 ; i < population.getSize() ; i++ ){
			Personnage persoTemp = population.getPerso(i);
			
			if(persoTemp.getEstJoueur()){
				
				// �v�nements d�clanch�s pour le joueur:
				
				if(key == KeyEvent.VK_Z) persoTemp.moveUp();
				if(key == KeyEvent.VK_Q) persoTemp.moveLeft();
				if(key == KeyEvent.VK_S) persoTemp.moveDown();
				if(key == KeyEvent.VK_D) persoTemp.moveRight();
				
				if(key == KeyEvent.VK_UP) persoTemp.attaqueUp();
				if(key == KeyEvent.VK_RIGHT) persoTemp.attaqueRight();
				if(key == KeyEvent.VK_LEFT) persoTemp.attaqueLeft();
				if(key == KeyEvent.VK_DOWN) persoTemp.attaqueDown();
				
			}
			
		}
		
	}
	public void keyReleased(KeyEvent e){
		int key = e.getKeyCode();
		
		for(int i = 0 ; i < population.getSize() ; i++ ){
			Personnage persoTemp = population.getPerso(i);
			
			if(persoTemp.getEstJoueur()){
				
				// �v�nements d�clanch�s pour le joueur:
				
				if(key == KeyEvent.VK_Z || key == KeyEvent.VK_Q || key == KeyEvent.VK_S || key == KeyEvent.VK_D){
					persoTemp.immobilize();
				}
				
				
			}
			
		}
		
	}

}
