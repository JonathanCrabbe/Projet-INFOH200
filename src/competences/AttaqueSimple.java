package competences;

import acteurs.Personnage;

public class AttaqueSimple implements Runnable {
	
	private Personnage attaquant;
	private Personnage cible;
	private int dmg; //Dégats subits
	
	/*
	 * Constructeur et exécution:
	 */
	
	public AttaqueSimple(Personnage attaquant, Personnage cible){
		this.attaquant = attaquant;
		this.cible = cible;
		dmg = attaquant.getForce();
	}
	
	
	
	public void run() {	
		cible.getDammage(dmg);
	}

}
