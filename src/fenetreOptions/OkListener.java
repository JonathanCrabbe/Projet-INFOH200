package fenetreOptions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ButtonGroup;
import javax.swing.JTextField;

import main.Game;

public class OkListener implements ActionListener {
	
	private Game game;
	private OptionDialog dialog;
	private ButtonGroup bg;
	private JTextField tailleField; 

	public OkListener(Game game, OptionDialog dialog, ButtonGroup bg, JTextField tailleField) {
		this.game = game;
		this.dialog = dialog;
		this.bg = bg;
		this.tailleField = tailleField;
	}

	public void actionPerformed(ActionEvent arg0) {
		
		int taillePlateau = Integer.valueOf(tailleField.getText());
		game.setTaillePlateau(taillePlateau);
		dialog.dispose();
		
	}

	

}
