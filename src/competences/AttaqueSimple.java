package competences;

import acteurs.Personnage;

public class AttaqueSimple implements Runnable {
	
	private Personnage attaquant;
	private Personnage cible;
	private int dmg;
	
	
	public AttaqueSimple(Personnage attaquant, Personnage cible){
		this.attaquant = attaquant;
		this.cible = cible;
		dmg = attaquant.getForce();
	}
	
	
	
	public void run() {
		
		cible.getDammage(dmg);
		
		//attaquant.attaqueCoord(xTarget, yTarget, dmg);
	}

}
