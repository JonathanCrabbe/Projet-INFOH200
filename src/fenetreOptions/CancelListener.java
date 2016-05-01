package fenetreOptions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.Game;

public class CancelListener implements ActionListener {
	
	private Game game;
	private OptionDialog dialog;

	public CancelListener(Game game, OptionDialog dialog) {
		this.game = game;
		this.dialog = dialog;
	}


	public void actionPerformed(ActionEvent e) {
		dialog.dispose(); //Ferme la fenêtre d'option
	}

}
