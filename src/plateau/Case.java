package plateau;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

public abstract class Case {
	
	protected BufferedImage image;
	protected int x;
	protected int y;
	protected int caseType;
	public static int dim = 20;

	public Case(int x, int y, int caseType){
		this.x = x;
		this.y = y;
		this.caseType = caseType;
	}
	
	public abstract  void tick();
	
	


	

}
