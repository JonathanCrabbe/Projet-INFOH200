package acteurs;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.*;


public class Joueur extends Personnage {
	

	public Joueur(int x, int y, int vitesse) {
		super(x, y, vitesse, true);
		try {
			this.image = ImageIO.read(new File("Images/Joueur.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public void tick() {
		x += vx;
		y += vy;
		
	}
	
	public void render(Graphics g){
		g.drawImage(image,x, y, 20,20, null);
	}

	

}
