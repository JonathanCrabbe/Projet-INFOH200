package fenetreOptions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JTextField;

import main.Game;

public class OkListener implements ActionListener {
	
	private Game game;
	private OptionDialog dialog;
	private ButtonGroup bg;
	private JTextField tailleField; 
	private JTextField nomField;

	public OkListener(Game game, OptionDialog dialog, 
			ButtonGroup bg, JTextField tailleField,JTextField nomField) {
		this.game = game;
		this.dialog = dialog;
		this.bg = bg;
		this.tailleField = tailleField;
		this.nomField = nomField;
	}

	public void actionPerformed(ActionEvent arg0) throws TailleInvalideException {
		
		int taillePlateau = Integer.valueOf(tailleField.getText());
		if(taillePlateau < 20) throw new TailleInvalideException();
		game.setTaillePlateau(taillePlateau);
		
		for(Enumeration<AbstractButton> buttons = 
				bg.getElements(); buttons.hasMoreElements();){
			AbstractButton button = buttons.nextElement();
			if (button.isSelected()) {
                game.setClasseJoueur(button.getText());
            }		
		}
		game.setNomJoueur(nomField.getText());
		
		dialog.dispose();		
		game.newGame();
		
	}

	

}
