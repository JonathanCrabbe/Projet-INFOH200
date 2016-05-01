package competences;

import java.io.Serializable;

import acteurs.Mage;

public class RegenMana implements Runnable, Serializable {
	
	private Mage mage;
	private transient Thread thread;
	private final int waitTime = 1000;//Temps d'attente du thread
	
	/*
	 * Constructeur et exécution
	 */

	
	public RegenMana(Mage mage) {
		this.mage = mage;
		this.start();
	}

	public void run() {
		while(true){	
			try {
				mage.setMana(mage.getMana() + 5);
				thread.sleep(waitTime); //Pour que le regain de manaa ne soit pas instantanné 
			} catch (InterruptedException e) {}
		}
	}
	
	public void start(){
		this.thread = new Thread(this);
		this.thread.start();
	}
	
	public boolean getIsRunning(){
		return !( this.thread == null);  //Utile pour le chargement de partie (le thread n'est pas sérializé)
	}
	
	

}
