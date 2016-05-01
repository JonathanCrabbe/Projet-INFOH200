package plateau;

import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import GUI.ImageContainer;
import main.VisualGameObject;

public class Mur extends Case{ //Case sur laquelle l'on ne peut se déplacer

	public Mur(int x, int y) {
		super(x, y, 1);
		
	}
	
	public void render(Graphics g, int xi, int yi) {
		g.drawImage(ImageContainer.imageMur, xi*dim, yi*dim, dim, dim, null);	
	}

	

}