package acteurs;

import java.awt.Color;
import java.awt.Graphics;

public class Joueur extends Personnage {

	public Joueur(int x, int y, int vitesse) {
		super(x, y, vitesse, true);
		
	}

	public void tick() {
		x += vx;
		y += vy;
		
	}

	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(x, y, 32, 32);
		
	}

}
