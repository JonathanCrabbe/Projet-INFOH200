package plateau;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import acteurs.Personnage;
import inventaire.Inventaire;
import main.VisualGameObject;

public abstract class Case implements VisualGameObject{
	
	protected BufferedImage image;
	protected int x;
	protected int y;
	protected int caseType;
	private Inventaire butin;


	
	
	public static int dim = 50;

	public Case(int x, int y, int caseType){
		this.x = x;
		this.y = y;
		this.caseType = caseType;
	}
	
	
	public abstract  void tick();
	
	public void render(Graphics g, int xi, int yi){
		g.drawImage(image, xi*dim, yi*dim, dim, dim, null);
		if(this.butin != null){
			try {
				g.drawImage(ImageIO.read(new File("Images/Cases/Loot.png")), xi*dim, yi*dim, dim, dim, null);
			} catch (IOException e) {}
		}
	}
	
	public void render(Graphics g){
		g.drawImage(image, x*dim, y*dim, dim, dim, null);
		if(this.butin != null){
			try {
				g.drawImage(ImageIO.read(new File("Images/Cases/Loot.png")), x*dim, y*dim, dim, dim, null);
			} catch (IOException e) {}
		}
	}
	
	/*
	 * Getters:
	 */
	
	public int getCaseType(){
		return this.caseType;
	}
	public int getX(){
		return this.x;
	}
	
	public int getY(){
		return this.y;
	}
	
	public Inventaire getButin(){
		return this.butin;
	}
	
	/*
	 * Setters:
	 */
	
	public void setButin(Inventaire butin){
		this.butin = butin;
	}
	
	/*
	 * Inventaire
	 */
	
	public void afficherButin(){
		if(this.butin != null){
			butin.changeVisible();
		}
	}


	

}
