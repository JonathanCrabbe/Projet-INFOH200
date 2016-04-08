package items;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import acteurs.Personnage;
import main.Game;

public class Potion extends Consommable {
	
	private int puissance; 

	public Potion(Game game, int puissance) {
		super(game);
		this.puissance = puissance;
		
		try {
			this.itemImage = ImageIO.read(new File("Images/Objets/Potion.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//Soigne le joueur
	public void useOn(Personnage perso) {
		//Le joueur ne peut avoir plus de vie que 100:
		int newHP = Math.min(perso.getHP() + puissance, 100);
		perso.setHP(newHP);

	}

}
