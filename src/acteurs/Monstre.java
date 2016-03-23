package acteurs;

import java.awt.Color;
import java.awt.Graphics;

public class Monstre extends PNJ {

	public Monstre(int x, int y, int vitesse) {
		super(x, y, vitesse);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect(x, y, 32, 32);

	}

}
