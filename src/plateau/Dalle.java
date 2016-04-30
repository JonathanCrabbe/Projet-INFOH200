package plateau;

import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import GUI.ImageContainer;
import main.VisualGameObject;

public class Dalle extends Case{

	public Dalle(int x, int y) {
		super(x, y, 0);
		
	}



	
	public void render(Graphics g, int xi, int yi) {
		
		g.drawImage(ImageContainer.imageDalle, xi*dim, yi*dim, dim, dim, null);
		if(this.getButin() != null){
			g.drawImage(ImageContainer.imageLoot,xi*dim, yi*dim, dim, dim, null);
		}	
		if(this.getLieuAOE()){
			g.drawImage(ImageContainer.imageProjectile,xi*dim, yi*dim, dim, dim, null);	
		}
	}
	
	
	
	

	

}
