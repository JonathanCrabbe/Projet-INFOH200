package acteurs;

import java.awt.Color;
import java.awt.Graphics;

import main.Game;
import main.VisualGameObject;


public class Allié extends PNJ implements VisualGameObject{

	public Allié(int x, int y, int vitesse, Game game) {
		super(x, y, game);
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
