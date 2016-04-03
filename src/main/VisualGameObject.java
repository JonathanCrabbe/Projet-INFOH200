package main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public interface VisualGameObject{
	
	/*
	 * Cette interface contient les caract�ristiques d'un objet repr�sentable
	 */
	
	
	public void tick(); //Cette fonction permets d'actualiser les attributs de l'objet
	
	public void render(Graphics g); //Repr�sente l'objet

}
