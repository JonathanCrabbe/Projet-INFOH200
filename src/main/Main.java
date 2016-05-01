package main;

import GUI.ImageContainer;
import fenetreOptions.OptionDialog;

public class Main {
	

	public static void main(String args[]){
		ImageContainer images = new ImageContainer();
		Game game = new Game();
		OptionDialog fenetreOptions = new OptionDialog(game,null, "Options de jeu", true); //Forcer l'utilisateur à entrer les options
	}
	
	

}
