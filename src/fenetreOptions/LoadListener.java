package fenetreOptions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.Game;

public class LoadListener implements ActionListener {
	
	private Game game;
	private OptionDialog dialog;

	public LoadListener(Game game, OptionDialog dialog) {
		this.game = game;
		this.dialog = dialog;
	}


	public void actionPerformed(ActionEvent arg0) { //Créer un jeu basé sur la sauvegarde stockée de "Save.tmp"
		
		dialog.dispose();
		game = game.load("Save.tmp");	
		game.loadGame(); //Pour ouvrir la fenêtre associée au jeu chargé
		

	}

}
