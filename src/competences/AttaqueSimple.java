package competences;

import acteurs.Personnage;

public class AttaqueSimple implements Runnable {
	
	private Personnage attaquant;
	private Personnage cible;
	private int xTarget;
	private int yTarget;
	private int dmg;
	
	
	public AttaqueSimple(Personnage attaquant, Personnage cible){
		this.attaquant = attaquant;
		this.cible = cible;
		xTarget = cible.getX();
		yTarget = cible.getY();
		dmg = attaquant.getForce();
	}
	
	
	
	public void run() {
		
		cible.getDammage(dmg);
		//attaquant.attaqueCoord(xTarget, yTarget, dmg);
	}

}
