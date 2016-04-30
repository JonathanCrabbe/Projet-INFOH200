package GUI;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageContainer {
	
	public static BufferedImage imageDalle;
	public static BufferedImage imageMur;
	public static BufferedImage imageLoot;
	public static BufferedImage imageJoueur;
	public static BufferedImage imageMonstre;
	public static BufferedImage imagePotion;
	public static BufferedImage imageFleche;
	public static BufferedImage imageProjectile;

	public ImageContainer() {

		try {
			this.imageDalle = ImageIO.read(new File("Images/Cases/Dalle.png"));
			this.imageMur = ImageIO.read(new File("Images/Cases/Mur.png"));
			this.imageLoot = ImageIO.read(new File("Images/Cases/Loot.png"));
			this.imageJoueur = ImageIO.read(new File("Images/Personnages/Joueur.png"));
			this.imageMonstre = ImageIO.read(new File("Images/Personnages/Monstre.png"));
			this.imagePotion = ImageIO.read(new File("Images/Objets/Potion.png"));
			this.imageFleche = ImageIO.read(new File("Images/Objets/Fleche.png"));
			this.imageProjectile = ImageIO.read(new File("Images/Projectiles/BouleDeFeu.png"));
			}catch (IOException e) {}
	}
	
	public static void setProjectile(String imageAdress) {

		try {
			imageProjectile = ImageIO.read(new File(imageAdress));
			}catch (IOException e) {}
	}
	
	

}
