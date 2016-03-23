package acteurs;

import java.awt.Graphics;
import java.util.ArrayList;

public class Population {
	
	private ArrayList<Personnage> personnages = new ArrayList<Personnage>();
	
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
	
	// Getters:
	
	public int getSize(){
		return personnages.size();
	}
	
	public Personnage getPerso(int i){
		return personnages.get(i);
	}

}
