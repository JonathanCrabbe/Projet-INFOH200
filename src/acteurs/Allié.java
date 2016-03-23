package acteurs;

import java.awt.Color;
import java.awt.Graphics;

public class Allié extends PNJ {

	public Allié(int x, int y, int vitesse) {
		super(x, y, vitesse);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.green);
		g.fillRect(x, y, 32, 32);

	}

}
