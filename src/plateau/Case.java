package plateau;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

public abstract class Case {
	
	protected BufferedImage image;
	protected int x;
	protected int y;
	protected int caseType;
	public static int dim = 50;

	public Case(int x, int y, int caseType){
		this.x = x;
		this.y = y;
		this.caseType = caseType;
	}
	
	
	public abstract  void tick();
	
	public void render(Graphics g){
		g.drawImage(image, x*dim, y*dim, dim, dim, null);
	}
	
	
	//Getters:
	
	public int getCaseType(){
		return this.caseType;
	}
	public int getX(){
		return this.x;
	}
	
	public int getY(){
		return this.y;
	}
	
	
	
	


	

}
