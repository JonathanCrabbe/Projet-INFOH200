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
	private ButtonGroup bg; //Groupe de bouton pour la s�lection de classe
	private JTextField tailleField; //Champ pour la taille du 
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
		if(taillePlateau < 20) throw new TailleInvalideException(); //Le plateau doit au moins fair 20 cases de cot�
		game.setTaillePlateau(taillePlateau); 
		
		for(Enumeration<AbstractButton> buttons = 
				bg.getElements(); buttons.hasMoreElements();){  //Enum�re les boutons pour voir lequel est s�lectionn�
			AbstractButton button = buttons.nextElement();
			if (button.isSelected()) {
                game.setClasseJoueur(button.getText()); //Donne le nom de la classe s�lectionn�e au jeu
            }		
		}
		game.setNomJoueur(nomField.getText()); //Si une virgule a �t� inser�e, il faut la suprimer (pour la persistance) 
		
		dialog.dispose();		
		game.newGame(); //Ouvre une nouvelle fen�tre
		
	}

	

}
